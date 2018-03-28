package elbossily.musicplayer;

/**
 * Created by Ahmed on 2/23/2018.
 */

public class SongsInformation {

    private String mSongName;
    private String mAlbumName;
    private String mSinger;

    //constructor
    public SongsInformation(String singer, String albumName, String songName) {
        mSongName = songName;
        mAlbumName = albumName;
        mSinger = singer;
    }

    public String getSongName() {
        return mSongName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getSinger() {
        return mSinger;
    }

}
