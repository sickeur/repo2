package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemConstants;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.R;

public class RICIVER_Net extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!SimpleClassThatCheckCNX.AppIsConnected(context)) { //There is no connection
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layoutDialogue = LayoutInflater.from(context).inflate(R.layout.retry_no_connection, null);
            builder.setView(layoutDialogue);

            AppCompatButton btnRetry = layoutDialogue.findViewById(R.id.btnRetry);

            //showDialogue
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}
