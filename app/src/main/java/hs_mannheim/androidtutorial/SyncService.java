package hs_mannheim.androidtutorial;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.FakeDatabase;
import com.example.Item;

import java.util.ArrayList;

public class SyncService extends IntentService {

    public final static String ACTION_SYNCED = "String hs_mannheim.androidtutorial.action.SYNCED";

    public SyncService() {
        this("SyncService");
    }

    public SyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("jd", "Intent received: " + intent.toString());
        ArrayList<Item> items = FakeDatabase.getItems();
        Log.d("jd", "Items loaded: " + items);

        insertItems(items);

        notifySynced();
    }

    private void insertItems(ArrayList<Item> items) {
        SQLLiteHelper helper = new SQLLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        for (Item item : items) {
            ContentValues values = new ContentValues();
            values.put("ID", item.getId());
            values.put("Title", item.getTitle());
            values.put("Html", item.getHtml());
            values.put("Description", item.getDescription());

            try {
                db.insertOrThrow("Items", null, values);
                Log.d("jd", "Inserted item " + item.getTitle());
            } catch (Exception e) {
                Log.d("jd", e.getMessage());
            }
        }
    }

    private void notifySynced() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_SYNCED);
        sendBroadcast(broadcastIntent);
    }
}
