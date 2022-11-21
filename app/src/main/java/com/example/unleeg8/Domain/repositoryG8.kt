package com.example.unleeg8.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unleeg8.Model.Shop
import com.example.unleeg8.Model.books
import com.example.unleeg8.Model.favorites
import com.google.firebase.firestore.FirebaseFirestore

class repositoryG8 {
    fun getBookData(): LiveData<MutableList<books>>{
        val mutableLiveData= MutableLiveData<MutableList<books>>()
        FirebaseFirestore.getInstance().collection("books").get().addOnSuccessListener {
            result->
            val bookList= mutableListOf<books>()
            for (document in result){
                val title= document.getString("title")
                val author= document.getString("author")
                val price= document.getLong("price")?.toInt()
                val url= document.getString("url")
                val detail= document.getString("detail")
                val book= books(title,author,price,url)
                bookList.add(book)
            }
            mutableLiveData.value= bookList
        }
        return mutableLiveData
    }

    fun getShopData(): LiveData<MutableList<Shop>>{
        val mutableLiveData= MutableLiveData<MutableList<Shop>>()
        FirebaseFirestore.getInstance().collection("shop").get().addOnSuccessListener {
                result->
            val bookList= mutableListOf<Shop>()
            for (document in result){
                val title= document.getString("title")
                val author= document.getString("author")
                val price= document.getLong("price")?.toInt()
                val url= document.getString("url")
                val book= Shop(title!!,author!!,price!!,url!!)
                bookList.add(book)
            }
            mutableLiveData.value= bookList
        }
        return mutableLiveData
    }

    fun getFavoriteData(): LiveData<MutableList<favorites>>{
        val mutableLiveData= MutableLiveData<MutableList<favorites>>()
        FirebaseFirestore.getInstance().collection("favorites").get().addOnSuccessListener {
                result->
            val bookList= mutableListOf<favorites>()
            for (document in result){
                val title= document.getString("title")
                val author= document.getString("author")
                val price= document.getLong("price")?.toInt()
                val url= document.getString("url")
                val book= favorites(title!!,author!!,price!!,url!!)
                bookList.add(book)
            }
            mutableLiveData.value= bookList
        }
        return mutableLiveData
    }
}