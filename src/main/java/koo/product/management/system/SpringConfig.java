package koo.product.management.system;

import koo.product.management.system.domain.item.Item;
import koo.product.management.system.repository.ItemRepository;
import koo.product.management.system.repository.JpaItemRepository;
import koo.product.management.system.repository.MemoryItemRepository;
import koo.product.management.system.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.swing.*;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(em);
    }

}
