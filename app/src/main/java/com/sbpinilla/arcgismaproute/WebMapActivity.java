package com.sbpinilla.arcgismaproute;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_map);

        final TextView txtDistansia = findViewById(R.id.distansia);
        final TextView txtTiempo= findViewById(R.id.tiempo);


        final WebView mWebview = findViewById(R.id.mapContainerWebView);

        final LinearLayout linearLoader = findViewById(R.id.container_loader);
        mWebview.getSettings().setJavaScriptEnabled(true);



        mWebview.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(final WebView view, String url) {
                super.onPageFinished(view, url);

                view.loadUrl(
                        "javascript:(function() { " +
                                "document.getElementById(\"ml-directions-searchbox\").style.display = \"none\"; "
                                + "document.getElementsByClassName(\"ml-searchbox-inner\")[0].style.display = \"none\";"
                                + "document.getElementsByClassName(\"ml-panes-directions-bottom-bar\")[0].style.display = \"none\";"
                                + "document.getElementsByClassName(\"ml-directions-pane-content\")[0].style.display = \"none\";"
                                + "})()");
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Menu-Activity. */
                        linearLoader.setVisibility(View.GONE);


                        view.evaluateJavascript(
                                "(function() { return (document.getElementsByClassName(\"ml-directions-pane-header-time-content\")[0].innerText); })();",
                                new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html) {
                                        Log.d("HTML", html);
                                        // code here

                                        txtTiempo.setText("Tiempo estimado:"+html.replace("\"",""));
                                    }
                                });

                        view.evaluateJavascript(
                                "(function() { return (document.getElementsByClassName(\"ml-directions-pane-header-distance\")[0].innerText); })();",
                                new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html) {
                                        Log.d("HTML", html.replace("(","").replace(")",""));
                                        // code here

                                        txtDistansia.setText("Distansia estimada:"+ html.replace("\"","").replace("(","").replace(")",""));
                                    }
                                });










                    }
                }, 3000);
            }
        });

            mWebview.loadUrl("https://www.google.com/maps/dir/4.8554194,-74.029606/4.8532757,-74.0494823/@4.8490416,-74.0386058,13z/data=!4m2!4m1!3e0");



    }
}
