package com.scqkzqtz.webview.web.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * webview中分享参数
 */

public class ShareEntity implements Serializable {
    private String objectId = "";
    private String url = "";
    private String title = "";
    private String content = "";
    private String imageUrl = "";
    private String imagePath = "";//本地图片
    private Bitmap imageData;//本地图片bitmap

    public ShareEntity(String url, String title, String content, String imageUrl) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getImageData() {
        return imageData;
    }

    public void setImageData(Bitmap imageData) {
        this.imageData = imageData;
    }
}