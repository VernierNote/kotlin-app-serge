package com.example.application

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.application.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var myDataBase: DatabaseReference
    private lateinit var myFireStore: FirebaseFirestore
    private lateinit var fireBaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        myDataBase = Firebase.database.reference
        myFireStore = Firebase.firestore
        fireBaseAuth = Firebase.auth

        // Получение ссылки на VideoView
//        var videoView = findViewById<VideoView>(R.id.videoView)
//
//        val videoUri = Uri.parse("android.resource://"+packageName+"/"+R.raw.video_sec)
//
//        videoView.setVideoURI(videoUri)
//        videoView.setOnPreparedListener { video ->
//            video.setVolume(0F, 0F)
//            video.isLooping = true
//        }babygravy474@gmail.com
//        videoView.start()



        mainBinding.buttonReg.setOnClickListener() {
            val userRegEmail = mainBinding.emailInput.text.toString()
            val userRegPassword = mainBinding.passwordInput.text.toString()
            val userRegNickName = mainBinding.nickInput.text.toString()
            val currentRegUserUID = fireBaseAuth.currentUser?.uid
            val userData = User(currentRegUserUID,userRegNickName,userRegEmail,userRegPassword)


            myFireStore.collection("users").document().set(userData)
            fireBaseAuth.createUserWithEmailAndPassword(userRegEmail, userRegPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainAppView::class.java)
                        intent.putExtra("email", userRegEmail)
                        intent.putExtra("nickName", userRegNickName)
                        intent.putExtra("userUID", currentRegUserUID)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Empty fields or already existing", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

        }

        mainBinding.buttonLog.setOnClickListener {
            val userLogNickName = mainBinding.nickInput.text.toString()
            val userEmailLogin = mainBinding.emailInput.text.toString()
            val userPasswordLogin = mainBinding.passwordInput.text.toString()
            val currentUserUID = fireBaseAuth.currentUser?.uid
            fireBaseAuth.signInWithEmailAndPassword(userEmailLogin, userPasswordLogin)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful && currentUserUID!!.isNotEmpty()) {
                        Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainAppView::class.java)
                        intent.putExtra("email", userEmailLogin)
                        intent.putExtra("nickName", userLogNickName)
                        intent.putExtra("logInUserUID", currentUserUID)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Wrong data", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}


//    private fun checkEmail(email: String, callback: (Boolean) -> Unit) {
//      task: Task<QuerySnapshot> ->
//        val userEmail = mainBinding.emailInput.text.toString()
//
//        myFireStore.collection("user").whereEqualTo("email", userEmail)
//            .get()
//            .addOnCompleteListener { task: Task<QuerySnapshot> ->
//                if (task.isSuccessful) {
//                    callback(task.result.documents.isNotEmpty())
//                } else {
//                    callback(false)

//                }
//            }
//    }


//    val userUniqueId = UUID.randomUUID().toString()
//    val userNickName = mainBinding.nickInput.text.toString()
//    val userEmail = mainBinding.emailInput.text.toString()
//    val userPassword = mainBinding.passwordInput.text.toString()
//    val userData = User(userUniqueId, userNickName, userEmail, userPassword)
//
//
//    checkingEmail(userEmail) { checkingEmailCallbackIsExist ->
//        if (checkingEmailCallbackIsExist) {
//            Toast.makeText(this, "Этот email уже зарегистрирован", Toast.LENGTH_SHORT).show()
//
//        } else if (userNickName.isNotEmpty() && userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
//
//            myFireStore.collection("user").document().set(userData)
//
//        } else if (userNickName == myFireStore.collection("user").whereEqualTo("nickName",userNickName).get().toString() &&
//            userEmail == myFireStore.collection("user").whereEqualTo("email",userEmail).get().toString() &&
//            userPassword == myFireStore.collection("user").whereEqualTo("password",userPassword).get().toString()){
//
//            val intent = Intent(this,MainActivity::class.java)
//            intent.putExtra("nickName",userNickName)
//            intent.putExtra("email",userEmail)
//            intent.putExtra("password",userPassword)
//
//
//
//        } else if (userNickName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
//
//            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//
//
//}
//private fun logInCheck(nickname:String,email:String,password:String,callback: (Boolean) -> Unit){
//    val userNickName = mainBinding.nickInput.text.toString()
//    val userEmail = mainBinding.emailInput.text.toString()
//    val userPassword = mainBinding.passwordInput.text.toString()
//
//    myFireStore.collection("user").whereEqualTo("")
//
//}

//package com.example.application
//                                   child("user").child(userId)
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.android.identity.util.UUID
//import com.example.application.databinding.ActivityMainBinding
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class MainActivity : AppCompatActivity() {
//
//    lateinit var mainBinding: ActivityMainBinding
//    private lateinit var myDataBase: DatabaseReference
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(mainBinding.root)
//        myDataBase = Firebase.database.reference
//    }
//
//    fun onClickAdd(view: View) {
//        val nickname = mainBinding.nickInput.text.toString()
//        val email = mainBinding.emailInput.text.toString()
//        val password = mainBinding.passwordInput.text.toString()
//
//
//        if (nickname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
//            val userId = UUID.randomUUID().toString()
//            val userData = User(userId, nickname, email, password)
//            myDataBase.child("users").child(userId).setValue(userData)
//                .addOnSuccessListener {
//                    Toast.makeText(this,"Успешное добавление",Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this,"Регистрация не удалась",Toast.LENGTH_SHORT).show()
//                }
//        }
//        else {
//            // Обработка случая, когда одно из полей пустое
//            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
//
//data class User(
//    val userId: String,
//    val nickname: String,
//    val email: String,
//    val password: String
//)
