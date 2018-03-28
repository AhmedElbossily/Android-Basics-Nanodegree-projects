package elbossily.alexandriatourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class RestaurantsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create array list for to hold restaurants information
        ArrayList<PlaceInformation> placeInformationArray = new ArrayList<>();
        // Add restaurants information
        placeInformationArray.add(new PlaceInformation(getString(R.string.first_restaurant_name),
                getString(R.string.first_restaurant_location), R.drawable.grill_village));
        placeInformationArray.add(new PlaceInformation(getString(R.string.second_restaurant_name),
                getString(R.string.second_restaurant_location), R.drawable.country_hills));
        placeInformationArray.add(new PlaceInformation(getString(R.string.third_restaurant_name),
                getString(R.string.third_restaurant_location), R.drawable.holmes_burger));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fourth_restaurant_name),
                getString(R.string.fourth_restaurant_location), R.drawable.byblos));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fifth_restaurant_name),
                getString(R.string.fifth_restaurant_location), R.drawable.smakmak));
        placeInformationArray.add(new PlaceInformation(getString(R.string.sixth_restaurant_name),
                getString(R.string.sixth_restaurant_location), R.drawable.pablo));
        placeInformationArray.add(new PlaceInformation(getString(R.string.seventh_restaurant_name),
                getString(R.string.seventh_restaurant_location), R.drawable.delices));
        placeInformationArray.add(new PlaceInformation(getString(R.string.eighth_restaurant_name),
                getString(R.string.eighth_restaurant_location), R.drawable.latino));
        placeInformationArray.add(new PlaceInformation(getString(R.string.ninth_restaurant_name),
                getString(R.string.ninth_restaurant_location), R.drawable.white_and_blue));

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        // find list view by its id
        ListView listView = rootView.findViewById(R.id.list_id);
        // define adapter
        PlaceArrayAdapter adapter = new PlaceArrayAdapter(getActivity(), placeInformationArray,
                getContext().getColor(R.color.cafes_color));
        //set the adapter to list
        listView.setAdapter(adapter);
        return rootView;
    }
}
