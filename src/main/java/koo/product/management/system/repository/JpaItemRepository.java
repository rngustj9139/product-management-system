package koo.product.management.system.repository;

import koo.product.management.system.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaItemRepository implements ItemRepository{

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        Item findedItem = em.find(Item.class, id);
        return findedItem;
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
            .getResultList();
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        Item findedItem = em.find(Item.class, itemId);
        findedItem.setItemName(updateParam.getItemName());
        findedItem.setPrice(updateParam.getPrice());
        findedItem.setQuantity(updateParam.getQuantity());
        findedItem.setOpen(updateParam.getOpen());
        findedItem.setRegions(updateParam.getRegions());
        findedItem.setItemType(updateParam.getItemType());
        findedItem.setDeliveryCode(updateParam.getDeliveryCode());
        findedItem.setAttachFile(updateParam.getAttachFile());
        findedItem.setImageFiles(updateParam.getImageFiles());
    }

    @Override
    public void delete(Long itemId) {
        Item findedItem = em.find(Item.class, itemId);
        em.remove(findedItem);
    }

}
