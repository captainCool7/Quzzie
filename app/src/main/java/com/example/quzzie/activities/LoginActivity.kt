package com.example.quzzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quzzie.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var editTextEmail: EditText
    lateinit var editTextPass: EditText
    lateinit var LoginBtn: Button
    lateinit var SignupBtn:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.login_email_address)
        editTextPass = findViewById(R.id.login_password)
        LoginBtn = findViewById(R.id.btn_login)
        SignupBtn = findViewById(R.id.btn_signup)
        LoginBtn.setOnClickListener {
            loginUser()
        }

        SignupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun loginUser(){
        val email = editTextEmail.text.toString()
        val password = editTextPass.text.toString()
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful()){
                    val intent =  Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}