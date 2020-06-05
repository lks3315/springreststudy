package com.tvstorm.demoprojectrest.user;

// HTTP Status code
// 200번대 -> OK, Success
// 400번대 -> Client 쪽 에러
// 500번대 -> Server 쪽 에러

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //Client의 에러라고 알리기 위해 추가
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
