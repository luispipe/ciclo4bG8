package com.example.unleeg8.View.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.Shop
import com.example.unleeg8.R
import com.squareup.picasso.Picasso

class ShopAdapter(val context: Context, var clickListener: OnShopItemClickListener):RecyclerView.Adapter<ShopAdapter.ViewHolder>(){

    var bookList= mutableListOf<Shop>()

    fun setListData(data:MutableList<Shop>){
        bookList=data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v= LayoutInflater.from(ViewGroup.context).inflate(
            R.layout.card_view_shop,
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
        fun bin(book: Shop, action:OnShopItemClickListener){
            //Acá agregar ImageView con picasso o gliger

            Picasso.get().load(book.url).into(itemView.findViewById<ImageView>(R.id.imgBook))
            itemView.findViewById<TextView>(R.id.tvTitleBook).text=book.title
            itemView.findViewById<TextView>(R.id.tvAuthorBook).text = book.author
            itemView.findViewById<TextView>(R.id.tvPriceBook).text= book.price.toString()
            val btndelete=itemView.findViewById<ImageButton>(R.id.delete)
            btndelete.setOnClickListener {
                action.onItemClickDelete(book,adapterPosition)
            }

        }
    }

}
interface OnShopItemClickListener{
    fun onItemClickDelete(book: Shop, position:Int)


}