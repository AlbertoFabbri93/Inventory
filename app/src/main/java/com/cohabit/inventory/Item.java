package com.cohabit.inventory;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item {
    private String aesthetics;
    private String color;
    private String dimensions;
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

    public String getAesthetics() {
        return aesthetics;
    }

    public void setAesthetics(String aesthetics) {
        this.aesthetics = aesthetics;
    }

    public String getColor() {
        return color;
    }

    public String getDimensions() {
        return dimensions;
    }

    public int getId() {
        return id;
    }

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String product_category, String material_category, String functionality, String aesthetics, String color, String dimensions, int id) {
        this.category = product_category;
        this.material = material_category;
        this.functionality = functionality;
        this.aesthetics = aesthetics;
        this.color = color;
        this.dimensions = dimensions;
        this.id = id;
    }
}
