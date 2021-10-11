package com.example.quzzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quzzie.R
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    lateinit var startBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        startBtn = findViewById(R.id.login_start)

        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            Toast.makeText(this,"User is already logged in",Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        startBtn.setOnClickListener {
//            val intent = Intent(this,LoginActivity::class.java)
//            startActivity(intent)
//            finish()
            redirect("LOGIN")
        }
    }

    private fun redirect(name: String){
        val intent = when(name){
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}