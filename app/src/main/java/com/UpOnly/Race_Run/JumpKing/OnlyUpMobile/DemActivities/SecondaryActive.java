package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.SimpleBlockerChecker;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.SimpleBlockerCheckerClass;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class SecondaryActive extends AppCompatActivity {

    private WebView SimpleWV1;
    private View SimpleViewDecore;
    private Dialog SimpleLoader;
    DuhAPIManager duhAPIManager;
    RICIVER_Net riciver_net;

    public static SharedPreferences DuhSPreferences = null;
    Popup DuhPopup;
    Handler DuhHandler;

    static boolean DuhItsActive =false;

    String DuhURL;

    private Dialog DuhDialongCPA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_active);
        SimpleWV1 =findViewById(R.id.gameLauncher);

        riciver_net =new RICIVER_Net();

        duhAPIManager =new DuhAPIManager(SecondaryActive.this);


        //CPA
        DuhDialongCPA = new Dialog(SecondaryActive.this);
        DuhDialongCPA.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DuhDialongCPA.setContentView(R.layout.cpa_offer_layout);
        DuhDialongCPA.setCanceledOnTouchOutside(false);
        DuhDialongCPA.setCancelable(false);
        final Button DuhEXITBtn = DuhDialongCPA.findViewById(R.id.exit);
        final Button DuhOfferBtn = DuhDialongCPA.findViewById(R.id.offer);
        final TextView Duhcpa_titleBtn = DuhDialongCPA.findViewById(R.id.cpa_title);
        final TextView Duhcpa_desctiptionBtn = DuhDialongCPA.findViewById(R.id.cpa_desctiption);
        Duhcpa_titleBtn.setText(DuhVariables.DuhCPATitle);
        Duhcpa_desctiptionBtn.setText(DuhVariables.DuhCPADescription);
        DuhEXITBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SecondaryActive.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to Exit?")
                        .setIcon(R.drawable.new_icon)
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SecondaryActive.super.onBackPressed();
                            }
                        }).create().show();
            }
        });

        DuhOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(DuhVariables.DuhCPALink));
                SecondaryActive.this.startActivity(intent);
                DuhDialongCPA.dismiss();
            }
        });

        //This will Hide Nav And Status Bar
        SimpleViewDecore =getWindow().getDecorView();
        SimpleViewDecore.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0){
                    SimpleViewDecore.setSystemUiVisibility(HideDuhBars());
                }
            }
        });

        //The Loading Dialogue While Waiting For the Ads
        SimpleLoader = new Dialog(SecondaryActive.this);
        SimpleLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SimpleLoader.setContentView(R.layout.ads_loading_layout);
        SimpleLoader.setCanceledOnTouchOutside(false);
        TextView loading_tv= SimpleLoader.findViewById(R.id.loading_TV);
        loading_tv.setText("Loading Resources");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        SimpleWV1.setVisibility(View.VISIBLE);
        DuhSPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        //Instantiating the Rate us Dialogue
        DuhPopup =new SecondaryActive.Popup(SecondaryActive.this);
        DuhPopup.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        DuhPopup.setCancelable(false);

        if(DuhSPreferences.getBoolean("firstrun", true)){

            DuhHandler =new Handler();
            DuhHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(DuhItsActive){
                        try {
                            DuhPopup.show();
                        }
                        catch (Exception exception){

                        }
                    }
                }
            }, DuhVariables.DuhRatingDuration);
        }


        if(DuhVariables.DuhCPAActive){
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        DuhDialongCPA.show();
                    }
                    catch (Exception ex){

                    }

                }
            }, DuhVariables.DuhCPATimer);

        }

        duhAPIManager.SelectedApiForAds();
        new SimpleBlockerCheckerClass.init(this).initializeWebView(SimpleWV1);
        SimpleBlockerChecker.SimpleAd_Host.addAll(Lilly_APP.Allowed_url_strings);

        SimpleWV1.clearCache(true);
        SimpleWV1.clearHistory();
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            DuhURL =extras.getString("Url");
            SimpleWV1.loadUrl(DuhURL);

        }

        SimpleWV1.setWebViewClient(new DuhHomeBrowser());
        SimpleWV1.setWebChromeClient(new DuhChrome());
        WebSettings webSettings= SimpleWV1.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(false);
        }

        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        if (DuhVariables.DuhMultipleWindows) {
            webSettings.setSupportMultipleWindows(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        }

        //This will allow the app to open ads on another tab

        DuhFunThatSHOWAds();

    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(riciver_net,filter);
        DuhItsActive =true;
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(riciver_net);
        DuhItsActive =false;
        super.onStop();
    }

    public void DuhFunThatSHOWAds(){
        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DuhFuncThatShowTheChosenInter();
                }
            }, DuhVariables.DuhTimerInPrimeActive);
        }
        catch (Exception exception){

        }

    }

    public void DuhFuncThatShowTheChosenInter(){
        try {
            SimpleLoader.show();
            SimpleWV1.onPause();
            duhAPIManager.showInter(new DuhAPIManager.EndInter() {
                @Override
                public void onEndInter() {
                    SimpleLoader.dismiss();
                    SimpleWV1.onResume();
                    DuhFunThatSHOWAds();
                }
            });
        }
        catch (Exception ex){

        }
    }

    public int HideDuhBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            SimpleViewDecore.setSystemUiVisibility(HideDuhBars());
        }
    }

    private class DuhHomeBrowser extends WebViewClient {

        DuhHomeBrowser() {}

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(DuhVariables.DuhAdBlocker){
                return !SimpleBlockerCheckerClass.blockAds(view,url) ? SimpleBlockerChecker.SimpleEmptyResourcesCreator() :
                        super.shouldInterceptRequest(view, url);
            }

            return super.shouldInterceptRequest(view, url);
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }



        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }


    private class DuhChrome extends WebChromeClient {
        private View DuhCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        DuhChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (DuhCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.DuhCustomView);
            this.DuhCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.DuhCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.DuhCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.DuhCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SimpleWV1.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SimpleWV1.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {

            if(SimpleWV1.canGoBack()){
                SimpleWV1.goBack();

            }
            else {
                SimpleWV1.destroy();
                super.onBackPressed();
            }

    }

    public class Popup extends Dialog {

        public  float userRate=0;
        private AppCompatActivity mActivity;

        public Popup(@NonNull AppCompatActivity context) {
            super(context);
            this.mActivity =context;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.simple_rating);

            final Button button_rateUs = findViewById(R.id.goToRateUs);
            final Button button_later = findViewById(R.id.leave);
            final RatingBar ratingBar= findViewById(R.id.stars);
            final ImageView imageView= findViewById(R.id.emoticon);
            final TextView message=findViewById(R.id.textMessage);
            message.setText(DuhVariables.DuhDialogRate);

            button_rateUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(userRate>2){
                            dismiss();
                            SecondaryActive.DuhSPreferences.edit().putBoolean("firstrun", false).commit();
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ mActivity.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                        else{
                            SecondaryActive.DuhSPreferences.edit().putBoolean("firstrun", true).commit();
                            dismiss();
                            Toast.makeText(mActivity,"Thanks for your simpleGUIRating!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(mActivity,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

            button_later.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!DuhVariables.DuhForceRate){
                        dismiss();
                    }
                }
            });

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(rating<=1){
                        imageView.setImageResource(R.drawable.simple_one_super_star);
                    }
                    else if(rating<=2){
                        imageView.setImageResource(R.drawable.two_super_star);

                    }
                    else if(rating<=3){
                        imageView.setImageResource(R.drawable.three_super_star);

                    }
                    else if(rating<=4){
                        imageView.setImageResource(R.drawable.simple_four_super_star);

                    }
                    else if(rating<=5){
                        imageView.setImageResource(R.drawable.simple_five_super_star);

                    }
                    //This is making my Emoji Animated
                    DuhImageAnimated(imageView);

                    userRate=rating;
                }
            });


        }


        private void DuhImageAnimated(ImageView imageView){
            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(200);
            imageView.startAnimation(scaleAnimation);
        }
    }

}