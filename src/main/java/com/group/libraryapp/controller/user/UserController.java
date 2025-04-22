package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//API 진입지점, UserController를 스프링 빈으로 등록
public class UserController {


    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    @PostMapping("/user")   // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {  //Body 사용
        /*String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
        //update메서드는 INSERT, UPDATE, DELETE 쿼리에 사용*/
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
        /*String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {

            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });*/
        // SQL 쿼리 결과를 UserResponse로 바꿔주는 역할

        /*
        +) RowMapper 인터페이스를 바로 구현한 익명클래스. jdbcTemplate.query에는 해당 클래스를 바로 실행시키는 로직 존재
        +) mapRow 메서드가 자동으로 반복적으로 실행되면서 쿼리를 한줄씩 객체로 변환해서 리턴
        반복해서 리턴된 객체들이 리스트로 정리되어 getUse()의 리턴값으로 반환
        */

            /*
            람다버전
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
            */

    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        /*String readSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        // 결과가 있으면 [0]의 리스트 리턴, 없으면 RowMapper가 아예 실행되지 않음(빈리스트 반환)

        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());*/

        userService.updateUser(request);
    }


    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {

       /* String readSql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        // 결과가 있으면 [0]의 리스트 리턴, 없으면 RowMapper가 아예 실행되지 않음(빈리스트 반환)

        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);*/

        userService.deleteUser(name);
    }

    /*@GetMapping("/user/error-test")
    public void errorTest(){
        throw new IllegalArgumentException();
    }
    에러 발생
    "status": 500,
    "error": "Internal Server Error",
    */
}
