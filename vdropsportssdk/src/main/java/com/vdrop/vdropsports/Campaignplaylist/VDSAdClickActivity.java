package com.vdrop.vdropsports.Campaignplaylist;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.vdrop.vdropsports.R;

public class VDSAdClickActivity extends AppCompatActivity {

    private WebView vdsWv;
    private ImageButton btn_mozilla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdsad_click);
        /*vdsWv = (WebView)findViewById(R.id.vds_webview);
        vdsWv.setWebViewClient(new MyBrowser());
        vdsWv.setWebChromeClient(new WebChromeClient());
        vdsWv.getSettings().setJavaScriptEnabled(true);
        vdsWv.getSettings().setLoadsImagesAutomatically(true);
        vdsWv.loadUrl("https://vdrop.test-app.link/b7p3OUv3kE");*/
        btn_mozilla = (ImageButton)findViewById(R.id.btn_mozilla);
        btn_mozilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String url = "https://vdrop.test-app.link/b7p3OUv3kE";
                    Intent intent = new Intent(Intent.ACTION_MAIN, null);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(new ComponentName("org.mozilla.firefox", "org.mozilla.firefox.App"));
                    intent.setAction("org.mozilla.gecko.BOOKMARK");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("args", "--url=" + url);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }


    public static void openAdsActivity(Activity activity) {

        Intent intent = new Intent(activity,VDSAdClickActivity.class);
        activity.startActivity(intent);
    }


    private class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, WebResourceRequest request){
          String url = request.getUrl().toString();
            /*if (Uri.parse(url).getScheme().equals("vdrop")){
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    Activity host = (Activity) view.getContext();
                    host.startActivity(intent);
                    return true;
                }catch (ActivityNotFoundException e){
                    Uri uri = Uri.parse(url);
                    view.loadUrl("http://play.google.com/store/apps/" +uri.getHost());
                    return false;
                }
            }*/

            return false;
        }

    }




    //Opera Browser Code
   /* String packageName = "com.opera.mini.android";
    String className = "com.opera.mini.android.Browser";
    Intent internetIntent = new Intent(Intent.ACTION_VIEW);
    internetIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    internetIntent.setClassName(packageName, className);
    startActivity(internetIntent);*/

//    Share Dialog Code

    /* Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "Share This App"));*/
}
