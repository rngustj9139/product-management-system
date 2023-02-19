package koo.product.management.system.api;

import koo.product.management.system.dto.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add") // Integer (price) 값에 문자를 넣으면 아예 바인딩이 안되서 예외 페이지 발생(컨트롤러 호출 X) (@RequestBody와는 다르게 @ModelAttribute는 일단 바인딩은 되고 컨트롤러가 호출이 된다.)
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) { // @Valid, @Validated는 HttpMessageConverter(@RequestBody)에서도 적용 가능
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult.getAllErrors());
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");

        return form;
    }

}
