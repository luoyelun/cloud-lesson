package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.enums.InformationEnum;
import icu.thyself.cloudlesson.enums.InformationEnumImpl;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/2 18:51
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    /**
     * 判断用户名是否存在
     */
    @Override
    public boolean isExists(Account account) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria()
                .andUsernameEqualTo(account.getUsername());
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        return accounts.size() > 0;
    }

    /**
     * 用户注册
     */
    @Transactional
    @Override
    public ResultDTO register(Account account) {
        //如果已存在这个用户
        if (isExists(account)) {
            return new ResultDTO(InformationEnumImpl.REGISTER_USER_EXITS.getCode(),
                    InformationEnumImpl.REGISTER_USER_EXITS.getMessage());
        }
        //不存在则插入数据库 返回注册成功
        try {
            account.setGmtCreate(System.currentTimeMillis());
            account.setRole("ROLE_USER");
            account.setHeader("http://q9p1v1fsb.bkt.clouddn.com/avatar.png");
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountMapper.insert(account);
            return new ResultDTO(InformationEnumImpl.REGISTER_SUCCESS.getCode(),
                    InformationEnumImpl.REGISTER_SUCCESS.getMessage());
        } catch (Exception e) {
            return new ResultDTO(InformationEnumImpl.REGISTER_ERROR.getCode(),
                    InformationEnumImpl.REGISTER_ERROR.getMessage());
        }
    }
}
