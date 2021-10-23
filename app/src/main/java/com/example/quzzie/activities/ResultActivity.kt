package com.example.quzzie.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quzzie.R
import com.example.quzzie.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        val quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
    }
    //3:21
}