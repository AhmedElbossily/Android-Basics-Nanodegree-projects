package elbossily.inventory.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import elbossily.inventory.InventoryContract.InventoryEntry;
import elbossily.inventory.R;

/**
 * Created by Ahmed on 3/11/2018.
 */

public class InventoryCursorAdapter extends CursorAdapter {
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // find views by their id
        TextView productNameTextView = view.findViewById(R.id.product_name_id);
        TextView priceTextView = view.findViewById(R.id.price_id);
        TextView quantityNameTextView = view.findViewById(R.id.quantity_id);
        Button saleButton = view.findViewById(R.id.sale_button);

        // set views
        productNameTextView.setText(cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME)));
        priceTextView.setText(cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE)) + " $");
        quantityNameTextView.setText(cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY)));

        //set up the decreasing quantity for the sale button
        // Get values from the database
        final int currentQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryEntry.COLUMN_QUANTITY));
        String currentId = cursor.getString(cursor.getColumnIndexOrThrow(InventoryEntry._ID));
        final Uri currentUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, Long.parseLong(currentId));

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check the quantity to be more than 1
                if (currentQuantity > 0) {
                    ContentValues values = new ContentValues();
                    values.put(InventoryEntry.COLUMN_QUANTITY, (currentQuantity - 1));
                    //update the current value
                    int newUpdate = context.getContentResolver().update(currentUri, values, null, null);
                    if (newUpdate == 0)
                        Toast.makeText(context, R.string.error_in_updating, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, R.string.quantity_not_valid, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
