package com.example.home.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.databinding.ItemTestBinding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-22 11:48
*/

class Test2Adapter(val list: List<String>) : RecyclerView.Adapter<Test2Adapter.ViewHolder>() {


    class ViewHolder(val binding: ItemTestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : String){
            binding.test = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<ItemTestBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_test,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list.get(position)
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}