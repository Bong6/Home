package com.example.home.ui.encyclopedias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.home.base.BaseFragment
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.databinding.FragmentEncyclopediasBinding
import com.example.home.ui.encyclopediasdetail.EncyclopediasDetailActivity
import com.example.home.utils.InjectorUtils
import kotlin.concurrent.thread


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:12
*/

class EncyclopediasFragment : BaseFragment() {

    private var list: List<EncyclopediaKnowledge> ?= null

    companion object{
        fun newInstance() : EncyclopediasFragment {
            val fragment = EncyclopediasFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEncyclopediasBinding.inflate(inflater,container,false)


        val viewModelFactory : EncyclopediasViewModelFactory = InjectorUtils.provideEncyclopediaViewModelFactory(requireContext())
        val viewModel : EncyclopediasViewModel = ViewModelProvider(this,viewModelFactory).get(EncyclopediasViewModel::class.java)

        //更新服务器最新数据
        viewModel.data.observe(requireActivity(),object : Observer<List<EncyclopediaKnowledge>>{
            override fun onChanged(list: List<EncyclopediaKnowledge>?) {
                this@EncyclopediasFragment.list = list
                binding.knowledge = list?.first()
            }
        })


        //点击查看服务器推送详情
        binding.encyclopediasCardview.setOnClickListener {
            val intent = Intent(requireContext(),EncyclopediasDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("knowledge",list?.first())
            intent.putExtra("bundle",bundle)
            requireContext().startActivity(intent)
        }

        //数据库检索
        binding.click = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.d("javed","111")
            thread {
                val sickName = binding.encyclopediasEdittext.text.toString()
                val queryExactlyEncyclopediaknowledge =
                    viewModel.queryExactlyEncyclopediaknowledge(sickName)

                queryExactlyEncyclopediaknowledge?.let {

                    val intent = Intent(requireContext(),EncyclopediasDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("knowledge",it)
                    intent.putExtra("bundle",bundle)
                    requireContext().startActivity(intent)

                }?: Log.d("javed","not find")
            }

        }
//        binding.encyclopediasFind.setOnClickListener {
//            thread {
//                val sickName = binding.encyclopediasEdittext.text.toString()
//                val queryExactlyEncyclopediaknowledge =
//                    viewModel.queryExactlyEncyclopediaknowledge(sickName)
//
//                if(queryExactlyEncyclopediaknowledge != null){
//                    Log.d("javed",queryExactlyEncyclopediaknowledge.sickName)
//                    Log.d("javed","success")
//                }else{
//                    Log.d("javed","not find")
//                }
////                queryExactlyEncyclopediaknowledge?.let {
////                    Log.d("javed",queryExactlyEncyclopediaknowledge.sickName)
////                    Log.d("javed","success")
////                }?: Log.d("javed","not find")
//            }
        }



        //查看功能
        binding.encyclopediasFind.setOnClickListener {
            val text = binding.encyclopediasEdittext.text.toString()
            thread {
//                val encyclopediaKnowledge : EncyclopediaKnowledge ?= viewModel.queryExactlyEncyclopediaknowledge(text)
//                encyclopediaKnowledge?.let {
//                    val intent = Intent(requireContext(),EncyclopediasDetailActivity::class.java)
//                    val bundle = Bundle()
//                    bundle.putParcelable("encyclopediaKnowledge",encyclopediaKnowledge)
//                    intent.putExtra("bundle",bundle)
//                    requireActivity().startActivity(intent)
//                }?: requireContext().
            }
        }


        return binding.root
    }


    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(1)

    }


}