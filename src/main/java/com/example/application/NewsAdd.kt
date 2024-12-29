package com.example.application

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.application.databinding.NewsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsAdd : AppCompatActivity() {
    private val REQUEST_CODE_STORAGE_PERMISSION = 100
    private lateinit var newsBinding: NewsBinding
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var launcher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsBinding = NewsBinding.inflate(layoutInflater)
        setContentView(newsBinding.root)
        fireStore = Firebase.firestore





        newsBinding.buttonCreateNews.setOnClickListener {
            val newsTitle = newsBinding.editTitleNews.text.toString()
            val newsDesc = newsBinding.editDescNews.text.toString()
            val newsTotal = News(newsTitle,newsDesc)
            fireStore.collection("news").document().set(newsTotal)
        }


//        checkPermissionStorage()
//        newsBinding.choosePhotoText.setOnClickListener {
//            pickImage()
//        }

//        launcher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == RESULT_OK) {
//                    result.data?.data?.let { uri ->
//                        newsBinding.imageView2.setImageURI(uri)
//                        val newsTitle = newsBinding.editTitleNews.text.toString()
//                        val newsDesc = newsBinding.editDescNews.text.toString()
//                        sendToFirestore(uri, newsTitle, newsDesc)
//                    }
//                }
//            }
//    }
//
//    private fun sendToFirestore(imageUri: Uri, newsTitle: String, newsDesc: String) {
//        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//        val data = baos
//
//        val news = hashMapOf(
//            "title" to newsTitle,
//            "description" to newsDesc,
//            "image" to data.toList()
//        )
//
//        fireStore.collection("news").add(news)
//    }
//
//    private fun pickImage() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        launcher.launch(intent)
//    }


        fun checkPermissionStorage() {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), REQUEST_CODE_STORAGE_PERMISSION
                )
            }
        }


    }
}