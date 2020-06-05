package com.tvstorm.demoprojectrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Contact DEFAULT_CONTACT = new Contact("Lee KyoungSeok",
            "http://www.tvstorm.com", "pow3351@tvstorm.com"); // 사용자 정보를 위한 Contact 객체

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
            "My user management REST API service", "1.0", "urn:tos", DEFAULT_CONTACT ,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>()); // API 정보

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json",
            "application/xml")); // API Info 부분에 대한 것을 커스터 마이징 해보기 위해 선언 (json or xml)

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
