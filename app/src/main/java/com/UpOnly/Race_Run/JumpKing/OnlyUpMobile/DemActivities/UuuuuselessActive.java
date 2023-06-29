package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class UuuuuselessActive extends AppCompatActivity {

    Button DuhButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_this);
        Whatever();
    }

    public void Whatever(){
        DuhButton = findViewById(R.id.t9awed);
        DuhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do ta9alwa
            }
        });
    }
}