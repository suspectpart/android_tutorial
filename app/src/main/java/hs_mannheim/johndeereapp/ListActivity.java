package hs_mannheim.johndeereapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        setContentView(R.layout.activity_list);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSyncButton = (Button) findViewById(R.id.btn_sync);
        mListView = (ListView) findViewById(R.id.lv_items);

        isSyncing = false;

        IntentFilter filter = new IntentFilter();
        filter.addAction(SyncService.ACTION_SYNCED);

        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ListActivity.this.onSynced();
            }
        }, filter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
        if (id == R.id.action_settings) {
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
        mProgressBar.setVisibility(View.INVISIBLE);
        mSyncButton.setVisibility(View.VISIBLE);
        isSyncing = false;

        updateItemList();
    }

    private void updateItemList() {
        SQLLiteHelper helper = new SQLLiteHelper(this);
        Cursor itemsCursor = helper.getReadableDatabase().query("Items", new String[]{"ID", "Title", "Description"}, null, null, null, null, null);

        itemsCursor.moveToFirst();

        ArrayList items = new ArrayList();

        itemsCursor.moveToFirst();
        while (!itemsCursor.isAfterLast()) {
            Item item = new Item(itemsCursor.getInt(0), itemsCursor.getString(1), itemsCursor.getString(2));
            items.add(item);
            itemsCursor.moveToNext();
        }

        mListView.setAdapter(new ItemAdapter(items));
    }

    public class ItemAdapter implements ListAdapter {

        private ArrayList<Item> mItems;

        public ItemAdapter(ArrayList<Item> items) {
            mItems = items;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(ListActivity.this);
            tv.setText(mItems.get(position).getTitle());
            return tv;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return mItems.size() <= 0;
        }
    }
}
