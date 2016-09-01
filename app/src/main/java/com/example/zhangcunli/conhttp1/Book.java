package com.example.zhangcunli.conhttp1;

/**
 * Created by zhangcunli on 2016/5/13.
 */
public class Book {
    //Variables that are in our json
    private int bookId;
    private String name;
    private String bookFace;

    public String getBookFace() {
        return bookFace;
    }

    public void setBookFace(String bookFace) {
        this.bookFace = bookFace;
    }

    public Book(int bookId, String name, String bookFace) {
        super();
        this.bookId = bookId;
        this.name = name;
        this.bookFace = bookFace;
    }

    public Book() {
    }

    //Getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
