package koo.product.management.system.repository;

import koo.product.management.system.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    void clearStore();

}
