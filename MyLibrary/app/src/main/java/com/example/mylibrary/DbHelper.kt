package com.example.mylibrary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val sql="CREATE TABLE $TABLE_BOOKS ($BOOK_ID  INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, $BOOK_TITLE TEXT,$BOOK_AUTHOR TEXT," +
                "$BOOK_TYPE TEXT,$BOOK_PAGES INTEGER)"

        db.execSQL(sql)
        Log.e("LOG","Criando")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME)
        onCreate(db)
    }


}
