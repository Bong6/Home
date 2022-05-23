package com.example.home.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.home.R
import com.example.home.base.BaseFragment
import com.example.home.database.entity.User
import com.example.home.databinding.FragmentPersonalBinding
import com.example.home.utils.Flag
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:11
*/

class PersonalFragment : BaseFragment() {
    private lateinit var viewModel : PersonalViewModel

    companion object{
        fun newInstance() : PersonalFragment {
            val fragment = PersonalFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory : PersonalViewModelFactory = InjectorUtils.providePersonalViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this,factory).get(PersonalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonalBinding.inflate(inflater,container,false)

        binding.user = Flag.user

        binding.personalPublishTimes.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("type",1)
            Navigation.findNavController(it).navigate(R.id.action_personal_to_publish_collection,bundle)
        }

        binding.personalCollectionTimes.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("type",2)
            Navigation.findNavController(it).navigate(R.id.action_personal_to_publish_collection,bundle)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(1)
    }

}