package elbossily.alexandriatourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class CinemaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create array list for to hold cinemas information
        ArrayList<PlaceInformation> placeInformationArray = new ArrayList<>();
        // Add cinemas information
        placeInformationArray.add(new PlaceInformation(getString(R.string.first_cinema_name),
                getString(R.string.first_cinema_location), R.drawable.cinema_1));
        placeInformationArray.add(new PlaceInformation(getString(R.string.second_cinema_name),
                getString(R.string.second_cinema_location), R.drawable.cinema_2));
        placeInformationArray.add(new PlaceInformation(getString(R.string.third_cinema_name),
                getString(R.string.third_cinema_location), R.drawable.cinema_3));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fourth_cinema_name),
                getString(R.string.fourth_cinema_location), R.drawable.cinema_4));
        placeInformationArray.add(new PlaceInformation(getString(R.string.fifth_cinema_name),
                getString(R.string.fifth_cinema_location), R.drawable.cinema_5));
        placeInformationArray.add(new PlaceInformation(getString(R.string.sixth_cinema_name),
                getString(R.string.sixth_cinema_location), R.drawable.cinema_6));
        placeInformationArray.add(new PlaceInformation(getString(R.string.seventh_cinema_name),
                getString(R.string.seventh_cinema_location), R.drawable.cinema_7));
        placeInformationArray.add(new PlaceInformation(getString(R.string.eighth_cinema_name),
                getString(R.string.eighth_cinema_location), R.drawable.cinema_8));
        placeInformationArray.add(new PlaceInformation(getString(R.string.ninth_cinema_name),
                getString(R.string.ninth_cinema_location), R.drawable.cinema_9));
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        // find list view by its id
        ListView listView = rootView.findViewById(R.id.list_id);
        // define adapter
        PlaceArrayAdapter adapter = new PlaceArrayAdapter(getActivity(), placeInformationArray,
                getContext().getColor(R.color.cinemas_color));
        //set the adapter to list
        listView.setAdapter(adapter);
        return rootView;
    }


}
