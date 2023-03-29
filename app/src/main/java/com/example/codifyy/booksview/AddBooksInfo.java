package com.example.codifyy.booksview;

public class AddBooksInfo {
    String name;
    String pdf_url;
    String buy_url;
    String book_cover_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;

    public AddBooksInfo(String name, String pdf_url, String buy_url) {
        this.name = name;
        this.pdf_url = pdf_url;
        this.buy_url = buy_url;
    }

    public AddBooksInfo(String name, String book_cover_url, String pdf_url, String buy_url,String title) {
        this.name = name;
        this.pdf_url = pdf_url;
        this.buy_url = buy_url;
        this.book_cover_url = book_cover_url;
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public void setBuy_url(String buy_url) {
        this.buy_url = buy_url;
    }

    public void setBook_cover_url(String book_cover_url) {
        this.book_cover_url = book_cover_url;
    }

    public String getName() {
        return name;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public String getBuy_url() {
        return buy_url;
    }

    public String getBook_cover_url() {
        return book_cover_url;
    }
}
