package cn.liusiqian.jsinterfacedemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private WebView webview;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //允许chrome调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        webview = (WebView) findViewById(R.id.web);
        btn = (Button) findViewById(R.id.btn);

        //enable Js
        webview.getSettings().setJavaScriptEnabled(true);
        //支持中文
        webview.getSettings().setDefaultTextEncodingName("GBK");
        //增加接口方法
        webview.addJavascriptInterface(new JSInterfaces(MainActivity.this), "launcher");
        //允许alert弹出窗口，需要转换为Android内部的控件
        webview.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public boolean onJsAlert(final WebView view, String url, final String message, JsResult result)
            {
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                return super.onJsAlert(view, url, message, result);
            }
        });
        webview.loadUrl("file:///android_asset/index.html");


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                webview.loadUrl("javascript:show('native调用web方法')");
                webview.loadUrl("javascript:check()");
            }
        });
    }
}
