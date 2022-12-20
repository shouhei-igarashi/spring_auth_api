package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.account.Account;

public interface UserDetailHistoryRepository {
    Boolean save(Account account);
}
