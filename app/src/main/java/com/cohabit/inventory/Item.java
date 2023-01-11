package com.cohabit.inventory;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {
    private String aesthetic;
    private String color;
    private String size;
    private String category;
    private String material;
    private String functionality;
    private int id;

    public String getCategory() {
        return category;
    }

    public String getMaterial() {
        return material;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getAesthetic() {
        return aesthetic;
    }

    public void setAesthetic(String aesthetic) {
        this.aesthetic = aesthetic;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String product_category, String material_category, String functionality, String aesthetic, String color, String size, int id) {
        this.category = product_category;
        this.material = material_category;
        this.functionality = functionality;
        this.aesthetic = aesthetic;
        this.color = color;
        this.size = size;
        this.id = id;
    }
}
