package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.PostDTO;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 21:42
 */

public interface PostService {
    List<PostDTO> latestPostList(int pageNum);
}
