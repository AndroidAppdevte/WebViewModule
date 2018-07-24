package com.scqkzqtz.webview.web.model;

import android.graphics.Color;

import java.io.Serializable;

/**
 * webview配置文件
 */

public class WebViewConfig implements Serializable {
    /**
     * 网页地址
     */
    private String url = "";
    /**
     * 是否显示进度条
     */
    private Boolean isShowProgressBar = false;
    /**
     * 进度条颜色
     */
    private int progressBarColor = Color.RED;
    /**
     * 头部右边加载的TYPE
     */
    private String titleRightType;
    /**
     * 是否显示底部分享，收藏布局
     */
    private Boolean isShowBottom = false;
    /**
     * 是否显示收藏按钮（底部，更多）
     */
    private Boolean isShowCollection = true;
    /**
     * 是否全屏
     */
    private Boolean isFullScreen = false;
    /**
     * 是否启用字体缩放功能
     */
    private Boolean isFontScaling = false;
    /**
     * 收藏ID
     */
    private String collectionObject;
    /**
     * 分享参数
     */
    private ShareEntity shareEntity;

    public WebViewConfig(String url) {
        this.url = url;
    }

    /**
     * 是否全屏
     */
    public WebViewConfig isFullScreen(Boolean fullScreen) {
        isFullScreen = fullScreen;
        return this;
    }

    /**
     * 头部右边加载的TYPE 不传或传空时不显示
     */
    public WebViewConfig setTitleRightType(String titleRightType) {
        this.titleRightType = titleRightType;
        return this;
    }

    /**
     * 是否显示底部分享，收藏布局  默认不显示
     */
    public WebViewConfig isShowBottom(Boolean showBottom) {
        isShowBottom = showBottom;
        return this;
    }

    /**
     * 是否显示收藏按钮（底部，更多）  默认显示
     */
    public WebViewConfig isShowCollection(Boolean showCollection) {
        isShowCollection = showCollection;
        return this;
    }

    /**
     * 是否启用字体缩放功能，默认不启用
     */
    public WebViewConfig isFontScaling(Boolean fontScaling) {
        isFontScaling = fontScaling;
        return this;
    }

    /**
     * 分享参数
     */
    public WebViewConfig setShareEntity(ShareEntity shareEntity) {
        this.shareEntity = shareEntity;
        return this;
    }

    /**
     * 是否显示进度条
     */
    public WebViewConfig isShowProgressBar(Boolean showProgressBar) {
        isShowProgressBar = showProgressBar;
        return this;
    }

    /**
     * 进度条颜色
     */
    public WebViewConfig setProgressBarColor(int progressBarColor) {
        this.progressBarColor = progressBarColor;
        return this;
    }

    /**
     * 收藏ID
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
}
