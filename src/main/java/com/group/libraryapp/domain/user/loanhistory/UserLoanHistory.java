package com.group.libraryapp.domain.user.loanhistory;


import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn
    // 연관관계 주인 쪽이 사용하는 Column 어노테이션. 기본형(@Column)이 아닌 연관관계 사용하는 객체의 경우 사용. 컬럼이름, 속성지정 가능
    @ManyToOne  //대출기록은 여러개고 소유자는 하나(N:1관계)
    private User user;  //관계의 주도권을 UserLoanHistory가 가지고 있음 (상대방을 가리키는 쪽)

    private String bookName;

    private boolean isReturn;
    // 0은 false 1은 true 매핑

    protected UserLoanHistory(){

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return bookName;
    }
}
