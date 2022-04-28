package com.example.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.databinding.ItemSquareRcvBinding
import com.example.home.database.entity.LocalData



/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-28 0:26
*/

class WaterFallAdapter() : RecyclerView.Adapter<WaterFallAdapter.ViewHolder>(),View.OnClickListener {

    private var list : List<LocalData> = listOf()
    private var onItemClickListener : OnItemClickListener ?= null
    private var recyclerView : RecyclerView ?= null

    constructor(list: List<LocalData>) : this(){
        this.list = list
    }

    fun setAllBean(list : List<LocalData> ){
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onClick(view : View?) {
        Log.d("javed","222")
        val position = recyclerView?.getChildAdapterPosition(view!!)
        onItemClickListener?.onItemClick(recyclerView!!,view!!,position!!,list.get(position));
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    class ViewHolder(val binding : ItemSquareRcvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : LocalData){
            binding.data = data
            val height = (300 + (Math.random() * 200)).toInt()
            binding.itemSquareImg.layoutParams.height = height
            binding.squareLinear.layoutParams.height = height + 180
            binding.squareCard.layoutParams.height = height + 180
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<ItemSquareRcvBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_square_rcv,
            parent,
            false
        )

        view.root.setOnClickListener(this)

        return ViewHolder(
            view
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

interface OnItemClickListener{
    fun onItemClick(recyclerView: RecyclerView,view: View,position: Int,data: LocalData)
}