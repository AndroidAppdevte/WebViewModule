package com.scqkzqtz.webview.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import com.scqkzqtz.librarybase.utils.PreferenceUtils;
import com.scqkzqtz.librarybase.utils.ToastUtils;
import com.scqkzqtz.webview.R;
import com.scqkzqtz.webview.databinding.FragmentWebViewBinding;
import com.scqkzqtz.webview.web.model.WebDateEntity;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zsx on 2018/6/11.
 */

public class WebViewFragment extends Fragment implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    private String AppVersion = "";
    private String userInfoStr = "";
    private String deviceInfoStr = "";
    private String sessionToken = "";
    private String loginTag = "";//登录后调用javascript使用

    private FragmentWebViewBinding binding;
    private WebDateEntity entity;

    private int Text_Size = 100;//字体大小
    private ScaleGestureDetector mScaleGestureDetector = null;//手势

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);
        init();
        initView();
//        binding.webview.loadUrl(entity.getUrl());

        binding.webview.loadUrl("file:///android_asset/qktz.app.html");
        return binding.getRoot();
    }

    private void init() {
        entity = (WebDateEntity) getArguments().getSerializable("entity");
        AppVersion = getAppVersionName(getActivity());
        sessionToken = "111111111";//TODO
        initUserInfoStr();
        initDeviceInfoStr();
    }

    private void initView() {
        initWebView();
        binding.titleBack.setOnClickListener(v -> {
            onKeyDown();
        });
        binding.titleClose.setOnClickListener(v -> getActivity().finish());
        //点击返回键
        binding.webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        onKeyDown();
                        return true;
                    }
                }
                return false;
            }
        });
        mScaleGestureDetector = new ScaleGestureDetector(getActivity(), this);
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setSavePassword(false);

// 第一个参数：这里需要一个与js映射的java对象
// 第二个参数：该java对象被映射为js对象后在js里面的对象名，在js中要调用该对象的方法就是通过这个来调用
        binding.webview.addJavascriptInterface(new JavascriptInterfaceModel(), "_qktzApp");
        binding.webview.setWebChromeClient(mWebChromeClient);
        binding.webview.setWebViewClient(mWebViewClient);
        binding.webview.setOnTouchListener(this);
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (title != null || !TextUtils.isEmpty(title)) {
                binding.titleName.setText(title);
            } else {
                binding.titleName.setText("");
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
            }
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            binding.webview.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (PreferenceUtils.getInt("TEXT_SIZE", 0) != 0)
                Text_Size = PreferenceUtils.getInt("TEXT_SIZE", 0);

            setTextSize(false);
            binding.invalidateAll();

            if (view.getTitle() == null || !("" + view.getTitle()).contains("about:blank")) {
                binding.titleName.setText("");
            }
        }
    };

    private void setTextSize(boolean isUp) {
        String tosetStr = "标准字体";
        switch (Text_Size) {
            case 75:
                tosetStr = "小号字体";
                binding.webview.loadUrl("javascript:scaleH5(1)");
                break;
            case 100:
                tosetStr = "标准字体";
                binding.webview.loadUrl("javascript:scaleH5(2)");
                break;
            case 125:
                tosetStr = "中号字体";
                binding.webview.loadUrl("javascript:scaleH5(3)");
                break;
            case 150:
                tosetStr = "大号字体";
                binding.webview.loadUrl("javascript:scaleH5(4)");
                break;
            default:
                binding.webview.loadUrl("javascript:scaleH5(2)");
                Text_Size = 100;
                PreferenceUtils.commitInt("TEXT_SIZE", Text_Size);
                break;
        }
        if (isUp)
            ToastUtils.showShortToast(getActivity(), tosetStr);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (binding.title.getVisibility() == View.VISIBLE &&
                binding.titleSize.getVisibility() == View.VISIBLE) {
            mScaleGestureDetector.onTouchEvent(event);
        }
        return false;
    }

    private boolean needScale = true;

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        if (needScale)
            if (scaleFactor > 1 && Text_Size < 150) {
                Text_Size = Text_Size + 25;
                needScale = false;
                PreferenceUtils.commitInt("TEXT_SIZE", Text_Size);
            } else if (scaleFactor < 1 && Text_Size > 75) {
                Text_Size = Text_Size - 25;
                needScale = false;
                PreferenceUtils.commitInt("TEXT_SIZE", Text_Size);
            }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        needScale = true;
        setTextSize(true);
    }


    /**
     * 处理回退 事件
     */
    public void onKeyDown() {
        binding.titleClose.setVisibility(View.VISIBLE);
        if (binding.webview.canGoBack()) {
            binding.webview.goBack();
        } else {
            getActivity().finish();
        }
    }

    class JavascriptInterfaceModel {
        @android.webkit.JavascriptInterface
        public void onBack() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onKeyDown();
                }
            });
        }

        @android.webkit.JavascriptInterface
        public void onFinish() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getActivity().finish();
                }
            });
        }

        @android.webkit.JavascriptInterface
        public void onLoadNewWindows(String url) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //TODO 新窗口打开页面
                }
            });
        }

        @android.webkit.JavascriptInterface
        public String getSessionToken() {
            return sessionToken;
        }

        @android.webkit.JavascriptInterface
        public String getUserInfo() {
            return userInfoStr;
        }

        @android.webkit.JavascriptInterface
        public String getDeviceInfo() {
            return deviceInfoStr;
        }

        @android.webkit.JavascriptInterface
        public void login(String tag) {
//            登录接口，跳转原生登录页。记录login传入的 loginTag参数，在登录成功后调用javascript:eval(" + loginTag + "('SessionToken'))通知H5刷新
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //TODO
                    loginTag = tag;
                    String SessionToken = "12346578";
                    binding.webview.loadUrl("javascript:eval(" + loginTag + "('" + SessionToken + "'))");//登陆成功后调用
                }
            });
        }
    }

    /**
     * 初始化用户信息json
     */
    public void initUserInfoStr() {
        JSONObject jsonObject = new JSONObject();
        try {
            //TODO 未添加参数
            jsonObject.put("userObjectId", "");
            jsonObject.put("userName", "");
            jsonObject.put("mobilePhoneNumber", "");
            jsonObject.put("headImageUrl", "");
            jsonObject.put("os", "android");
            deviceInfoStr = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化设备信息json
     */
    public void initDeviceInfoStr() {
        JSONObject jsonObject = new JSONObject();
        try {
            //TODO 未添加参数
            jsonObject.put("appVersion", AppVersion);
            jsonObject.put("channel", "");
            jsonObject.put("clientid", "");
            jsonObject.put("os", "android");
            userInfoStr = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取app版本号
     *
     * @param context
     * @return
     */

    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
