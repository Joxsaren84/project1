package ru.joxaren.project1.models;

public class Book {

    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int bookYear;
    private Integer bookReaderId;

    public Book(int bookId, String bookName, String bookAuthor, int bookYear, int bookReaderId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
        this.bookReaderId = bookReaderId;
    }

    public Book(){};

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookYear() {
        return bookYear;
    }

    public Integer getBookReaderId() {
        return bookReaderId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public void setBookReaderId(Integer bookReaderId) {
        this.bookReaderId = bookReaderId;
    }
}
