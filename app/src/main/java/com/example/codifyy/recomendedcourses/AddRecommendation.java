package com.example.codifyy.recomendedcourses;

public class AddRecommendation {
    int imageId;
    String title;
    String url;

    public AddRecommendation(int imageId, String title, String url) {
        this.imageId = imageId;
        this.title = title;
        this.url = url;
    }

    public AddRecommendation(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
