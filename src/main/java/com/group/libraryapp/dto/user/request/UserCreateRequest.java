package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age;    //null 표현 가능해야 하기 때문에 int형 아닌 Integer

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
