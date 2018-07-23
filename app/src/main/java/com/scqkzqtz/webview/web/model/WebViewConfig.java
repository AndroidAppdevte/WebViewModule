package com.scqkzqtz.webview.web.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.scqkzqtz.librarybase.utils.DrawableUtil;
import com.scqkzqtz.webview.R;

import java.io.Serializable;

/**
 * Created by zsx on 2018/2/2.
 */

public class WebViewConfig implements Serializable {
    private String url = "";//网页地址
    private Boolean isShowProgressBar = false;//是否显示进度条
    private int progressBarColor = Color.RED;//进度条颜色
    private String titleRightType;//头部右边加载的TYPE
    private Boolean isShowBottom = false;//是否显示底部分享，收藏按钮
    private Boolean isShowCollection = true;//是否显示收藏按钮（底部，更多）
    private Boolean isFullScreen = false;//是否全屏
    private Boolean isFontScaling = false;//是否启用字体缩放功能
    private String collectionObject;//收藏ID
    private ShareEntity shareEntity;//分享参数

    public WebViewConfig(String url) {
        this.url = url;
    }

    /**
     * @param fullScreen 是否全屏
     * @return
     */
    public WebViewConfig isFullScreen(Boolean fullScreen) {
        isFullScreen = fullScreen;
        return this;
    }

    /**
     * @param titleRightType 头部右边加载的TYPE
     */
    public WebViewConfig setTitleRightType(String titleRightType) {
        this.titleRightType = titleRightType;
        return this;
    }

    /**
     * @param showBottom 是否显示底部分享，收藏按钮  默认不显示
     * @return
     */
    public WebViewConfig isShowBottom(Boolean showBottom) {
        isShowBottom = showBottom;
        return this;
    }

    /**
     * @param showCollection 是否显示收藏按钮（底部，更多）  默认显示
     * @return
     */
    public WebViewConfig isShowCollection(Boolean showCollection) {
        isShowCollection = showCollection;
        return this;
    }

    /**
     * @param fontScaling 是否启用字体缩放功能
     */
    public WebViewConfig isFontScaling(Boolean fontScaling) {
        isFontScaling = fontScaling;
        return this;
    }

    /**
     * @param shareEntity 分享参数
     */
    public WebViewConfig setShareEntity(ShareEntity shareEntity) {
        this.shareEntity = shareEntity;
        return this;
    }

    /**
     * @param showProgressBar 是否显示进度条
     * @return
     */
    public WebViewConfig isShowProgressBar(Boolean showProgressBar) {
        isShowProgressBar = showProgressBar;
        return this;
    }

    /**
     * @param progressBarColor 进度条颜色
     */
    public WebViewConfig setProgressBarColor(int progressBarColor) {
        this.progressBarColor = progressBarColor;
        return this;
    }

    /**
     * @param collectionObject 收藏ID
     */
    public WebViewConfig setCollectionObject(String collectionObject) {
        this.collectionObject = collectionObject;
        return this;
    }

    public String getCollectionObject() {
        return collectionObject;
    }

    public int getProgressBarColor() {
        return progressBarColor;
    }

    public Boolean getShowProgressBar() {
        return isShowProgressBar;
    }

    public Boolean getFontScaling() {
        return isFontScaling;
    }

    public String getTitleRightType() {
        return titleRightType;
    }

    public Boolean getFullScreen() {
        return isFullScreen;
    }

    public Boolean getShowCollection() {
        return isShowCollection;
    }

    public Boolean getShowBottom() {
        return isShowBottom;
    }

    public String getUrl() {
        return url;
    }

    public ShareEntity getShareEntity() {
        return shareEntity;
    }


    /**
     * 分享
     */
    class ShareEntity implements Serializable {
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
}
