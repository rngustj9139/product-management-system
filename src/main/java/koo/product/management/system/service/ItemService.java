package koo.product.management.system.service;

import koo.product.management.system.domain.item.Item;
import koo.product.management.system.repository.ItemRepository;
import koo.product.management.system.repository.MemoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional // jpa 사용할때 꼭 붙이기
public class ItemService {

    private final ItemRepository itemRepository;

    public Item load(Item item) {
        itemRepository.save(item);

        return item;
    }

    public Item findOneItem(Long id) {
        Item item = itemRepository.findById(id);
        return item;
    }

    public List<Item> findAllItem() {
        List<Item> list = itemRepository.findAll();
        return list;
    }

    public void modify(Long itemId, Item updateParam) {
        itemRepository.update(itemId, updateParam);
    }

    public void erase(Long itemId) {
        itemRepository.delete(itemId);
    }

    // 페이지 별 상품 조회
    public List<Item> findItems(int page) {
        return itemRepository.findAllPaging(page);
    }

    // 페이지의 개수
    public int[] pageList() {
        int totalPage = itemRepository.itemCount() / 10;
        totalPage = ((itemRepository.itemCount() % 10) == 0) ? totalPage : totalPage + 1;

        int[] pages = new int[totalPage];
        for(int i = 0; i < totalPage; i++) {
            pages[i] = i + 1;
        }

        return pages;
    }

}
