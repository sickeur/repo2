package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemUtils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.Lilly_APP;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities.SecondaryActive;
import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DuhAdapterClass extends RecyclerView.Adapter<DuhAdapterClass.GamesViewHolder> {

    Context mContext;
    ArrayList<DuhModelHelper> gamerModeModels;

    public DuhAdapterClass(Context mContext, ArrayList<DuhModelHelper> gamerModeModels) {
        this.mContext = mContext;
        this.gamerModeModels = gamerModeModels;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_with_amazing_ui,null);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.Game_Name.setText(gamerModeModels.get(position).getGame_Name());
        holder.Game_Description.setText(gamerModeModels.get(position).getGame_Description());
        final DuhModelHelper model= gamerModeModels.get(position);
        Glide.with(mContext).load(model.getGame_Icon()).into(holder.Game_Icon);
        Glide.with(mContext).load(model.getGame_Bg_Image()).into(holder.Game_bg_Img);

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int Position) {
                String Url= Lilly_APP.DuhModelHelpers.get(Position).getGame_Url();
                Intent intent=new Intent(mContext, SecondaryActive.class);
                intent.putExtra("Url",Url);
                mContext.startActivity(intent);
//                Toast.makeText(mContext,"clicked "+Position,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return gamerModeModels.size();
    }

    public static class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView Game_Icon;
        ImageView Game_bg_Img;
        TextView Game_Name;
        TextView Game_Description;
        ItemClickListner listner;


        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Game_Icon = itemView.findViewById(R.id.Game_Icon);
            this.Game_bg_Img = itemView.findViewById(R.id.bg_img);
            this.Game_Name= itemView.findViewById(R.id.Game_Name);
            this.Game_Description= itemView.findViewById(R.id.Game_Description);
            itemView.setOnClickListener(this);
        }




        @Override
        public void onClick(View view) {
            this.listner.onItemClickListner(view,getLayoutPosition());
        }

        public void setItemClickListner(ItemClickListner ic){
            this.listner=ic;
        }

    }

}
