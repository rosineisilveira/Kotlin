package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class BookDao(context: Context) {
    var banco = DbHelper(context)

    fun insert(book: Book): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOK_TITLE, book.title )
        contentValues.put(BOOK_AUTHOR, book.author )
        contentValues.put(BOOK_TYPE, book.type)
        //contentValues.put(BOOK_READ, book.read)
        contentValues.put(BOOK_PAGES, book.pages)
        //contentValues.put(BOOK_READ_PAGES, book.readPages)

        var resp_id=   db.insert(TABLE_BOOKS, null, contentValues)
        val msg = if(resp_id!=-1L){
            "Inserido com sucesso"
        }else{
            "Erro ao inserir"
        }
        db.close()
        return msg
    }

    fun update(book: Book):
            Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(BOOK_ID, book.id )
        contentValues.put(BOOK_TITLE, book.title )
        contentValues.put(BOOK_AUTHOR, book.author )

        contentValues.put(BOOK_TYPE, book.type)
        //contentValues.put(BOOK_READ, book.read)
        contentValues.put(BOOK_PAGES, book.pages)
        //contentValues.put(BOOK_READ_PAGES, book.readPages)

        db.insertWithOnConflict(TABLE_BOOKS,null,contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()

        return true
    }

    fun remove(book: Book) : Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_BOOKS,"ID =?", arrayOf(book.id.toString()))
    }

    fun getAll(): ArrayList<Book>{
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val  sql = "SELECT *  from "+ TABLE_BOOKS
        val cursor = db.rawQuery(sql ,null)
        val books =ArrayList<Book>()
        while (cursor.moveToNext()){
            val book= bookFromCursor(cursor)
            books.add(book)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }


    fun getByName(name:String): ArrayList<Book>{
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val  sql = "SELECT *  from "+ TABLE_BOOKS+" where $BOOK_TITLE like '%$name%'"
        val cursor = db.rawQuery(sql ,null)
        val books =ArrayList<Book>()
        while (cursor.moveToNext()){
            val book= bookFromCursor(cursor)
            books.add(book)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${books.size}")
        return books
    }


    @SuppressLint("Range")
    private fun bookFromCursor(cursor: Cursor): Book {
        val id = cursor.getInt(cursor.getColumnIndex(BOOK_ID))
        val title = cursor.getString(cursor.getColumnIndex(BOOK_TITLE))
        val author = cursor.getString(cursor.getColumnIndex(BOOK_TYPE))
        val type = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR))
        //val read = cursor.getString(cursor.getColumnIndex(BOOK_READ))
        val pages = cursor.getInt(cursor.getColumnIndex(BOOK_PAGES))
        //val readPages = cursor.getInt(cursor.getColumnIndex(BOOK_READ_PAGES))

        return Book(id,title,author,type,pages)
    }
}