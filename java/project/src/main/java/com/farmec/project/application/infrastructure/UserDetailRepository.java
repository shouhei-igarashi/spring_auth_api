package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.account.Account;

public interface UserDetailRepository {
    Boolean existsByEmail(Account account); 
    Boolean save(Account account);
    void delete(Account account);
}
