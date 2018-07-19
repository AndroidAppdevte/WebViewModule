package com.scqkzqtz.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.scqkzqtz.librarybase.utils.PreferenceUtils;
import com.scqkzqtz.webview.web.WebViewActivity;
import com.scqkzqtz.webview.web.model.WebDateEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        PreferenceUtils.init(this);

        WebDateEntity webDateEntity = new WebDateEntity("http://webapplaika.shgs99.com/dist/views/shop/index.html#!/?goldTab=1&session_token=&version=v3.3.4&os=android");
        startActivity(WebViewActivity.getIntent(MainActivity.this, webDateEntity));
    }
}
