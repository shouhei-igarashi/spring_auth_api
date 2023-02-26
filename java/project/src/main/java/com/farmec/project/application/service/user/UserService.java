package com.farmec.project.application.service.user;

import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

public interface UserService {
    /**
     * ユーザ取得
     * @param signIn　SignIn
     * @return UserDetailsImpl
     */
    UserDetailsImpl getUserDetails(SignIn signIn);

    /**
     * ユーザ作成
     * @param signUp　SignUp
     * @param role　ロール文字列
     * @return true or false
     */
    Boolean createUser(SignUp signUp);

    /**
     * ユーザ作成
     * @param signUp　SignUp
     * @param role　ロール文字列
     * @return true or false
     */
    Boolean createAdminUser(SignUp signUp);
    
    /**
     * 管理者権限チェック
     * @param userDetailsImpl
     * @return true or false
     */
    Boolean isAdminPrivileges(UserDetailsImpl userDetails);

    /**
     * ユーザ権限チェック
     * @param userDetailsImpl
     * @return true or false
     */
    Boolean isUserPrivileges(UserDetailsImpl userDetails);
}
