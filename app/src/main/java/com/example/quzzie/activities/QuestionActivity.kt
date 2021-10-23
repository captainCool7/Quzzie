package com.example.quzzie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quzzie.R
import com.example.quzzie.adapters.OptionAdapter
import com.example.quzzie.models.Question
import com.example.quzzie.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    lateinit var description: TextView
    lateinit var optionList: RecyclerView
    lateinit var prevBtn:Button
    lateinit var nextBtn:Button
    lateinit var submitBtn:Button
    var quizzes : MutableList<Quiz>? = null
    var questions : MutableMap<String, Question>? = null
    var index=1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        description = findViewById(R.id.description)
        optionList  = findViewById(R.id.options)
        prevBtn = findViewById(R.id.prevButton)
        nextBtn = findViewById(R.id.nextButton)
        submitBtn = findViewById(R.id.submitButton)
        setupFirestore()
        setupEventListener()
    }

    private fun setupEventListener() {
        prevBtn.setOnClickListener {
            index--;
            bindView()
        }

        nextBtn.setOnClickListener {
            index++;
            bindView()
        }

        submitBtn.setOnClickListener {
            Log.d("Finalquiz",questions.toString())

            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setupFirestore() {
        val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("Date")
        if(date!=null){
            val collectionReference = firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it!=null && !it.isEmpty){
                        Log.d("DATA",it.toObjects(Quiz::class.java).toString())
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindView()
                    }
                }
        }
    }

    private fun bindView(){
        prevBtn.visibility = View.GONE
        nextBtn.visibility = View.GONE
        submitBtn.visibility = View.GONE

        if(index==1){
            nextBtn.visibility=View.VISIBLE
        }
        else if(index == questions!!.size){
            submitBtn.visibility = View.VISIBLE
            prevBtn.visibility = View.VISIBLE
        }else{
            prevBtn.visibility = View.VISIBLE
            nextBtn.visibility = View.VISIBLE
        }
        val question = questions!!["question$index"]
        question?.let {
            description.text = it.description
            val optionAdapter = OptionAdapter(this,it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }
    }
}