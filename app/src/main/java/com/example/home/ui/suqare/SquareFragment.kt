package com.example.home.ui.suqare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.home.R
import com.example.home.adapter.WaterFallAdapter
import com.example.home.databinding.FragmentSquareBinding
import com.example.home.database.entity.LocalData
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:12
*/

class SquareFragment : Fragment() {
    companion object{
        fun newInstance() : SquareFragment {
            val fragment = SquareFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSquareBinding.inflate(inflater,container,false)


        val factory : SquareViewModelFactory = InjectorUtils.provideLocalDataListViewModelFactory(requireContext())
        val viewModel : SquareViewModel = ViewModelProvider(this,factory).get(SquareViewModel::class.java)


        val adapter = WaterFallAdapter()
        binding.squareRcv.adapter = adapter
        binding.squareRcv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        viewModel.data.observe(requireActivity(),object : Observer<List<LocalData>>{
            override fun onChanged(list: List<LocalData>) {
                adapter.setAllBean(list)
            }
        })

        return binding.root
    }
}