package com.scqkzqtz.webview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.scqkzqtz.librarybase.utils.DrawableUtil;
import com.scqkzqtz.librarybase.utils.PreferenceUtils;
import com.scqkzqtz.webview.databinding.ActivityMainBinding;
import com.scqkzqtz.webview.web.WebViewActivity;
import com.scqkzqtz.webview.web.model.WebViewConfig;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        PreferenceUtils.init(this);
        initView();
    }

    private void initView() {


        binding.bt.setOnClickListener(v -> {

            WebViewConfig webViewConfig = new WebViewConfig("https://www.baidu.com/")
                    .isShowProgressBar(binding.cbPb.isChecked())       //是否显示进度条  默认不显示
                    .setProgressBarColor(Color.RED)//进度条颜色
                    .isFullScreen(binding.cbFs.isChecked())           //是否全屏，这里为true以下设置都没有作用
                    .setTitleRightType("MORE")                       //头部右边加载的TYPE,更具type去处理加载布局
                    .isShowBottom(binding.cbSb.isChecked())            //是否显示底部分享，收藏按钮  默认不显示
                    .isShowCollection(binding.cbCo.isChecked())        //是否显示收藏按钮（底部，更多）  默认显示
                    .isFontScaling(binding.cbFsc.isChecked());          //是否启用字体缩放功能  默认不启用
            startActivity(WebViewActivity.getIntent(MainActivity.this, webViewConfig));

        });
    }
}
