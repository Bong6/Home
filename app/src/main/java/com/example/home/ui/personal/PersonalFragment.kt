package com.example.home.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.home.base.BaseFragment
import com.example.home.database.entity.User
import com.example.home.databinding.FragmentPersonalBinding
import com.example.home.utils.InjectorUtils


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:11
*/

class PersonalFragment : BaseFragment() {
    companion object{
        fun newInstance() : PersonalFragment {
            val fragment = PersonalFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonalBinding.inflate(inflater,container,false)


        val factory : PersonalViewModelFactory = InjectorUtils.providePersonalViewModelFactory(requireContext())
        val viewModel : PersonalViewModel = ViewModelProvider(this,factory).get(PersonalViewModel::class.java)

        viewModel.data.observe(requireActivity(),object : Observer<User>{
            override fun onChanged(user: User) {
                binding.user = user
            }
        })


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(1)
    }

}