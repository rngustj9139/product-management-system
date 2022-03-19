package koo.product.management.system.domain.item;

import koo.product.management.system.file.UploadFile;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data // setter, getter, toString
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    @Embedded // 임베디드 타입
    private UploadFile attachFile; // 첨부파일 하나
    @ElementCollection // 값타입 컬렉션
    @CollectionTable(name = "IMAGE_FILES")
    private List<UploadFile> imageFiles; // 이미지 파일은 여러개 업로드 가능

    private Boolean open; // 판매 여부
    @ElementCollection // 값타입 컬렉션
    @CollectionTable(name = "REGIONS")
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
