package koo.product.management.system.dto;

import koo.product.management.system.domain.item.ItemType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data // setter, getter, toString
public class ItemUpdateForm {

    @NotNull // (빈 값이면 안됨)
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull // (빈 값이면 안됨)
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull // (빈 값이면 안됨)
    private Integer quantity; // 수정에서는 수량을 자유롭게 변경할 수 있다. (빈 값만 아니면 됨)

    private Boolean open; // 판매 여부
    private List<String> regions; // 등록 지역
    private ItemType itemType; // 상품 종류
    private String deliveryCode; // 배송 방식

}
