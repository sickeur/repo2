package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants;

import java.net.MalformedURLException;
import java.net.URL;

public class TheHoster {
    public static String getHost(String url) throws MalformedURLException {
        return new URL(url).getHost();
    }
}
