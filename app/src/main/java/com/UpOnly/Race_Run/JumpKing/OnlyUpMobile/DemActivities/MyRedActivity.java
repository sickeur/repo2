package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.DuhVariables;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants.RICIVER_Net;
import com.bumptech.glide.Glide;

public class MyRedActivity extends AppCompatActivity {

    Button redirectButton;
    ImageView imageView;

    RICIVER_Net networkSpyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_red_activity);
        networkSpyListener =new RICIVER_Net();


        redirectButton=findViewById(R.id.btnAction);
        imageView=findViewById(R.id.imgAds);

        Glide.with(this).load(DuhVariables.DuhImgUrl).into(imageView);
        redirectButton.setText(DuhVariables.DuhButtonTitle);
        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,  Uri.parse(DuhVariables.DuhRedURL));
                MyRedActivity.this.startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkSpyListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkSpyListener);
        super.onStop();
    }
}