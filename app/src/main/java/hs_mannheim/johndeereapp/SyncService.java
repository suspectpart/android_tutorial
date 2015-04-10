package hs_mannheim.johndeereapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SyncService extends IntentService{

    public SyncService() {
        this("SyncService");
    }

    public SyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("jd", "Intent received: " + intent.toString());
    }
}
