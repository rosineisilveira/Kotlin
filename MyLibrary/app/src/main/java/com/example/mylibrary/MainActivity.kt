package com.example.mylibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.net.InetAddress.getByName
import java.net.NetworkInterface.getByName


class MainActivity : AppCompatActivity() {

    private var booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter()
        //initRecyclerView()
        var btnSave = findViewById<Button>(R.id.btnSave)
        var btnSearch = findViewById<Button>(R.id.btnSearch)
        var edBook = findViewById<EditText>(R.id.edBook)

        btnSave.setOnClickListener{
            val it = Intent(this, SaveActivity::class.java)
            startActivity(it)
        }
        btnSearch.setOnClickListener {
            findAdapter(edBook.text.toString())
        }
    }


    override fun onResume() {
        super.onResume()
        updateAdapter()
       // initRecyclerView()
    }

    private fun updateAdapter() {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        var txtMsg = findViewById<TextView>(R.id.txtMsg)
        val bookDao = BookDao(this)
        booksList.clear() //todo
        booksList = bookDao.getAll()
        if (booksList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Sem livros cadastrados")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }


    private fun findAdapter(name: String) {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        var txtMsg = findViewById<TextView>(R.id.txtMsg)
        val bookDao = BookDao(this)
        booksList.clear()
        booksList = bookDao.getByName(name)
        if (booksList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Nenhum $name encontrado")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        Log.v("LOG", "Inicia RecyclerView")
        val adapter2 = BookAdapter(booksList)
        rvDados.adapter = adapter2
        //val layout = GridLayoutManager(this, 1)
         val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}



