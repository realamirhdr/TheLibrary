package com.company;

public abstract class Product {
    private String name;
    private String category;
    private long id;
    private int amount = 0;

    public abstract String toString();
    public abstract String getType();

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProductId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

}
