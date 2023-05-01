package koo.product.management.system.SpringTypeConverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1") // localhost:8080/hello-v1?data=10
    public String helloV1 (HttpServletRequest request) {
        String data = request.getParameter("data"); // 문자 타입으로 조회된다. (http 요청 파라미터는 모두 문자료 처리된다.)
        Integer intValue = Integer.valueOf(data); // 숫자 타입으로 변경
        System.out.println("intValue = " + intValue);

        return "OK";
    }

    @GetMapping("/hello-v2")
    public String helloV2 (@RequestParam Integer data) { // 스프링이 중간에서 문자를 숫자로 변환해줌 (타입컨버터 이용, @ModelAttribute, @PathVariable에도 적용됨)
        System.out.println("data = " + data);

        return "OK";
    }

    // 개발자가 직접 타입을 변경하고 싶으면 Converter 이용 (converter 라이브러리는 많으니 org.springframework.core.convert.converter.Converter 이용하기)



}