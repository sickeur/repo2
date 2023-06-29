package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants;

import android.content.Context;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

public class SimpleBlockerCheckerClass {


    static Map<String, Boolean> DuhUrls = new HashMap<>();

    public static boolean blockAds(WebView view, String url) {
        boolean ad;
        if (!DuhUrls.containsKey(url)) {
            ad = SimpleBlockerChecker.CheckIfItsAnAd(url);
            DuhUrls.put(url, ad);
        } else {
            ad = DuhUrls.get(url);
        }
        return ad;
    }

    public static class init {
        Context context;

        public init(Context context) {
            SimpleBlockerChecker.init(context);
            this.context = context;
        }

        public void initializeWebView(WebView mWebView) {


        }
    }
}
