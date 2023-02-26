package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.model.secure.SignUp;

/**
 * usersテーブル操作
 */
public interface UserRepository {
    /**
     * ユーザ登録
     * @param signup　認証情報
     * @return 登録結果
     */
    Boolean save(SignUp signUp);
    
    /**
     * ユーザ存在確認　Email
     * @param signup　認証情報
     * @return true or false
     */
    Boolean existsByEmail(String email);
    
    /**
     * ユーザ存在確認　Email Role
     * @param userDetails UserDetailsImpl
     * @return true or false
     */
    Boolean existsByUserDetailsImpl(UserDetailsImpl userDetails);

    /**
     * ユーザ取得
     * @param signIn SignIn
     * @return　UserDetailsImpl
     */
    UserDetailsImpl findByEmail(String email);
}
