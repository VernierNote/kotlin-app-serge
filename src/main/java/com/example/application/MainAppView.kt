package com.example.application

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application.databinding.ActivityMainAppBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainAppView : AppCompatActivity() {

    private lateinit var mainAppBinding: ActivityMainAppBinding
    private lateinit var firestore: FirebaseFirestore

    var adapter = NewsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainAppBinding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(mainAppBinding.root)
        firestore = Firebase.firestore

        mainAppBinding.recycleNews.layoutManager = LinearLayoutManager(this)
        mainAppBinding.recycleNews.adapter = adapter

       firestore.collection("news").get().addOnSuccessListener{ querySnapshot->
           adapter.clearNews()
           for (document in querySnapshot){
               val title = document.getString("title")
               val description = document.getString("description")

                   val news = News(title, description)
                   adapter.addNews(news)
                   adapter.notifyDataSetChanged()

           }
       }



        mainAppBinding.buttonAddNews.setOnClickListener {
            val intent = Intent(this,NewsAdd::class.java)
            startActivity(intent)
        }


    }

}
