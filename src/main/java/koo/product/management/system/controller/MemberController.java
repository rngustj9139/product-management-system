package koo.product.management.system.controller;

import koo.product.management.system.domain.item.DeliveryCode;
import koo.product.management.system.domain.item.ItemType;
import koo.product.management.system.domain.member.EmailCode;
import koo.product.management.system.domain.member.GenderType;
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

    @ModelAttribute("genderTypes")
    public GenderType[] genderTypes() {
        GenderType[] genderTypes = GenderType.values();

        return genderTypes;
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberSaveForm") MemberSaveForm memberSaveForm) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute MemberSaveForm memberSaveForm, BindingResult bindingResult) {
        if(memberSaveForm.getGender() == null) {
            bindingResult.rejectValue("gender", "genderIsNull"); // field 에러일 경우 rejectValue, object(global) 에러일 경우 reject
        }

        log.info("first email = {}, last email = {}", memberSaveForm.getEmailFirst(), memberSaveForm.getEmailLast());

        if(!memberSaveForm.getEmailLast().equals("naver.com") && !memberSaveForm.getEmailLast().equals("google.com") && !memberSaveForm.getEmailLast().equals("daum.net")) { // string간의 비교는 equals 함수를 사용해야한다.
            bindingResult.rejectValue("emailLast", "emailFormat");
        }

        if(bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        Member member = new Member();
        member.setEmail(memberSaveForm.getEmailFirst() + "@" + memberSaveForm.getEmailLast());
        member.setLoginId(memberSaveForm.getLoginId());
        member.setPassword(memberSaveForm.getPassword());
        member.setName(memberSaveForm.getName());
        member.setGender(memberSaveForm.getGender());

//        log.info("signed up member email = {}", member.getEmail());
//        log.info("signed up member gender = {}", member.getGender());

        memoryMemberRepository.save(member);

        return "redirect:/";
    }

}
