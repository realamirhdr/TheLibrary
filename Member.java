package com.company;


public class Member extends User {
    private long id;
    public DynamicArray borrowed = new DynamicArray();
    private int borrowedAmount = 0;

    public Member(String username, String password, long id) {
        this.setUsername(username);
        this.setPassword(password);
        this.setMemberId(id);
    }

    public Member() {

    }
    
    public void setMemberId(long id) {
        this.id = id;
    }

    public void setBorrowed(Product product) {
        if (this.borrowedAmount < 5) {
            this.borrowed.add(product);
            this.borrowedAmount++;
        }
        else {
            System.out.println("max borrow reached");
        }
    }

    public int getBorrowedAmount() {
        return borrowedAmount;
    }

    public long getId() {
        return id;
    }

    public DynamicArray getBorrowed() {
        return this.borrowed;
    }

    public void showBorrowed() {
        if (borrowedAmount == 0) {
            System.out.println("no borrows?");
        }
        else {
            for (int i = 0; i < borrowed.getIndex(); i++) {
                Product tempProduct = (Product) borrowed.getElement(i);
                System.out.println("#" + (i + 1) + "-" + tempProduct.toString());
            }
        }
    }

    public void returnBorrowed(int i) {
        if (this.borrowedAmount > 0) {
            Product tempProduct = (Product) borrowed.getElement(i - 1);
            borrowed.remove(tempProduct);
            this.borrowedAmount--;
        }
        else {
            System.out.println("no borrows");
        }
    }

    public String borrowedToString() {
        if (borrowedAmount == 0) return ",-1";

        String str = ",";
        for (int i = 0; i < borrowedAmount; i++) {
            Product tempProduct = (Product) borrowed.getElement(i);
            str = str + tempProduct.getType() + tempProduct.getId() ;
            if (i < borrowedAmount - 1) {
                str = str + "@";
            }
        }
        return str;
    }

    public String toString() {
        return "{" + this.getUsername() + ", " + this.getId() + "}";
    }

    public String toWrite(String str) {
        return this.getUsername() + "," + this.getPassword() +  "," + this.getId() + this.borrowedToString() + "\n";
    }
}


