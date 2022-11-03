package com.example.unleeg8.View.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.unleeg8.R


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardBook= view.findViewById<CardView>(R.id.fragBook)
        cardBook.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_booksFragment2)
        }

        val cardShop= view.findViewById<CardView>(R.id.fragShop)

        cardShop.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_shopFragment)
        }

        val cardFavorites= view.findViewById<CardView>(R.id.fragFavorite)
        cardFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_favoritesFragment)
        }

        val cardProfile= view.findViewById<CardView>(R.id.fragProfile)
        cardProfile.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_profileFragment)
        }

        val imgShip= view.findViewById<ImageView>(R.id.nav_ship)
        imgShip.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_shipFragment)
        }

        val txtHelp= view.findViewById<TextView>(R.id.nav_help)
        txtHelp.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_helpFragment)
        }

    }



}