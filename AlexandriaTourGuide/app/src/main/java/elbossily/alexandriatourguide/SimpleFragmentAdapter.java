package elbossily.alexandriatourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ahmed on 2/26/2018.
 */

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    //constructor
    private String[] mTabTitles;

    public SimpleFragmentAdapter(FragmentManager fm, String tabTitles[]) {
        super(fm);
        mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        //return fragments according to position
        if (position == 0) return new RestaurantsFragment();
        else if (position == 1) return new CinemaFragment();
        else if (position == 2) return new MallsFragment();
        else return new HotelsFragment();
    }

    //Number of fragments
    @Override
    public int getCount() {
        return 4;
    }

    //set the title of every fragments according to the position
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
