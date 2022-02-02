package com.learn.project_wallz.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.learn.project_wallz.MODELS.ImageModel;

import java.util.ArrayList;

public class Mydatabase extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_NAME = "Favorites";
    public static final String DB_TABLE = "Posts";
    public static final String DB_COL_NAME = "Imageurl";
    public static final String DB_COL_PHONE = "Imagedesc";
    public static final String DB_COL_ID = "ID";
    public Mydatabase(Context context) {
        super(context, DB_NAME , null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create;
        create = " CREATE TABLE " + DB_TABLE + "(" + DB_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + DB_COL_NAME + " TEXT ," + DB_COL_PHONE + " TEXT " + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }
    public void addNewCourse( String url , String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COL_NAME, url);
        values.put(DB_COL_PHONE, desc);
        db.insert(DB_TABLE, null, values);
        Log.d("MYData", "One Entry Added");
        db.close();

    }
   public ArrayList<ImageModel> GETDATA(){
        ArrayList<ImageModel> FAV = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String qeri = " SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(qeri,null);
        if(cursor.moveToFirst()){
            do {
                  ImageModel data = new ImageModel();
                  data.setId(Integer.parseInt(cursor.getString(0)));
                  data.setUrl(cursor.getString(1));
                  data.setInfo(cursor.getString(2));
                  FAV.add(data);


            }while (cursor.moveToNext());
        }
        return FAV;


    }
    public int updatedata(int id , String newname , String newnumber){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COL_ID,id);
        values.put(DB_COL_NAME,newname);
        values.put(DB_COL_PHONE,newnumber);
        return db.update(DB_TABLE,values,DB_COL_ID +"=?", new String[]{String.valueOf(id)});

    }
    public void delete_CN(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DB_TABLE,DB_COL_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }
}


