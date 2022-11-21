package com.example.unleeg8.View.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.books
import com.example.unleeg8.Model.favorites
import com.example.unleeg8.R
import com.squareup.picasso.Picasso

class FavoritesAdapter(val context: Context, var clickListener: OnFavoritesItemClickListener): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>(){

    var bookList= mutableListOf<favorites>()

    fun setListData(data:MutableList<favorites>){
        bookList=data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v= LayoutInflater.from(ViewGroup.context).inflate(
            R.layout.card_view_favorites,
            ViewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val book= bookList[i]
        viewHolder.bin(book, clickListener)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bin(book: favorites, action:OnFavoritesItemClickListener){
            //Acá agregar ImageView con picasso o gliger

            Picasso.get().load(book.url).into(itemView.findViewById<ImageView>(R.id.imgBook))
            itemView.findViewById<TextView>(R.id.tvTitleBook).text=book.title
            itemView.findViewById<TextView>(R.id.tvAuthorBook).text = book.author
            itemView.findViewById<TextView>(R.id.tvPriceBook).text= book.price.toString()
            val btndelete=itemView.findViewById<ImageButton>(R.id.delete)
            btndelete.setOnClickListener {
                action.onItemClickDelete(book,adapterPosition)
            }
            itemView.setOnClickListener {
                action.onItemClickBook(book,adapterPosition)
            }

        }
    }

}
interface OnFavoritesItemClickListener{
    fun onItemClickBook(book: favorites, position:Int)
    fun onItemClickDelete(book: favorites, position:Int)


}