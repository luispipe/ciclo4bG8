package com.example.unleeg8.View.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.books
import com.example.unleeg8.R


class BookAdapter(val context: Context):RecyclerView.Adapter<BookAdapter.ViewHolder>(){

    var bookList= mutableListOf<books>()

    fun setListData(data:MutableList<books>){
        bookList=data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_books,
        ViewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val book= bookList[i]
        viewHolder.bin(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bin(book:books){
            //Acá agregar ImageView con picasso o gliger
            itemView.findViewById<TextView>(R.id.tvTitleBook).text=book.title
            itemView.findViewById<TextView>(R.id.tvAuthorBook).text = book.author
            itemView.findViewById<TextView>(R.id.tvPriceBook).text= book.price.toString()
        }
    }

}