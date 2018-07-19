package com.scqkzqtz.webview.web.model;

import java.io.Serializable;

/**
 * Created by zsx on 2018/2/2.
 */

public class WebDateEntity implements Serializable {
    private String url = "";//网页地址
    private String titleName = "";//页面标题

    public WebDateEntity(String url) {
        this.url = url;
    }

    /**
     * 页面标题
     *
     * @param titleName
     * @return
     */
    public WebDateEntity setTitleName(String titleName) {
        this.titleName = titleName;
        return this;
    }
}
