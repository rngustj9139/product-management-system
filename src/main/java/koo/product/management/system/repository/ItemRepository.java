package koo.product.management.system.repository;

import koo.product.management.system.domain.item.Item;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long itemId, Item updateParam);
    void delete(Long itemId);
    List<Item> findAllPaging(int page); // 페이징(전체 상품 조회)
    int itemCount(); // 페이징(전체 상품의 개수)

}
