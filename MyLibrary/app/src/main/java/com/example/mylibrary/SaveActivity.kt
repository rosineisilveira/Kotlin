package com.example.mylibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val edTitle= findViewById<EditText>(R.id.edTitle)
        val edAuthor= findViewById<EditText>(R.id.edAuthor)
        val edType= findViewById<EditText>(R.id.edType)
        val edPages= findViewById<EditText>(R.id.edPages)
        //val edReadPages= findViewById<EditText>(R.id.edReadPages)

        btnBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })


        btnSave.setOnClickListener(View.OnClickListener {
            var book = Book(null,edTitle.text.toString(),edAuthor.text.toString(),edType.text.toString(),
                (edPages.text.toString().toInt()))
            var bookDao = BookDao(this)
            bookDao.insert(book)
            onBackPressed()
        })
    }

}