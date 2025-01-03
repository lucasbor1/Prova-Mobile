package com.example.prova;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Conexao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "banco.db";
    private static final int DATABASE_VERSION = 1;

    public Conexao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE conselho (id INTEGER PRIMARY KEY, conselho TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conselho");
        onCreate(db);
    }

    public boolean conselhoExiste(String conselho) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT 1 FROM conselho WHERE conselho = ?";
        Cursor cursor = db.rawQuery(query, new String[]{conselho});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }

    public void deletaConselho(String conselho) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("conselho", "conselho = ?", new String[]{conselho});
        db.close();
    }


    public List<String> getConselhos() {
        List<String> conselhos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT conselho FROM conselho", null);
        if (cursor.moveToFirst()) {
            do {
                String conselho = cursor.getString(0);
                conselhos.add(conselho);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return conselhos;
    }
}
