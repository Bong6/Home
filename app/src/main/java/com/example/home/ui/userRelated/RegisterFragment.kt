package com.example.home.ui.userRelated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home.databinding.FragmentLoginBinding
import com.example.home.databinding.FragmentRegisterBinding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-04 19:31
*/

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(inflater,container,false)

        return binding.root
    }

}