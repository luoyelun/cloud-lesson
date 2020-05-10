package icu.thyself.cloudlesson.service;

/**
 * @author luoyelun
 * @date 2020/5/9 21:59
 */

public interface AttentionService {
    boolean isAttention(Long accountId, Long tid);

    boolean addAttention(Long accountId, Long tid);

    boolean removeAttention(Long accountId, Long tid);
}
