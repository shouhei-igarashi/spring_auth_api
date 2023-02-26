package com.farmec.project.application.service.user.impl.sub;

import com.farmec.project.domain.model.secure.SignUp;

public interface UserStatusSubRecordService {
    Boolean createStatus(SignUp signUp);
}
