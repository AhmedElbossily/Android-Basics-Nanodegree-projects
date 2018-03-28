package elbossily.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Create ArrayList for song information
    ArrayList<SongsInformation> songsInformationArrayList;
    //create intent for moving from main activity to play now activity
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songsInformationArrayList = new ArrayList<SongsInformation>();
        // Add Album songs to singer Akon
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Right Now"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Beautiful"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Keep you Much Longer"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Troublemaker"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "We Don't Care"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "I'm So Paid"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Holla Holla"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Against the Grain"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Be With You"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Sunny Day"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Birthmark"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Freedom", "Over the Edge"));
        // Add another Album songs to singer Akon
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Angle"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Tha Na Na"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Bounce"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "So Blue"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Secret"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Better"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "No Matter What"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "This Land is our Land"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Burning Alive"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Feeling A NiKKa"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "Shine The Light"));
        songsInformationArrayList.add(new SongsInformation("Akon", "Stadium", "To Each His Own"));
        //Add album songs to singer Bruno Mars
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Watching Her Move"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Faded"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Take The Long Way Home"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Lost"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Lights"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Rest"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Turn Around"));
        songsInformationArrayList.add(new SongsInformation("Bruno Mars", "Earth to Mars", "Where Did She Go"));

        //create a custom Adapter
        SongsAdapter songsAdapter = new SongsAdapter(this, songsInformationArrayList);

        // Find ListView by its id
        ListView songsListView = (ListView) findViewById(R.id.songs_list_id);

        //set the created adapter to ArrayList
        songsListView.setAdapter(songsAdapter);

        //intent construction
        intent = new Intent(MainActivity.this, PlayNowActivity.class);

        // Create a click listener for items in array list
        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create intent for moving from this activity to play now activity
                // put all information of the selected item into the intent
                intent.putExtra("singer", songsInformationArrayList.get(position).getSinger());
                intent.putExtra("album", songsInformationArrayList.get(position).getAlbumName());
                intent.putExtra("song", songsInformationArrayList.get(position).getSongName());
                //start play now activity
                startActivity(intent);
            }
        });

        //create a click listener for playing now button
        Button playingNowButton = (Button) findViewById(R.id.playing_now_button_id);
        playingNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
