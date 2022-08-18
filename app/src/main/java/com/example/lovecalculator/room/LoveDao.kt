package com.example.lovecalculator.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import com.example.lovecalculator.network.LoveModel
import retrofit2.http.Query

@Dao
interface LoveDao {

    @Insert
    fun insert(loveModel: LoveEntity)

    @androidx.room.Query("SElECT * FROM love_model ORDER BY firstName ASC")
    fun getAll(): LiveData<List<LoveEntity>>

}
