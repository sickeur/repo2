package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemAPI.DuhAPIManager;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.StickJoyView;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.SimpleBlockerChecker;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.SimpleBlockerCheckerClass;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;

public class PrimeActive extends AppCompatActivity {

    VideoView simple_VV;
    private View simple_ZwakView;
    private Dialog simple_AdsDialog;
    private WebView simple_WebS;
    DuhAPIManager duhAPIManager;
    public static SharedPreferences sharedP = null;
    simple_PopRateUpIng maybeItsTheSimplePopRateUpIng;
    Handler aGodDamnHandler;
    static boolean ActiveChecker =false;

    RICIVER_Net riciver_net;

    private Dialog simple_Dia_CPA;

    ImageButton simple_run;
    ImageButton simple_jump;
    ImageButton simple_Settings;

    private StickJoyView stickJoyView;




    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prime_active);

        simple_jump =findViewById(R.id.jumpBtn);
        simple_run =findViewById(R.id.runBtn);
        simple_Settings =findViewById(R.id.settings);
        stickJoyView = (StickJoyView) findViewById(R.id.joystickView);


        riciver_net =new RICIVER_Net();

        simple_VV =findViewById(R.id.gameVideo);
        simple_WebS =findViewById(R.id.simpleWView);
        duhAPIManager =new DuhAPIManager(PrimeActive.this);

        //This will Hide Nav And Status Bar
        simple_ZwakView =getWindow().getDecorView();
        simple_ZwakView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0){
                    simple_ZwakView.setSystemUiVisibility(simple_Hide_The_System_Bars());
                }
            }
        });

        //The Loading Dialogue While Waiting For the Ads
        simple_AdsDialog = new Dialog(PrimeActive.this);
        simple_AdsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        simple_AdsDialog.setContentView(R.layout.ads_loading_layout);
        simple_AdsDialog.setCanceledOnTouchOutside(false);
        TextView simple_loading_tv= simple_AdsDialog.findViewById(R.id.loading_TV);
        simple_loading_tv.setText("Loading Resources");

        //CPA
        simple_Dia_CPA = new Dialog(PrimeActive.this);
        simple_Dia_CPA.requestWindowFeature(Window.FEATURE_NO_TITLE);
        simple_Dia_CPA.setContentView(R.layout.cpa_offer_layout);
        simple_Dia_CPA.setCanceledOnTouchOutside(false);
        simple_Dia_CPA.setCancelable(false);
        final Button simple_EXIT = simple_Dia_CPA.findViewById(R.id.exit);
        final Button simple_Offer = simple_Dia_CPA.findViewById(R.id.offer);
        final TextView simple_cpa_title = simple_Dia_CPA.findViewById(R.id.cpa_title);
        final TextView simple_cpa_desctiption = simple_Dia_CPA.findViewById(R.id.cpa_desctiption);
        simple_cpa_title.setText(DuhVariables.DuhCPATitle);
        simple_cpa_desctiption.setText(DuhVariables.DuhCPADescription);
        simple_EXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PrimeActive.this)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to Exit?")
                        .setIcon(R.drawable.new_icon)
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.no,null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PrimeActive.super.onBackPressed();
                            }
                        }).create().show();
            }
        });

        simple_Offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(DuhVariables.DuhCPALink));
                PrimeActive.this.startActivity(intent);
                simple_Dia_CPA.dismiss();
            }
        });




        if(DuhVariables.DuhTypeOfPrimeActivity.equals("video")) {
            simple_AdsDialog.show();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        simple_AdsDialog.dismiss();
                        simple_VV.start();
                    }
                    catch (Exception ex){

                    }

                }
            },5000);
        }



        if(DuhVariables.DuhTypeOfPrimeActivity.equals("video")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            simple_VV.setVisibility(View.VISIBLE);
            simple_VV.setVideoURI(Uri.parse(DuhVariables.DuhVideoURL));
            simple_VV.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    if(DuhVariables.DuhCPAActive){
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    simple_Dia_CPA.show();
                                }
                                catch (Exception ex){

                                }

                            }
                        }, DuhVariables.DuhCPATimer);

                    }
                    else {
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PrimeActive.this, R.string.ToastFailMessage,Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(PrimeActive.this, Simple_Lowel_Activity.class);
                                startActivity(intent);
                                finish();
                            }
                        },2500);
                    }



                }
            });
        }
        else if(DuhVariables.DuhTypeOfPrimeActivity.equals("webview")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            simple_WebS.setVisibility(View.VISIBLE);
            sharedP = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            //Instantiating the Rate us Dialogue
            maybeItsTheSimplePopRateUpIng =new simple_PopRateUpIng(PrimeActive.this);
            maybeItsTheSimplePopRateUpIng.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            maybeItsTheSimplePopRateUpIng.setCancelable(false);

            if(sharedP.getBoolean("firstrun", true)){

                    aGodDamnHandler =new Handler();
                    aGodDamnHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(ActiveChecker){
                                try {
                                maybeItsTheSimplePopRateUpIng.show();
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
                            simple_Dia_CPA.show();
                        }
                        catch (Exception ex){

                        }

                    }
                }, DuhVariables.DuhCPATimer);

            }

            if(DuhVariables.DuhJoystick){
                simple_jump.setVisibility(View.VISIBLE);
                simple_run.setVisibility(View.VISIBLE);
                simple_Settings.setVisibility(View.VISIBLE);
                stickJoyView.setVisibility(View.VISIBLE);
            }



            simple_jump.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            simple_WebS.evaluateJavascript("simulateKey(90,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            simple_WebS.evaluateJavascript("simulateKey(90,\"up\");",null);
                            break;
                    }
                    return false;
                }
            });

            simple_run.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            simple_WebS.evaluateJavascript("simulateKey(88,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            simple_WebS.evaluateJavascript("simulateKey(88,\"up\");",null);
                            break;
                    }
                    return false;
                }
            });



            simple_Settings.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    int eventaction = event.getAction();

                    switch (eventaction) {
                        case MotionEvent.ACTION_DOWN:
                            simple_WebS.evaluateJavascript("simulateKey(13,\"down\");",null);

                            return true;

                        case MotionEvent.ACTION_UP:
                            simple_WebS.evaluateJavascript("simulateKey(13,\"up\");",null);
                            break;
                    }
                    return false;

                }
            });

            //Event listener that always returns the variation of the angle in degrees, motion power in percentage and direction of movement
            stickJoyView.StickJoyMoveListener(new StickJoyView.OnJoystickMoveListener() {

                @Override
                public void onValueChanged(int angle, int power, int direction) {
                    simple_down_keyup();
                    simple_up_keyup();
                    simple_left_keyup();
                    simple_right_keyup();
                    // TODO Auto-generated method stub
                    switch (direction) {
                        case StickJoyView.FRONT:
                            simple_up_keydown();
                            break;
                        case StickJoyView.FRONT_RIGHT:
                            simple_up_keydown();
                            simple_left_keydown();
                            break;
                        case StickJoyView.RIGHT:
                            simple_left_keydown();
                            break;
                        case StickJoyView.RIGHT_BOTTOM:
                            simple_left_keydown();
                            simple_down_keydown();
                            break;
                        case StickJoyView.BOTTOM:
                            simple_down_keydown();
                            break;
                        case StickJoyView.BOTTOM_LEFT:
                            simple_down_keydown();
                            simple_right_keydown();
                            break;
                        case StickJoyView.LEFT:
                            simple_right_keydown();
                            break;
                        case StickJoyView.LEFT_FRONT:
                            simple_up_keydown();
                            simple_right_keydown();
                            break;
                        default:
                    }
                }
            }, StickJoyView.DEFAULT_LOOP_INTERVAL);


            duhAPIManager.SelectedApiForAds();
            new SimpleBlockerCheckerClass.init(this).initializeWebView(simple_WebS);
            SimpleBlockerChecker.SimpleAd_Host.addAll(Lilly_APP.Allowed_url_strings);

            simple_WebS.clearCache(true);
            simple_WebS.clearHistory();
            if(Lilly_APP.SimpleIsGoogleHere){
                simple_WebS.loadUrl(DuhVariables.DuhFakeSiteURL);
            }else {
                simple_WebS.loadUrl(DuhVariables.DuhSiteURL);
            }

            simple_WebS.setWebViewClient(new Simple_Browser_house());
            simple_WebS.setWebChromeClient(new SimpleMyWebChromeClient());
            WebSettings webSettings= simple_WebS.getSettings();
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

            simple_showAds();

        }


    }



    public void simple_up_keydown(){
        simple_WebS.evaluateJavascript("simulateKey(38,\"down\");",null);

    }
    public void simple_down_keydown(){
        simple_WebS.evaluateJavascript("simulateKey(40,\"down\");",null);


    }
    public void simple_right_keydown(){
        simple_WebS.evaluateJavascript("simulateKey(39,\"down\");",null);

    }
    public void simple_left_keydown(){
        simple_WebS.evaluateJavascript("simulateKey(37,\"down\");",null);

    }

    public void simple_up_keyup(){
        simple_WebS.evaluateJavascript("simulateKey(38,\"up\");",null);

    }
    public void simple_down_keyup(){
        simple_WebS.evaluateJavascript("simulateKey(40,\"up\");",null);


    }
    public void simple_left_keyup(){
        simple_WebS.evaluateJavascript("simulateKey(37,\"up\");",null);

    }
    public void simple_right_keyup(){
        simple_WebS.evaluateJavascript("simulateKey(39,\"up\");",null);

    }





    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            simple_ZwakView.setSystemUiVisibility(simple_Hide_The_System_Bars());
        }
    }
    public int simple_Hide_The_System_Bars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(riciver_net,filter);
        ActiveChecker =true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(riciver_net);
        ActiveChecker =false;
        super.onStop();
    }

    public void simple_showAds(){
        try {
            Handler adsHandler=new Handler();
            adsHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    simple_SelectInterstitial();
                }
            }, DuhVariables.DuhTimerInPrimeActive);
        }
        catch (Exception exception){

        }

    }

    public void simple_SelectInterstitial(){
        try {
            simple_AdsDialog.show();
            simple_WebS.onPause();
            duhAPIManager.showInter(new DuhAPIManager.EndInter() {
                @Override
                public void onEndInter() {
                    simple_AdsDialog.dismiss();
                    simple_WebS.onResume();
                    simple_showAds();
                }
            });
        }
        catch (Exception ex){

        }
    }
    private class Simple_Browser_house extends WebViewClient {

        Simple_Browser_house() {}

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if(DuhVariables.DuhAdBlocker){
                if(DuhVariables.DuhEvaluateJS){
                    try {
                        // Define the JavaScript code to modify the src attribute
                        String javascriptCode = DuhVariables.DuhJSFunc;

                        // Execute the JavaScript code using evaluateJavascript
                        simple_WebS.evaluateJavascript(javascriptCode, null);
                    }
                    catch (Exception ex){

                    }
                }
                return !SimpleBlockerCheckerClass.blockAds(view,url) ? SimpleBlockerChecker.SimpleEmptyResourcesCreator() :
                        super.shouldInterceptRequest(view, url);
            }






            return super.shouldInterceptRequest(view, url);
        }



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if(DuhVariables.DuhEvaluateJS){
                try {
                    // Define the JavaScript code to modify the src attribute
                    String javascriptCode = DuhVariables.DuhJSFunc;

                    // Execute the JavaScript code using evaluateJavascript
                    simple_WebS.evaluateJavascript(javascriptCode, null);
                }
                catch (Exception ex){

                }
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(DuhVariables.DuhEvaluateJS){
                try {
                    // Define the JavaScript code to modify the src attribute
                    String javascriptCode = DuhVariables.DuhJSFunc;

                    // Execute the JavaScript code using evaluateJavascript
                    simple_WebS.evaluateJavascript(javascriptCode, null);
                }
                catch (Exception ex){

                }
            }

            super.onPageFinished(view, url);

        }



        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }


    private class SimpleMyWebChromeClient extends WebChromeClient {
        private View myCustomedView;
        private WebChromeClient.CustomViewCallback myCustomViewCB;
        protected FrameLayout mFullscreenContainer;
        private int myOriginalOrientation;
        private int myOriginalSystemUIVisibility;


        SimpleMyWebChromeClient() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (myCustomedView == null) {
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
            ((FrameLayout)getWindow().getDecorView()).removeView(this.myCustomedView);
            this.myCustomedView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.myOriginalSystemUIVisibility);
            setRequestedOrientation(this.myOriginalOrientation);
            this.myCustomViewCB.onCustomViewHidden();
            this.myCustomViewCB = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.myCustomedView != null)
            {
                onHideCustomView();
                return;
            }
            this.myCustomedView = paramView;
            this.myOriginalSystemUIVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.myOriginalOrientation = getRequestedOrientation();
            this.myCustomViewCB = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.myCustomedView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        simple_WebS.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        simple_WebS.restoreState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if(DuhVariables.DuhTypeOfPrimeActivity.equals("webview")){
            if(simple_WebS.canGoBack()){
                simple_WebS.goBack();

            }
            else {
                simple_WebS.destroy();
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }

    }

    public class simple_PopRateUpIng extends Dialog {

        public  float userRate=0;
        private AppCompatActivity mActivity;

        public simple_PopRateUpIng(@NonNull AppCompatActivity context) {
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
                            PrimeActive.sharedP.edit().putBoolean("firstrun", false).commit();
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ mActivity.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                        else{
                            PrimeActive.sharedP.edit().putBoolean("firstrun", true).commit();
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
                    SimpleImageAnimating(imageView);

                    userRate=rating;
                }
            });


        }


        private void SimpleImageAnimating(ImageView imageView){
            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(200);
            imageView.startAnimation(scaleAnimation);
        }
    }


}