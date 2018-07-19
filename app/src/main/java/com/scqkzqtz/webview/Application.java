package com.scqkzqtz.webview;

import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by zsx on 2018/6/20.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initWebview();
    }

    private void initWebview() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
                Log.d("app", " onCoreInitFinished");
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
