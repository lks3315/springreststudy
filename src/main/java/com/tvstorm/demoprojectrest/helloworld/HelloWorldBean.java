package com.tvstorm.demoprojectrest.helloworld;

// lombok : 자동 생성 기능 (setter, getter, constructor, toString)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 생성자 유형이 만들어짐
@NoArgsConstructor // 디폴트 생성자 같이 만들어짐
public class HelloWorldBean {
    private String message;
}
