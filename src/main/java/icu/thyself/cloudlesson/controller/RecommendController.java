package icu.thyself.cloudlesson.controller;

import com.qiniu.storage.model.DefaultPutRet;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Recommend;
import icu.thyself.cloudlesson.provider.QiNiuProvider;
import icu.thyself.cloudlesson.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author luoyelun
 * @date 2020/5/14 16:31
 */
@Controller
public class RecommendController {
    @Autowired
    QiNiuProvider qiNiuProvider;
    @Autowired
    RecommendService recommendService;

    @GetMapping("/recommend")
    public String recommend(Model model) {
        model.addAttribute("recommends", recommendService.getAllRecommend());
        return "recommend";
    }

    @ResponseBody
    @PostMapping("/admin/recommend/fmImageUpload")
    public ResultDTO fmImageUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResultDTO(201, "选择文件");
        }
        try {
            DefaultPutRet upload = qiNiuProvider.upload(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            return new ResultDTO(200, "http://qbppo2eal.bkt.clouddn.com/" + upload.key);
        } catch (Exception e) {
            return new ResultDTO(201, "图片上传失败");
        }
    }

    @ResponseBody
    @PostMapping("/admin/recommend/addRecommend")
    public ResultDTO addRecommend(@RequestBody Recommend recommend) {
        int i = recommendService.addRecommend(recommend);
        if (i > 0) {
            return new ResultDTO(200, "添加完成");
        }
        return new ResultDTO(201, "添加失败");

    }

    @ResponseBody
    @GetMapping("/admin/recommend/getRecommend/{rid}")
    public Recommend getRecommend(@PathVariable("rid") Long rid) {
        return recommendService.getRecommendByid(rid);
    }

    @ResponseBody
    @PostMapping("/admin/recommend/modify")
    public ResultDTO modifyRecommend(@RequestBody Recommend recommend) {
        int i = recommendService.updateRecommend(recommend);
        if (i > 0) {
            return new ResultDTO(200, "修改成功");
        } else {
            return new ResultDTO(201, "修改失败，请重试");
        }
    }

    @ResponseBody
    @GetMapping("/admin/recommend/delete/{rid}")
    public ResultDTO deleteRecommend(@PathVariable("rid") Long rid) {
        int i = recommendService.deleteRecommend(rid);
        if (i > 0) {
            return new ResultDTO(200, "删除成功");
        } else {
            return new ResultDTO(201, "删除失败");
        }
    }
}
