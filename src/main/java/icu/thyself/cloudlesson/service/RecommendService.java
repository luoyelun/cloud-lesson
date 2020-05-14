package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.model.Recommend;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/14 17:30
 */

public interface RecommendService {
    /**
     * 添加课程推荐
     */
    int addRecommend(Recommend recommend);

    /**
     * 获得推荐列表
     */
    List<Recommend> getAllRecommend();

    /**
     * 通过id查找推荐记录
     */
    Recommend getRecommendByid(Long recommendId);

    /**
     * 修改推荐
     */
    int updateRecommend(Recommend recommend);

    /**
     * 删除推荐
     */
    int deleteRecommend(Long rid);
}
