package com.cohabit.inventory;

public class Item {
    private String name;
    private String product_category;
    private String material_category;
    private String functionality;
    private String aesthetics;
    private String color;
    private String dimensions;
    private String id;

    public Item() {
    }

    public Item(String name, String product_category, String material_category, String functionality, String aesthetics, String color, String dimensions, String id) {
        this.name = name;
        this.product_category = product_category;
        this.material_category = material_category;
        this.functionality = functionality;
        this.aesthetics = aesthetics;
        this.color = color;
        this.dimensions = dimensions;
        this.id = id;
    }
}
