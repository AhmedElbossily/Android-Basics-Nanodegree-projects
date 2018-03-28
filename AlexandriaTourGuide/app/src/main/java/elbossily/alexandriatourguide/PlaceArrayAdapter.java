package elbossily.alexandriatourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 2/26/2018.
 */

public class PlaceArrayAdapter extends ArrayAdapter<PlaceInformation> {
    private int mColor;

    public PlaceArrayAdapter(@NonNull Context context, ArrayList<PlaceInformation> placesArray, int color) {
        super(context, 0, placesArray);
        mColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //define the current place
        PlaceInformation currentPlace = getItem(position);
        //define current View
        View currentView = convertView;
        //check if current is null to inflate it
        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        //find image view by its id and set its image
        ImageView imageView = currentView.findViewById(R.id.image_id);
        imageView.setImageResource(currentPlace.getImageResourceFile());

        //find place name TextView by its  id and set it
        TextView placeNameTextView = currentView.findViewById(R.id.place_name_id);
        placeNameTextView.setText(currentPlace.getPlaceName());

        //find place name TextView by its  id and set it
        TextView locationTextView = currentView.findViewById(R.id.location_id);
        locationTextView.setText(currentPlace.getLocation());

        //find LinearLayoyt that hold text views to change its color
        LinearLayout linearLayout = currentView.findViewById(R.id.linear_layout_id);
        linearLayout.setBackgroundColor(mColor);
        return currentView;
    }
}