package hs_mannheim.johndeereapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class ListActivity extends ActionBarActivity {

    private static final int ITEM_LOGOUT = 1;
    private ProgressBar mProgressBar;
    private Button mSyncButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSyncButton = (Button) findViewById(R.id.btn_sync);
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
    }
}
