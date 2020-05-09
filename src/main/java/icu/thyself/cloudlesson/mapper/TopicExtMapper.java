package icu.thyself.cloudlesson.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author luoyelun
 * @date 2020/5/9 14:42
 */
@Repository
public interface TopicExtMapper {
    void updateReplyCount(@Param("id") Long id);

    void updateViewCount(@Param("id") Long id);
}
