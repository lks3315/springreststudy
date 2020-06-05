package com.tvstorm.demoprojectrest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // 타입을 User데이터 객체와 @Id가 선언된 변수타입
}
