package com.example.codifyy;

public class AddLanguages {
    private int imageId;
    private String Name;

    public AddLanguages(int imageId, String name) {
        this.imageId = imageId;
        Name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return Name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        Name = name;
    }
}
