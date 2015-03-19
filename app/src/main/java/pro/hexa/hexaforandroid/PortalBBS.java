package pro.hexa.hexaforandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kdk on 2015-02-26.
 */
public class PortalBBS extends Fragment {
    Context mContext;
    public PortalBBS(){this.mContext=mContext;}
    private Bundle webViewBundle;
    WebView unsitbap;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unist_bus,null);
        unsitbap = (WebView)view.findViewById(R.id.unist_bus_webView);
        unsitbap.getSettings().setJavaScriptEnabled(true);
        unsitbap.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        unsitbap.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        unsitbap.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        unsitbap.setWebViewClient(new MyWebViewClient());
//        unsitbap.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("AlertDialog")
//                        .setMessage(message)
//                        .setPositiveButton(android.R.string.ok,
//                                new AlertDialog.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        result.confirm();
//                                    }
//                                })
//                        .setCancelable(false)
//                        .create()
//                        .show();
//
//                return true;
//            }
//        });

        if (webViewBundle == null) {
            unsitbap.loadUrl("http://lab.hexa.pro/portalbbs");
        } else {
            unsitbap.restoreState(webViewBundle);
        }

        return view;
    }

    public class MyWebViewClient extends WebViewClient {
        /* (non-Java doc)
         * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
         */


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".mp4"))
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "video/*");

                view.getContext().startActivity(intent);
                return true;
            }
            else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }}
    //TODO 페이지 유지
    @Override
    public void onPause() {
        super.onPause();
        webViewBundle = new Bundle();
        unsitbap.saveState(webViewBundle);
    }

}