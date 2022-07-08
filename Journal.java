package com.company;

public class Journal extends Product{
    private long magazineNumber;
    private String date;

    public Journal(String name,long magazineNumber, String date, String category, long id, int amount) {
        this.setName(name);
        this.setMagazineNumber(magazineNumber);
        this.setDate(date);
        this.setCategory(category);
        this.setProductId(id);
        this.setAmount(amount);
    }

    public void setMagazineNumber(long magazineNumber) {
        this.magazineNumber = magazineNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getMagazineNumber() {
        return magazineNumber;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return "j";
    }

    public String toString() {
        return this.getName() + "," + this.getMagazineNumber() + "," + this.getDate() + "," + this.getCategory() + "," + this.getId() + "," + this.getAmount() + "\n";
    }
}
