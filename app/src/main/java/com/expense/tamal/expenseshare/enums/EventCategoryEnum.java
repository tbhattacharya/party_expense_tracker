package com.expense.tamal.expenseshare.enums;

/**
 * Created by tamal on 13/10/2016.
 */
public enum EventCategoryEnum {
    PARTY(1, "Party"),
    TOUR(2, "Tour"),
    PICNIC(3, "Picnic");

    public int categoryId;
    public String categoryName;

    EventCategoryEnum(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
