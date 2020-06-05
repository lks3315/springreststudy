package com.tvstorm.demoprojectrest.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssn"}) // 외부로 노출시키고 싶지 않은 데이터를 서버에서 클라쪽으로 json 넘겨줄 때 표시해주지 않음
@JsonFilter("UserInfoV2")
@NoArgsConstructor
@Component
public class UserV2 extends User {
    private String grade;
}
