package hs_mannheim.androidtutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.Item;

import java.util.ArrayList;


public class ListActivity extends ActionBarActivity {

    private static final int ITEM_LOGOUT = 1;
    private ProgressBar mProgressBar;
    private Button mSyncButton;
    private ListView mListView;
    private boolean isSyncing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hs_mannheim.androidtutorial.R.layout.activity_list);

        mProgressBar = (ProgressBar) findViewById(hs_mannheim.androidtutorial.R.id.progressBar);
        mSyncButton = (Button) findViewById(hs_mannheim.androidtutorial.R.id.btn_sync);
        mListView = (ListView) findViewById(hs_mannheim.androidtutorial.R.id.lv_items);

        isSyncing = false;

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ItemDetailsActivity.class);
                intent.putExtra("id", (int) id);
                startActivity(intent);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(SyncService.ACTION_SYNCED);

        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ListActivity.this.onSynced();
            }
        }, filter);

        updateItemList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(hs_mannheim.androidtutorial.R.menu.menu_list, menu);
        menu.add(ITEM_LOGOUT, ITEM_LOGOUT, 1, "Logout");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == hs_mannheim.androidtutorial.R.id.action_settings) {
            return true;
        }

        if (id == ITEM_LOGOUT) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSyncClicked(View view) {
        Intent intent = new Intent(this, SyncService.class);
        startService(intent);
        mProgressBar.setVisibility(View.VISIBLE);
        mSyncButton.setVisibility(View.INVISIBLE);
        isSyncing = true;
    }

    private void onSynced() {
        isSyncing = false;
        mProgressBar.setVisibility(View.INVISIBLE);
        mSyncButton.setVisibility(View.VISIBLE);
        isSyncing = false;

        updateItemList();
    }

    private void updateItemList() {
        if(isSyncing) return;

        SQLLiteHelper helper = new SQLLiteHelper(this);
        Cursor itemsCursor = helper.getReadableDatabase().query("Items", new String[]{"ID", "Title", "Html", "Description"}, null, null, null, null, null);

        itemsCursor.moveToFirst();

        ArrayList items = new ArrayList();

        itemsCursor.moveToFirst();
        while (!itemsCursor.isAfterLast()) {
            Item item = new Item(itemsCursor.getInt(0), itemsCursor.getString(1), itemsCursor.getString(2), itemsCursor.getString(3));
            items.add(item);
            itemsCursor.moveToNext();
        }

        mListView.setAdapter(new ItemAdapter(items));
    }
}
