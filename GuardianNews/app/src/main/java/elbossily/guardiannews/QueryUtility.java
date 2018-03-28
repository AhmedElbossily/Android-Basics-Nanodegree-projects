package elbossily.guardiannews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Ahmed on 3/1/2018.
 */

public class QueryUtility {

    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;

    //keys
    private static final String KEY_TITLE = "webTitle";
    private static final String KEY_SECTION_NAME = "sectionName";
    private static final String KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_RESPONSE = "response";
    private static final String KEY_RESULTS = "results";
    private static final String KEY_WEB_URL = "webUrl";

    public static final String LOG_TAG = QueryUtility.class.getSimpleName();

    private QueryUtility() {
    }

    public static ArrayList<NewsData> fetchDataFromUrl(String requestedUrl) {
        //create url
        URL url = createUrl(requestedUrl);

        //make HTTP request and return String of Json
        String jsonString = null;
        try {
            jsonString = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error Closing input stream", e);
        }

        //extract array list from Json response
        ArrayList<NewsData> newsArrayList = extractArrayList(jsonString);

        //return ArrayList
        return newsArrayList;
    }

    private static URL createUrl(String requestedUrl) {
        URL url = null;
        try {
            url = new URL(requestedUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with Creating Url", e);
        }
        return url;
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //check connection response
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            } else Log.e(LOG_TAG, "Error code response: " + urlConnection.getResponseCode());
        } catch (IOException e) {
            Log.e(LOG_TAG, "error with opening connection", e);
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList extractArrayList(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) return null;

        ArrayList<NewsData> newsArrayList = new ArrayList<>();

        try {
            JSONObject baseJsonObject = new JSONObject(jsonResponse).getJSONObject(KEY_RESPONSE);
            JSONArray jsonArray = baseJsonObject.getJSONArray(KEY_RESULTS);
            //read from Array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentJsonObject = jsonArray.getJSONObject(i);
                String newsTitle = currentJsonObject.getString(KEY_TITLE);
                String section = currentJsonObject.getString(KEY_SECTION_NAME);
                String publicationDate = currentJsonObject.optString(KEY_WEB_PUBLICATION_DATE, " No Available publication Date");
                String web_Url = currentJsonObject.getString(KEY_WEB_URL);
                JSONArray tagArray = currentJsonObject.getJSONArray(KEY_TAGS);

                String authorsName = null;
                //get authors names from tag array
                for (int j = 0; j < tagArray.length(); j++) {
                    JSONObject currentTag = tagArray.getJSONObject(j);
                    String authorTotalName;
                    String authorFirstName = currentTag.optString(KEY_FIRST_NAME);
                    String authorSecondName = currentTag.optString(KEY_LAST_NAME);

                    if (!TextUtils.isEmpty(authorSecondName))
                        authorTotalName = authorFirstName + " " + authorSecondName;
                    else authorTotalName = authorFirstName;

                    if (!TextUtils.isEmpty(authorTotalName))
                        authorsName = authorsName + authorTotalName + ", ";
                }

                newsArrayList.add(new NewsData(newsTitle, section, authorsName, publicationDate, web_Url));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error with extracting Json Data", e);
        }
        return newsArrayList;
    }
}
