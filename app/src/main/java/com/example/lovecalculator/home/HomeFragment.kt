package com.example.lovecalculator.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.App
import com.example.lovecalculator.R
import com.example.lovecalculator.Utils
import com.example.lovecalculator.common.BaseFragment
import com.example.lovecalculator.databinding.FragmentHomeBinding
import com.example.lovecalculator.network.LoveModel
import com.example.lovecalculator.room.LoveEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel : LoveViewModel by viewModels()

    @Inject
    lateinit var utils: Utils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun inflate(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initClicker() {
        with(binding){
            calculateBtn.setOnClickListener{
                viewModel.fillLoveModel(firstEd.text.toString(),secondEd.text.toString())
                    .observe(viewLifecycleOwner) { model ->
                        val loveEntity = LoveEntity(
                            firstName = model.firstName,
                            secondName = model.secondName,
                            percentage = model.percentage,
                            result = model.result
                        )

                        App.appDataBase.loveDao().insert(loveEntity)
                        Log.e("ololo", "initClicker: ${model.result}", )
                    }
            }

            historyBtn.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
        }

    }
}