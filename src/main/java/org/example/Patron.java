package org.example;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String patronMemberId;
    private String patronName;
    private String contactNo;
    private List<Book> borrowHistory;
    private List<Book> currentBorrowed;

    // Constructor
    public Patron(String patronMemberId, String patronName, String contactNo) {
        this.patronMemberId = patronMemberId;
        this.patronName = patronName;
        this.contactNo = contactNo;
        this.borrowHistory = new ArrayList<>();
        this.currentBorrowed = new ArrayList<>();
    }

    // Getters
    public String getPatronMemberId() {
        return patronMemberId;
    }
    public String getPatronName() {
        return patronName;
    }
    public String getContactNo(){
        return contactNo;
    }
    public List<Book> getBorrowHistory(){
        return borrowHistory;
    }
    public List<Book> getCurrentBorrowed(){
        return currentBorrowed;
    }

    // Setters
    public String setPatronMemberId(String patronMemberId) {
        return this.patronMemberId = patronMemberId;
    }
    public String setPatronName(String patronName) {
        return this.patronName = patronName;
    }
    public String setContactNo(String contactNo) {
        return this.contactNo = contactNo;
    }

    public void bookHistory(Book book) {
        if (book != null && !borrowHistory.contains(book)) {
            currentBorrowed.add(book);
            borrowHistory.add(book);
        }
    }

    public void removeBook(Book book) {
        currentBorrowed.remove(book);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronMemberId='" + patronMemberId + '\'' +
                ", patronName='" + patronName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
