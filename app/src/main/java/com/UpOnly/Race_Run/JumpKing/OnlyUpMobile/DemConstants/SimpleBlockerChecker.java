package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;

import androidx.annotation.WorkerThread;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

public class SimpleBlockerChecker {
    private static final String HostFile = "granny.txt";
    public static final Set<String> SimpleAd_Host = new HashSet<>();

    public static void init(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    loadFromAssets(context);
                } catch (IOException e) {
                    // noop
                }
                return null;
            }
        }.execute();
    }

    @WorkerThread
    private static void loadFromAssets(Context context) throws IOException {
        InputStream stream = context.getAssets().open(HostFile);
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            SimpleAd_Host.add(line);
        }
        bufferedReader.close();
        inputStreamReader.close();
        stream.close();
    }

    public static boolean CheckIfItsAnAd(String url) {
        try {
            return CheckIfAdIsHost(TheHoster.getHost(url));
        } catch (MalformedURLException e) {
            return false;
        }

    }

    private static boolean CheckIfAdIsHost(String host) {
        if (TextUtils.isEmpty(host)) {
            return false;
        }
        int index = host.indexOf(".");
        return index >= 0 && (SimpleAd_Host.contains(host) ||
                index + 1 < host.length() && CheckIfAdIsHost(host.substring(index + 1)));
    }

    public static WebResourceResponse SimpleEmptyResourcesCreator() {
        return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream("".getBytes()));
    }
}
