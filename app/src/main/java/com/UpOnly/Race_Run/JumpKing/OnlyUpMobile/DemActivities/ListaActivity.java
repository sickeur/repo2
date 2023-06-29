package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemUtils.DuhAdapterClass;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class ListaActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_websites);
        mRecyclerView=findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new LinearLayoutManager(this);
        mAdapter=new DuhAdapterClass(this, Lilly_APP.DuhModelHelpers);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}