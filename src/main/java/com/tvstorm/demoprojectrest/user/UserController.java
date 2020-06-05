package com.tvstorm.demoprojectrest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class UserController {
    private UserDAOService service;

    @Autowired
    public UserController(UserDAOService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        //user가 null인 경우 서버에서 에러코드를 보내도록 변경
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        //HATEOAS
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //retrieveAllUsers 메소드와 링크 연결
        resource.add(linkTo.withRel("all-users")); // 실제 링크 하려고 하는 대상을 지정

        return resource;
    }

    @PostMapping("/users") // PostMethod 처리, 해당 url의 요청이 오면 PostMethod 처리
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // @RequestBody로 서버로 요청 보냄, user 추가해 볼 것임
        //@Valid로 유효성 검사
        User savedUser = service.save(user);

        // server가 응답 하는 메시지의 차이를 두기 위해서 추가하여 리턴함
        // ServletUriComponentsBuilder는 현재 요청의 uri를 가져옴,
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
