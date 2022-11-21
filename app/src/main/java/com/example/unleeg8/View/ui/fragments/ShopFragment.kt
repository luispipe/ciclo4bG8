package com.example.unleeg8.View.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unleeg8.Model.Shop
import com.example.unleeg8.R
import com.example.unleeg8.View.adapter.BookAdapter
import com.example.unleeg8.View.adapter.OnShopItemClickListener
import com.example.unleeg8.View.adapter.ShopAdapter
import com.example.unleeg8.ViewModel.BookViewModel
import com.example.unleeg8.ViewModel.ShopViewModel
import com.google.firebase.firestore.FirebaseFirestore


class ShopFragment : Fragment(), OnShopItemClickListener {
    lateinit var recyclerShop: RecyclerView
    lateinit var adapter: ShopAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(ShopViewModel::class.java) }
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var priceT:TextView
    lateinit var shopT:TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view=inflater.inflate(R.layout.fragment_shop, container, false)
        recyclerShop=view.findViewById(R.id.rvShop)
        priceT=view.findViewById(R.id.priceShop)
        shopT=view.findViewById(R.id.Shopfinal)
        adapter=ShopAdapter(requireContext(),this)
        recyclerShop.layoutManager=LinearLayoutManager(context)
        recyclerShop.adapter=adapter
        observeData()
        pricetotal()
        shopT.setOnClickListener {
            makePurchase()
        }
        return view
    }

    private fun makePurchase() {
       val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("Realizar Compra")
        builder.setMessage("¿Desea realizar esta compra?")
        builder.setPositiveButton("Ok"){
            dialog,which->
            findNavController().navigate(R.id.action_shopFragment_to_menuFragment)
        }
        builder.setNegativeButton("Cancel",null)
        builder.show()
    }

    private fun pricetotal() {
        database.collection("shop")
            .get()
            .addOnSuccessListener {
                result->
                val preciounitario= mutableListOf<String>()
                for (document in result){
                    val precio=document["price"].toString()
                    preciounitario.add(precio!!)
                }
                val preciototal=preciounitario.mapNotNull { it.toIntOrNull() }.sum()
                priceT.setText(Integer.toString(preciototal))
            }
    }

    private fun observeData() {
        viewmodel.fetchShopData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onItemClickDelete(book: Shop, position: Int) {
        database.collection("shop")
            .document(book.title)
            .delete()
    }

}