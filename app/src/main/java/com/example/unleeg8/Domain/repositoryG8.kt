package com.example.unleeg8.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unleeg8.Model.books
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
                val book= books(title,author,price,url)
                bookList.add(book)
            }
            mutableLiveData.value= bookList
        }
        return mutableLiveData
    }
}