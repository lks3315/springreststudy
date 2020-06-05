package com.tvstorm.demoprojectrest.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin") // 공통적으로 들어가는 url (제일 앞에)
public class AdminUserController {
    private UserDAOService service;

    @Autowired
    private UserV2 userV2;

    @Autowired
    public AdminUserController(UserDAOService service) {
        this.service = service;
    }

    @GetMapping("/v1/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn"); // /admin/user/{id} 접속시에 해당 필터의 정보들이 보여지게 됨
        // UserInfo filter add
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    // 개인적으로 그냥 params 값으로 관리하는 것이 괜찮은 듯
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1") // params 값으로 version control, value 끝에 찍히므로 / 찍는거 주의
//    @GetMapping(value = "/users/{id}", headers = "X-VERSION=1") // header 값으로 버전 관리
    @GetMapping(value = "/users/{id}", produces = "application/vendor.tvstorm.appv1+json") //produces는 마임타임을 지정하여 버전관리 하는 방법
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        //user가 null인 경우 서버에서 에러코드를 보내도록 변경
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password", "ssn"); // /admin/user/{id} 접속시에 해당 필터의 정보들이 보여지게 됨
        // UserInfo filter add
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-VERSION=2")
    @GetMapping(value = "/users/{id}", produces = "application/vendor.tvstorm.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        //user가 null인 경우 서버에서 에러코드를 보내도록 변경
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        // User -> User2
//        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssn
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade"); // /admin/user/{id} 접속시에 해당 필터의 정보들이 보여지게 됨
        // UserInfo filter add
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userV2);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
