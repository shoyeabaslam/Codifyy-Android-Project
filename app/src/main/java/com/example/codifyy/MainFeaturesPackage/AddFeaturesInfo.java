package com.example.codifyy.MainFeaturesPackage;

public class AddFeaturesInfo{

    int image_id;
    String Name;
    int color_id;

    public AddFeaturesInfo(int image_id, String name, int color_id) {
        this.image_id = image_id;
        Name = name;
        this.color_id = color_id;
    }

    public AddFeaturesInfo(int image_id, String name) {
        this.image_id = image_id;
        Name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }
}
