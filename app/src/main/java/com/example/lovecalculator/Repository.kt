package com.example.lovecalculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lovecalculator.network.LoveApi
import com.example.lovecalculator.network.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

class Repository @Inject constructor(private val api: LoveApi) {

    fun getLoveModel(firstName:String,secondName: String) : MutableLiveData<LoveModel>{
        val mutableLoveModel : MutableLiveData<LoveModel> = MutableLiveData()

        api.calculateLove(firstName = firstName, secondName = secondName)
            .enqueue(object :Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.isSuccessful) {
                        mutableLoveModel.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.message}", )
            }
        })

        return mutableLoveModel
    }
}