package com.example.unleeg8.View.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.favorites
import com.example.unleeg8.R
import com.example.unleeg8.View.adapter.FavoritesAdapter
import com.example.unleeg8.View.adapter.OnFavoritesItemClickListener
import com.example.unleeg8.View.adapter.ShopAdapter
import com.example.unleeg8.ViewModel.FavoritesViewModel
import com.example.unleeg8.ViewModel.ShopViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FavoritesFragment : Fragment(), OnFavoritesItemClickListener {
    lateinit var recyclerFavorites: RecyclerView
    lateinit var adapter: FavoritesAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(FavoritesViewModel::class.java) }
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_favorites, container, false)
        recyclerFavorites=view.findViewById(R.id.rvFavorites)
        adapter= FavoritesAdapter(requireContext(),this)
        recyclerFavorites.layoutManager= LinearLayoutManager(context)
        recyclerFavorites.adapter=adapter
        observeData()
        return view
    }

    private fun observeData() {
        viewmodel.fetchFavoritesData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClickBook(book: favorites, position: Int) {
        val title:String?=book.title
        val author:String?=book.author
        val price:String=book.price.toString()
        val url:String?=book.url
        val bundle= bundleOf("title" to title,
            "author" to author,
            "price" to price,
            "url" to url,)
        findNavController().navigate(R.id.action_favoritesFragment_to_book_detail_Fragment,bundle)
    }

    override fun onItemClickDelete(book: favorites, position: Int) {
        database.collection("favorites")
            .document(book.title)
            .delete()
    }


}