package com.company;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        String username;
        String password;
        boolean logoutFlag = false;

        String name;
        String category;
        long id;
        int amount = 0;

        while (true) {

            System.out.println("1. Login as a member");
            System.out.println("2. Login as a manager");
            System.out.println("3. Exit");
            int menuOperationNumber = intChecker();

            switch (menuOperationNumber) {
                case 1: // Member
                    System.out.println("Enter your username:");
                    username = sc.next();
                    System.out.println("Enter your password:");
                    password = sc.next();
                    logoutFlag = false;
                    if (library.memberLogin(username, password) == true) {
                        long memberId = library.getMemberId(username, password);
                        while (logoutFlag == false) {
                            System.out.println("1. Log out");
                            System.out.println("2. Borrow a book/journal");
                            System.out.println("3. Return a book/journal");
                            System.out.println("4. View all borrowed products");
                            int memberOperationNumber = intChecker();

                            switch (memberOperationNumber) {
                                case 1:
                                    logoutFlag = true;
                                    break;
                                case 2:
                                    System.out.println("What kind of product would you like to borrow?\n1.Books\n2.Journals");
                                    int borrowOperationNumber = intChecker();

                                    switch (borrowOperationNumber) {
                                        case 1:
                                            System.out.println("Books:");
                                            library.showAllBooks();
                                            if (library.getBooks().getIndex() > 0) {
                                                System.out.println("Please enter the book's ID");
                                                long bookId = longChecker();
                                                library.borrowBook(memberId, bookId);
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Journals:");
                                            library.showAllJournals();
                                            if (library.getJournals().getIndex() > 0) {
                                                System.out.println("Please enter the journals's ID");
                                                long journalId = longChecker();
                                                library.borrowJournal(memberId, journalId);
                                            }
                                            break;
                                    }
                                    break;
                                case 3:
                                    System.out.println("which one of these products are you returning?");
                                    library.viewAllborrowed(memberId);
                                    int returningProductNumber = intChecker();
                                    library.returnProduct(memberId, returningProductNumber);
                                    break;
                                case 4:
                                    library.viewAllborrowed(memberId);
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Incorrect username or password.\nPlease try again.");
                    }

                    break;
                case 2: // Manager
                    System.out.println("Enter your username:");
                    username = sc.next();
                    System.out.println("Enter your password:");
                    password = sc.next();
                    logoutFlag = false;
                    if (library.managerLogin(username, password) == true) {
                        while (logoutFlag == false) {
                            System.out.println("1. Add a product");
                            System.out.println("2. Add a member");
                            System.out.println("3. View all members");
                            System.out.println("4. View all products");
                            System.out.println("5. Delete a product by ID");
                            System.out.println("6. Log out");
                            int managerOperationNumber = intChecker();

                            switch (managerOperationNumber) {
                                case 1:
                                    System.out.println("1. Add a book");
                                    System.out.println("2. Add a journal");
                                    int addingProductOperationNumber = intChecker();

                                    switch (addingProductOperationNumber) {
                                        case 1:
                                            System.out.println("Enter the new book's name:");
                                            sc.nextLine();
                                            name = sc.nextLine();
                                            System.out.println("Enter the new book's writer:");
                                            String writer = sc.nextLine();
                                            System.out.println("Enter the new book's category:");
                                            category = sc.next();
                                            System.out.println("Enter the new book's ID:");
                                            id = longChecker();
                                            System.out.println("Enter the new book's amount:");
                                            amount = intChecker();
                                            library.addBook(name, writer, category, id, amount);
                                            break;
                                        case 2:
                                            System.out.println("Enter the new journal's name:");
                                            sc.nextLine();
                                            name = sc.nextLine();
                                            System.out.println("Enter the new journals's magazine number:");
                                            long magazineNumber = longChecker();
                                            System.out.println("Enter the new journals's date:");
                                            String date = sc.next();
                                            System.out.println("Enter the new journals's category:");
                                            category = sc.next();
                                            System.out.println("Enter the new journals's ID:");
                                            id = longChecker();
                                            System.out.println("Enter the new journals's amount:");
                                            amount = intChecker();
                                            library.addJournal(name, magazineNumber, date, category, id, amount);
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter the new member's username:");
                                    username = sc.next();
                                    System.out.println("Enter the new member's password:");
                                    password = sc.next();
                                    System.out.println("Enter the new member's ID:");
                                    id = longChecker();
                                    library.addmember(username, password, id);
                                    break;
                                case 3:
                                    library.showAllMembers();
                                    break;
                                case 4:
                                    library.showAllProducts();
                                    break;
                                case 5:
                                    System.out.println("Do you want to remove a book(Enter 1) or a journal(Enter 2)?");
                                    int removeOperationMenu = intChecker();
                                    switch (removeOperationMenu) {
                                        case 1:
                                            System.out.println("Enter the intended book's ID?");
                                            library.showAllBooks();
                                            id = longChecker();
                                            library.removeBook(id);
                                            break;
                                        case 2:
                                            System.out.println("Enter the intended journal's ID?");
                                            library.showAllJournals();
                                            id = longChecker();
                                            library.removeJournal(id);
                                            break;
                                    }
                                    break;
                                case 6:
                                    logoutFlag = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Incorrect username or password.\nPlease try again.");
                    }
                    logoutFlag = false;
                    break;
                case 3: // exit
                    library.saveChanges();
                    System.exit(1);
            }
        }
    }

    public static int intChecker() {
        int i = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                i = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Value!\nPlease try again.");
            }
        }
        return i;
    }

    public static long longChecker() {
        long i = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                i = scanner.nextLong();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Value!\nPlease try again.");
            }
        }
        return i;
    }

}




