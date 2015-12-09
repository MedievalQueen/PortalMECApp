package com.example.hednisk.portalmec2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hednisk on 25/11/15.
 */
public class BDWrapper extends SQLiteOpenHelper {

    public static final String COLLECTIONS = "Collections";
    public static final String COLLECTION_ID = "_id";
    public static final String COLLECTION_NAME = "_name";
    public static final String COLLECTION_IMAGE = "_img";//THUMBNAIL

    public static final String DATABASE_NAME = "Collections.db";
    public static final int DATABASE_VERSION = 1;

    public static final String FILES = "Files";
    public static final String COLLECTION = "_collection_id";
    public static final String FILE_ID = "_id";
    public static final String FILE_NAME = "_name";
    public static final String FILE_IMAGE = "_img";


    public static final String DATABASE_COLL_CREATE = "Create table " + COLLECTIONS
            + "("+ COLLECTION_ID+" integer primary key autoincrement, "
            + COLLECTION_IMAGE+ "blob, "
            + COLLECTION_NAME+ " text not null);";

    public static final String DATABASE_IMG_CREATE = "Create table " + FILES
            + "("+ FILE_ID+" integer primary key autoincrement, "
            + FILE_IMAGE+ "blob, "
            + COLLECTION+ "integer foreign key, "
            + FILE_NAME+ " text not null);";

    public BDWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_COLL_CREATE);
        db.execSQL(DATABASE_IMG_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+COLLECTIONS);
        onCreate(db);
    }
}
