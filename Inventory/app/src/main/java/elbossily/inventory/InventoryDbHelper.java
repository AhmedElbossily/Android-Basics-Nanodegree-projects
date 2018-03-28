package elbossily.inventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import elbossily.inventory.InventoryContract.InventoryEntry;

/**
 * Created by Ahmed on 3/10/2018.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.dp";
    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create string for creating table
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME
                + " ( "
                + InventoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_QUANTITY + " DEFAULT 0, "
                + InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL "
                + ");";
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //create String for deleting table
        String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + InventoryEntry.TABLE_NAME + " ;";
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
