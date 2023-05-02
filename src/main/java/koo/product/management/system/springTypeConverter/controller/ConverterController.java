package koo.product.management.system.springTypeConverter.controller;

import koo.product.management.system.springTypeConverter.type.IpPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) { // 뷰 템플릿에 컨버터 적용 (객체 => 문자)
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));

        return "converter-view";
    }

}
