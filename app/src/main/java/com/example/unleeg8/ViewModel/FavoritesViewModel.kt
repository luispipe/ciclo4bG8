package com.example.unleeg8.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unleeg8.Domain.repositoryG8
import com.example.unleeg8.Model.Shop
import com.example.unleeg8.Model.favorites

class FavoritesViewModel: ViewModel() {
    private val repository = repositoryG8()

    fun fetchFavoritesData(): LiveData<MutableList<favorites>> {
        val mutableLiveData= MutableLiveData<MutableList<favorites>>()
        repository.getFavoriteData().observeForever {
            mutableLiveData.value = it
        }
        return mutableLiveData
    }


}