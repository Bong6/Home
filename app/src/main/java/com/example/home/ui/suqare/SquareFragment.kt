package com.example.home.ui.suqare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.home.MainActivity
import com.example.home.R
import com.example.home.adapter.OnItemClickListener
import com.example.home.adapter.WaterFallAdapter
import com.example.home.base.BaseFragment
import com.example.home.databinding.FragmentSquareBinding
import com.example.home.database.entity.LocalData
import com.example.home.ui.squaredetail.SquareDetailActivity
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:12
*/

class SquareFragment : BaseFragment() {

    private var adapter : WaterFallAdapter ?= null

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

        binding.squareRcv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter = WaterFallAdapter()
        adapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                recyclerView: RecyclerView,
                view: View,
                position: Int,
                data: LocalData
            ) {
                val intent = Intent(requireContext(),SquareDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("data",data)
                intent.putExtra("bundle",bundle)
                requireContext().startActivity(intent)
            }

        })


        binding.squareRcv.adapter = adapter

        viewModel.data.observe(requireActivity(),object : Observer<List<LocalData>>{
            override fun onChanged(list: List<LocalData>) {
                adapter?.setAllBean(list)
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(0)
    }


    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}