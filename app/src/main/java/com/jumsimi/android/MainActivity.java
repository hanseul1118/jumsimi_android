package com.jumsimi.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://www.naver.com";

    private WebView mWebView;
    private WebSettings mWebSettings;

    private Toast mToast;
    private long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webview);

        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);

        mWebView.loadUrl(URL);

    }

    @Override
    public void onBackPressed() {
        if (mWebView.getOriginalUrl().equalsIgnoreCase(URL)) {
            if (CheckBackPressed()) return;
            super.onBackPressed();
        } else if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if (CheckBackPressed()) return;
            super.onBackPressed();
        }
    }

    private boolean CheckBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return true;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            mToast.cancel();
        }
        return false;
    }

    private void showGuide() {
        mToast = Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        mToast.show();
    }
}
