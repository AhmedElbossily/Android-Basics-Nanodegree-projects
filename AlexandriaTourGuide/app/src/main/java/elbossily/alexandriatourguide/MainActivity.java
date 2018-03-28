package elbossily.alexandriatourguide;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an array for tab titles
        final String tabTitles[] = new String[]{getString(R.string.cafe), getString(R.string.cinema),
                getString(R.string.mall), getString(R.string.hotel)};

        // Set the content of the activity to use the activity_main.xml layout file
        ViewPager viewPager = findViewById(R.id.view_pager_id);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(getSupportFragmentManager(), tabTitles);

        //set thw adapter into view pager
        viewPager.setAdapter(adapter);

        //find tabLayout by it's id
        TabLayout tableLayout = findViewById(R.id.sliding_tabs);

        //set up tab with View pager
        tableLayout.setupWithViewPager(viewPager);
    }
}
