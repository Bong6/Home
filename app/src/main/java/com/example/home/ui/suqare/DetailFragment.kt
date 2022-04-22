package com.example.home.ui.suqare

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home.database.entity.LocalData
import com.example.home.databinding.FragmentDetailBinding
import com.example.home.ui.encyclopedias.EncyclopediasFragment


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-31 14:44
*/

class DetailFragment : Fragment() {


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
        val binding = FragmentDetailBinding.inflate(inflater,container,false)


        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val data : LocalData = args.data

        binding.detailData = data


        return binding.root
    }


}
