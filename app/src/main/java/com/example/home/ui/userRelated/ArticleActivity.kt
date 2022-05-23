package com.example.home.ui.userRelated

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.home.R
import com.example.home.databinding.ActivityArticleBinding
import com.example.home.test.Case24


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-05-21 11:31
*/

class ArticleActivity : AppCompatActivity(),View.OnClickListener {

    private var imagePath : String ?= null
    private lateinit var binding : ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article)

        binding.articleCancelTxt.setOnClickListener(this)
        binding.articlImg.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.article_cancel_txt ->{
                val alert = AlertDialog.Builder(this)
                    .setTitle("将本次编辑保留？")
                    .setPositiveButton("不保留",object  : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            //将数据删除
                            val sp = getSharedPreferences("article_img", MODE_PRIVATE)
                            val editor = sp.edit()
                            editor.remove("imgPath")
                            editor.remove("title")
                            editor.remove("content")
                            editor.apply()
                            finish()
                        }
                    })
                    .setNegativeButton("保留",object  : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            //存储本次选择的图片路径，用以再次打开app设置图片
                            val sp = getSharedPreferences("article_img", MODE_PRIVATE)
                            val editor = sp.edit() //获取edit()
                            editor.putString("imgPath", imagePath)
                            editor.putString("title",binding.articleTitleTxt.text.toString())
                            editor.putString("content",binding.articleContentTxt.text.toString())
                            editor.apply()
                            finish()
                        }
                    })
                    .show()
            }

            R.id.articl_img ->{
                requestPermissino()
            }

            else    ->{

            }
        }
    }

    private fun requestPermissino() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            openAlbum()
        }
    }

    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, Case24.CHOOSE_PHOTO) //打开相册
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum()
            } else {
                Toast.makeText(this, "你拒绝了该权限", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Case24.CHOOSE_PHOTO -> if (resultCode == RESULT_OK) {
                //判断手机系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    //4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data)
                }
            }
            else -> {
            }
        }
    }

    private fun handleImageOnKitKat(data: Intent?) {
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，则通过document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri!!.authority) {
                val id = docId.split(":").toTypedArray()[1] //解析出数字格式的id
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri!!.authority) {
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public downloads"),
                    java.lang.Long.valueOf(docId)
                )
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = getImagePath(uri, null)
        } else if ("file".equals(uri!!.scheme, ignoreCase = true)) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri!!.path
        }
        displayImage(imagePath) //根据图片路径显示图片
    }



    @SuppressLint("Range")
    private fun getImagePath(uri: Uri, selection: String?): String? {
        var path: String? = null
        //通过Uri和selection来获取真实的图片路径
        //通过Uri和selection来获取真实的图片路径
        val cursor = contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    private fun displayImage(imagePath: String?) {
        if (imagePath != null && imagePath != "") {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding.articlImg.setImageBitmap(bitmap)
        }
    }

    override fun onResume() {
        super.onResume()
        //设置再次app时显示的图片
        val sp = getSharedPreferences("article_img", MODE_PRIVATE)
        sp?.let {
            //取出上次存储的图片路径设置此次的图片展示
            val beforeImagePath = sp.getString("imgPath", null)
            displayImage(beforeImagePath)
            binding.articleTitleTxt.setText(sp.getString("title",null))
            binding.articleContentTxt.setText(sp.getString("content",null))
        }
    }
}