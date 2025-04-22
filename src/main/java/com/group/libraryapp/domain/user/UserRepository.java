package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    //By 앞
    //find~ : 1건을 가져옴. 반환타입은 객체 또는 Optional
    //findAll : 결과물 N개. List<타입> 반환
    //exists~ : 쿼리가 존재하는지 확인. boolean타입으로 반환
    //count~ : SQL 결과 개수. 반환 타입 long


}
