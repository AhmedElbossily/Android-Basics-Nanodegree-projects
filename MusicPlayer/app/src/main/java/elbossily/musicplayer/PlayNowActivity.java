package elbossily.musicplayer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import elbossily.musicplayer.databinding.ActivityPlayNowBinding;

public class PlayNowActivity extends AppCompatActivity {
    //create a binding object
    ActivityPlayNowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view based on binding library
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_now);
        //get the intent for data extraction
        final Intent intent = getIntent();
        //set textViews of song name based on the selected item
        binding.songId.setText(intent.getStringExtra("song"));
        //set textViews of album name based on the selected item
        binding.albumId.setText(intent.getStringExtra("album"));
        //set textViews of Singer name based on the selected item
        binding.artistId.setText(intent.getStringExtra("singer"));
        // set click listener to back button
        binding.backButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent for moving from PlayNowActivity to MainActivity
                Intent backIntent = new Intent(PlayNowActivity.this, MainActivity.class);
                //start the back activity
                startActivity(backIntent);
            }
        });
    }
}
