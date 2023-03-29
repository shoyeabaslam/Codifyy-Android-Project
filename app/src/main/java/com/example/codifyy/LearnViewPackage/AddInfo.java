package com.example.codifyy.LearnViewPackage;

public class AddInfo {
    String title;
    String img_url;
    String web_url;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public AddInfo(String title, String img_url, String web_url) {
        this.title = title;
        this.img_url = img_url;
        this.web_url = web_url;
    }
}
