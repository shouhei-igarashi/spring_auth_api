package com.farmec.project.application.service.user.impl.sub;

import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

/**
 * User取得service
 */
public interface UserSubQueryService {
    /**
     * ユーザの存在チェック
     * @param signUp SignUp
     * @return true or false
     */
    Boolean existsUser(SignUp signUp);

    /**
     * ユーザの存在チェック
     * @param userDetails UserDetailsImpl
     * @return true or false
     */
    Boolean existsUser(UserDetailsImpl userDetails);

    /**
     * 権限の存在チェック
     * @param userDetails UserDetailsImpl
     * @return true or false
     */
    Boolean existsprivilege(UserDetailsImpl userDetails);

    /**
     * 認証ユーザ取得
     * @param signIn SignIn
     * @return UserDetailsImpl
     */
    UserDetailsImpl getUser(SignIn signIn);
}
