package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.mapper.RecommendMapper;
import icu.thyself.cloudlesson.model.Recommend;
import icu.thyself.cloudlesson.model.RecommendExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/14 17:31
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    RecommendMapper recommendMapper;

    @Override
    public int addRecommend(Recommend recommend) {
        return recommendMapper.insert(recommend);
    }

    @Override
    public List<Recommend> getAllRecommend() {
        RecommendExample recommendExample = new RecommendExample();
        recommendExample.setOrderByClause("id desc");
        return recommendMapper.selectByExample(recommendExample);
    }

    @Override
    public Recommend getRecommendByid(Long recommendId) {
        return recommendMapper.selectByPrimaryKey(recommendId);
    }

    @Override
    public int updateRecommend(Recommend recommend) {
        return recommendMapper.updateByPrimaryKey(recommend);
    }

    @Override
    public int deleteRecommend(Long rid) {
        return recommendMapper.deleteByPrimaryKey(rid);
    }
}
