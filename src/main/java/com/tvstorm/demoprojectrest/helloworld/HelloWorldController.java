package com.tvstorm.demoprojectrest.helloworld;

import com.tvstorm.demoprojectrest.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController // Responsebody가 있으므로 bean을 리턴할 경우 알아서 json 포맷으로 반환하게 됨
public class HelloWorldController {
    // GET
    // hello-world (endPoint)
    // @RequestMapping(method=RequestMethod.GET, path="/hello-world")

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World~!~!~!~!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}") // {name} 가변적인 데이터
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World Bean, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized") // 다국어 처리 예시
    public String helloWorldInternal(
            @RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message",null, locale);
    }
}
