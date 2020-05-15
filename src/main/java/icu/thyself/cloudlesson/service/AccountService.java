package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.AccountManageDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/2 18:50
 */

public interface AccountService {
    boolean isExists(Account account);

    ResultDTO register(Account account);

    Account selectAccountByUsername(String username);

    Account selectAccountById(Long id);

    int updateAccountAvatar(Long accountId, String link);

    int updateAccountInfo(Account account);

    /**
     * 分页获得用户列表
     * pageNum:页数
     * keyword:搜索
     *
     * @return
     */
    List<AccountManageDTO> selectAccount(int pageNum, String keyword);
}
