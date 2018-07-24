package com.scqkzqtz.webview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.scqkzqtz.base.library.utils.PreferenceUtils;
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
                    .isShowProgressBar(binding.cbPb.isChecked())
                    .setProgressBarColor(Color.RED)
                    .isFullScreen(binding.cbFs.isChecked())
                    .setTitleRightType("MORE")
                    .isShowBottom(binding.cbSb.isChecked())
                    .isShowCollection(binding.cbCo.isChecked())
                    .isFontScaling(binding.cbFsc.isChecked());
            startActivity(WebViewActivity.getIntent(MainActivity.this, webViewConfig));

        });
    }
}
