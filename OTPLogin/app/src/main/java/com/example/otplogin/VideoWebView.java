package com.example.otplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class VideoWebView extends AppCompatActivity {
    WebView webView;
    WebSettings webSettings;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_web_view);

        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/game.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webSettings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient());


    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);

    }
}
