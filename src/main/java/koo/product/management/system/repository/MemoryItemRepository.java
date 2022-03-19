package koo.product.management.system.repository;

import koo.product.management.system.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository{

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setOpen(updateParam.getOpen());
        findItem.setRegions(updateParam.getRegions());
        findItem.setItemType(updateParam.getItemType());
        findItem.setDeliveryCode(updateParam.getDeliveryCode());
        findItem.setAttachFile(updateParam.getAttachFile());
        findItem.setImageFiles(updateParam.getImageFiles());
    }

    @Override
    public void delete(Long itemId) {
        store.remove(itemId);
        --sequence;
    }

    @Override
    public List<Item> findAllPaging(int page) {
        return null;
    }

    @Override
    public int itemCount() {
        return 0;
    }

//    public void clearStore() {
//        store.clear();
//    }

}
