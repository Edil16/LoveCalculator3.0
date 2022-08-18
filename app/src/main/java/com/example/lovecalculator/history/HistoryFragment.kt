package com.example.lovecalculator.history

import android.os.Bundle
import android.view.View
import com.example.lovecalculator.App
import com.example.lovecalculator.common.BaseFragment
import com.example.lovecalculator.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override fun inflate(): FragmentHistoryBinding {

        return FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appDataBase.loveDao().getAll().observe(viewLifecycleOwner) {
            var s = ""
            it.forEach{
                s += it.firstName + " " + it.secondName + "\n"


            }
            binding.historyTv.text = s
        }
    }

    override fun initClicker() {
        with(binding) {

        }
    }
}

