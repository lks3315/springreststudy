package com.tvstorm.demoprojectrest.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssn"}) // 외부로 노출시키고 싶지 않은 데이터를 서버에서 클라쪽으로 json 넘겨줄 때 표시해주지 않음
//@JsonFilter("UserInfo")
@ApiModel(description = "Domain object for user Detail information")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "Please input user name.")
    private String name;

    @Past
    @ApiModelProperty(notes = "Please input user registration date.")
    private Date joinDate;

//    @JsonIgnore // 외부로 노출 시키고 싶지 않은 data에 선언
    @ApiModelProperty(notes = "Please input user password.")
    private String password;

//    @JsonIgnore
    @ApiModelProperty(notes = "Please input user Resident registration number.")
    private String ssn;
}
