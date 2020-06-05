package com.tvstorm.demoprojectrest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    // 예외처리를 하기 위해 사용하려고 만든 객체

    private Date timestamp;
    private String message;
    private String details;

}
