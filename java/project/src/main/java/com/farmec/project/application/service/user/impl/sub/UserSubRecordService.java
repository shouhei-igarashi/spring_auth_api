package com.farmec.project.application.service.user.impl.sub;

import com.farmec.project.domain.model.secure.SignUp;

/**
 * ユーザー更新service
 */
public interface UserSubRecordService {
    /**
     * ユーザ登録
     * @param signUp SignUp
     * @return true or false
     */
    Boolean createUser(SignUp signUp);
}
