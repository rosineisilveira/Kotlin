package com.example.mylibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnRemove = findViewById<Button>(R.id.btnRemove)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val edTitle= findViewById<EditText>(R.id.edTitle)
        val edAuthor= findViewById<EditText>(R.id.edAuthor)
        val edType= findViewById<EditText>(R.id.edType)
        val edPages= findViewById<EditText>(R.id.edPages)
        //val edReadPages= findViewById<EditText>(R.id.edReadPages)
        //val edRead= findViewById<EditText>(R.id.edRead)

        val book = intent.getParcelableExtra<Book>("book")

        edTitle.setText(book?.title.toString())
        edAuthor.setText(book?.author.toString())
        edType.setText(book?.type.toString())
        //edRead.setText(book?.read.toString())
        edPages.setText(book?.pages.toString())
        //edReadPages.setText(book?.readPages.toString())


        btnRemove.setOnClickListener {
            val bookDao = BookDao(this)
            if (book != null) {
                bookDao.remove(book)
            }
            onBackPressed()

        }

        btnSave.setOnClickListener {
            var bookUpdate = Book(
                book?.id, edTitle.text.toString() , edAuthor.text.toString(), edType.text.toString(),
                 (edPages.text.toString().toInt())
            )
            var bookDao = BookDao(this)
            bookDao.update(bookUpdate)
            onBackPressed()
        }

        btnBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }
}