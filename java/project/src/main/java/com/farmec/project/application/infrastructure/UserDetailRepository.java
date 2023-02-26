package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.account.Account;

/**
 * user_detailテーブル操作
 */
public interface UserDetailRepository {
    /**
     * user_detail登録
     * @param account　アカウント
     * @return true or false
     */
    Boolean save(Account account);
    
    /**
     * user_detail削除
     * @param userDetails UserDetailsImpl
     * @return true or false
     */
    Boolean delete(Account account);

    /**
     * user_detail取得
     * @param userDetails UserDetailsImpl
     * @return Account
     */
    Account findByEmail(String email);

    /**
     * user_detail存在チェック
     * @param userDetails UserDetailsImpl
     * @return true or false
     */
    Boolean existsByEmail(String email);
}
