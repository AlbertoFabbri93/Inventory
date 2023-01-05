package com.cohabit.inventory;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {
    public String product_category;
    public String material_category;
    public String functionality;
    public String aesthetics;
    public String color;
    public String dimensions;

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String product_category, String material_category, String functionality, String aesthetics, String color, String dimensions) {
        this.product_category = product_category;
        this.material_category = material_category;
        this.functionality = functionality;
        this.aesthetics = aesthetics;
        this.color = color;
        this.dimensions = dimensions;
    }
}
