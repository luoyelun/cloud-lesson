package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.AttentionDTO;
import icu.thyself.cloudlesson.mapper.AttentionMapper;
import icu.thyself.cloudlesson.mapper.TopicMapper;
import icu.thyself.cloudlesson.model.Attention;
import icu.thyself.cloudlesson.model.AttentionExample;
import icu.thyself.cloudlesson.model.Topic;
import icu.thyself.cloudlesson.model.TopicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/9 22:00
 */
@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    AttentionMapper attentionMapper;
    @Autowired
    TopicMapper topicMapper;

    /**
     * 是否关注
     * accountId:用户id
     * tid：主题id
     */
    @Override
    public boolean isAttention(Long accountId, Long tid) {
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.createCriteria().andTopicIdEqualTo(tid)
                .andAccountIdEqualTo(accountId);
        List<Attention> attentions = attentionMapper.selectByExample(attentionExample);
        return attentions.size() > 0;
    }

    /**
     * 添加关注
     * accountId:用户id
     * tid：主题id
     */
    @Override
    public boolean addAttention(Long accountId, Long tid) {
        Attention attention = new Attention();
        attention.setAccountId(accountId);
        attention.setTopicId(tid);
        attention.setGmtCreate(System.currentTimeMillis());
        int i = attentionMapper.insertSelective(attention);
        return i > 0;
    }

    /**
     * 取消关注
     * accountId:用户id
     * tid：主题id
     */
    @Override
    public boolean removeAttention(Long accountId, Long tid) {
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.createCriteria().andAccountIdEqualTo(accountId)
                .andTopicIdEqualTo(tid);
        Attention attention = attentionMapper.selectByExample(attentionExample).get(0);
        int i = attentionMapper.deleteByPrimaryKey(attention.getId());
        return i > 0;
    }

    @Override
    public List<AttentionDTO> getAttentions(Long accountId) {
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.setOrderByClause("gmt_create desc");
        attentionExample.createCriteria().andAccountIdEqualTo(accountId);
        List<Attention> attentions = attentionMapper.selectByExample(attentionExample);
        List<AttentionDTO> attentionDTOS = new ArrayList<>();
        for (Attention a : attentions) {
            AttentionDTO attentionDTO = new AttentionDTO();
            TopicExample topicExample = new TopicExample();
            topicExample.createCriteria().andIdEqualTo(a.getTopicId());
            Topic topic = topicMapper.selectByExample(topicExample).get(0);
            attentionDTO.setTopicId(topic.getId());
            attentionDTO.setTopicTitle(topic.getTitle());
            attentionDTOS.add(attentionDTO);
        }
        return attentionDTOS;
    }
}
