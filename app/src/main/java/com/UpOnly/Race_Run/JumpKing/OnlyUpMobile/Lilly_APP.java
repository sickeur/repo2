package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile;

import android.app.ActivityManager;
import android.app.Application;
import android.widget.Toast;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemModels.DUHlllGuide;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemUtils.DuhModelHelper;
import com.android.volley.RequestQueue;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Lilly_APP extends Application  {
    public static RequestQueue SimpleQueueRequest;
    public static ArrayList<DUHlllGuide> DUHlllGuides =new ArrayList<>();
    public static Set<String> Allowed_url_strings =new HashSet<>();
    public static int SimpleJsonFinishedChecking =0;
    public static int SimpleWaitingCounter =0;

    public static ArrayList<DuhModelHelper> DuhModelHelpers =new ArrayList<>();

    public static boolean SimpleAdReadyChecker =true;

    public static boolean SimpleIsGoogleHere = false;


    @Override
    public void onCreate() {
        super.onCreate();
        try {

            //UnityAds Has an Error, When The app Has Cleared Data and cache, Banner and Inter works fine
            //After Launching the App, some Data get stored, making UnityAds Not working on its second launch
            //So we need to clear Data Each time the App gets closed or destroyed
//            if(!isAppRunning()){
//                if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
//                    try {
//                        ((ActivityManager)this.getSystemService(ACTIVITY_SERVICE))
//                                .clearApplicationUserData(); // note: it has a return value!
//
//                    }
//                    catch (Exception ex){
//                        Toast.makeText(this,"Error at Activity Service"+ex,Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//            }



//            queue = Volley.newRequestQueue(this);
//            getData();

            String ONESIGNAL_APP_ID = getString(R.string.One_Signal_ID);


            // Enable verbose OneSignal logging to debug issues if needed.
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

            // OneSignal Initialization
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);

            // promptForPushNotifications will show the native Android notification permission prompt.
            // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
            OneSignal.promptForPushNotifications();


        }
        catch (Exception ex){
            Toast.makeText(Lilly_APP.this,"Unexpected Error",Toast.LENGTH_LONG).show();
        }
    }



//    private void getData(){
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, DuhVariables.myJsonUrl, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//
//                            JSONArray jsonGuidesArray=response.getJSONArray("Guide");
//                            JSONArray jsonAppConfigArray=response.getJSONArray("App_Config");
//                            JSONArray jsonGamesConfigArray=response.getJSONArray("Games_Config");
//                            JSONArray jsonAdsConfigArray =response.getJSONArray("Ads_Units");
//                            JSONArray jsonCustomRedirectArray=response.getJSONArray("Redirect");
//                            JSONArray jsonAllowedUrlArray=response.getJSONArray("Host");
//
//
//                            for(int i=0;i<jsonGuidesArray.length();i++){
//                                JSONObject step=jsonGuidesArray.getJSONObject(i);
//                                String title=step.getString("title");
//                                String text=step.getString("text");
//                                String image=step.getString("image");
//                                DUHlllGuide modelForNextArrow =new DUHlllGuide(title,text,image);
//                                modelGuideList.add(modelForNextArrow);
//                            }
//
//
//
//                            JSONObject AppConfig= jsonAppConfigArray.getJSONObject(0);
//
//                            DuhVariables.SelectedAd = AppConfig.getString("Active_Ad");
//                            DuhVariables.AdsStyle = AppConfig.getString("Ads_Style");
//                            DuhVariables.InterStyle = AppConfig.getString("Inter_Style");
//                            DuhVariables.InterCounter = AppConfig.getInt("Inter_Counter");
//                            DuhVariables.Secondary_Inter_Type = AppConfig.getString("Secondary_Inter_Type");
//                            DuhVariables.TheInterIndex = AppConfig.getInt("Inter_Ads_Index");
//                            DuhVariables.SelectMainActivityType = AppConfig.getString("mainActivityType");
//                            DuhVariables.UrlOfThePrivacy = AppConfig.getString("privacy_url");
//                            DuhVariables.UrlForTheStore = AppConfig.getString("pub_store_url");
//                            DuhVariables.SkipTheSteps = AppConfig.getBoolean("skip_steps");
//                            DuhVariables.SkipTheGuide = AppConfig.getBoolean("skip_guide");
//                            DuhVariables.WV_Url = AppConfig.getString("webview_url");
//                            DuhVariables.Duration_Time_For_Ads = AppConfig.getInt("Time");
//                            DuhVariables.Duration_Time_For_Rate = AppConfig.getInt("rating_popup_timer");
//                            DuhVariables.TheRatingDisplayedMessage = AppConfig.getString("rating_popup_Message");
//                            DuhVariables.VideoUrlPlay = AppConfig.getString("main_video_url");
//                            DuhVariables.is_CPA_Offer_Active = AppConfig.getBoolean("is_CPA_Offer_Active");
//                            DuhVariables.CPA_title = AppConfig.getString("CPA_title");
//                            DuhVariables.CPA_Description = AppConfig.getString("CPA_Description");
//                            DuhVariables.CPA_link = AppConfig.getString("CPA_link");
//                            DuhVariables.CPA_Timer = AppConfig.getInt("CPA_Timer");
//                            DuhVariables.is_AdBlocker_Active =AppConfig.getBoolean("is_AdBlocker_Active");
//                            DuhVariables.is_Support_Multiple_Windows_Active =AppConfig.getBoolean("is_Support_Multiple_Windows_Active");
//                            DuhVariables.is_Ad_Background_Active=AppConfig.getBoolean("is_Ad_Background_Active");
//
//
//                            //Play Games Activity
//                            JSONObject gameConfig= jsonGamesConfigArray.getJSONObject(0);
//                            DuhVariables.Activate_Play_Games=gameConfig.getBoolean("Activate_Play_Games");
//                            JSONArray jsonGamesList=gameConfig.getJSONArray("Games");
//
//                            for(int i=0;i<jsonGamesList.length();i++){
//                                JSONObject step=jsonGamesList.getJSONObject(i);
//                                String Game_Name=step.getString("Game_Name");
//                                String Game_Description=step.getString("Game_Description");
//                                String Game_Icon=step.getString("Game_Icon");
//                                String Game_Url=step.getString("Game_Url");
//                                String Game_bg_Img=step.getString("Game_bg_Img");
//                                DuhModelHelper gamesModel=new DuhModelHelper(Game_Name,Game_Description,Game_Icon,Game_bg_Img,Game_Url);
//                                gamerModeModels.add(gamesModel);
//                            }
////                            gameConfig.getString()
//
//                            //Mix Selected Ads
//                            DuhVariables.MixedSelectedAds= Arrays.asList(DuhVariables.SelectedAd.split(";"));
//
//                            if(DuhVariables.AdsStyle.equals("mix")){
//                                DuhVariables.InterType=DuhVariables.MixedSelectedAds.get(0);
//                                DuhVariables.BannerType=DuhVariables.MixedSelectedAds.get(1);
//                                DuhVariables.NativeType=DuhVariables.MixedSelectedAds.get(2);
//                            }
//                            else {
//                                DuhVariables.InterType=DuhVariables.MixedSelectedAds.get(0);
//                                DuhVariables.BannerType=DuhVariables.MixedSelectedAds.get(0);
//                                DuhVariables.NativeType=DuhVariables.MixedSelectedAds.get(0);
//                            }
//
//
//
//                            JSONObject AdsConfig= jsonAdsConfigArray.getJSONObject(0);
//
//                            //max
//                            DuhVariables.MaxFirstIndex = AdsConfig.getString("max_inter_first");
//                            DuhVariables.MaxSecondIndex = AdsConfig.getString("max_inter_second");
//                            DuhVariables.MaxThirdIndex = AdsConfig.getString("max_inter_third");
//                            DuhVariables.MaxFourthIndex = AdsConfig.getString("max_inter_fourth");
//                            DuhVariables.MaxBanner = AdsConfig.getString("max_banner");
//                            DuhVariables.MaxNative = AdsConfig.getString("max_native");
//
//                            //fb
//                            DuhVariables.Fan_inter = AdsConfig.getString("fb_inter");
//                            DuhVariables.Fan_banner = AdsConfig.getString("fb_banner");
//                            DuhVariables.Fan_native = AdsConfig.getString("fb_native");
//
//                            //admob
//                            DuhVariables.admob_inter_id = AdsConfig.getString("admob_inter");
//                            DuhVariables.admob_banner_id = AdsConfig.getString("admob_banner");
//                            DuhVariables.admob_native_id = AdsConfig.getString("admob_native");
//
//                            //yandex
//                            DuhVariables.Yandex_inter = AdsConfig.getString("yandex_inter");
//                            DuhVariables.Yandex_Banner = AdsConfig.getString("yandex_banner");
//
//                            //Unity
//                            DuhVariables.UnityGameId= AdsConfig.getString("UnityGameId");
//                            DuhVariables.unity_banner= AdsConfig.getString("unity_banner");
//                            DuhVariables.unity_inter= AdsConfig.getString("unity_inter");
//
//                            JSONObject redirectModel= jsonCustomRedirectArray.getJSONObject(0);
//
//                            //Custom Redirect Activity
//                            DuhVariables.ImgUrl = redirectModel.getString("img");
//                            DuhVariables.ButtonMessage = redirectModel.getString("text_btn");
//                            DuhVariables.UrlToGoTo = redirectModel.getString("url_to_redirect");
//                            DuhVariables.ShowActivityOfRedirection = redirectModel.getBoolean("showRedirectActivity");
//
////                            for(int i=0;i<jsonAllowedUrlArray.length();i++){
////
////                                JSONObject allowed_url=jsonAllowedUrlArray.getJSONObject(i);
////                                String title=allowed_url.getString("allowed_url");
////                                Allowed_url.add(title);
////
////                            }
////                            JSONArray QuestionsArray=QuestionsObject.getJSONArray("questions");
//                            for (int i = 0; i < jsonAllowedUrlArray.length(); i++) {
//                                String title = jsonAllowedUrlArray.getString(i);
//                                Allowed_url.add(title);
//                            }
//
//                            isJsonDone=1;
//
//                        } catch (JSONException e) {
//                            Toast.makeText(Lilly_APP.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                isJsonDone=2;
//                error.printStackTrace();
//            }
//        });
//
//        request.setShouldCache(false);
//        queue.add(request);
//
//    }

    private boolean isAppRunning() {
        ActivityManager m = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        int n=0;
        while(itr.hasNext()){
            n++;
            itr.next();
        }
        if(n==1){ // App is killed
            return false;
        }

        return true; // App is in background or foreground
    }


}
