package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.yandex.mobile.ads.common.InitializationListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class KhadamBGActivity extends AppCompatActivity {

    Activity activity;
    DuhAPIManager adsApi;
    int Time = 200;
    RequestQueue queue;

    public boolean isAdmobInitialized=false,isUnityInitialized=false,isMaxInitialized=false,isYandexInitialized=false;
    public String myLog="Active Ad : "+ DuhVariables.DuhAdNetwork +" is initialized : ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        activity = this;

        Log.d("Ayman Log", "WorderInterstitial Started");


        adsApi = new DuhAPIManager(KhadamBGActivity.this);


        //Toast.makeText(getApplicationContext(), "KhadamBGActivity Opened!", Toast.LENGTH_SHORT).show();

        queue = Volley.newRequestQueue(this);
        getData();
    }

    private void getData(){
        //Toast.makeText(getApplicationContext(), "Getting Json data...", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, SplashSceneActivity.SimpleUrlJson, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //Toast.makeText(getApplicationContext(), "JSON Loaded!", Toast.LENGTH_SHORT).show();
                            JSONArray jsonAdsConfigArray = response.getJSONArray("Ads_and_Units");

                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);


                            DuhVariables.DuhAdNetwork = AdsConfig.getString("Active_Ad");
                            DuhVariables.DuhAdStyle = AdsConfig.getString("Ads_Style");
                            DuhVariables.DuhInterStyle = AdsConfig.getString("Inter_Style");
                            DuhVariables.DuhSecondaryInterCounter = AdsConfig.getInt("Inter_Counter");
                            DuhVariables.DuhSecondaryInter = AdsConfig.getString("Secondary_Inter_Type");
                            DuhVariables.DuhInterSecondaryCounter = AdsConfig.getInt("work_with_how_many_inters");

                            // I Don't know if I should delete this or not
//                            DuhVariables.is_Ad_Background_Active=AppConfig.getBoolean("is_Ad_Background_Active");




                            //Mix Selected Ads
                            DuhVariables.DuhAdsMix = Arrays.asList(DuhVariables.DuhAdNetwork.split(";"));

                            if(DuhVariables.DuhAdStyle.equals("mix")){
                                DuhVariables.DuhInter = DuhVariables.DuhAdsMix.get(0);
                                DuhVariables.DuhBanner = DuhVariables.DuhAdsMix.get(1);
                                DuhVariables.DuhNative = DuhVariables.DuhAdsMix.get(2);
                            }
                            else {
                                DuhVariables.DuhInter = DuhVariables.DuhAdsMix.get(0);
                                DuhVariables.DuhBanner = DuhVariables.DuhAdsMix.get(0);
                                DuhVariables.DuhNative = DuhVariables.DuhAdsMix.get(0);
                            }




                            //max
                            DuhVariables.Max1 = AdsConfig.getString("max_inter_first");
                            DuhVariables.Max2 = AdsConfig.getString("max_inter_second");
                            DuhVariables.Max3 = AdsConfig.getString("max_inter_third");
                            DuhVariables.Max4 = AdsConfig.getString("max_inter_fourth");
                            DuhVariables.MaxBanner = AdsConfig.getString("max_banner");
                            DuhVariables.MaxNative = AdsConfig.getString("max_native");

                            //fb
                            DuhVariables.MetaInter = AdsConfig.getString("fb_inter");
                            DuhVariables.MetaBanner = AdsConfig.getString("fb_banner");
                            DuhVariables.MetaNative = AdsConfig.getString("fb_native");

                            //admob
                            DuhVariables.GoogleInter = AdsConfig.getString("admob_inter");
                            DuhVariables.GoogleBanner = AdsConfig.getString("admob_banner");
                            DuhVariables.GoogleNative = AdsConfig.getString("admob_native");

                            //yandex
                            DuhVariables.YandexInterId = AdsConfig.getString("yandex_inter");
                            DuhVariables.YandexBannerId = AdsConfig.getString("yandex_banner");

                            //Unity
                            DuhVariables.UnityID = AdsConfig.getString("UnityGameId");
                            DuhVariables.unityBanner = AdsConfig.getString("unity_banner");
                            DuhVariables.unityInter = AdsConfig.getString("unity_inter");


                            //Toast.makeText(getApplicationContext(), "Loading ads...", Toast.LENGTH_SHORT).show();
                            checkSelectedAd();
                            adsApi.SelectedApiForAds();
                        } catch (JSONException e) {
                            //Toast.makeText(KhadamBGActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                finish();
                error.printStackTrace();
            }
        });

        request.setShouldCache(false);
        queue.add(request);
    }

    public void showAds(){

        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ShowTheChosenInter();
                }
            }, DuhVariables.DuhTimerInPrimeActive);
        }
        catch (Exception exception){

        }

    }

    public void ShowTheChosenInter(){
        //Toast.makeText(getApplicationContext(), "Loading interstitial...", Toast.LENGTH_SHORT).show();

        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if(!Lilly_APP.SimpleAdReadyChecker){
                            adsHandler.postDelayed(this,Time);
                        }
                        else {

                            if(!DuhVariables.DuhBGActive){
                                finish();
                            }
                            else {
                                checkInit();
                                adsApi.showInter(new DuhAPIManager.EndInter() {
                                    @Override
                                    public void onEndInter() {
                                        //Toast.makeText(getApplicationContext(), "Interstitial finished.", Toast.LENGTH_SHORT).show();
                                        checkInit();
                                        finish();
                                    }
                                });
                            }





                        }

                    }
                }, Time);
            }
        catch (Exception ex){
            //Toast.makeText(getApplicationContext(), "Failed to show interstitial!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }



    @Override
    public void onBackPressed() {
        //Block back button
    }

    private void checkSelectedAd() {
        try {
            MaxInit();
            AdmobInit();
            FanInit();
            YandexInit();
            UnityInit();

            ShowTheChosenInter();
        }
        catch (Exception exception){
            //Toast.makeText(KhadamBGActivity.this,""+exception,Toast.LENGTH_SHORT).show();
        }
    }

    private void UnityInit() {
        UnityAds.initialize(getApplicationContext(), DuhVariables.UnityID, DuhVariables.testMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

                isUnityInitialized=true;
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
    }

    private void YandexInit() {
        com.yandex.mobile.ads.common.MobileAds.initialize(this, new InitializationListener() {
            @Override
            public void onInitializationCompleted() {

                isYandexInitialized=true;
            }
        });
    }

    private void FanInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        //Fb Meta Init
        AudienceNetworkAds.initialize(this);
    }

    private void AdmobInit() {
        //Admob Init
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                isAdmobInitialized=true;
            }
        });
    }

    private void MaxInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                isMaxInitialized=true;
            }
        });
    }

    private void checkInit(){
        switch (DuhVariables.DuhAdNetwork){
            case "max" :
                Log.d("Logs", myLog + isMaxInitialized);

                break;

            case "admob" :
                Log.d("Logs", myLog + isAdmobInitialized);

                break;

            case "fb" :
                Log.d("Logs", myLog + "Facebook");
                break;


            case "yandex" :
                Log.d("Logs", myLog + isYandexInitialized);
                break;

            case "unity":
                Log.d("Logs", myLog + isUnityInitialized);
                break;

            default:
                Log.d("Logs", "Nothing is Initialized");

                break;
        }
    }

}
