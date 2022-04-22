package com.example.home.ui.encyclopedias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.databinding.FragmentEncyclopediasBinding
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:12
*/

class EncyclopediasFragment : Fragment() {

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

        viewModel.data.observe(requireActivity(),object : Observer<List<EncyclopediaKnowledge>>{
            override fun onChanged(list: List<EncyclopediaKnowledge>?) {
                binding.knowledge = list?.last()
            }

        })

        return binding.root
    }


}