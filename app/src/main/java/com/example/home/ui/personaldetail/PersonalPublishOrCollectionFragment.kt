package com.example.home.ui.personaldetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.home.adapter.WaterFallAdapter
import com.example.home.base.BaseFragment
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.databinding.FragmentPersonalpublishorcollectionBinding
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-20 10:09
*/

class PersonalPublishOrCollectionFragment : BaseFragment() {

    /*
    * 区分 发表还是收藏
    * 1 发表
    * 2 收藏
    * */
    private var type : Int = 1
    private var adapter : WaterFallAdapter ?= null
    private lateinit var viewModel : PersonalPublishOrCollectionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = arguments?.getInt("type",1)!!

        val factory : PersonalPublishOrCollectionViewModelFactory = InjectorUtils.providePersonalPublishOrCollectionViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this,factory).get(PersonalPublishOrCollectionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonalpublishorcollectionBinding.inflate(inflater,container,false)

        binding.squareRcv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        if (type == 1){
            adapter = WaterFallAdapter(1)
            binding.squareRcv.adapter = adapter

            viewModel.localdata.observe(requireActivity(),object : Observer<List<LocalData>> {
                override fun onChanged(list: List<LocalData>) {
                    adapter?.setListLocalData(list)
                    Log.d("javed",list.last().title)
                }
            })
        }else if (type == 2){
            adapter = WaterFallAdapter(2)
            binding.squareRcv.adapter = adapter

            viewModel.knowledge.observe(requireActivity(),object : Observer<List<EncyclopediaKnowledge>> {
                override fun onChanged(list: List<EncyclopediaKnowledge>) {
                    adapter?.setLiseEncyclopedias(list)
                    Log.d("javed",list.last().sickName)
                }
            })
        }


        return binding.root
    }




    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}