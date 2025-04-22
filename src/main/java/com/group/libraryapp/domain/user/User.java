package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //스프링이 User객체와 user 테이블을 같은 것으로 바라본다.
public class User {

    @Id //이 필드를 primary key로 간주한다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //primary key는 자동 생성되는 값이다.(IDENTITY == auto _increment)
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name"/*이름이 같으면 생략 가능*/)   //name(varchar 20)
    private String name;

    // @Column 생략 가능(테이블의 age와 완전히 동일함)
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //cascade : 유저와 연결된 UserLoanHistory까지 삭제하는 옵션 / orphanRemoval 관게가 끊어진 데이터를 자동으로 제거하는 옵션
    //User 입장에서는 1:N관계 / 연관관계의 주인이 아닌 쪽에 mappedBy 옵션 >> 연관관계 주인이 가지고있는 필드 이름 값으로 넣어줌(user)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User(){   //JPA를 사용하기 위해서는 기본 생성자가 꼭 필요하다

    }

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();

        /*for (UserLoanHistory userLoanHistory : userLoanHistories) {
            if(userLoanHistory.getBookName().equals(bookName)){
                userLoanHistory.doReturn();
                return;
            }
        }
        throw new IllegalArgumentException();*/

    }
}
