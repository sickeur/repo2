package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;


public class Enjoy_Activity extends AppCompatActivity {

    RelativeLayout bannerContainer;
    LinearLayout nativeAdContainer;

    BannerAdView yandexBanner;
    NativeBannerView yandexNative;

    DuhAPIManager duhApi_manager;
    private Dialog dialog;

    Button letsGoNext;
    RICIVER_Net RICIVERNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enjoy_activity);

         RICIVERNet =new RICIVER_Net();

        Lilly_APP.SimpleAdReadyChecker =true;

        bannerContainer=findViewById(R.id.LayoutBanner);
        nativeAdContainer=findViewById(R.id.My_Native_Wrapper);
        yandexBanner=findViewById(R.id.yandex_banner);
        yandexNative=findViewById(R.id.Yandex_Native_Container);

        //The Loading Dialogue While Waiting For the Ads
        dialog = new Dialog(Enjoy_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ads_loading_layout);
        dialog.setCanceledOnTouchOutside(false);

        duhApi_manager =new DuhAPIManager(this);
        duhApi_manager.SelectedApiForAds();
        duhApi_manager.showBanner(bannerContainer,yandexBanner);
        duhApi_manager.showNative(nativeAdContainer);

        letsGoNext =findViewById(R.id.next);
        letsGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                duhApi_manager.showInter(new DuhAPIManager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();
                        Intent intent=new Intent(Enjoy_Activity.this, Ra2issi_Menu.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(RICIVERNet,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(RICIVERNet);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}