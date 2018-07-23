package com.scqkzqtz.webview.web;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.scqkzqtz.webview.R;
import com.scqkzqtz.webview.databinding.ActivityWebViewBinding;
import com.scqkzqtz.webview.web.model.WebViewConfig;


public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_root, webViewFragment).commitAllowingStateLoss();
    }

    /**
     * @param context
     * @param config  web配置文件
     * @return
     */
    public static Intent getIntent(Context context, WebViewConfig config) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        if (config != null)
            bundle.putSerializable("config", config);
        intent.putExtras(bundle);
        return intent;
    }
}
