package koo.product.management.system.springTypeConverter.controller;

import koo.product.management.system.springTypeConverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) { // 뷰 템플릿에 컨버터 적용
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));

        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) { // 폼에다가 컨버터 적용
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);

        return "converter-form";
    }

    @Data
    static class Form {

        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }

    }

}
