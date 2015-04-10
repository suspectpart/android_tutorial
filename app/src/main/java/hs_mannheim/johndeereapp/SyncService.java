package hs_mannheim.johndeereapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.Item;
import com.example.JohnDeereDatabase;

import java.util.ArrayList;

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
        ArrayList<Item> items = JohnDeereDatabase.getItems();
        Log.d("jd", "Items loaded: " + items);
    }
}
