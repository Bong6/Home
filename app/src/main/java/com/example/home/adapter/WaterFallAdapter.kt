package com.example.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.database.entity.EncyclopediaKnowledge
import com.example.home.database.entity.LocalData
import com.example.home.databinding.ItemEncyclopediasBinding
import com.example.home.databinding.ItemLocaldataBinding
import com.example.home.net.entity.AnswerInformation


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-28 0:26
*/


/*
type区分哪里使用
0 -> square
1 -> personal_publish
2 -> personal_collection
*/
class WaterFallAdapter(private var type : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),View.OnClickListener {

    private var list_localdata : List<LocalData> = listOf()
    private var list_encyclopedias : List<EncyclopediaKnowledge> = listOf()
    //有评论给Square使用
    private var onItemClickListenerWithComment : OnItemClickListenerWithComment ?= null
    //无评论给Personal使用
    private var onItemClickListener : OnItemClickListener ?= null
    private var recyclerView : RecyclerView ?= null
    private var listComment : MutableList<MutableList<AnswerInformation>> ?= null

    constructor(list: List<LocalData>,type : Int) : this(type){
        this.list_localdata = list
    }

    fun setListLocalData(list : List<LocalData>){
        this.list_localdata = list
        notifyDataSetChanged()
    }

    fun setLiseEncyclopedias(list : List<EncyclopediaKnowledge>){
        this.list_encyclopedias = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemClickListenerWithComment(onItemClickListener : OnItemClickListenerWithComment){
        this.onItemClickListenerWithComment = onItemClickListener
    }

    fun setListComment(listComment : MutableList<MutableList<AnswerInformation>>){
        this.listComment = listComment
    }

    override fun onClick(view : View?) {
        val position = recyclerView?.getChildAdapterPosition(view!!)
        when(type){
            0   ->{
                onItemClickListenerWithComment?.onItemClick(recyclerView!!,view!!,position!!,list_localdata.get(position),listComment!!.get(position))
            }

            1   ->{
                onItemClickListener?.onItemClick(recyclerView!!,view!!,position!!,list_localdata.get(position))
            }

            else    ->{

            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    class LocalDataViewHolder(val binding : ItemLocaldataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : LocalData){
            binding.data = data
            val height = (300 + (Math.random() * 200)).toInt()
            binding.itemSquareImg.layoutParams.height = height
            binding.squareLinear.layoutParams.height = height + 180
            binding.squareCard.layoutParams.height = height + 180
            binding.executePendingBindings()
        }
    }

    class EncyclopediasViewHolder(val binding : ItemEncyclopediasBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(knowledge : EncyclopediaKnowledge){
            binding.knowledge = knowledge
            val height = (300 + (Math.random() * 200)).toInt()
            binding.itemCollectionImg.layoutParams.height = height
            binding.squareLinear.layoutParams.height = height + 180
            binding.itemCollectionCard.layoutParams.height = height + 180
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when(viewType){
            0,1  ->{
                val view = DataBindingUtil.inflate<ItemLocaldataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_localdata,
                    parent,
                    false
                )

                view.root.setOnClickListener(this)

                return LocalDataViewHolder(
                    view
                )

            }

            else   ->{
                val view = DataBindingUtil.inflate<ItemEncyclopediasBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_encyclopedias,
                    parent,
                    false
                )

                view.root.setOnClickListener(this)

                return EncyclopediasViewHolder(
                    view
                )
            }
        }

//        when(viewType){
//            0,1     ->{
//                val view = DataBindingUtil.inflate<ItemSquareRcvBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.item_square_rcv,
//                    parent,
//                    false
//                )
//
//                view.root.setOnClickListener(this)
//
//                return ViewHolder(
//                    view
//                )
//            }
//
//            else    ->{
//                val view = DataBindingUtil.inflate<ItemCollectionBinding>(
//                    LayoutInflater.from(parent.context),
//                    R.layout.item_collection,
//                    parent,
//                    false
//                )
//
//                view.root.setOnClickListener(this)
//
//                return ViewHolder2(
//                    view
//                )
//            }
//        }
    }



    override fun getItemCount(): Int {
        if (type == 0 || type == 1){
            return list_localdata.size
        }else{
            return list_encyclopedias.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(type){
            0     ->{
                return 0
            }

            1     ->{
                return 1
            }

            else    ->{
                return 2
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LocalDataViewHolder){
            val data = list_localdata.get(position)
            holder.bind(data)
        }else if (holder is EncyclopediasViewHolder){
            val data = list_encyclopedias.get(position)
            holder.bind(data)
        }
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        when(type){
//            0,1     ->{
//                val data = list.get(position)
//                holder.bind(data)
//            }
//
//            else    ->{
//                val date = list.get(position)
//                holder.bind(date)
//            }
//        }
//    }

}

interface OnItemClickListenerWithComment{
    fun onItemClick(recyclerView: RecyclerView,view: View,position: Int,localData: LocalData,list: MutableList<AnswerInformation>)
}

interface OnItemClickListener{
    fun onItemClick(recyclerView: RecyclerView,view: View,position: Int,localData: LocalData)
}
