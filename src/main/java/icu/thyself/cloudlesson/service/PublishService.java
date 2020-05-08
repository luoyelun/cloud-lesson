package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Topic;

/**
 * @author luoyelun
 * @date 2020/5/5 12:01
 */
public interface PublishService {
    ResultDTO insertPost(Topic topic, String username);
}
