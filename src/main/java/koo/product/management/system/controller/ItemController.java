package koo.product.management.system.controller;

import koo.product.management.system.domain.item.DeliveryCode;
import koo.product.management.system.domain.item.Item;
import koo.product.management.system.domain.item.ItemType;
import koo.product.management.system.dto.ItemSaveForm;
import koo.product.management.system.dto.ItemUpdateForm;
import koo.product.management.system.repository.MemoryItemRepository;
import koo.product.management.system.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ModelAttribute("regions") // 이 컨트롤러 속에서 어떤 메서드를 호출하더라도 모델에 데이터가 담겨 뷰로 전달됨 (key == regions)
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>(); // ListedHashMap은 순서가 보장됨

        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");

        return regions;
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();

        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));

        return deliveryCodes;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        ItemType[] itemTypes = ItemType.values();

        return itemTypes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findAllItem();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemService.findOneItem(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // 특정 필드가 아닌 복합 룰 검증 (global error, object error)
        if(form.getPrice() != null && form.getQuantity() != null) {
            int result = form.getPrice() * form.getQuantity();
            if(result < 10000) { ;
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, null);
            }
        }

        // 검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
//         BindingResult는 자동으로 모델에 담겨서 뷰로 전달된다.
//         form은 자동으로 모델에 담겨서 뷰로 전달된다. (key == "item")
            return "items/addForm";
        }

        // 검증 성공 로직
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setItemType(form.getItemType());
        item.setDeliveryCode(form.getDeliveryCode());
        item.setOpen(form.getOpen());
        item.setRegions(form.getRegions());

        Item savedItem = itemService.load(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOneItem(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 특정 필드가 아닌 복합 룰 검증 (global error, object error)
        if(form.getPrice() != null && form.getQuantity() != null) {
            int result = form.getPrice() * form.getQuantity();
            if(result < 10000) { ;
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, null);
            }
        }

        // 검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "items/editForm";
        }

        // 검증 성공 로직
        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());
        itemParam.setItemType(form.getItemType());
        itemParam.setDeliveryCode(form.getDeliveryCode());
        itemParam.setOpen(form.getOpen());
        itemParam.setRegions(form.getRegions());

        itemService.modify(itemId, itemParam);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

}

