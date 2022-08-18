package com.example.theshoestore.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theshoestore.ShoeModel


class ShoeViewModel() : ViewModel() {


    var shoeName = MutableLiveData<String> ()
    var shoeCompany = MutableLiveData<String>()
    var shoeSize = MutableLiveData<String>()
    var shoeDescription = MutableLiveData<String>()

    private var shoesList = mutableListOf<ShoeModel>()

    // create liveData to more encapsulation
    private var _shoeListLiveData = MutableLiveData<List<ShoeModel>>()

    val dataShoeList: LiveData<List<ShoeModel>>
        get() = _shoeListLiveData

    fun onSave (){
         shoesList.add(ShoeModel(shoeName.value.toString(),shoeCompany.value.toString(),shoeSize.value.toString(),shoeDescription.value.toString()))
        _shoeListLiveData.value = shoesList
        Log.i("test",shoesList.size.toString())
    }
}