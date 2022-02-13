package koo.product.management.system.dto;

import koo.product.management.system.domain.item.ItemType;
import koo.product.management.system.file.UploadFile;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data // setter, getter, toString
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    private MultipartFile attachFile; // 첨부파일 하나
    private List<MultipartFile> imageFiles; // 이미지 파일은 여러개 업로드 가능

    private Boolean open; // 판매 여부
    private List<String> regions; // 등록 지역
    private ItemType itemType; // 상품 종류
    private String deliveryCode; // 배송 방식

}
