package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks.DuhAdmobAds;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks.DuhMaxAds;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks.DuhMetaAds;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks.DuhUnityAds;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemNetworks.DuhYNDXAds;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.yandex.mobile.ads.banner.BannerAdView;

public class DuhAPIManager {

    public static final String TAG = "DuhAPIManager";
    private AppCompatActivity mActivity;

    DuhMaxAds applovinMaxAds;
    DuhMetaAds duh_meta_ads;
    DuhAdmobAds duh_admob_ads;
    DuhYNDXAds duhYNDX_ads;
    DuhUnityAds duhUnity__ads;

    public DuhAPIManager(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public interface EndInter{
        void onEndInter();
    }

    public void SelectedApiForAds(){
        switch (DuhVariables.DuhInter){
            case "max" :
                applovinMaxAds =new DuhMaxAds(mActivity);
                break;

            case "admob" :
                duh_admob_ads =new DuhAdmobAds(mActivity);
                duh_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                duh_meta_ads =new DuhMetaAds(mActivity);
                break;

            case "yandex" :
                duhYNDX_ads =new DuhYNDXAds(mActivity);
                break;

            case "unity":
                duhUnity__ads =new DuhUnityAds(mActivity);
                break;

            default:
                break;
        }

        if(!DuhVariables.DuhInterStyle.equals("solo")){
            SecondaryAdApiPicker();
        }

        if(DuhVariables.DuhAdStyle.equals("solo")) return;

        switch (DuhVariables.DuhBanner){
            case "max" :
                applovinMaxAds =new DuhMaxAds(mActivity);
                break;

            case "admob" :
                duh_admob_ads =new DuhAdmobAds(mActivity);
                duh_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                duh_meta_ads =new DuhMetaAds(mActivity);
                break;


            case "yandex" :
                duhYNDX_ads =new DuhYNDXAds(mActivity);
                break;

            case "unity":
                duhUnity__ads =new DuhUnityAds(mActivity);
                break;

            default:
                break;
        }

        switch (DuhVariables.DuhNative){
            case "max" :
                applovinMaxAds =new DuhMaxAds(mActivity);
                break;

            case "admob" :
                duh_admob_ads =new DuhAdmobAds(mActivity);
                duh_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                duh_meta_ads =new DuhMetaAds(mActivity);
                break;


            case "yandex" :
                duhYNDX_ads =new DuhYNDXAds(mActivity);
                break;

            case "unity":
                duhUnity__ads =new DuhUnityAds(mActivity);
                break;

            default:
                break;
        }

    }
    public void SecondaryAdApiPicker(){
        switch (DuhVariables.DuhSecondaryInter){
            case "max" :
                applovinMaxAds =new DuhMaxAds(mActivity);
                break;

            case "admob" :
                duh_admob_ads =new DuhAdmobAds(mActivity);
                duh_admob_ads.InterAdsLoader();
                break;

            case "fb" :
                duh_meta_ads =new DuhMetaAds(mActivity);
                break;


            case "yandex" :
                duhYNDX_ads =new DuhYNDXAds(mActivity);
                break;

            case "unity":
                duhUnity__ads =new DuhUnityAds(mActivity);
                break;

            default:
                break;
        }

    }

    public void showBanner(RelativeLayout allBannerContainer, BannerAdView YandexBannerContainer){
        switch (DuhVariables.DuhBanner){
            case "max" :
                applovinMaxAds.showBanner(allBannerContainer);
                break;

            case "admob" :
                duh_admob_ads.ShowingBanner(allBannerContainer);
                break;

            case "fb" :
                duh_meta_ads.ShowingBanner(allBannerContainer);
                break;

            case "yandex" :
                duhYNDX_ads.showBanner(YandexBannerContainer);
                break;


            case "unity" :
                duhUnity__ads.showingBanner(YandexBannerContainer);
                break;


            default:
                break;
        }
    }

    public void showNative(LinearLayout nativeContainer){
        switch (DuhVariables.DuhNative){
            case "max" :
                applovinMaxAds.createNativeAd(nativeContainer);
                break;

            case "admob" :
                duh_admob_ads.NativeAdsShower(nativeContainer);
                break;

            case "fb" :
                duh_meta_ads.loadNativeAd();
                break;


            case "yandex" :
                duhYNDX_ads.showNative(nativeContainer);
                break;

            case "unity" :
                duhUnity__ads.LoadAndSHowNative(nativeContainer);
                break;


            default:
                break;
        }
    }

    public void showSecondaryInter(EndInter endInter){
        switch (DuhVariables.DuhSecondaryInter){
            case "max" :
                indexInterEcpm(endInter);
                break;

            case "admob" :
                duh_admob_ads.InterAdsShower(new DuhAdmobAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "fb" :
                duh_meta_ads.LoadAndShowInter(new DuhMetaAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            case "yandex" :
                duhYNDX_ads.showInter(new DuhYNDXAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "unity" :
                duhUnity__ads.LoadAndShowInter(new DuhUnityAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            default:
                endInter.onEndInter();
                break;
        }

    }

    public void showInter(EndInter endInter){
        if(!DuhVariables.DuhInterStyle.equals("solo")){
            if(Lilly_APP.SimpleWaitingCounter >= DuhVariables.DuhSecondaryInterCounter){
                showSecondaryInter(endInter);
                Lilly_APP.SimpleWaitingCounter =0;
                return;
            }
            Lilly_APP.SimpleWaitingCounter++;
        }

//        Log.d("ridoux_log", "Show inter...");
//        Log.d("ridoux_log", "DuhVariables.InterType: " + DuhVariables.InterType);

        switch (DuhVariables.DuhInter){
            case "max" :
                indexInterEcpm(endInter);
                break;

            case "admob" :
                duh_admob_ads.InterAdsShower(new DuhAdmobAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "fb" :
                duh_meta_ads.LoadAndShowInter(new DuhMetaAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            case "yandex" :
                duhYNDX_ads.showInter(new DuhYNDXAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case "unity" :
                duhUnity__ads.LoadAndShowInter(new DuhUnityAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;


            default:
                endInter.onEndInter();
                break;
        }

    }

    public void indexInterEcpm(EndInter endInter){
//        Log.d("ridoux_log", "DuhVariables.TheInterIndex:" + DuhVariables.TheInterIndex);
        switch (DuhVariables.DuhInterSecondaryCounter){
            case 4 :
                applovinMaxAds.show_four_inter(new DuhMaxAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case 3:

                applovinMaxAds.show_three_inter(new DuhMaxAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            case 2 :

                applovinMaxAds.show_two_inter(new DuhMaxAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;
            case 1 :

                applovinMaxAds.show_one_inter(new DuhMaxAds.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        endInter.onEndInter();
                    }
                });
                break;

            default: endInter.onEndInter(); break;
        }
    }







}
