package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.AuthorApplyDTO;
import icu.thyself.cloudlesson.model.AuthorApply;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/16 15:44
 */

public interface AuthorApplyService {
    /**
     * 创建一个申请
     */
    int createAuthorApply(AuthorApply authorApply);

    /**
     * 判断这个用户是否已经申请，并且处于未处理状态
     */
    boolean isExistApply(Long accountId);

    /**
     * 获得申请列表
     */
    List<AuthorApplyDTO> getAllAuthorApply(int pageNum);

    /**
     * 修改用户权限，修改申请状态
     */
    int updateAccountRoleAndStatusById(AuthorApply authorApply);
}


