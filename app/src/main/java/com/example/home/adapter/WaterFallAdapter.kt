package com.example.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.databinding.ItemSquareRcvBinding
import com.example.home.database.entity.LocalData
import com.example.home.ui.suqare.SquareFragmentDirections


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-28 0:26
*/

class WaterFallAdapter() : RecyclerView.Adapter<WaterFallAdapter.ViewHolder>() {

    private var list : List<LocalData> = listOf()

    constructor(list: List<LocalData>) : this(){
        this.list = list
    }

    fun setAllBean(list : List<LocalData> ){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : ItemSquareRcvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : LocalData, onClickListener: View.OnClickListener){
            binding.data = data
            val height = (300 + (Math.random() * 200)).toInt()
            binding.itemSquareImg.layoutParams.height = height
            binding.squareLinear.layoutParams.height = height + 180
            binding.squareCard.layoutParams.height = height + 180
            binding.click = onClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<ItemSquareRcvBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_square_rcv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list.get(position)
        holder.bind(data,createOnClickListener(data))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun createOnClickListener(data: LocalData): View.OnClickListener {
        return View.OnClickListener{    it  ->
            Navigation.findNavController(it).navigate(SquareFragmentDirections.actionSquareToDetail(data))
        }
    }

}