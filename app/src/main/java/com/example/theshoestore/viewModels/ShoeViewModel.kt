package com.example.theshoestore.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theshoestore.ShoeModel


class ShoeViewModel() : ViewModel() {

//    private var shoesList = mutableListOf<ShoeModel>()
//
//    // create liveData to more encapsulation
//    private var _shoeListLiveData = MutableLiveData<List<ShoeModel>>()
//
//    val dataShoeList: LiveData<List<ShoeModel>>
//        get() = _shoeListLiveData
//
//    fun onSave(shoeName: String, shoeCompany: String, shoeSize: String, shoeDescription: String) {
//        val newItem = ShoeModel(shoeName, shoeCompany, shoeSize, shoeDescription)
//        newItem.let {
//            shoesList.add(it)
//            _shoeListLiveData.value = shoesList
//        }
//    }

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