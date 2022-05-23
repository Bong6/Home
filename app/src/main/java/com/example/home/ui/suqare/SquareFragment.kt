package com.example.home.ui.suqare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.home.R
import com.example.home.adapter.OnItemClickListener
import com.example.home.adapter.OnItemClickListenerWithComment
import com.example.home.adapter.WaterFallAdapter
import com.example.home.base.BaseFragment
import com.example.home.databinding.FragmentSquareBinding
import com.example.home.database.entity.LocalData
import com.example.home.net.GetInformationAPI
import com.example.home.net.entity.AnswerInformation
import com.example.home.net.entity.NetLocalData
import com.example.home.ui.squaredetail.SquareDetailActivity
import com.example.home.ui.userRelated.UserRelatedActivity
import com.example.home.utils.GetInformation
import com.example.home.utils.InjectorUtils
import com.example.kotlindemo.RetrofitClient.APIClient
import com.example.home.utils.Flag
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:12
*/

class SquareFragment : BaseFragment() {

    private var adapter : WaterFallAdapter ?= null
    private lateinit var viewModel : SquareViewModel



    companion object{
        //评论数据
        private lateinit var listComment : MutableList<MutableList<AnswerInformation>>

        fun newInstance() : SquareFragment {
            val fragment = SquareFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory : SquareViewModelFactory = InjectorUtils.provideLocalDataListViewModelFactory(requireContext())
        viewModel  = ViewModelProvider(this,factory).get(SquareViewModel::class.java)

        //从网络加载数据
        getNetLocalDataFromNet()
    }

    fun getNetLocalDataFromNet(){
        //读取网络数据
        APIClient.mInstance.create(GetInformationAPI::class.java).getLocalData().enqueue(
            object : Callback<NetLocalData> {
                override fun onResponse(
                    call: Call<NetLocalData>,
                    response: Response<NetLocalData>
                ) {
                    if (response.body() != null){
                        val netLocalData = response.body()!!

                        //版本没变,没发生更新
                        if (netLocalData.version == Flag.curVersion){
                            //保存评论信息    -> 这部分不在数据库中
                            netLocalData.let {
                                listComment = mutableListOf()
                                for (data in it.data){
                                    val answerInformation : List<AnswerInformation> = data.answerInformation
                                    listComment.add(answerInformation as MutableList<AnswerInformation>)
                                }
                            }
                            Flag.curVersion = netLocalData.version
                            adapter?.setListComment(listComment)
                            Log.d("javed","nochange finish")
                        }else{
                            //把网络数据插入到数据库中
                            netLocalData.let {
                                //评论数据
                                listComment = mutableListOf()
                                //插入到数据库的数据
                                val list : MutableList<LocalData> = mutableListOf()
                                for (data in it.data){
                                    //插入到数据库的数据
                                    val localData : LocalData = LocalData(
                                        data.recordId,
                                        data.imageId,
                                        data.userId,
                                        data.userPhoto,
                                        data.userName,
                                        data.time,
                                        data.title,
                                        data.describe
                                    )
                                    list.add(localData)
                                    //评论数据
                                    val answerInformation : List<AnswerInformation> = data.answerInformation
                                    listComment.add(answerInformation as MutableList<AnswerInformation>)
                                }
                                thread {
                                    //1.清楚原先旧数据
                                    viewModel.getLocalDataRepository().deleteLocalData()
                                    //2.插入新数据
                                    viewModel.getLocalDataRepository().insertLocalData(list)
                                    //3.更新版本
                                    Flag.curVersion = it.version
                                }
                                Log.d("javed","change finish")
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<NetLocalData>, t: Throwable) {
                    Log.d("javed", "onFailure")
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSquareBinding.inflate(inflater,container,false)


        binding.squareRcv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter = WaterFallAdapter(0)
        adapter?.setOnItemClickListenerWithComment(object : OnItemClickListenerWithComment {
            override fun onItemClick(
                recyclerView: RecyclerView,
                view: View,
                position: Int,
                localData: LocalData,
                list: MutableList<AnswerInformation>
            ) {
                //判断用户是否已经登录    ->  未登录需要先登录
                if (Flag.user == null){
                    val intent = Intent(requireContext(), UserRelatedActivity::class.java)
                    requireContext().startActivity(intent)
                }else{
                    val commentList = arrayListOf<AnswerInformation>()
                    for (data in list){
                        commentList.add(data)
                    }

                    val intent = Intent(requireContext(),SquareDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("commentList",commentList)
                    bundle.putParcelable("data",localData)

                    intent.putExtra("bundle",bundle)
                    requireContext().startActivity(intent)
                }
            }
        })


        binding.squareRcv.adapter = adapter

        viewModel.data.observe(requireActivity(),object : Observer<List<LocalData>>{
            override fun onChanged(list: List<LocalData>) {
                adapter?.setListLocalData(list)
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setMainScrollToolBar(0)
    }


    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}