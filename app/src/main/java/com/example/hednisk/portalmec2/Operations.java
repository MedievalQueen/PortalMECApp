package com.example.hednisk.portalmec2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hednisk on 25/11/15.
 */

//operations related  to SQLite database
public class Operations {
    private BDWrapper dbHelper;
    private String[] COLLECTION_TABLE_COLUMNS = {BDWrapper.COLLECTION_ID, BDWrapper.COLLECTION_NAME};//BDWrapper.COLLECTION_IMAGE
   // private String[] FILES_TABLE_COLUMNS = {BDWrapper.FILE_ID, BDWrapper.FILE_NAME,BDWrapper.COLLECTION_ID};

    private SQLiteDatabase database;

    public Operations(Context c){
        this.dbHelper = new BDWrapper(c);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    //downloads selected collection to smartphone
    public Collection downloadCollection(String name){
        ContentValues values=new ContentValues();
        values.put(BDWrapper.COLLECTION_NAME, name);
        //insert images
        //values.put(BDWrapper.COLLECTION_IMAGE, );
        long collId = database.insert(BDWrapper.COLLECTIONS, null, values);

        Cursor c= database.query(BDWrapper.COLLECTIONS, COLLECTION_TABLE_COLUMNS,
                BDWrapper.COLLECTION_ID + " = " + collId, null, null, null, null);

        c.moveToFirst();
        Collection coll=parseCollection(c);
        c.close();
        return coll;
    }

    //will it be used??
    public void deleteCollection(int id){
        database.delete(BDWrapper.COLLECTIONS, BDWrapper.COLLECTION_ID + " = " + id, null);
    }

    //generates a list of collections from user
    public List getAllCollections(){
        List collections=new ArrayList();
        Cursor cursor = database.query(BDWrapper.COLLECTIONS, COLLECTION_TABLE_COLUMNS, null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Collection col=parseCollection(cursor);
            collections.add(col);
            cursor.moveToNext();
        }
        cursor.close();
        return collections;
    }
/*
    public List getFilesFromCollection(int id){
        List collections=new ArrayList();
        Cursor cursor = database.query(BDWrapper.FILES, FILES_TABLE_COLUMNS, null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Collection col=parseFiles(cursor);
            collections.add(col);
            cursor.moveToNext();
        }
        cursor.close();
        return collections;
    }*/

    private Collection parseCollection(Cursor c){
        Collection coll=new Collection();
        coll.setId((c.getInt(0)));
        coll.setName((c.getString(1)));
        return coll;
    }
/*
    private Collection parseFiles(Cursor c){
        Collection coll=new Collection();
        coll.setId((c.getInt(0)));
        coll.setName((c.getString(1)));
        return coll;
    }
*/
}
