package elbossily.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Ahmed on 3/1/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {

    String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {
        if (mUrl == null) return null;
        List<NewsData> newsList = QueryUtility.fetchDataFromUrl(mUrl);
        return newsList;
    }
}
