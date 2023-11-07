package com.example.serverside;

public class Book {

    private String isbn;
    private String title;
    private String synopsis;
    private String author;
    private String category;
    private int publicationYear;
    private String email;

    public Book(String isbn, String title, String synopsis, String author, String category, int publicationYear, String email){
        super();
        this.isbn = isbn;
        this.title = title;
        this.synopsis = synopsis;
        this.author = author;
        this.category = category;
        this.publicationYear = publicationYear;
        this.email = email;
    }

    public Book() {

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
