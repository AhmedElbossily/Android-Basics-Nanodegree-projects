package elbossily.alexandriatourguide;

/**
 * Created by Ahmed on 2/26/2018.
 */

public class PlaceInformation {
    //Create variables to store place name, location and image resource
    private String mPlaceName;
    private String mLocation;
    private int mImageResourceFile;

    //Constructor
    public PlaceInformation(String placeName, String location, int imageResourceFile) {
        mPlaceName = placeName;
        mLocation = location;
        mImageResourceFile = imageResourceFile;
    }

    // methods definition to get the stored information
    public String getPlaceName() {
        return mPlaceName;
    }

    public String getLocation() {
        return mLocation;
    }

    public int getImageResourceFile() {
        return mImageResourceFile;
    }
}
