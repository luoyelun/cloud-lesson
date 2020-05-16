package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/16 16:22
 */
@Data
public class AuthorApplyDTO {
    private Long id;
    private Long accountId;
    private String accountUsername;
    private String applyReason;
    private String createTime;
    private String status;
    private Integer pageNum;
    private Integer maxPageNum;
}
