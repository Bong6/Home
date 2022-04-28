package com.example.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.database.entity.Comment
import com.example.home.database.entity.LocalData
import com.example.home.databinding.ItemSuqareDetailRcvBinding


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-04-27 9:23
*/

class DetailCommentAdapter() : RecyclerView.Adapter<DetailCommentAdapter.ViewHolder>() {

    private lateinit var list: List<Comment>

    constructor(list: List<Comment>) : this(){
        this.list = list
    }

    class ViewHolder(val binding : ItemSuqareDetailRcvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment : Comment){
            binding.comment = comment
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<ItemSuqareDetailRcvBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_suqare_detail_rcv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = list[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}