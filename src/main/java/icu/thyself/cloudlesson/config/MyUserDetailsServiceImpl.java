package icu.thyself.cloudlesson.config;

import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/3 20:53
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if (accounts.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(accounts.get(0).getId());
        userInfo.setName(accounts.get(0).getName());
        userInfo.setUsername(accounts.get(0).getUsername());
        userInfo.setPassword(accounts.get(0).getPassword());
        userInfo.setRoles(accounts.get(0).getRole());
        return userInfo;
    }
}