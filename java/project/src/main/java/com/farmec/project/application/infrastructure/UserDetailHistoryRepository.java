package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.account.Account;

/**
 * user_detail_historiesテーブル操作
 */
public interface UserDetailHistoryRepository {
    /**
     * user_detail_histories登録
     * @param account　Account
     * @return true or false
     */
    Boolean save(Account account);
}
