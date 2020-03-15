package com.example.ejemplosqlite;
import android.content.Context;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public class Almacenamiento extends SQLiteOpenHelper{

    public Almacenamiento(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ejemplo(id int primary key, nombre text, telefono int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
