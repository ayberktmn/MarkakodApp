package com.ayberk.markakodapp.Retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.markakodapp.Models.Base
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitInstance) {

    fun getData(liveData: MutableLiveData<Base>){
        retrofitServiceInstance.getData().enqueue(object : retrofit2.Callback<Base>{
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}