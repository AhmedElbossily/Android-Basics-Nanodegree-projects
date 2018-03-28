package elbossily.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsData>> {

    NewsAdapter newsAdapter;
    final String BASE_URL_STRING = "https://content.guardianapis.com/search";
    private TextView emptyText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find Views by their id
        ListView newsListView = findViewById(R.id.list_id);
        emptyText = findViewById(R.id.no_internet_id);
        progressBar = findViewById(R.id.loading_spinner);

        // set empty View for the list
        newsListView.setEmptyView(emptyText);

        // check if there is internet connection or not
        ConnectivityManager connManger = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManger.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            final ArrayList<NewsData> newsArrayList = new ArrayList<>();
            newsAdapter = new NewsAdapter(this, newsArrayList);
            newsListView.setAdapter(newsAdapter);
            //initiate loader
            getLoaderManager().initLoader(0, null, this);
            //create click listener for every item in list to open web page in browser
            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //get item url
                    String url = newsArrayList.get(position).getWebUrl();
                    //Create new intent
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            emptyText.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {
        //get the Preference
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //get topic name
        String topicSearch = sharedPrefs.getString(getString(R.string.settings_topic_key),
                getString(R.string.settings_default_key));
        // get value of order
        String orderByString = sharedPrefs.getString(getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default_value));
        // Create the base uri
        Uri baseUri = Uri.parse(BASE_URL_STRING);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        // append query parameter
        uriBuilder.appendQueryParameter("q",topicSearch);
        uriBuilder.appendQueryParameter("from-date","2018-01-01");
        uriBuilder.appendQueryParameter("api-key","86c0ccef-d477-4668-8f5f-c418217552ff");
        uriBuilder.appendQueryParameter("show-tags","contributor");
        uriBuilder.appendQueryParameter("order-by",orderByString);

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> data) {
        //remove progress bar
        progressBar.setVisibility(View.GONE);
        //in case of list is empty set the empty text to be "no news"
        emptyText.setText(R.string.no_news);
        newsAdapter.clear();
        if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        newsAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings_id){
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
