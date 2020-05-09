package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.CommentDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Comment;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/8 14:31
 */

public interface CommentService {

    List<CommentDTO> getCommentsByTopicId(Long tid);

    ResultDTO createComment(Comment comment);
}
