package com.example.user.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class veritabani extends SQLiteOpenHelper {

    final public String veritabani_ismi="haritam";
    veritabani(Context context) {
        super(context,"haritam",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE harita(id INTEGER PRIMARY KEY AUTOINCREMENT, Latitude DOUBLE NOT NULL, location VARCHAR NOT NULL, Longitude DOUBLE NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS harita");
        onCreate(db);
    }
    public void alanEkle(harita harita){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Latitude",harita.getLatitude());
        cv.put("location",harita.getLocation());
        cv.put("Longitude",harita.getLongitude());
        db.insert("harita",null,cv);
       Log.i("db ekle","başarılı");
        db.close();

    }
    public List<harita> alanlariListele(){
        List<harita> haritaa=new ArrayList<>();
        String query="select * from harita ";
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery(query,null);
        harita harita=null;
        if(cursor.moveToFirst()){
            do{
                harita=new harita();
                harita.setLatitude(cursor.getDouble(1));
                harita.setLocation(cursor.getString(2));
                harita.setLongitude(cursor.getDouble(3));

haritaa.add(harita);
            }while (cursor.moveToNext());
        }
return haritaa;

    }
    public void alanSilme(String Location){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("harita","location=?",new String[]{Location});
        Log.i("db çıkarma","başarılı");
    db.close();}
}
