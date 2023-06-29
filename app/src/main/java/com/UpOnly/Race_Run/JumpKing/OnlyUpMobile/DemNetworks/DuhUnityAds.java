package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class DuhUnityAds {


    private MaxAd DuhnativeAd;

    public static  BannerView DuhbannerView;

    public interface AdFinished{
        void onAdFinished();
    }
    private final AppCompatActivity DuhmActivity;

    public DuhUnityAds(AppCompatActivity activity){
        this.DuhmActivity =activity;
    }

    public void showingBanner(RelativeLayout adContainer) {

        DuhbannerView = new BannerView(DuhmActivity, DuhVariables.unityBanner, new UnityBannerSize(320, 50));
        DuhbannerView.setListener(new BannerView.Listener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                adContainer.setVisibility(View.VISIBLE);
                adContainer.addView(bannerAdView);
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                super.onBannerFailedToLoad(bannerAdView, errorInfo);
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {
                super.onBannerClick(bannerAdView);
            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {
                super.onBannerLeftApplication(bannerAdView);
            }
        });
        DuhbannerView.load();

    }

    public void LoadAndShowInter(AdFinished adFinished) {

        IUnityAdsLoadListener adsLoadListener = new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAds.show(DuhmActivity, DuhVariables.unityInter, new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                        adFinished.onAdFinished();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                        adFinished.onAdFinished();
                    }
                } );
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                adFinished.onAdFinished();
            }
        };


        UnityAds.load(DuhVariables.unityInter, adsLoadListener);

    }

    public void LoadAndSHowNative(LinearLayout nativeAdContainer){

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


}
