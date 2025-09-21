package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Lending {
    private Map<Book, LendingInfo> lendingRecords;
    private Inventory inventory;

    public static class LendingInfo {
        private String patron;
        private LocalDate checkoutDate;
        private LocalDate dueDate;
        private LocalDate returnDate; // Use Integer to allow null values

        public LendingInfo(String patron, LocalDate checkoutDate, LocalDate dueDate) {
            this.patron = patron;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
            this.returnDate = null; // Initially, the book is not returned
        }

        public String getPatron() {
            return patron;
        }

        public LocalDate getCheckoutDate() {
            return checkoutDate;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public LocalDate getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
        }
        @Override
        public String toString() {
            return "LendingInfo{" +
                    "patron='" + patron + '\'' +
                    ", checkoutDate=" + checkoutDate +
                    ", dueDate=" + dueDate +
                    ", returnDate=" + returnDate +
                    '}';
        }
    }
    public Lending(Inventory inventory){
        this.inventory = inventory;
        lendingRecords = new HashMap<>();
    }
    // Lending operations

    public void checkoutBook(Book book, Patron patron){
        if(book != null && inventory.getAvailableBooks().contains(book)){
            inventory.getAvailableBooks().remove(book);
            inventory.getBorrowedBooks().add(book);
            patron.bookHistory(book);

            LendingInfo lendingInfo = new LendingInfo(patron.getPatronMemberId(), LocalDate.now(), LocalDate.now().plusWeeks(2));
            lendingRecords.put(book, lendingInfo);
        }
        else
            System.out.println("Book is not available for checkout.");
    }
    public void returnBook(Book book, Patron patron){
        if(book != null && inventory.getBorrowedBooks().contains(book)){
            inventory.getBorrowedBooks().remove(book);
            inventory.getAvailableBooks().add(book);
            patron.removeBook(book);

            LendingInfo lendingInfo = lendingRecords.get(book);
            if (lendingInfo != null) {
                lendingInfo.setReturnDate(LocalDate.now());
            }
        }
        else
            System.out.println("Book is not available for return.");
    }

    public LendingInfo getLendingInfo(Book book){
        return lendingRecords.get(book);
    }

}
