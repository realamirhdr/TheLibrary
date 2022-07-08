package com.company;

import jdk.management.jfr.FlightRecorderMXBean;

import java.awt.image.MemoryImageSource;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;


public class Library {
    private User admin = new Manager("admin", "admin");
    private static DynamicArray members = new DynamicArray();
    private static int userAmount = 0;
    private static DynamicArray books = new DynamicArray();
    private static int bookAmount = 0;
    private static DynamicArray journals = new DynamicArray();
    private static int journalAmount = 0;

    public Library() {
        BookReader();
        JournalReader();
        MemberReader();
    }

    public static DynamicArray getJournals() {
        return journals;
    }

    public static DynamicArray getBooks() {
        return books;
    }

    public boolean managerLogin(String username, String password) {
        if (username.equals(admin.getUsername())) {
            if (password.equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean memberLogin(String username, String password) {
        for (int i = 0; i < this.userAmount; i++) {
            Member tempMember = (Member) members.getElement(i);
            if (username.equals(tempMember.getUsername())) {
                if (password.equals(tempMember.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getMemberId (String username, String password) {
        for (int i = 0; i < this.userAmount; i++) {
            Member tempMember = (Member) members.getElement(i);
            if (username.equals(tempMember.getUsername())) {
                if (password.equals(tempMember.getPassword())) {
                    return tempMember.getId();
                }
            }
        }
        return 0;
    }

    public void addmember(String username, String password, long id) {
        members.add(new Member(username, password, id));
        this.userAmount++;
        System.out.println("Member added successfully.");
    }

    public void addBook(String name, String writer, String category, long id, int amount) {
        if (this.bookAmount > 0) {
            for (int i = 0; i < bookAmount; i++) {
                Book tempBook = (Book) books.getElement(i);
                if (tempBook.getName().equals(name)) {
                    int newAmount = amount + tempBook.getAmount();
                    tempBook.setAmount(newAmount);
                    break;
                } else {
                    books.add(new Book(name, writer, category, id, amount));
                    this.bookAmount++;
                    System.out.println("Book added successfully.");
                    break;
                }
            }
        }
        else {
            books.add(new Book(name, writer, category, id, amount));
            this.bookAmount++;
            System.out.println("Book added successfully.");
        }
    }

    public void addJournal(String name,long magazineNumber, String date, String category, long id, int amount) {
        if (this.journalAmount > 0) {
            for (int i = 0; i < journalAmount; i++) {
                Journal tempJournal = (Journal) journals.getElement(i);
                if (this.journalAmount > 0 && tempJournal.getName().equals(name)) {
                    int newAmount = amount + tempJournal.getAmount();
                    tempJournal.setAmount(newAmount);
                    break;
                } else {
                    this.journals.add(new Journal(name, magazineNumber, date, category, id, amount));
                    this.journalAmount++;
                    System.out.println("Journal added successfully.");
                    break;
                }
            }
        }
        else {
            this.journals.add(new Journal(name, magazineNumber, date, category, id, amount));
            this.journalAmount++;
            System.out.println("Journal added successfully.");
        }
    }

    public void removeBook(long id) {
        Book book = (Book) findBook(id);
        for (int i = 0; i < userAmount; i++) {
            Member member = (Member) members.getElement(i);
            for (int j = 0; j < member.getBorrowedAmount(); j++) {
                DynamicArray borrowed = member.getBorrowed();
                Product item = (Product) borrowed.getElement(j);
                try {
                    if (item.equals(book)) {
                        System.out.println("Can't remove the book due to it being borrowed by a member.");
                    } else {
                        this.books.remove(book);
                        bookAmount--;
                        System.out.println("Book removed successfully.");
                    }
                } catch (Exception e) {
                    System.out.println("Could not find the book you were looking for.\nPlease try again.");
                }
            }
        }
    }

    public void removeJournal(long id) {
        Journal journal = (Journal) findJournal(id);
        for (int i = 0; i < userAmount; i++) {
            Member member = (Member) members.getElement(i);
            for (int j = 0; j < member.getBorrowedAmount(); j++) {
                DynamicArray borrowed = member.getBorrowed();
                Product item = (Product) borrowed.getElement(j);
                try {
                    if (item.equals(journal)) {
                        System.out.println("Can't remove the journal due to it being borrowed by a member.");
                    } else {
                        this.journals.remove(journal);
                        journalAmount--;
                        System.out.println("Journal removed successfully.");
                    }
                } catch (Exception e) {
                    System.out.println("Could not find the journal you were looking for.\nPlease try again.");
                }
            }
        }
    }

    public void showAllMembers() {
        if (this.userAmount > 0) {
            for (int i = 0; i < this.userAmount; i++) {
                Member tempMember = (Member) members.getElement(i);
                System.out.println("#" + (i+1) + "-" + tempMember.toString());
            }
        }
        else {
            System.out.println("No members?");
        }
    }

    public void showAllBooks() {
        System.out.println("\nID is second to last.");
        if (this.bookAmount > 0) {
            for (int i = 0; i < this.bookAmount; i++) {
                Book tempBook = (Book) books.getElement(i);
                System.out.println("#" + (i+1) + "-" + tempBook.toString());
            }
        }
        else {
            System.out.println("No books?");
        }
    }

    public void showAllJournals() {
        System.out.println("\nID is second to last.");
        if (this.journalAmount > 0) {
            for (int i = 0; i < this.journalAmount; i++) {
                Journal tempJournal = (Journal) journals.getElement(i);
                System.out.println("#" + (i+1) + "-" + tempJournal.toString());
            }
        }
        else {
            System.out.println("No journals?");
        }
    }

    public void showAllProducts() {
        System.out.println("Books:");
        showAllBooks();
        System.out.println("Journals:");
        showAllJournals();
    }

    private Member findMember(long id) {
        for (int i = 0; i < members.getIndex(); i++) {
            Member tempMember = (Member) members.getElement(i);
            if (tempMember.getId() == id) {
                return tempMember;
            }
        }
        return null;
    }

    private static Book findBook(long id) {
        for (int i = 0; i < bookAmount; i++) {
            Book tempBook = (Book) books.getElement(i);
            if (tempBook.getId() == id) {
                return tempBook;
            }
        }
        return null;
    }


    private static Journal findJournal(long id) {
        for (int i = 0; i < journalAmount; i++) {
            Journal tempJournal = (Journal) journals.getElement(i);
            if (tempJournal.getId() == id) {
                return tempJournal;
            }
        }
        return null;
    }

    public void borrowBook(long memberId, long bookId) {
        if (books.getIndex() + 1 > 0) {
            findMember(memberId).setBorrowed(findBook(bookId));
        }
        else {
            System.out.println("No books");
        }
    }

    public void borrowJournal(long memberId, long journalId) {
        if (journals.getIndex() + 1 > 0) {
            findMember(memberId).setBorrowed(findJournal(journalId));
        }
        else {
            System.out.println("no journals");
        }
    }

    public void viewAllborrowed(long id) {
        findMember(id).showBorrowed();
    }

    public void returnProduct(long memberId, int i) {
        findMember(memberId).returnBorrowed(i);
    }

    public static void MemberWriter(String memberInfo) {
        try {
            FileWriter fw = new FileWriter("G:\\JAVA\\theLibrary-V2.0\\Data\\Members.txt", true);
            for (int i = 0; i < memberInfo.length(); i++) {
                fw.write(memberInfo.charAt(i));
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public static void BookWriter(String bookInfo) {
        try {
            FileWriter fw = new FileWriter("G:\\JAVA\\theLibrary-V2.0\\Data\\Books.txt", true);
            for (int i = 0; i < bookInfo.length(); i++) {
                fw.write(bookInfo.charAt(i));
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public static void JournalWriter(String journalInfo) {
        try {
            FileWriter fw = new FileWriter("G:\\JAVA\\theLibrary-V2.0\\Data\\Journals.txt", true);
            for (int i = 0; i < journalInfo.length(); i++) {
                fw.write(journalInfo.charAt(i));
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public static void MemberReader() {
         try {
             FileReader fr = new FileReader("G:\\JAVA\\theLibrary-V2.0\\Data\\Members.txt");

             String str = new String();
             char ch;

             while (fr.ready()) {
                 ch = (char) fr.read();

                 if (ch == '\n') {
                     String[] memberData = str.split(",");
                     Member loadedMember = new Member(memberData[0], memberData[1], Long.parseLong(memberData[2]));
                     if (!memberData[3].equals("-1")) {
                         String[] borrowedProductsId = memberData[3].split("@");
                         Product[] borrowedProducts = new Product[borrowedProductsId.length];
                         for (int i = 0; i < borrowedProductsId.length; i++) {
                             String idString = borrowedProductsId[i].substring(1);
                             long id = Long.parseLong(idString);
                             if (borrowedProductsId[i].charAt(0) == 'b') {
                                 borrowedProducts[i] = (Book) findBook(id);
                             } else if (borrowedProductsId[i].charAt(0) == 'j') {
                                 borrowedProducts[i] = (Journal) findJournal(id);
                             }
                             loadedMember.setBorrowed(borrowedProducts[i]);
                         }
                     }
                     members.add(loadedMember);
                     userAmount++;
                     str = new String();
                 }
                 else {
                     str = str + ch;
                 }
             }
             fr.close();
         } catch (Exception e) {
             System.out.println("Error");
         }
    }

    public static void BookReader() {
        try {
            FileReader fr = new FileReader("G:\\JAVA\\theLibrary-V2.0\\Data\\Books.txt");

            String str = new String();
            char ch;

            while (fr.ready()) {
                ch = (char) fr.read();

                if (ch == '\n') {
                    String[] bookData = str.split(",");
                    Book loadedBook = new Book(bookData[0], bookData[1], bookData[2], Long.parseLong(bookData[3]), Integer.parseInt(bookData[4]));
                    books.add(loadedBook);
                    bookAmount++;
                    str = new String();
                }
                else {
                    str = str + ch;
                }
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void JournalReader() {
        try {
            FileReader fr = new FileReader("G:\\JAVA\\theLibrary-V2.0\\Data\\Journals.txt");

            String str = new String();
            char ch;

            while (fr.ready()) {
                ch = (char) fr.read();

                if (ch == '\n') {
                    String[] journalData = str.split(",");
                    Journal loadedJournal = new Journal(journalData[0], Long.parseLong(journalData[1]), journalData[2], journalData[3], Long.parseLong(journalData[4]),  Integer.parseInt(journalData[5]));
                    journals.add(loadedJournal);
                    journalAmount++;
                    str = new String();
                }
                else {
                    str = str + ch;
                }
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void clearFile(String address) {
        try {
            FileWriter fw = new FileWriter(address, false);
            fw.write("");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void saveChanges() {
        clearFile("G:\\JAVA\\theLibrary-V2.0\\Data\\Members.txt");
        for (int i = 0; i < members.getIndex(); i++) {
            Member member = (Member) members.getElement(i);
            MemberWriter(member.toWrite("file"));
        }

        clearFile("G:\\JAVA\\theLibrary-V2.0\\Data\\Books.txt");
        for (int i = 0; i < books.getIndex(); i++) {
            BookWriter(books.getElement(i).toString());
        }

        clearFile("G:\\JAVA\\theLibrary-V2.0\\Data\\Journals.txt");
        for (int i = 0; i < journals.getIndex(); i++) {
            JournalWriter(journals.getElement(i).toString());
        }

        System.out.println("All changes saved successfully.");
    }
}

