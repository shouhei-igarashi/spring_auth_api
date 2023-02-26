package com.farmec.project.application.service.account;

import com.farmec.project.domain.model.account.Account;

/**
 * Account更新Service
 */
public interface AccountRecordService {
    /**
     * Account登録
     * @param account アカウント
     * @return true or false
     */
    public Boolean createAccount(Account account);
}
