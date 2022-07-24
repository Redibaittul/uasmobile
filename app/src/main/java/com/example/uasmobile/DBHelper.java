package com.example.uasmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String database_nama = "db_login";
    public static final String table_nama = "table_login";

    public static final String rov_id = "_id";
    public static final String rov_username = "Username";
    public static final String rov_password = "Password";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_nama, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_nama + "(" + rov_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + rov_username + " TEXT," + rov_password + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_nama);

    }

    //Inser Data
    public void inserData(ContentValues values){
        db.insert(table_nama, null, values);
    }

    public boolean checkUser(String usernama, String password){
        String[] columns = {rov_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = rov_username + "=?" + " and " + rov_password + "=?";
        String[] selectionArgs = {usernama,password};
        Cursor cursor = db.query(table_nama, columns, selection, selectionArgs, null, null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
