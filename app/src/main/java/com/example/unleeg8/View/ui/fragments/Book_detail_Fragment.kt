package com.example.unleeg8.View.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.unleeg8.Model.books
import com.example.unleeg8.R
import com.example.unleeg8.View.adapter.OnBookItemClickListener
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class Book_detail_Fragment : DialogFragment(), OnBookItemClickListener {
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_book_detail_, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cover = arguments?.getString("url")
        val title = arguments?.getString("title")
        val author = arguments?.getString("author")
        val price = arguments?.getString("price")

        Picasso.get().load(cover).into(view.findViewById<ImageView>(R.id.ivBookDetail))
        view.findViewById<TextView>(R.id.tvNameBookDetail).text= title
        view.findViewById<TextView>(R.id.tvAuthorBookDetailt).text= author
        view.findViewById<TextView>(R.id.tvPriceBookDetail).text= price.toString()
        val buttonshoping=view.findViewById<Button>(R.id.btBuyBookDetail)
        val book=books(title,author,price?.toInt(),cover)
        buttonshoping.setOnClickListener {
            onItemClickCarrito(book)
        }
        val buttonfavorites=view.findViewById<ImageButton>(R.id.favoritos)
        buttonfavorites.setOnClickListener {
            onItemClickfavority(book)
        }

      /*  view.findViewById<Button>(R.id.btBuyBookDetail).setOnClickListener {
            l
        }*/

    }

    override fun onItemClickBook(book: books, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClickCarrito(book: books) {
        val title:String?=book.title
        val author:String?=book.author
        val price:Int?=book.price
        val url:String?=book.url
        val data= hashMapOf(
            "title" to title,
            "author" to author,
            "price" to price,
            "url" to url
        )
        database.collection("shop")
            .document(title.toString())
            .set(data)
            .addOnSuccessListener {
                Toast.makeText(context,"libro añadido a compras", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onItemClickfavority(book: books) {
        val title:String?=book.title
        val author:String?=book.author
        val price:Int?=book.price
        val url:String?=book.url
        val data= hashMapOf(
            "title" to title,
            "author" to author,
            "price" to price,
            "url" to url
        )
        database.collection("favorites")
            .document(title.toString())
            .set(data)
            .addOnSuccessListener {
                Toast.makeText(context,"libro añadido a favoritos",Toast.LENGTH_SHORT).show()
            }
    }


}