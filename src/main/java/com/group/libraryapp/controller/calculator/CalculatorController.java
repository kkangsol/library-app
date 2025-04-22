package com.group.libraryapp.controller.calculator;


import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //API의 진입 지점(Controller)으로 설정
public class CalculatorController {

    @GetMapping("/add") // HTTP 메서드 GET ("path") >> HTTP로 GET add 하면 해당 메서드 실행
    public int addTwoNumbers(CalculatorAddRequest request){
        //쿼리를 통한 데이터를(파라미터)함수로 연결
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")   // 아래 함수를 HTTP 메서드가 POST이고 path가 /multiply인 API로 만든다.
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        //HTTP 바디 안에 담긴 JSON을 CalculatorMultiplyRequest 객체로 변환하는 어노테이션
        //POST 메서드는 바디에 데이터를 담는다.
        return request.getNumber1() * request.getNumber2();
    }
}
