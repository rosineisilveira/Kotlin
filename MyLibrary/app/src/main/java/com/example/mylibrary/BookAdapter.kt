package com.example.mylibrary

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: List<Book>):
    RecyclerView.Adapter<BookAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.v("LOG", "onCreate")
        val v= LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        val vh = VH(v)



        vh.itemView.setOnClickListener{
            val book= books[vh.adapterPosition]
            val it = Intent(parent.context,UpdateActivity::class.java)
            it.putExtra("book",book)
            parent.context.startActivity(it)
        }
        return vh

    }
    override fun getItemCount(): Int {
        return books.size
    }
    override fun onBindViewHolder(holder: VH, position: Int) {

        var book=books[position]
        holder.txtTitle.text = book.title.toString()
        holder.txtAuthor.text= book.author.toString()
        holder.txtTipo.text= book.type.toString()
        //holder.txtRead.text= book.read.toString()
        holder.txtPages.text= book.pages.toString()
        //holder.txtReadPages.text= book.readPages.toString()


    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        var txtAuthor =view.findViewById<TextView>(R.id.txtAuthor)
        var txtTipo  = view.findViewById<TextView>(R.id.txtType)
        //var txtRead= view.findViewById<TextView>(R.id.txtRead)
        var txtPages= view.findViewById<TextView>(R.id.txtPages)
        //var txtReadPages= view.findViewById<TextView>(R.id.txtReadPages)
        init {
            // Define click listener for the ViewHolder's View.
             txtTitle = view.findViewById(R.id.txtTitle)
             txtAuthor =view.findViewById(R.id.txtAuthor)
             txtTipo  = view.findViewById(R.id.txtType)
             //txtRead= view.findViewById(R.id.txtRead)
             txtPages= view.findViewById(R.id.txtPages)
             //txtReadPages= view.findViewById(R.id.txtReadPages)


        }


    }

}