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

class SignUpActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var editTextEmail :EditText
    lateinit var editTextPassword :EditText
    lateinit var editTextConfirmPassword :EditText
    lateinit var btnSignUp : Button
    lateinit var btnLogIn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.signup_email_address)
        editTextPassword = findViewById(R.id.signup_password)
        editTextConfirmPassword = findViewById(R.id.signup_confirm_password)
        btnSignUp = findViewById(R.id.btn_signup)
        btnLogIn = findViewById(R.id.btn_login)

        btnLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp.setOnClickListener{
            signupUser()
        }
    }

    private fun signupUser(){
        val email: String = editTextEmail.text.toString()
        val password : String = editTextPassword.text.toString()
        val confirmPassword : String = editTextConfirmPassword.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(this,"Email and Password cannot be blank",Toast.LENGTH_SHORT).show()
            return
        }

        if(password!=confirmPassword){
            Toast.makeText(this,"Password and Confirm password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Successfully created New User",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Error creating New User",Toast.LENGTH_SHORT).show()
                }
            }

    }
}