package edu.buffalo.cse.cse486586.groupmessenger1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE="Distributed_DB";
    private static final String TABLE_NAME="RANDOM_TB";
    private static final String key="key";
    private static final String value="value";

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE, null  , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists '"+TABLE_NAME + "'");
        db.execSQL("create table "+TABLE_NAME+"( "+key+" string PRIMARY KEY ,"+value+" string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists '"+TABLE_NAME + "'");
        db.execSQL("create table "+TABLE_NAME+"( "+key+" string PRIMARY KEY ,"+value+" string)");
    }
    public boolean insert(ContentValues contentValues){
        SQLiteDatabase sdb=this.getReadableDatabase();
        int ret=(int)sdb.insert(TABLE_NAME,null,contentValues);
        if(ret==-1){

            ret=(int)sdb.update(TABLE_NAME,contentValues,"key=?",new String[]{contentValues.keySet().toString()});
        }
        return ret!=-1;
    }
    public Cursor retrieve(String selection){
        SQLiteDatabase sdb=this.getReadableDatabase();
        Cursor cr=sdb.rawQuery("select * from "+TABLE_NAME+" where key=?",new String[]{selection});
        return cr;
    }
}
