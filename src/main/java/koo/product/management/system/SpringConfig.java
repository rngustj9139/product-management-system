package koo.product.management.system;

import koo.product.management.system.domain.item.Item;
import koo.product.management.system.repository.ItemRepository;
import koo.product.management.system.repository.MemoryItemRepository;
import koo.product.management.system.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MemoryItemRepository();
    }

}
