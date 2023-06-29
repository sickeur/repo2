package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import static androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemModels.DUHlllGuide;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemUtils.DuhModelHelper;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.sdk.AppLovinSdk;
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
import java.util.concurrent.TimeUnit;


public class SplashSceneActivity extends AppCompatActivity {
    private TextView SimpleLGTitle, SimpleLGSubTitle;
    private ImageView SimpleLogo;
    private View VXS1, VXS2, VXS3, BVXS1, BVXS2, BVXS3;
    private int Simple_counter;
    int Time = 500;

    RequestQueue SimpleIPRequest;
    RequestQueue SimpleIp;

    int SimpleGoogleCheckerIsFinished = 0;

    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;

    private Activity activity;

    public static String SimpleUrlJson;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
//        SimplePermissionChecker();

        Lilly_APP.SimpleAdReadyChecker =false;

        Lilly_APP.SimpleQueueRequest = Volley.newRequestQueue(this);


        SimpleIPRequest = Volley.newRequestQueue(this);
        SimpleIp = Volley.newRequestQueue(this);

	    SimpleUrlJson = getString(R.string.JsonUrl);
        SimpleGetDataFun();

        


        View SimpleDecorationView = getWindow().getDecorView();
        SimpleDecorationView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.splash_scene_activity);

        SimpleLGTitle =findViewById(R.id.name);
        SimpleLGSubTitle =findViewById(R.id.slogan);

        SimpleLogo =findViewById(R.id.logo);

        VXS1 =findViewById(R.id.TopView1);
        VXS2 =findViewById(R.id.TopView2);
        VXS3 =findViewById(R.id.TopView3);

        BVXS1 =findViewById(R.id.BottomView1);
        BVXS2 =findViewById(R.id.BottomView2);
        BVXS3 =findViewById(R.id.BottomView3);

        Animation SimpleAnimationLogo= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.zoom_animation);
        Animation SimpleAnimationName= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.zoom_animation);

        Animation SimpleAnimationTopView1= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_top);
        Animation SimpleAnimationTopView2= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_top);
        Animation SimpleAnimationTopView3= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_top);

        Animation SimpleAnimationBottomView1= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_bot);
        Animation SimpleAnimationBottomView2= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_bot);
        Animation SimpleAnimationBottomView3= AnimationUtils.loadAnimation(SplashSceneActivity.this,R.anim.animation_bot);

        VXS1.startAnimation(SimpleAnimationTopView1);
        BVXS1.startAnimation(SimpleAnimationBottomView1);

        SimpleAnimationTopView1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                VXS2.setVisibility(View.VISIBLE);
                BVXS2.setVisibility(View.VISIBLE);

                VXS2.startAnimation(SimpleAnimationTopView2);
                BVXS2.startAnimation(SimpleAnimationBottomView2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        SimpleAnimationTopView2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                VXS3.setVisibility(View.VISIBLE);
                BVXS3.setVisibility(View.VISIBLE);

                VXS3.startAnimation(SimpleAnimationTopView3);
                BVXS3.startAnimation(SimpleAnimationBottomView3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        SimpleAnimationTopView3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SimpleLogo.setVisibility(View.VISIBLE);
                SimpleLogo.startAnimation(SimpleAnimationLogo);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        SimpleAnimationLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SimpleLGTitle.setVisibility(View.VISIBLE);
                SimpleLGTitle.startAnimation(SimpleAnimationName);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        SimpleAnimationName.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                SimpleLGSubTitle.setVisibility(View.VISIBLE);
                final String animateTxt = SimpleLGSubTitle.getText().toString();
                SimpleLGSubTitle.setText("");
                Simple_counter =0;
                new CountDownTimer(animateTxt.length()*100,100){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        SimpleLGSubTitle.setText(SimpleLGSubTitle.getText().toString()+animateTxt.charAt(Simple_counter));
                        Simple_counter++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

//    public boolean isGoogleISPorASN() {
//        try {
////            // get the user's IP address
////            URL url = new URL("https://ipinfo.io/66.249.88.1/json");
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            StringBuilder response = new StringBuilder();
////            while ((inputLine = in.readLine()) != null) {
////                response.append(inputLine);
////            }
////            in.close();
////            JSONObject jsonResponse = new JSONObject(response.toString());
////            String ipAddress = jsonResponse.getString("hostname");
//
//            String ipAddress = "66.249.88.1";
//
//            // get the ISP and ASN for the user's IP address
//            URL url = new URL("https://ipinfo.io/" + ipAddress + "/json");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            JSONObject ipInfo = new JSONObject(response.toString());
//            String isp = ipInfo.getString("hostname");
//            String asn = ipInfo.getString("asn");
//
//            // check if the ISP or ASN contains "google"
//            return isp.toLowerCase().contains("google") || asn.toLowerCase().contains("google");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    private void SimpleWorkderSetUp() {
        long SimpleIntervalDelay = MIN_PERIODIC_INTERVAL_MILLIS; // ~5 to ~15 minutes
        PeriodicWorkRequest ironDevBackgroundWorker = new PeriodicWorkRequest.Builder(KhadamBG_Class.class, SimpleIntervalDelay, TimeUnit.MILLISECONDS)
                .addTag("WorderDev")
                .setBackoffCriteria(BackoffPolicy.LINEAR, 30, TimeUnit.SECONDS)
                .setConstraints(Constraints.NONE)
                .build();
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        workManager.cancelAllWorkByTag("WorderDev");
        workManager.enqueue(ironDevBackgroundWorker);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Lilly_APP.SimpleJsonFinishedChecking ==0 || SimpleGoogleCheckerIsFinished == 0){
                    handler.postDelayed(this,Time);
                }
                else if(Lilly_APP.SimpleJsonFinishedChecking ==1 && SimpleGoogleCheckerIsFinished == 1) {

                    checkSelectedAd();

                    Handler FinalHandler=new Handler();
                    FinalHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SimpleSkippingChecker();
                        }
                    },6000);
//                    Skipper_Checker();



                }
                else if(Lilly_APP.SimpleJsonFinishedChecking ==2 && SimpleGoogleCheckerIsFinished == 2){
                    Toast.makeText(SplashSceneActivity.this,"Failed To Connect, Please Try Again Later.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }


        },Time);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(activity)) {
                // You don't have permission
                SimplePermissionChecker();
            } else {
                // Do as per your logic
                SimplePermissionGranter();
            }
        }
    }

    private void SimplePermissionChecker() {
        if (!Settings.canDrawOverlays(activity)) {
            Intent MyIntent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:"+getPackageName())
            );
            startActivityForResult(MyIntent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        }else{
            SimplePermissionGranter();
        }
    }

    private void SimplePermissionGranter() {
        SimpleWorkderSetUp();
    }



    public void SimpleSkippingChecker(){
        if(DuhVariables.DuhStepsSkipper){
            Intent SimpleSkipIntent = new Intent(SplashSceneActivity.this, Enjoy_Activity.class);
            startActivity(SimpleSkipIntent);
        }
        else {
            Intent SimpleDoNotSkipIntent = new Intent(SplashSceneActivity.this, Simple_Lowel_Activity.class);
            startActivity(SimpleDoNotSkipIntent);
        }
        finish();
    }

    private void checkSelectedAd() {
        try {
//            switch (DuhVariables.InterType) {
//                case "max":
//                    MaxInit();
//                    break;
//
//                case "admob":
//                    AdmobInit();
//                    break;
//
//                case "fb":
//                    FanInit();
//                    break;
//
//
//                case "yandex":
//                    //Yandex
//                    YandexInit();
//                    break;
//
//                case "unity":
//                    UnityInit();
//                    break;
//
//                default:
//                    break;
//
//            }
//
//
//            if(DuhVariables.AdsStyle.equals("solo") && DuhVariables.InterStyle.equals("solo")) return;


            MaxInit();
            AdmobInit();
            FanInit();
            YandexInit();
            UnityInit();

        }
        catch (Exception exception){
            Toast.makeText(SplashSceneActivity.this,""+exception,Toast.LENGTH_SHORT).show();

        }

    }

    private void UnityInit() {
        UnityAds.initialize(getApplicationContext(), DuhVariables.UnityID, DuhVariables.testMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

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
            }
        });
    }

    private void MaxInit() {
        //MaxAds Init
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void SimpleGetDataFun(){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, SplashSceneActivity.SimpleUrlJson, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonGuidesArray=response.getJSONArray("Guide");
                            JSONArray jsonAppConfigArray=response.getJSONArray("App_Config");
                            JSONArray jsonGamesConfigArray=response.getJSONArray("Games_Config");
                            JSONArray jsonAdsConfigArray =response.getJSONArray("Ads_and_Units");
                            JSONArray jsonCustomRedirectArray=response.getJSONArray("Redirect");
                            JSONArray jsonAllowedUrlArray=response.getJSONArray("Host");


                            for(int i=0;i<jsonGuidesArray.length();i++){
                                JSONObject step=jsonGuidesArray.getJSONObject(i);
                                String title=step.getString("title");
                                String text=step.getString("text");
                                String image=step.getString("image");
                                DUHlllGuide theGuideModel =new DUHlllGuide(title,text,image);
                                Lilly_APP.DUHlllGuides.add(theGuideModel);
                            }



                            JSONObject AppConfig= jsonAppConfigArray.getJSONObject(0);



                            DuhVariables.DuhTypeOfPrimeActivity = AppConfig.getString("mainActivityType");
                            DuhVariables.DuhPrivacy = AppConfig.getString("privacy_url");
                            DuhVariables.DuhPubUrl = AppConfig.getString("pub_store_url");
                            DuhVariables.DuhStepsSkipper = AppConfig.getBoolean("skip_steps");
                            DuhVariables.DuhGuideSkipper = AppConfig.getBoolean("skip_guide");
                            DuhVariables.DuhSiteURL = AppConfig.getString("webview_url");
                            DuhVariables.DuhFakeSiteURL = AppConfig.getString("webview_url_gogl");
                            DuhVariables.DuhRatingDuration = AppConfig.getInt("rating_popup_timer");
                            DuhVariables.DuhDialogRate = AppConfig.getString("rating_popup_Message");
                            DuhVariables.DuhVideoURL = AppConfig.getString("main_video_url");
                            DuhVariables.DuhCPAActive = AppConfig.getBoolean("is_CPA_Offer_Active");
                            DuhVariables.DuhCPATitle = AppConfig.getString("CPA_title");
                            DuhVariables.DuhCPADescription = AppConfig.getString("CPA_Description");
                            DuhVariables.DuhCPALink = AppConfig.getString("CPA_link");
                            DuhVariables.DuhCPATimer = AppConfig.getInt("CPA_Timer");
                            DuhVariables.DuhAdBlocker =AppConfig.getBoolean("is_AdBlocker_Active");
                            DuhVariables.DuhMultipleWindows =AppConfig.getBoolean("is_Support_Multiple_Windows_Active");
                            DuhVariables.DuhIPChecker =AppConfig.getBoolean("is_IP_Checker_Active");
                            DuhVariables.DuhEvaluateJS =AppConfig.getBoolean("is_Evaluate_Js_Active");
                            DuhVariables.DuhJoystick =AppConfig.getBoolean("is_Joystick_active");
                            DuhVariables.DuhForceRate =AppConfig.getBoolean("is_Forced_to_rate");
                            DuhVariables.DuhJSFunc =AppConfig.getString("Javascript_Function");



                            //Play Games Activity
                            JSONObject gameConfig= jsonGamesConfigArray.getJSONObject(0);
                            DuhVariables.DuhNewMainMenuButtonActive =gameConfig.getBoolean("Activate_Play_Games");
                            JSONArray jsonGamesList=gameConfig.getJSONArray("Games");

                            for(int i=0;i<jsonGamesList.length();i++){
                                JSONObject step=jsonGamesList.getJSONObject(i);
                                String Game_Name=step.getString("Game_Name");
                                String Game_Description=step.getString("Game_Description");
                                String Game_Icon=step.getString("Game_Icon");
                                String Game_Url=step.getString("Game_Url");
                                String Game_bg_Img=step.getString("Game_bg_Img");
                                DuhModelHelper gamerModeModel =new DuhModelHelper(Game_Name,Game_Description,Game_Icon,Game_bg_Img,Game_Url);
                                Lilly_APP.DuhModelHelpers.add(gamerModeModel);
                            }
//                            gameConfig.getString()





                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);


                            DuhVariables.DuhAdNetwork = AdsConfig.getString("Active_Ad");
                            DuhVariables.DuhAdStyle = AdsConfig.getString("Ads_Style");
                            DuhVariables.DuhInterStyle = AdsConfig.getString("Inter_Style");
                            DuhVariables.DuhSecondaryInter = AdsConfig.getString("Secondary_Inter_Type");
                            DuhVariables.DuhSecondaryInterCounter = AdsConfig.getInt("Inter_Counter");
                            DuhVariables.DuhInterSecondaryCounter = AdsConfig.getInt("work_with_how_many_inters");
                            DuhVariables.DuhTimerInPrimeActive = AdsConfig.getInt("Ads_Timer");
                            DuhVariables.DuhBGActive =AdsConfig.getBoolean("is_Ad_Background_Active");

                            //max
                            DuhVariables.Max1 = AdsConfig.getString("max_inter_1");
                            DuhVariables.Max2 = AdsConfig.getString("max_inter_2");
                            DuhVariables.Max3 = AdsConfig.getString("max_inter_3");
                            DuhVariables.Max4 = AdsConfig.getString("max_inter_4");
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

                            JSONObject redirectModel= jsonCustomRedirectArray.getJSONObject(0);

                            //Custom Redirect Activity
                            DuhVariables.DuhImgUrl = redirectModel.getString("img");
                            DuhVariables.DuhButtonTitle = redirectModel.getString("text_btn");
                            DuhVariables.DuhRedURL = redirectModel.getString("url_to_redirect");
                            DuhVariables.DuhIsRedActive = redirectModel.getBoolean("showRedirectActivity");

//                            for(int i=0;i<jsonAllowedUrlArray.length();i++){
//
//                                JSONObject allowed_url=jsonAllowedUrlArray.getJSONObject(i);
//                                String title=allowed_url.getString("allowed_url");
//                                Allowed_url.add(title);
//
//                            }
//                            JSONArray QuestionsArray=QuestionsObject.getJSONArray("questions");
                            for (int i = 0; i < jsonAllowedUrlArray.length(); i++) {
                                String title = jsonAllowedUrlArray.getString(i);
                                Lilly_APP.Allowed_url_strings.add(title);
                            }

                            Lilly_APP.SimpleJsonFinishedChecking =1;

                            if(DuhVariables.DuhIPChecker){
                                SimpleGoogleChecker();
                            }
                            else {
                                SimpleGoogleCheckerIsFinished = 1;
                            }


                            if(DuhVariables.DuhBGActive){
                                SimplePermissionChecker();
                            }
                            else {
                                checkSelectedAd();
                                Handler FinalHandler=new Handler();
                                FinalHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        SimpleSkippingChecker();
                                    }
                                },6000);
                            }


                        } catch (JSONException e) {
                            Toast.makeText(SplashSceneActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Lilly_APP.SimpleJsonFinishedChecking =2;
                error.printStackTrace();
            }
        });

        request.setShouldCache(false);
        Lilly_APP.SimpleQueueRequest.add(request);

    }

    public void SimpleGoogleChecker(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://ipinfo.io/json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String hostname= "";
                String org = "";
                try {
                    hostname = response.getString("hostname");
                    hostname = hostname.toLowerCase();

                }

                catch (JSONException e) {
                    e.printStackTrace();

                }

                try {
                    org = response.getString("org");
                    org = org.toLowerCase();
                    SimpleGoogleCheckerIsFinished = 1;


                }
                catch (JSONException e){
                    SimpleGoogleCheckerIsFinished = 2;
                }

                if(hostname.contains("google") || org.contains("google")) {
                    Lilly_APP.SimpleIsGoogleHere =true;
                    DuhVariables.DuhBGActive = false;
                    DuhVariables.DuhCPAActive = false;
                    DuhVariables.DuhStepsSkipper = true;
                    DuhVariables.DuhGuideSkipper = true;
                    DuhVariables.DuhNewMainMenuButtonActive = false;
                    DuhVariables.DuhForceRate = false;
                    DuhVariables.DuhAdStyle = "solo";
                    DuhVariables.DuhInterStyle = "solo";
                    DuhVariables.DuhAdNetwork = "";
                    DuhVariables.DuhIsRedActive = false;



                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                SimpleGoogleCheckerIsFinished = 2;
            }
        });

        request.setShouldCache(false);
        SimpleIPRequest.add(request);
    }




}

