package icu.thyself.cloudlesson.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.thyself.cloudlesson.dto.AccountManageDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.enums.InformationEnumImpl;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
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
    @Transactional(rollbackFor = Exception.class)
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
            account.setHeader("http://qbppo2eal.bkt.clouddn.com/avatar.png");
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountMapper.insertSelective(account);
            return new ResultDTO(InformationEnumImpl.REGISTER_SUCCESS.getCode(),
                    InformationEnumImpl.REGISTER_SUCCESS.getMessage());
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDTO(InformationEnumImpl.REGISTER_ERROR.getCode(),
                    InformationEnumImpl.REGISTER_ERROR.getMessage());
        }
    }

    @Override
    public Account selectAccountByUsername(String username) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        return accounts.get(0);
    }

    @Override
    public Account selectAccountById(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAccountAvatar(Long accountId, String link) {
        Account account = new Account();
        account.setId(accountId);
        account.setHeader(link);
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public int updateAccountInfo(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public List<AccountManageDTO> selectAccount(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        AccountExample accountExample = new AccountExample();
        if (!StringUtils.isEmpty(keyword)) {
            accountExample.or().andUsernameLike("%" + keyword + "%");
            accountExample.or().andNameLike("%" + keyword + "%");
        }
        PageInfo<Account> accounts = new PageInfo<>(accountMapper.selectByExample(accountExample));
        List<AccountManageDTO> accountManageDTOS = new ArrayList<>();
        for (Account a : accounts.getList()) {
            AccountManageDTO accountManageDTO = new AccountManageDTO();
            accountManageDTO.setId(a.getId());
            accountManageDTO.setUsername(a.getUsername());
            accountManageDTO.setName(a.getName());
            accountManageDTO.setCreateTime(DateFormatUtils.format(a.getGmtCreate(), "yyyy/MM/dd"));
            accountManageDTO.setQq(a.getQq());
            accountManageDTO.setWechat(a.getWechat());
            if (StringUtils.contains(a.getRole(), "ROLE_ADMIN")) {
                continue;
            } else if (StringUtils.contains(a.getRole(), "ROLE_AUTHOR")) {
                accountManageDTO.setRole("作者");
            } else {
                accountManageDTO.setRole("普通用户");
            }
            if (a.getStatus() == 0) {
                accountManageDTO.setStatus("正常登录");
            } else {
                accountManageDTO.setStatus("禁止登录");
            }
            accountManageDTO.setPageNum(accounts.getPageNum());
            accountManageDTO.setMaxPageNum(accounts.getPages());
            accountManageDTOS.add(accountManageDTO);
        }
        return accountManageDTOS;
    }

    @Override
    public int updateStatusAndRoleByAccountId(Byte status, String role, Long accountId) {
        Account account = new Account();
        account.setId(accountId);
        account.setStatus(status);
        account.setRole(role);
        return accountMapper.updateByPrimaryKeySelective(account);
    }
}
