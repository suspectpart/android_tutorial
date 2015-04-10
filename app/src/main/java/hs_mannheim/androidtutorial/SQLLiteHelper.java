package hs_mannheim.androidtutorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DICTIONARY_TABLE_NAME = "items";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "ID INTEGER UNIQUE, " +
                    "Title TEXT, " +
                    "Description TEXT);";

    SQLLiteHelper(Context context) {
        super(context, "ITEMS_DB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DICTIONARY_TABLE_NAME + ";");
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }
}
