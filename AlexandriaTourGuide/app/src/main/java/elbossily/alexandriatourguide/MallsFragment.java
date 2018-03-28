package elbossily.alexandriatourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MallsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create array list for to hold malls information
        ArrayList<PlaceInformation> placeInformationArray = new ArrayList<>();
        // Add malls information
        placeInformationArray.add(new PlaceInformation(getString(R.string.first_mall_name),
                getString(R.string.first_mall_location), R.drawable.cinema_1));
        placeInformationArray.add(new PlaceInformation(getString(R.string.second_mall_name),
                getString(R.string.second_mall_location), R.drawable.cinema_2));
        placeInformationArray.add(new PlaceInformation(getString(R.string.third_mall_name),
                getString(R.string.third_mall_location), R.drawable.cinema_3));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fourth_mall_name),
                getString(R.string.fourth_mall_location), R.drawable.cinema_4));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fifth_mall_name),
                getString(R.string.fifth_mall_location), R.drawable.cinema_5));
        placeInformationArray.add(new PlaceInformation(getString(R.string.sixth_mall_name),
                getString(R.string.sixth_mall_location), R.drawable.cinema_6));
        placeInformationArray.add(new PlaceInformation(getString(R.string.seventh_mall_name),
                getString(R.string.seventh_mall_location), R.drawable.cinema_7));
        placeInformationArray.add(new PlaceInformation(getString(R.string.eighth_mall_name),
                getString(R.string.eighth_mall_location), R.drawable.cinema_8));
        placeInformationArray.add(new PlaceInformation(getString(R.string.ninth_mall_name),
                getString(R.string.ninth_mall_location), R.drawable.cinema_9));
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment, container, false);

        // find list view by its id
        ListView listView = rootView.findViewById(R.id.list_id);

        // define adapter
        PlaceArrayAdapter adapter = new PlaceArrayAdapter(getActivity(), placeInformationArray,
                getContext().getColor(R.color.malls_color));

        //set the adapter to list
        listView.setAdapter(adapter);
        return rootView;
    }
}
