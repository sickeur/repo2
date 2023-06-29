package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class DuhMaxAds {


    //Native
    private MaxAd DuhnativeAd;

    //Banner
    private MaxAdView DuhadView;

    private final AppCompatActivity DuhmActivity;
    public boolean isCompleted=false;
    public boolean isClosed=false;




    public interface AdFinished{
        void onAdFinished();
    }

    public DuhMaxAds(AppCompatActivity appCompatActivity){
        this.DuhmActivity =appCompatActivity;
    }



    public void show_four_inter(final AdFinished adFinished){
        try {
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(DuhVariables.Max4, DuhmActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_three_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_three_inter(adFinished);
        }

    }

    public void show_three_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(DuhVariables.Max3, DuhmActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_two_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_two_inter(adFinished);
        }
    }

    public void show_two_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(DuhVariables.Max2, DuhmActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    show_one_inter(adFinished);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            show_one_inter(adFinished);
        }
    }

    public void show_one_inter(final AdFinished adFinished){
        try{
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(DuhVariables.Max1, DuhmActivity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    interstitialAd.showAd();
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adFinished.onAdFinished();
                }
            });

            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception exception){
            adFinished.onAdFinished();
        }
    }



    public void showBanner(RelativeLayout bannerContainer) {
        DuhadView =new MaxAdView(DuhVariables.MaxBanner, DuhmActivity);
        DuhadView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

                int width= ViewGroup.LayoutParams.MATCH_PARENT;
                int height= DuhmActivity.getResources().getDimensionPixelOffset(R.dimen.max_banner_height);

                DuhadView.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
                DuhadView.setBackgroundColor(ContextCompat.getColor(DuhmActivity,R.color.white));
                bannerContainer.addView(DuhadView);
                bannerContainer.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        DuhadView.loadAd();
    }



    public void createNativeAd(LinearLayout nativeAdContainer)
    {

        MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader( DuhVariables.MaxNative, DuhmActivity);
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( DuhnativeAd != null )
                {
                    nativeAdLoader.destroy(DuhnativeAd);
                }

                // Save ad for cleanup.
                DuhnativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
            }
        } );

        nativeAdLoader.loadAd();
    }

    public void showReward(AdFinished adFinished){
        isCompleted=false;
        isClosed=false;
        MaxRewardedAd rewardedAd = MaxRewardedAd.getInstance( DuhVariables.MaxReward, DuhmActivity);
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {
                isCompleted=true;
            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                rewardedAd.showAd();

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                isClosed=true;
                adFinished.onAdFinished();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                adFinished.onAdFinished();
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                adFinished.onAdFinished();
            }
        });

        rewardedAd.loadAd();
    }

    public boolean checkRewardAdCompleted(){
        if(isClosed && isCompleted){
            isClosed=false;
            isCompleted=false;
            return true;
        }
        else{
            isClosed=false;
            isCompleted=false;
            return false;
        }
    }
}
