package com.learn.project_wallz.MODELS;

public class ImageModel {
    public String url;
    public String info;
    int id;

    public ImageModel(){}
    public ImageModel(String url , String info){
        this.url = url;
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
    public String getUrl() {
        return url;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
