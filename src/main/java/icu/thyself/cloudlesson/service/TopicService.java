package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.IndexTopicDTO;
import icu.thyself.cloudlesson.dto.TopicDTO;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 21:42
 */

public interface TopicService {

    /**
     * pageNum:页数
     * tag:根据标签
     * keyword:查询关键字
     * orderBy:排序字段
     */
    List<IndexTopicDTO> getTopicList(int pageNum, String tag, String keyword, String orderBy);

    /**
     * 通过ID查找主题
     */
    TopicDTO getById(Long tid);

    /**
     * 查询主题 模糊查询
     */
//    List<IndexTopicDTO> search(Integer pageNum, String keyword);
}
