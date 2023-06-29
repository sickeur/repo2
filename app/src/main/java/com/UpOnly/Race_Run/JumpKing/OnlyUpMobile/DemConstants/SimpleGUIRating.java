package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class SimpleGUIRating extends Dialog {

    public  float ScoreRate =0;
    private AppCompatActivity DuhmContext;

    Dialog DuhLoadDialog;


    public SimpleGUIRating(@NonNull AppCompatActivity DuhmContext) {
        super(DuhmContext);
        this.DuhmContext = DuhmContext;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_rating);

        final Button DuhrateUs = findViewById(R.id.goToRateUs);
        final Button Duhlater = findViewById(R.id.leave);
        final RatingBar DuhBar= findViewById(R.id.stars);
        final ImageView DuhIV= findViewById(R.id.emoticon);

        //The Loading Dialogue While Waiting For the Ads
        DuhLoadDialog = new Dialog(DuhmContext);
        DuhLoadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DuhLoadDialog.setContentView(R.layout.ads_loading_layout);
        DuhLoadDialog.setCanceledOnTouchOutside(true);


        DuhrateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(ScoreRate >2){
                        dismiss();
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ DuhmContext.getPackageName()));
                        DuhmContext.startActivity(intent);
                    }
                    else{
                        dismiss();
                        Toast.makeText(DuhmContext,"Thanks for your rating!",Toast.LENGTH_SHORT).show();
                    }

                }
                catch (ActivityNotFoundException e){
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id="));
                    DuhmContext.startActivity(intent);
                }
            }
        });

        Duhlater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!DuhVariables.DuhForceRate){
                    dismiss();
                }
            }
        });

        DuhBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating<=1){
                    DuhIV.setImageResource(R.drawable.simple_one_super_star);
                }
                else if(rating<=2){
                    DuhIV.setImageResource(R.drawable.two_super_star);

                }
                else if(rating<=3){
                    DuhIV.setImageResource(R.drawable.three_super_star);

                }
                else if(rating<=4){
                    DuhIV.setImageResource(R.drawable.simple_four_super_star);

                }
                else if(rating<=5){
                    DuhIV.setImageResource(R.drawable.simple_five_super_star);

                }
                //This is making my Emoji Animated
                DuhEmoIcons(DuhIV);

                ScoreRate =rating;
            }
        });

    }

    private void DuhEmoIcons(ImageView imageView){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        imageView.startAnimation(scaleAnimation);
    }

}
