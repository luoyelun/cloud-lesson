package icu.thyself.cloudlesson.provider;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author luoyelun
 * @date 2020/5/1 13:38
 */
@Service
public class QiNiuProvider {
    @Value("${qiniu.oss.accessKey}")
    private String accessKey;
    @Value("${qiniu.oss.secretKey}")
    private String secretKey;
    @Value("${qiniu.oss.bucket}")
    private String bucket;
    /*
     *创建上传对象Region.huanan()地区
     *华东	Region.region0(), Region.huadong()
     *华北	Region.region1(), Region.huabei()
     *华南	Region.region2(), Region.huanan()
     *北美	Region.regionNa0(), Region.beimei()
     *东南亚	Region.regionAs0(), Region.xinjiapo()
     */
    private final Configuration configuration = new Configuration(Region.huanan());

    public DefaultPutRet upload(InputStream inputStream, String fileName, String fileType) {
        Auth auth = Auth.create(accessKey, secretKey);
        UploadManager uploadManager = new UploadManager(configuration);
        String token = auth.uploadToken(bucket, null, 24 * 60 * 60 * 365 * 10, null);
        try {
            Response response = uploadManager.put(inputStream, fileName, token, null, fileType);
            if (response.statusCode == 200) {
                return JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }
}
