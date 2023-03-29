package com.example.codifyy.MainFeaturesPackage;

public class AddData {
   int Images;
   String img_name;
   String img_url;
   String if_click_url="";

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getIf_click_url() {
        return if_click_url;
    }

    public void setIf_click_url(String if_click_url) {
        this.if_click_url = if_click_url;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }

    public AddData(String img_url, String if_click_url) {
        this.img_url = img_url;
        this.if_click_url = if_click_url;
    }

    public AddData(int images,String img_name) {
        this.Images = images;
        this.img_name = img_name;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }
}

