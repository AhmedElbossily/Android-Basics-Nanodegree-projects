package elbossily.inventory;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import elbossily.inventory.InventoryContract.InventoryEntry;
import elbossily.inventory.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ActivityEditBinding binding;
    Uri sentUri = null;
    final int LOADER_ID = 0;
    private boolean mInventoryChanged = false;
    // create onTouch listener that listen to any touch of user
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mInventoryChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        // initiate quantity with zero
        int initialQuantity = 0;
        binding.quantityId.setText("" + initialQuantity);
        //increase quantity by one when add button is clicked
        binding.plusButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(binding.quantityId.getText().toString().trim());
                quantity++;
                binding.quantityId.setText("" + quantity);
            }
        });
        //decrease quantity by one when minus button is clicked
        binding.minusButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(binding.quantityId.getText().toString().trim());
                if (quantity == 0)
                    Toast.makeText(EditActivity.this, R.string.quantity_not_valid, Toast.LENGTH_SHORT).show();
                else {
                    quantity--;
                    binding.quantityId.setText("" + quantity);
                }
            }
        });
        binding.phoneButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = binding.phoneId.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        Intent intent = getIntent();
        sentUri = intent.getData();
        if (sentUri == null) {
            setTitle(R.string.add_product);
            //invalidate delete option in case of new adding
           invalidateOptionsMenu();
        } else {
            setTitle(R.string.update_product);
            getLoaderManager().initLoader(LOADER_ID, null, this);
        }

        //Check any touch from the user
        binding.productNameId.setOnTouchListener(mTouchListener);
        binding.priceId.setOnTouchListener(mTouchListener);
        binding.plusButtonId.setOnTouchListener(mTouchListener);
        binding.minusButtonId.setOnTouchListener(mTouchListener);
        binding.phoneId.setOnTouchListener(mTouchListener);
        binding.supplierNameId.setOnTouchListener(mTouchListener);
    }

    // helper method for creating alert dialog for going back
    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_message);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        //create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // helper method for creating alert dialog for deleting
    private void showDeleteDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure);
        builder.setPositiveButton(R.string.yes, discardButtonClickListener);
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });
        //create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        //if nothing change return
        if (!mInventoryChanged) {
            super.onBackPressed();
            return;
        }
        //other wise setup warning dialog
        DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //user clicked discard
                finish();
            }
        };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void save() {
        int check = 0;
        if (TextUtils.isEmpty(binding.productNameId.getText().toString().trim())) {
            check = 1;
            Toast.makeText(this, R.string.missing_product_name, Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(binding.priceId.getText().toString().trim())) {
            check = 1;
            Toast.makeText(this, R.string.missing_price, Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(binding.quantityId.getText().toString().trim())) {
            check = 1;
            Toast.makeText(this, R.string.quantity, Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(binding.supplierNameId.getText().toString().trim())) {
            check = 1;
            Toast.makeText(this, R.string.missing_supplier_name, Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(binding.phoneId.getText().toString().trim())) {
            check = 1;
            Toast.makeText(this, R.string.missing_supplier_phone, Toast.LENGTH_SHORT).show();
        }
        Uri uri;
        if (check == 0) {
            ContentValues values = new ContentValues();
            values.put(InventoryEntry.COLUMN_PRODUCT_NAME, binding.productNameId.getText().toString().trim());
            values.put(InventoryEntry.COLUMN_PRICE, Integer.parseInt(binding.priceId.getText().toString().trim()));
            values.put(InventoryEntry.COLUMN_QUANTITY, Integer.parseInt(binding.quantityId.getText().toString().trim()));
            values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, binding.supplierNameId.getText().toString().trim());
            values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, binding.phoneId.getText().toString().trim());
            //insert data into table
            if (sentUri == null) {
                uri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
                if (uri == null)
                    Toast.makeText(this, R.string.error_in_saving, Toast.LENGTH_SHORT).show();
                else {
                    finish();
                    Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
                }
            } else {
                //update the inf.
                int newUpdate = getContentResolver().update(sentUri, values, null, null);
                if (newUpdate == 0)
                    Toast.makeText(this, R.string.error_in_updating, Toast.LENGTH_SHORT).show();
                else {
                    finish();
                    Toast.makeText(this, R.string.updated, Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    //delete product
    private void deleteOneCustomers() {
        int row = getContentResolver().delete(sentUri, null, null);
        if (row == -1) Toast.makeText(this, "Error in deleting", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                DialogInterface.OnClickListener yesButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteOneCustomers();
                                finish();
                            }
                        };
                showDeleteDialog(yesButtonClickListener);
                return true;
            case android.R.id.home:
                // Navigate back to parent activity
                if (!mInventoryChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                } else {
                    DialogInterface.OnClickListener discardButtonClickListener =
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    NavUtils.navigateUpFromSameTask(EditActivity.this);
                                }
                            };
                    showUnsavedChangesDialog(discardButtonClickListener);
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {InventoryEntry.COLUMN_ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER};
        return new CursorLoader(this,
                sentUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            binding.productNameId.setText(data.getString(data.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME)));
            binding.priceId.setText(Integer.toString(data.getInt(data.getColumnIndex(InventoryEntry.COLUMN_PRICE))));
            binding.quantityId.setText(Integer.toString(data.getInt(data.getColumnIndex(InventoryEntry.COLUMN_QUANTITY))));
            binding.supplierNameId.setText(data.getString(data.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME)));
            binding.phoneId.setText(data.getString(data.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (sentUri == null){
        MenuItem menuItem = menu.findItem(R.id.action_delete);
        menuItem.setVisible(false);}
        return super.onPrepareOptionsMenu(menu);
    }
}
