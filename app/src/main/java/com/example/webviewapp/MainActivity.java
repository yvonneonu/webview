package com.example.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // getSupportActionBar()
        webView = findViewById(R.id.webview);
       // createWebPrintJob(webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://spiel90.com/posterminal.html");


        //webView.addJavascriptInterface(new WebViewJWebViewJavaScriptInterface(this), "app");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");
    }

    public class WebViewJavaScriptInterface {

        private Context context;

        /*
         * Need a reference to the context in order to sent a post message
         */
        public WebViewJavaScriptInterface(Context context) {
            this.context = context;
        }

        /*
         * This method can be called from Android. @JavascriptInterface
         * required after SDK version 17.
         */


        @JavascriptInterface
        //public void printimage
        public void makeToast(String message, boolean lengthLong) {
            Toast.makeText(context, message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
            Log.d("me", "" + message);
        }
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }

}