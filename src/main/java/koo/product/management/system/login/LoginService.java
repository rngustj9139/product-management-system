package koo.product.management.system.login;

import koo.product.management.system.domain.member.Member;
import koo.product.management.system.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemoryMemberRepository memoryMemberRepository;

    // return값이 null이면 로그인 실패
    public Member login(String loginId, String password) {
//        Optional<Member> findedMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findedMemberOptional.get();
//
//        if(member.getPassword().equals(password)) {
//            return member;
//        }else {
//            return null;
//        }

        return memoryMemberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
