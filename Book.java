package com.company;

public class Book extends Product{
    String writer;

    public Book(String name, String writer, String category, long id, int amount) {
        this.setName(name);
        this.setWriter(writer);
        this.setCategory(category);
        this.setProductId(id);
        this.setAmount(amount);
    }

    public Book() {

    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public String getType() {
        return "b";
    }

    public String toString() {
        return this.getName() + "," + this.getWriter() + "," + this.getCategory() + "," + this.getId() + "," + this.getAmount() + "\n";
    }

}
