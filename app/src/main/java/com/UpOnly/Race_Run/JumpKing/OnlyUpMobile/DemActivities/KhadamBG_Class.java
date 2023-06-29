package com.UpOnly.Race_Run.JumpKing.OnlyUpMobile.DemActivities;


import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class KhadamBG_Class extends Worker {

    Context appContext;
    static String LOG_TAG = "irondev_worker_backg";

    public KhadamBG_Class(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        appContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        return startWorker();
    }

    private Result startWorker() {
        Intent i = new Intent(appContext, KhadamBGActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(i);
        return Result.success();
    }

    interface MyCallBack {
        void done();
    }
}
