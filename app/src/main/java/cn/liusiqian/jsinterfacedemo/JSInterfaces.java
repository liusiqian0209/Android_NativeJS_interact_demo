package cn.liusiqian.jsinterfacedemo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Créé par liusiqian 16/11/26.
 */

public class JSInterfaces
{
    private Context mContext;

    public JSInterfaces(Context context)
    {
        mContext = context;
    }

    //方法必须公开
    @JavascriptInterface
    public void showInfo(String info)
    {
        Toast.makeText(mContext,info,Toast.LENGTH_SHORT).show();
    }
}
