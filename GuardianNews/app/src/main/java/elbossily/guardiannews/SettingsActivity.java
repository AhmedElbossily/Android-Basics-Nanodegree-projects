package elbossily.guardiannews;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class NewsPreferenceFragments extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            // find topic search preference
            Preference topicSearch = findPreference(getString(R.string.settings_topic_key));
            bindPreferenceSummeryToValue(topicSearch);

            //find order by preference
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummeryToValue(orderBy);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0){
                    CharSequence[] label = listPreference.getEntries();
                    preference.setSummary(label[prefIndex]);
                }
            }else{
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void bindPreferenceSummeryToValue(Preference preference){
            //set Listener to preference
            preference.setOnPreferenceChangeListener(this);
            //get context of preference
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
           //get String
            String preferenceString = sharedPrefs.getString(preference.getKey(),"");
            //apply it to onPreferenceChange method
            onPreferenceChange(preference,preferenceString);
        }
    }
}
