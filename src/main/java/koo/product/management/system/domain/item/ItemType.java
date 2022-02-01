package koo.product.management.system.domain.item;

public enum ItemType {

    BOOK("책"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
