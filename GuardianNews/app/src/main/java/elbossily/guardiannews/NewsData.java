package elbossily.guardiannews;

/**
 * Created by Ahmed on 3/1/2018.
 */

public class NewsData {
    private String mTitle;
    private String mSection;
    private String mPublicationDate;
    private String mAuthorName;
    private String mWebUrl;

    public NewsData(String title, String section, String authorName,
                    String publicationDate, String webUrl) {
        mTitle = title;
        mSection = section;
        mPublicationDate = publicationDate;
        mAuthorName = authorName;
        mWebUrl = webUrl;
    }

    //get methods
    public String getTitleName() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}