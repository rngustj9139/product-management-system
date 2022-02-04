package koo.product.management.system;

import koo.product.management.system.domain.item.Item;
import koo.product.management.system.domain.member.Member;
import koo.product.management.system.repository.MemoryItemRepository;
import koo.product.management.system.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemoryItemRepository memoryItemRepository;
    private final MemoryMemberRepository memoryMemberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memoryItemRepository.save(new Item("itemA", 10000, 10));
        memoryItemRepository.save(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        memoryMemberRepository.save(member);
    }

}
