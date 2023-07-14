package com.ayberk.markakodapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.markakodapp.Models.Base
import com.ayberk.markakodapp.Retrofit.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel@Inject constructor(private val repository: RetrofitRepository) : ViewModel() {

    var DataList: MutableLiveData<Base>

    init {
             DataList = MutableLiveData()
    }
    fun getDataLiveData(): MutableLiveData<Base>{
        return DataList
    }
    fun  loadData(){
        repository.getData(DataList)
    }
}