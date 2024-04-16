package com.capstone.userservice.domain.profile.exception;

import com.capstone.userservice.global.exception.Code;
import com.capstone.userservice.global.exception.GlobalException;
import lombok.Getter;

@Getter
public class ProfileException extends GlobalException {

    public ProfileException(Code errorCode) {
        super(errorCode);
    }

    public ProfileException(Code errorCode, String message) {
        super(errorCode, message);
    }
}
