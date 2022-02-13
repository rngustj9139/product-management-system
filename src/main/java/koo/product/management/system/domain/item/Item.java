package koo.product.management.system.domain.item;

import koo.product.management.system.file.UploadFile;
import lombok.Data;

import java.util.List;

@Data // setter, getter, toString
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private UploadFile attachFile; // 첨부파일 하나
    private List<UploadFile> imageFiles; // 이미지 파일은 여러개 업로드 가능

    private Boolean open; // 판매 여부
    private List<String> regions; // 등록 지역
    private ItemType itemType; // 상품 종류
    private String deliveryCode; // 배송 방식

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
