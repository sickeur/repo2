package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.SimpleGUIRating;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;

import java.net.MalformedURLException;
import java.net.URL;

public class Ra2issi_Menu extends AppCompatActivity {

    ConsentForm consentForm;

    RelativeLayout bannerContainer;
    LinearLayout nativeAdContainer;

    BannerAdView yandexBanner;
    NativeBannerView yandexNative;

    Button lets_start, lets_rate, lets_go_to_more_apps, lets_privacy, lets_play_games;
    private Dialog dialog;
    SimpleGUIRating simpleGUIRating;

    DuhAPIManager duhApi_manager;

    RICIVER_Net RICIVERNet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ra2issi_menu);

        RICIVERNet =new RICIVER_Net();

        bannerContainer=findViewById(R.id.LayoutBanner);
        nativeAdContainer=findViewById(R.id.My_Native_Wrapper);

        yandexBanner=findViewById(R.id.yandex_banner);
        yandexNative=findViewById(R.id.Yandex_Native_Container);

        //The Loading Dialogue While Waiting For the Ads
        dialog = new Dialog(Ra2issi_Menu.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ads_loading_layout);
        dialog.setCanceledOnTouchOutside(false);

        //Instantiating the Rate us Dialogue
        simpleGUIRating =new SimpleGUIRating(Ra2issi_Menu.this);
        simpleGUIRating.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        simpleGUIRating.setCancelable(false);

        duhApi_manager =new DuhAPIManager(this);
        duhApi_manager.SelectedApiForAds();
        duhApi_manager.showBanner(bannerContainer,yandexBanner);
        duhApi_manager.showNative(nativeAdContainer);

        gdpr();

        simpleGUIRating.show();

        lets_start =findViewById(R.id.start);
        lets_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                duhApi_manager.showInter(new DuhAPIManager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();

                        if(DuhVariables.DuhGuideSkipper){

                            if(DuhVariables.DuhIsRedActive){
                                Intent intent=new Intent(Ra2issi_Menu.this, MyRedActivity.class);
                                startActivity(intent);
                                return;
                            }
                            Intent intent=new Intent(Ra2issi_Menu.this, PrimeActive.class);
                            startActivity(intent);

                        }
                        else{

                            Intent intent=new Intent(Ra2issi_Menu.this, Lguide_Dyali.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        lets_play_games =findViewById(R.id.play_games);

        if(DuhVariables.DuhNewMainMenuButtonActive) lets_play_games.setVisibility(View.VISIBLE);

        lets_play_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                duhApi_manager.showInter(new DuhAPIManager.EndInter() {
                    @Override
                    public void onEndInter() {
                        dialog.dismiss();
                        Intent intent=new Intent(Ra2issi_Menu.this, ListaActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        lets_rate =findViewById(R.id.Please_Rate_Me_Button);
        lets_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleGUIRating.show();
            }
        });

        lets_privacy =findViewById(R.id.privacy);
        lets_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(DuhVariables.DuhPrivacy));
                Ra2issi_Menu.this.startActivity(intent);
            }
        });

        lets_go_to_more_apps =findViewById(R.id.more_apps);
        lets_go_to_more_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,  Uri.parse(DuhVariables.DuhPubUrl));
                Ra2issi_Menu.this.startActivity(intent);
            }
        });
    }

    public void gdpr(){

        ///////////////////////GDPR Badr////////////////////////////
        ConsentInformation consentInformation = ConsentInformation.getInstance(Ra2issi_Menu.this);

        String[] publisherIds = {"pub-5353669282093267"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
// User's consent status successfully updated.
                switch (consentStatus){
                    case PERSONALIZED:
                        ConsentInformation.getInstance(Ra2issi_Menu.this)
                                .setConsentStatus(ConsentStatus.PERSONALIZED);
                        break;
                    case NON_PERSONALIZED:
                        ConsentInformation.getInstance(Ra2issi_Menu.this)
                                .setConsentStatus(ConsentStatus.NON_PERSONALIZED);
                        break;

                    case UNKNOWN:
                        if
                        (ConsentInformation.getInstance(Ra2issi_Menu.this).isRequestLocationInEeaOrUnknown
                                ()){
                            URL privacyUrl = null;
                            try {

                                privacyUrl = new URL(DuhVariables.DuhPrivacy);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
// Handle error.
                            }
                            consentForm = new ConsentForm.Builder(Ra2issi_Menu.this,privacyUrl)
                                    .withListener(new ConsentFormListener() {
                                        @Override
                                        public void onConsentFormLoaded() {
                                            // Consent form loaded successfully.
                                            showform();
                                        }
                                        @Override
                                        public void onConsentFormOpened() {
                                            // Consent form was displayed.
                                        }
                                        @Override
                                        public void onConsentFormClosed( ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                                            // Consent form was closed.

                                        }
                                        @Override
                                        public void onConsentFormError(String errorDescription) {
                                            // Consent form error.
                                        }
                                    })
                                    .withPersonalizedAdsOption()
                                    .withNonPersonalizedAdsOption()
                                    .build();
                            consentForm.load();
                        } else {
                            ConsentInformation.getInstance(Ra2issi_Menu.this)
                                    .setConsentStatus(ConsentStatus.PERSONALIZED);
                        }
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.

            }

            private void showform(){
                if (consentForm !=null){

                    consentForm.show();
                }
            }
        });

        //////////////////////GDPR Badr/////////////////////////////////////



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
}