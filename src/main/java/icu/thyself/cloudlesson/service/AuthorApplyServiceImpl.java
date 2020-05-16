package icu.thyself.cloudlesson.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.thyself.cloudlesson.dto.AuthorApplyDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.AuthorApplyMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AuthorApply;
import icu.thyself.cloudlesson.model.AuthorApplyExample;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/16 15:46
 */
@Service
public class AuthorApplyServiceImpl implements AuthorApplyService {
    @Autowired
    AuthorApplyMapper authorApplyMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public int createAuthorApply(AuthorApply authorApply) {
        return authorApplyMapper.insertSelective(authorApply);
    }

    @Override
    public boolean isExistApply(Long accountId) {
        AuthorApplyExample authorApplyExample = new AuthorApplyExample();
        authorApplyExample.createCriteria().andAccountIdEqualTo(accountId)
                .andStatusEqualTo((byte) 0);
        return authorApplyMapper.selectByExample(authorApplyExample).size() > 0;
    }

    @Override
    public List<AuthorApplyDTO> getAllAuthorApply(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        AuthorApplyExample authorApplyExample = new AuthorApplyExample();
        authorApplyExample.setOrderByClause("status");
        PageInfo<AuthorApply> pageInfo = new PageInfo<>(authorApplyMapper.selectByExample(authorApplyExample));
        List<AuthorApplyDTO> authorApplyDTOS = new ArrayList<>();
        for (AuthorApply a : pageInfo.getList()) {
            AuthorApplyDTO dto = new AuthorApplyDTO();
            Account account = accountMapper.selectByPrimaryKey(a.getAccountId());
            dto.setId(a.getId());
            dto.setAccountId(account.getId());
            dto.setAccountUsername(account.getUsername());
            dto.setApplyReason(a.getApplyReason());
            dto.setCreateTime(DateFormatUtils.format(a.getGmtCreate(), "yyyy/MM/dd HH:mm"));
            if (a.getStatus() == 0) {
                dto.setStatus("未处理");
            } else if (a.getStatus() == 1) {
                dto.setStatus("已通过");
            } else {
                dto.setStatus("已拒绝");
            }
            dto.setPageNum(pageInfo.getPageNum());
            dto.setMaxPageNum(pageInfo.getPages());
            authorApplyDTOS.add(dto);
        }
        return authorApplyDTOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateAccountRoleAndStatusById(AuthorApply authorApply) {
        AuthorApply dbAuthorApply = authorApplyMapper.selectByPrimaryKey(authorApply.getId());
        Account account = accountMapper.selectByPrimaryKey(dbAuthorApply.getAccountId());
        if (authorApply.getStatus() == 1) {
            account.setRole("ROLE_USER,ROLE_AUTHOR");
        } else {
            account.setRole("ROLE_USER");
        }
        try {
            int i = accountMapper.updateByPrimaryKeySelective(account);
            int i1 = authorApplyMapper.updateByPrimaryKeySelective(authorApply);
            if (i == 1 && i1 == 1) {
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
}
