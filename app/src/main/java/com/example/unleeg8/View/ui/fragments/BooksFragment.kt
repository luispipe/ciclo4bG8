package com.example.unleeg8.View.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.R
import com.example.unleeg8.View.adapter.BookAdapter
import com.example.unleeg8.ViewModel.BookViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class BooksFragment : Fragment() {
    lateinit var recyclerBook: RecyclerView
    lateinit var adapter: BookAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(BookViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_books, container, false)
        recyclerBook= view.findViewById(R.id.rvBook)
        adapter= BookAdapter(requireContext())
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



}