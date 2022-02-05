package koo.product.management.system.domain.member;

public enum GenderType {

    MALE("남성"), FEMALE("여성");

    private final String description;

    GenderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
