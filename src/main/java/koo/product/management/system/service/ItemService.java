package koo.product.management.system.service;

import koo.product.management.system.domain.item.Item;
import koo.product.management.system.repository.ItemRepository;
import koo.product.management.system.repository.MemoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
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

}
