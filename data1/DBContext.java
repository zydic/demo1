package com.example.foldergallery.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBContext extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "p9moviemaker_category_frame";
    public static final String ID = "id";
    public static final String FRAME_ID = "frameid";
    public static final String FRAME_CATEGORY_ID = "catid";
    public static final String FRAME_APPLICATION_CATEGORY_ID = "applicationid";
    public static final String FRAME_APPLICATION_VERSION = "applicationversion";
    public static final String FRAME_THEME_NAME = "themename";
    public static final String FRAME_THEME_INFO = "themeinfo";
    public static final String FRAME_THEME_BUNDLE = "themebundle";
    public static final String FRAME_THEME_BUNDLE_SIZE = "themebundlesize";
    public static final String FRAME_CATEGORY_THUMBNAILBIG = "catthumbnailbig";
    public static final String FRAME_CATEGORY_THUMBNAILSMALL = "catthumbnailsmall";
    public static final String FRAME_CATEGORY_SOUNDNAME = "catsoundname";
    public static final String FRAME_CATEGORY_SOUNDFILE = "catsoundfile";
    public static final String FRAME_CATEGORY_SOUNDFILESIZE = "catsoundfilesize";
    public static final String FRAME_CATEGORY_NOOFIMAGES = "catnoofimages";
    public static final String FRAME_CATEGORY_HEIGHT = "catheight";
    public static final String FRAME_CATEGORY_WIDTH = "catwidth";
    public static final String FRAME_CATEGORY_VIDEOORCARD = "catvideoorcard";
    public static final String FRAME_CATEGORY_GAMEOBJECT = "catgameobject";
    public static final String FRAME_CATEGORY_GETISPREMIUM = "catgetispremium";
    public static final String FRAME_CATEGORY_GET_THEME_COUNTER = "catthemecounter";
    public static final String FRAME_CATEGORY_GET_STATUS = "catstatus";
    public static final String FRAME_CATEGORY_GET_STATUS_ISNEWRELEASE = "catstatusisnewrelease";
    public static final String FRAME_CATEGORY_ISSTATIC = "catisstatic";
    public static final String FRAME_CATEGORY_ISDOWNLOADED = "catisdownloaded";


    public static final String PHOTOBOOK_CATEGOTY_THUMBNAIL = "catthumbnail";
    public static final String PHOTOBOOK_CATEGOTY_ISSTATIC = "catisstatic";

    public static final String DATA_ID = "id";
    public static final String DATA_CATEGOTY_ID = "catid";
    public static final String DATA_APPLICATION_CATEGORY_ID = "applicationid";
    public static final String DATA_CATEGOTY_NAME = "catname";
    public static final String DATA_CATEGOTY_ISSTATIC = "catisstatic";
    public static final String DATA_CATEGOTY_ISDOWNLOADED = "catisdownloaded";
    private static final String LOG_TAG = "Database";
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "p9moviemaker.db";


    public static final String TABLE_NAME_DATA = "p9moviemaker_category_data";

    private static final String QUERY_CREATE_PHOTO_FRAME_DATA_CATEGORY_TABLE = String
            .format("CREATE TABLE %s ("
                            + "%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "%s INTEGER NOT NULL, "
                            + "%s INTEGER NOT NULL, "
                            + "%s TEXT NOT NULL, "

                            + "%s INTEGER  NOT NULL DEFAULT  1,"
                            + "%s INTEGER  NOT NULL DEFAULT  1 )"
                    ,
                    TABLE_NAME_DATA,
                    DATA_ID, DATA_CATEGOTY_ID,
                    DATA_APPLICATION_CATEGORY_ID,
                    DATA_CATEGOTY_NAME,

                    DATA_CATEGOTY_ISSTATIC,
                    DATA_CATEGOTY_ISDOWNLOADED);

    private static final String QUERY_CREATE_PHOTO_FRAME_CATEGORY_TABLE = String
            .format("CREATE TABLE %s ("
                            + "%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s INTEGER  NOT NULL DEFAULT  0,"
                            + "%s INTEGER  NOT NULL DEFAULT  0 )"
                    ,
                    TABLE_NAME, ID,
                    FRAME_ID, FRAME_CATEGORY_ID,
                    FRAME_APPLICATION_CATEGORY_ID,
                    FRAME_APPLICATION_VERSION,
                    FRAME_THEME_NAME,
                    FRAME_THEME_INFO,
                    FRAME_THEME_BUNDLE,
                    FRAME_THEME_BUNDLE_SIZE,
                    FRAME_CATEGORY_THUMBNAILBIG,
                    FRAME_CATEGORY_THUMBNAILSMALL,
                    FRAME_CATEGORY_SOUNDNAME,
                    FRAME_CATEGORY_SOUNDFILE,
                    FRAME_CATEGORY_SOUNDFILESIZE,
                    FRAME_CATEGORY_NOOFIMAGES,
                    FRAME_CATEGORY_HEIGHT,
                    FRAME_CATEGORY_WIDTH,
                    FRAME_CATEGORY_VIDEOORCARD,
                    FRAME_CATEGORY_GAMEOBJECT,
                    FRAME_CATEGORY_GETISPREMIUM,
                    FRAME_CATEGORY_GET_THEME_COUNTER,
                    FRAME_CATEGORY_GET_STATUS,
                    FRAME_CATEGORY_GET_STATUS_ISNEWRELEASE,

                    FRAME_CATEGORY_ISSTATIC,
                    FRAME_CATEGORY_ISDOWNLOADED);


    private static final String[] DATABASE_TABLES_QUERY = {

            QUERY_CREATE_PHOTO_FRAME_CATEGORY_TABLE, QUERY_CREATE_PHOTO_FRAME_DATA_CATEGORY_TABLE};

    public DBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String table : DATABASE_TABLES_QUERY) {
            try {
                db.execSQL(table);
            } catch (Exception ex) {
                Log.e(LOG_TAG, "Error creating table", ex);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}