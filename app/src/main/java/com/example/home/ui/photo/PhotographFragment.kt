package com.example.home.ui.photo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.home.databinding.FragmentPhotographBinding
import com.example.home.test.Case24
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-03-26 0:10
*/

class PhotographFragment : Fragment() {

    //拍照
    private var imageUri: Uri? = null
    //拍照    ->  图片保存的路径
    private val filePath =
        Environment.getExternalStorageDirectory().toString() + File.separator + "output_image.jpg"
    //选择
    private var imagePath: String? = null

    private lateinit var binding : FragmentPhotographBinding


    companion object{
        fun newInstance() : PhotographFragment {
            val fragment = PhotographFragment()
            return fragment
        }

        //声明一个请求码，用于识别返回的结果
        val TAKE_PHOTO = 1
        val CHOOSE_PHOTO = 2
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotographBinding.inflate(inflater,container,false)


        binding.takePhoto.setOnClickListener {
            //请求相机权限
            requestPermission(TAKE_PHOTO)
        }

        binding.usePhoto.setOnClickListener {
            //请求相册权限
            requestPermission(CHOOSE_PHOTO)
        }

        //设置默认图片
        setDefualtImage();

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //设置再次app时显示的图片
        val sp: SharedPreferences = requireContext().getSharedPreferences("sp_img", Context.MODE_PRIVATE)
        //取出上次存储的图片路径设置此次的图片展示
        val beforeImagePath = sp.getString("imgPath", null)
        displayImage(beforeImagePath)
    }


    private fun setDefualtImage() {
        val outputImage = File(filePath)
        if (!outputImage.exists()) {
            return
        }
        binding.picture.setImageBitmap(BitmapFactory.decodeFile(filePath))
    }

    //申请权限
    private fun requestPermission(requestCode: Int) {
        when(requestCode){
            TAKE_PHOTO -> {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    //请求权限
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                        1
                    )
                } else {
                    //调用
                    requestCamera()
                }
            }

            CHOOSE_PHOTO -> {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1
                    )
                } else {
                    openAlbum()
                }
            }

            else -> {

            }
        }
    }

    //申请权限的结果
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
                Toast.makeText(requireContext(), "你拒绝了该权限", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    //打开相册
    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, Case24.CHOOSE_PHOTO)
    }


    //使用系统相机
    private fun requestCamera() {
        val outputImage = File(filePath)

        try{
            //如果目录不存在就创建
            if (!outputImage.parentFile.exists()) {
                outputImage.parentFile.mkdirs()
            }
            //如果目录存在就删除
            if (outputImage.exists()) {
                outputImage.delete()
            }
            //创建新文件
            outputImage.createNewFile()

            imageUri = if (Build.VERSION.SDK_INT >= 24) {
                FileProvider.getUriForFile(
                    requireContext(),
                    "com.example.mydemo.fileprovider", outputImage
                )
            } else {
                Uri.fromFile(outputImage)
            }
            //使用隐示的Intent，系统会找到与它对应的活动，即调用摄像头，并把它存储
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, PhotographFragment.TAKE_PHOTO)
            //调用会返回结果的开启方式，返回成功的话，则把它显示出来
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    //对返回结果的处理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            TAKE_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                try {
                    val bitmap =
                        BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(
                            imageUri!!
                        ))

                    //将图片解析成Bitmap对象，并把它显现出来
                    binding.picture.setImageBitmap(bitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }

            CHOOSE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data)
                    }
                }
            }

            else -> {
            }
        }
    }

    //处理图片
    private fun handleImageOnKitKat(data: Intent?) {
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(requireContext(), uri)) {
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
        displayImage(imagePath)

    }

    //根据图片路径显示图片
    private fun displayImage(imagePath: String?) {
        if (imagePath != null && imagePath != "") {

            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding.picture.setImageBitmap(bitmap)
            //存储上次选择的图片路径，用以再次打开app设置图片
            val sp: SharedPreferences =
                requireContext().getSharedPreferences("sp_img", Context.MODE_PRIVATE) //创建xml文件存储数据，name:创建的xml文件名
            val editor = sp.edit() //获取edit()
            editor.putString("imgPath", imagePath)
            editor.apply()

        } else {
            Toast.makeText(requireContext(), "获取图片失败", Toast.LENGTH_SHORT).show()
        }
    }

    //将选择的图片Uri转换为路径
    @SuppressLint("Range")
    private fun getImagePath(uri: Uri, selection: String?): String? {
        var path: String? = null
        //通过Uri和selection来获取真实的图片路径
        val cursor: Cursor? = requireContext().getContentResolver().query(uri, null, selection, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }
}