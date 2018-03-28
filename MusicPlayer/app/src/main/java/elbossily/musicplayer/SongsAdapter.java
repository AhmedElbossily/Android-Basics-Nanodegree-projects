package elbossily.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 2/24/2018.
 */

public class SongsAdapter extends ArrayAdapter<SongsInformation> {
    //constructor
    public SongsAdapter(@NonNull Context context, @NonNull ArrayList<SongsInformation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SongsInformation currentInformation = getItem(position);
        View currentView = convertView;

        if (convertView == null) {
            //inflate View by layout
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // set the song name in the item
        TextView songNameTextView = (TextView) currentView.findViewById(R.id.song_id);
        songNameTextView.setText(currentInformation.getSongName());

        // set artist name in the item
        TextView artistNameTextView = (TextView) currentView.findViewById(R.id.artist_id);
        artistNameTextView.setText(currentInformation.getSinger());

        // set album name in the item
        TextView albumNameTextView = (TextView) currentView.findViewById(R.id.album_id);
        albumNameTextView.setText(currentInformation.getAlbumName());

        return currentView;
    }
}
