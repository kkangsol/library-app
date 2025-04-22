package com.group.libraryapp.temp;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    @OneToOne
    private Address address;

    public void setAddress(Address address) {
        this.address = address; //연관관계의 주인이 setter 사용
        address.setPerson(this);
    }
}

/*
연관관계 주인 효과
1. 상대 테이블을 참조하고 있으면 연관관계의 주인;
2. 연관관계의 주인이 아니면 mappedBy를 사용
3. 연관관계의 주인의 setter가 사용되어야만 테이블 연결
*/
