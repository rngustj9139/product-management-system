package koo.product.management.system.login;

import koo.product.management.system.domain.member.Member;
import koo.product.management.system.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession(true); // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성 (디폴트가 true)
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); // 세션에 로그인 회원 정보 보관, 이후 브라우저의 쿠키 저장소에 응답보냄

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션이 존재하면 그 세션을 가져오고 없다면 세션을 새로 만들지 않는다.(null 이 반환됨)

        if (session != null) {
            session.invalidate(); // 세션 정보를 삭제
        }

        return "redirect:/";
    }

}
