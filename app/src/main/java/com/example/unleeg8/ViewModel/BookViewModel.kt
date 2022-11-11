package com.example.unleeg8.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unleeg8.Domain.repositoryG8
import com.example.unleeg8.Model.books

class BookViewModel: ViewModel() {
   private val repository = repositoryG8()

    fun fetchBookData(): LiveData<MutableList<books>>{
        val mutableLiveData= MutableLiveData<MutableList<books>>()
        repository.getBookData().observeForever {
            mutableLiveData.value = it
        }
        return mutableLiveData
    }


}