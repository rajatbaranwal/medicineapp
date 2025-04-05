package com.example.medicinelemonsoft;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MedicineSqlite extends SQLiteAssetHelper {


    public MedicineSqlite(Context context) {
        super(context, "medicine.db", null, 1);


    }

    public Cursor loadData() {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from brand", null);
        return cursor;


    }

    public Cursor searchData(String key) {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from brand where brand_name like ?", new String[]{"%"+key+"%"});
        return cursor;

    }

}
