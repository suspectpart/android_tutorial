package hs_mannheim.androidtutorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class ItemDetailsActivity extends ActionBarActivity {

    private int mId;
    private TextView mTitle;
    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hs_mannheim.androidtutorial.R.layout.activity_item_details);
        Intent intent = getIntent();

        mId = intent.getIntExtra("id", -1);

        mTitle = (TextView) findViewById(hs_mannheim.androidtutorial.R.id.tv_title);
        mDescription = (TextView) findViewById(hs_mannheim.androidtutorial.R.id.tv_description);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLLiteHelper sqlLiteHelper = new SQLLiteHelper(this);
        SQLiteDatabase db = sqlLiteHelper.getReadableDatabase();
        Cursor items = db.query("items", new String[]{"Title", "Description"}, "ID = " + mId, null, null, null, null);

        items.moveToFirst();

        String title = items.getString(0);
        String description = items.getString(1);

        mTitle.setText(title);
        mDescription.setText(description);
    }
}
