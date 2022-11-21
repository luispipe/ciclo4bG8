package com.example.unleeg8.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unleeg8.Domain.repositoryG8
import com.example.unleeg8.Model.Shop

class ShopViewModel: ViewModel() {
    private val repository = repositoryG8()

    fun fetchShopData(): LiveData<MutableList<Shop>> {
        val mutableLiveData= MutableLiveData<MutableList<Shop>>()
        repository.getShopData().observeForever {
            mutableLiveData.value = it
        }
        return mutableLiveData
    }


}