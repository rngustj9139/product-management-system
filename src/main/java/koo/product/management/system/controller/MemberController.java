package koo.product.management.system.controller;

import koo.product.management.system.domain.item.DeliveryCode;
import koo.product.management.system.domain.member.EmailCode;
import koo.product.management.system.domain.member.Member;
import koo.product.management.system.dto.MemberSaveForm;
import koo.product.management.system.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemoryMemberRepository memoryMemberRepository;

    @ModelAttribute("emailCodes")
    public List<EmailCode> emailCodes() {
        List<EmailCode> emailCodes = new ArrayList<>();

        emailCodes.add(new EmailCode("naver.com", "네이버"));
        emailCodes.add(new EmailCode("google.com", "구글"));
        emailCodes.add(new EmailCode("daum.net", "다음"));

        return emailCodes;
    }


    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberSaveForm") MemberSaveForm memberSaveForm) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberSaveForm memberSaveForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        Member member = new Member();
        member.setEmail(memberSaveForm.getEmailFirst() + "@" + memberSaveForm.getEmailLast());
        member.setLoginId(memberSaveForm.getLoginId());
        member.setPassword(memberSaveForm.getPassword());
        member.setName(memberSaveForm.getName());

        log.info("signed up member email = {}", member.getEmail());

        memoryMemberRepository.save(member);

        return "redirect:/";
    }

}
