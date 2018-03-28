package elbossily.inventory.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import elbossily.inventory.InventoryContract.InventoryEntry;
import elbossily.inventory.InventoryDbHelper;

/**
 * Created by Ahmed on 3/11/2018.
 */

public class InventoryProvider extends ContentProvider {
    private static final String LOG_TAG = InventoryProvider.class.getSimpleName();
    //define constants for detect the type of path
    private static final int INVENTORY = 100;
    private static final int INVENTORY_ID = 101;
    // Create Uri matcher
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(InventoryEntry.CONTENT_AUTHORITY, InventoryEntry.PATH_INVENTORY, INVENTORY);
        sUriMatcher.addURI(InventoryEntry.CONTENT_AUTHORITY, InventoryEntry.PATH_INVENTORY +
                "/#", INVENTORY_ID);
    }

    private InventoryDbHelper mDbInventory;

    @Override
    public boolean onCreate() {
        mDbInventory = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbInventory.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("can't query Uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown type for this Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertProduct(uri, values);
            default:
                throw new IllegalArgumentException("Can't insert this Uri: " + uri);
        }
    }

    // inset helper function
    private Uri insertProduct(Uri uri, ContentValues values) {
        // check whether the user insert product name or not
        String productName = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
        if (productName == null) {
            throw new IllegalArgumentException("product name is empty");
        }
        // check price
        int productPrice = -1;
        productPrice = values.getAsInteger(InventoryEntry.COLUMN_PRICE);
        if (productPrice == -1) {
            throw new IllegalArgumentException("product price is empty");
        }
        // check Quantity
        int productQuantity = -1;
        productQuantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
        if (productQuantity == -1) {
            throw new IllegalArgumentException("Product quantity is empty");
        }
        //check supplier name
        String supplierName = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
        if (supplierName == null) {
            throw new IllegalArgumentException("Supplier name is empty");
        }
        //check supplier name
        String supplierPhone = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
        if (supplierPhone == null) {
            throw new IllegalArgumentException("Supplier phone number is empty");
        }
        //get writable database
        SQLiteDatabase database = mDbInventory.getWritableDatabase();
        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);
        //check the information was inserted or not
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert a row");
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbInventory.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                int deleteTable = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return deleteTable;
            case INVENTORY_ID:
                selection = InventoryEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int deleteRow = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return deleteRow;
            default:
                throw new IllegalArgumentException("can't delet this Uri: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return inventoryUpdate(uri, values, selection, selectionArgs);
            case INVENTORY_ID:
                selection = InventoryEntry.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return inventoryUpdate(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Can't update this URI: " + uri);
        }
    }

    // update helper method
    private int inventoryUpdate(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //check the name is exist or not in the update
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_NAME)) {
            String name = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("can't update because product name is missing");
            }
        }
        //check the price is exist or not in the update
        if (values.containsKey(InventoryEntry.COLUMN_PRICE)) {
            int price = -1;
            price = values.getAsInteger(InventoryEntry.COLUMN_PRICE);
            if (price == -1) {
                throw new IllegalArgumentException("can't update because product price is missing");
            }
        }
        //check the quantity is exist or not in the update
        if (values.containsKey(InventoryEntry.COLUMN_QUANTITY)) {
            int quantity = -1;
            quantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
            if (quantity == -1) {
                throw new IllegalArgumentException("can't update because product quantity is missing");
            }
        }
        //check the supplier name is exist or not in the update
        if (values.containsKey(InventoryEntry.COLUMN_SUPPLIER_NAME)) {
            String supplier = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
            if (supplier == null) {
                throw new IllegalArgumentException("can't update because supplier name is missing");
            }
        }
        //check the supplier phone number is exist or not in the update
        if (values.containsKey(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER)) {
            String phone = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            if (phone == null) {
                throw new IllegalArgumentException("can't update because phone number is missing");
            }
        }

        if (values.size() == 0) return 0;
        //get writable database
        SQLiteDatabase database = mDbInventory.getWritableDatabase();
        int newUpdate = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return newUpdate;
    }
}
