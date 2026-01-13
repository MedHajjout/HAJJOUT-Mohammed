package com.yaceen.dmntest.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.yaceen.dmntest.model.Contact;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null) {
            instance = new DatabaseHelper(context);
        }

        return  instance;
    }
    public DatabaseHelper(@Nullable Context context) {
        super(context, "contacts.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists contacts(id integer primary key autoincrement, nom text, phone text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts;");
        onCreate(sqLiteDatabase);
    }

    public Contact getContactById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM contacts WHERE id = ?",
                new String[]{ String.valueOf(id) }
        );

        Contact contact = null;

        if (c.moveToFirst()) {
            contact = new Contact(
                    c.getInt(c.getColumnIndexOrThrow("id")),
                    c.getString(c.getColumnIndexOrThrow("nom")),
                    c.getString(c.getColumnIndexOrThrow("phone"))
            );
        }

        c.close();
        return contact;
    }

}
