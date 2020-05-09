package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;

/**
 * @author luoyelun
 * @date 2020/5/2 18:50
 */

public interface AccountService {
    boolean isExists(Account account);

    ResultDTO register(Account account);


    Account selectAccountByUsername(String username);
}
