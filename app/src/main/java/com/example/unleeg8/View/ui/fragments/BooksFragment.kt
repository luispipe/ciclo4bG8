package com.example.unleeg8.View.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.books
import com.example.unleeg8.R
import com.example.unleeg8.View.adapter.BookAdapter
import com.example.unleeg8.View.adapter.OnBookItemClickListener
import com.example.unleeg8.ViewModel.BookViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class BooksFragment : Fragment(), OnBookItemClickListener {
    lateinit var recyclerBook: RecyclerView
    lateinit var adapter: BookAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(BookViewModel::class.java) }
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_books, container, false)
        recyclerBook= view.findViewById(R.id.rvBook)
        adapter= BookAdapter(requireContext(), this)
        recyclerBook.layoutManager= LinearLayoutManager(context)
        recyclerBook.adapter=adapter
        observeData()
        return view

    }

    fun observeData(){
        viewmodel.fetchBookData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClickBook(book: books, position: Int) {
        val title:String?=book.title
        val author:String?=book.author
        val price:String=book.price.toString()
        val url:String?=book.url
        val bundle= bundleOf("title" to title,
            "author" to author,
            "price" to price,
            "url" to url,)
        findNavController().navigate(R.id.action_booksFragment2_to_book_detail_Fragment,bundle)
    }



    override fun onItemClickCarrito(book: books) {
    }

    override fun onItemClickfavority(book: books) {

    }


}