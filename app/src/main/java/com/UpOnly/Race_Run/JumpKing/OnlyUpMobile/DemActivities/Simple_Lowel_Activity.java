package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemModels.DUHlllModel;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.bumptech.glide.Glide;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class Simple_Lowel_Activity extends AppCompatActivity  {

    GifImageView SimpleGifIV1, SimpleGifIV2;
    TextView SimpleTitle1, SimpleTitleGIF1, SimpleTitleGIF2;

    RelativeLayout SimpleBannerRL;
    LinearLayout SimpleNativeLL;

    BannerAdView SimpleBannerAdView;
    NativeBannerView SimpleYandexAdNative;

    DuhAPIManager simpleapi_managerDuh;

    ArrayList<DUHlllModel> DUHlllModels =new ArrayList<>();
    int Simple_Position =0;
    private Dialog Simpledialog;

    RICIVER_Net simpleRICIVERNet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_lowel_activity);

        Lilly_APP.SimpleAdReadyChecker =true;

        simpleRICIVERNet =new RICIVER_Net();

        SimpleBannerRL =findViewById(R.id.LayoutBanner);
        SimpleNativeLL =findViewById(R.id.My_Native_Wrapper);
        SimpleBannerAdView =findViewById(R.id.yandex_banner);
        SimpleYandexAdNative =findViewById(R.id.Yandex_Native_Container);

        SimpleTitle1 =findViewById(R.id.selectTV);
        SimpleTitleGIF1 =findViewById(R.id.text1);
        SimpleTitleGIF2 =findViewById(R.id.text2);
        SimpleGifIV1 =findViewById(R.id.IVselect1);
        SimpleGifIV2 =findViewById(R.id.IVselect2);

        //The Loading Dialogue While Waiting For the Ads
        Simpledialog = new Dialog(Simple_Lowel_Activity.this);
        Simpledialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Simpledialog.setContentView(R.layout.ads_loading_layout);
        Simpledialog.setCanceledOnTouchOutside(false);

        SimpleModelGetter(DUHlllModels);
        SimpleStepGetter(Simple_Position);

        simpleapi_managerDuh =new DuhAPIManager(this);
        simpleapi_managerDuh.SelectedApiForAds();
        simpleapi_managerDuh.showBanner(SimpleBannerRL, SimpleBannerAdView);
        simpleapi_managerDuh.showNative(SimpleNativeLL);

        SimpleGifIV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetsSimplyGoNext();
            }
        });

        SimpleGifIV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LetsSimplyGoNext();
            }
        });

    }

    public void LetsSimplyGoNext(){
        Simpledialog.show();
        simpleapi_managerDuh.showInter(new DuhAPIManager.EndInter() {
            @Override
            public void onEndInter() {
                Simpledialog.dismiss();
                LetsSimplyGoPrevious();
            }
        });
    }

    private void LetsSimplyGoPrevious(){
        Simple_Position++;
        if(Simple_Position < DUHlllModels.size()){
            SimpleStepGetter(Simple_Position);
        }
        else {
            Intent intent=new Intent(Simple_Lowel_Activity.this, Enjoy_Activity.class);
            this.startActivity(intent);
        }

    }

    private void SimpleStepGetter(int position){
        try {
            SimpleTitle1.setText(DUHlllModels.get(position).getPleaseSelect_TV());
            SimpleTitleGIF1.setText(DUHlllModels.get(position).getChoice_1());
            SimpleTitleGIF2.setText(DUHlllModels.get(position).getChoice_2());
            Glide.with(this).load(DUHlllModels.get(position).getImageView1()).circleCrop().into(SimpleGifIV1);
            Glide.with(this).load(DUHlllModels.get(position).getImageView2()).circleCrop().into(SimpleGifIV2);
        }
        catch (Exception ex){
            Toast.makeText(Simple_Lowel_Activity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void SimpleModelGetter(ArrayList<DUHlllModel> theStepsonModelForTheSteps){
        theStepsonModelForTheSteps.add(new DUHlllModel("Please select your gender","Male","Female", R.drawable.rajel,R.drawable.mraaaa));
        theStepsonModelForTheSteps.add(new DUHlllModel("Please select your language","English","Spanish", R.drawable.england,R.drawable.spania));
        theStepsonModelForTheSteps.add(new DUHlllModel("Are you 18+ ?","Above 18","Under 18", R.drawable.simple_eighteen_super,R.drawable.simple_not_super_eighteen));
        theStepsonModelForTheSteps.add(new DUHlllModel("Select the Human","Human","Robot", R.drawable.not_a_robot,R.drawable.the_cute_robooot));
        theStepsonModelForTheSteps.add(new DUHlllModel("Select your closest Server","U.S.A","Europe", R.drawable.captain_america_flag,R.drawable.oropa));
//        theStepsonModelForTheSteps.add(new DUHlllModel("Select internet Connection type","WiFi","Mobile Data", R.drawable.wifi_icon,R.drawable.three_g));
    }

    @Override
    protected void onStart() {
        IntentFilter Simplefilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(simpleRICIVERNet,Simplefilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(simpleRICIVERNet);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}