package elbossily.inventory;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ahmed on 3/10/2018.
 */

public class InventoryContract {

    public static final class InventoryEntry implements BaseColumns {
        // define Authority
        public static final String CONTENT_AUTHORITY = "elbossily.inventory";
        public static final String PATH_INVENTORY = "inventory";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);
        //define content item and list type
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY
                + "/" + PATH_INVENTORY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY
                + "/" + PATH_INVENTORY;
        // database table definitions
        public static final String TABLE_NAME = "inventory";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "phone";
    }
}
