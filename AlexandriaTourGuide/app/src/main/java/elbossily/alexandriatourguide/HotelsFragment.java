package elbossily.alexandriatourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class HotelsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create array list for to hold hotels information
        ArrayList<PlaceInformation> placeInformationArray = new ArrayList<>();
        // Add hotels information
        placeInformationArray.add(new PlaceInformation(getString(R.string.first_hotel_name),
                getString(R.string.first_hotel_location), R.drawable.hotel_1));
        placeInformationArray.add(new PlaceInformation(getString(R.string.second_hotel_name),
                getString(R.string.second_hotel_location), R.drawable.hotel_2));
        placeInformationArray.add(new PlaceInformation(getString(R.string.third_hotel_name),
                getString(R.string.third_hotel_location), R.drawable.hotel_3));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fourth_hotel_name),
                getString(R.string.fourth_hotel_location), R.drawable.hotel_4));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fifth_hotel_name),
                getString(R.string.fifth_hotel_location), R.drawable.hotel_5));
        placeInformationArray.add(new PlaceInformation(getString(R.string.sixth_hotel_name),
                getString(R.string.sixth_hotel_location), R.drawable.hotel_6));
        placeInformationArray.add(new PlaceInformation(getString(R.string.seventh_hotel_name),
                getString(R.string.seventh_hotel_location), R.drawable.hotel_7));
        placeInformationArray.add(new PlaceInformation(getString(R.string.eighth_hotel_name),
                getString(R.string.eighth_hotel_location), R.drawable.hotel_8));
        placeInformationArray.add(new PlaceInformation(getString(R.string.ninth_hotel_name),
                getString(R.string.ninth_hotel_location), R.drawable.hotel_9));
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        // find list view by its id
        ListView listView = rootView.findViewById(R.id.list_id);
        // define adapter
        PlaceArrayAdapter adapter = new PlaceArrayAdapter(getActivity(), placeInformationArray,
                getContext().getColor(R.color.hotels_color));
        //set the adapter to list
        listView.setAdapter(adapter);
        return rootView;
    }
}
