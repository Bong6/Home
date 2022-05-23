package com.example.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.databinding.ItemLocaldataDetailBinding
import com.example.home.net.entity.AnswerInformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 9:23
*/

class DetailCommentAdapter() : RecyclerView.Adapter<DetailCommentAdapter.ViewHolder>() {

    private var list: List<AnswerInformation> ?= null

    constructor(list: List<AnswerInformation>?) : this(){
        this.list = list
    }

    class ViewHolder(val binding : ItemLocaldataDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment : AnswerInformation){
            binding.comment = comment
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<ItemLocaldataDetailBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_localdata_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = list?.get(position)
        comment?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        if (list == null){
            return 0
        }else {
            return list!!.size
        }
    }

}