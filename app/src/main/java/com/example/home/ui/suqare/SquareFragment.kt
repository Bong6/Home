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
import com.example.home.adapter.WaterFallAdapter
import com.example.home.base.BaseFragment
import com.example.home.databinding.FragmentSquareBinding
import com.example.home.database.entity.LocalData
import com.example.home.net.GetInformationAPI
import com.example.home.net.entity.AnswerInformation
import com.example.home.net.entity.NetLocalData
import com.example.home.ui.squaredetail.SquareDetailActivity
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
    private lateinit var listComment : MutableList<MutableList<AnswerInformation>>


    companion object{
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
        //模仿网络操作
        APIClient.mInstance.create(GetInformationAPI::class.java).getAction().enqueue(
            object : Callback<NetLocalData> {
                override fun onResponse(
                    call: Call<NetLocalData>,
                    response: Response<NetLocalData>
                ) {
                    if (response.body() != null){
                        val jsonData = GetInformation.getNetLocalData()
                        val gson = Gson()
                        val netLocalData : NetLocalData = gson.fromJson(jsonData, NetLocalData::class.java)

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
                        }else{
                            //把网络数据插入到数据库中
                            netLocalData.let {
                                val list : MutableList<LocalData> = mutableListOf()
                                for (data in it.data){
                                    val localData : LocalData = LocalData(
                                        data.recordId,
                                        data.imageId.toInt(),
                                        data.userId,
                                        data.userPhoto.toInt(),
                                        data.userName,
                                        data.time,
                                        data.title,
                                        data.describe
                                    )
                                    list.add(localData)
                                }
                                thread {
                                    //1.清楚原先旧数据

                                    //2.插入新数据
                                    viewModel.getLocalDataRepository().insertLocalData(list)
                                }
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<NetLocalData>, t: Throwable) {
                    Log.d("javed", "onFailure")
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSquareBinding.inflate(inflater,container,false)


        binding.squareRcv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter = WaterFallAdapter()
        adapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                recyclerView: RecyclerView,
                view: View,
                position: Int,
                localData: LocalData,
                list: MutableList<AnswerInformation>
            ) {
                //判断用户是否已经登录    ->  未登录需要先登录
                if (Flag.userId.equals(0)){
                    Navigation.findNavController(view).navigate(
                        R.id.action_square_to_login
                    )
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
                adapter?.setAllBean(list)
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