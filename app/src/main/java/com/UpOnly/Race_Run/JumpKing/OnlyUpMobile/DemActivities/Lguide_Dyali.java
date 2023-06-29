package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.bumptech.glide.Glide;
import com.yandex.mobile.ads.banner.BannerAdView;

public class Lguide_Dyali extends AppCompatActivity {

    ImageView MoveNext, MovePrevious, simple_IV;
    TextView H1_Tv, Paragraph_TV;
    int position=0;
    private Dialog adsUiLoader;

    RelativeLayout bannerContainer;
    BannerAdView yandex_The_Banner;

    DuhAPIManager adminAPI;

    RICIVER_Net networkSpyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lguide_dyali);

        networkSpyListener =new RICIVER_Net();

        bannerContainer=findViewById(R.id.LayoutBanner);
        yandex_The_Banner =findViewById(R.id.yandex_banner);

        MoveNext =findViewById(R.id.imgNext);
        MovePrevious =findViewById(R.id.imgPrevious);
        H1_Tv =findViewById(R.id.guide_title);
        Paragraph_TV =findViewById(R.id.guide_description);
        simple_IV =findViewById(R.id.guide_image);

        //The Loading Dialogue While Waiting For the Ads
        adsUiLoader = new Dialog(Lguide_Dyali.this);
        adsUiLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adsUiLoader.setContentView(R.layout.ads_loading_layout);
        adsUiLoader.setCanceledOnTouchOutside(false);

        adminAPI =new DuhAPIManager(this);
        adminAPI.SelectedApiForAds();
        adminAPI.showBanner(bannerContainer, yandex_The_Banner);

        H1_Tv.setText(Lilly_APP.DUHlllGuides.get(position).getMyTitle());
        Paragraph_TV.setText(Lilly_APP.DUHlllGuides.get(position).getMyDescription());
        Glide.with(this).load(Lilly_APP.DUHlllGuides.get(position).getMyImage()).into(simple_IV);

        MoveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>= Lilly_APP.DUHlllGuides.size()-1){
                    if(DuhVariables.DuhIsRedActive){
                        Intent intent=new Intent(Lguide_Dyali.this, MyRedActivity.class);
                        startActivity(intent);
                        return;
                    }
                    adminAPI.showInter(new DuhAPIManager.EndInter() {
                        @Override
                        public void onEndInter() {
                            Intent intent=new Intent(Lguide_Dyali.this, PrimeActive.class);
                            startActivity(intent);
                        }
                    });

                }
                else{
                    Inter_On_Next();
                }
            }
        });

        MovePrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inter_On_Back();
            }
        });

    }
    private void Go_Next(){
        position++;
        if(position>= Lilly_APP.DUHlllGuides.size()-1) position= Lilly_APP.DUHlllGuides.size()-1;
        H1_Tv.setText(Lilly_APP.DUHlllGuides.get(position).getMyTitle());
        Paragraph_TV.setText(Lilly_APP.DUHlllGuides.get(position).getMyDescription());
        Glide.with(this).load(Lilly_APP.DUHlllGuides.get(position).getMyImage()).into(simple_IV);
    }

    private void Go_Previous(){
        position--;
        if(position<=0) position=0;
        H1_Tv.setText(Lilly_APP.DUHlllGuides.get(position).getMyTitle());
        Paragraph_TV.setText(Lilly_APP.DUHlllGuides.get(position).getMyDescription());
        Glide.with(this).load(Lilly_APP.DUHlllGuides.get(position).getMyImage()).into(simple_IV);
    }

    private void Inter_On_Next(){
        adsUiLoader.show();
        adminAPI.showInter(new DuhAPIManager.EndInter() {
            @Override
            public void onEndInter() {
                adsUiLoader.dismiss();
                Go_Next();
            }
        });
    }

    private void Inter_On_Back(){
        adsUiLoader.show();
        adminAPI.showInter(new DuhAPIManager.EndInter() {
            @Override
            public void onEndInter() {
                adsUiLoader.dismiss();
                Go_Previous();
            }
        });
    }


    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkSpyListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkSpyListener);
        super.onStop();
    }
}