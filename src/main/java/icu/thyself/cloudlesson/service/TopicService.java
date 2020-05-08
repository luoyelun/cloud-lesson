package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.IndexTopicDTO;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 21:42
 */

public interface TopicService {

    /**
     * pageNum:页数
     * tag:根据标签
     * orderBy:排序字段
     */
    List<IndexTopicDTO> getTopicList(int pageNum, String tag, String keyWord, String orderBy);


}
