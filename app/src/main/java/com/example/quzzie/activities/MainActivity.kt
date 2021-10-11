package com.example.quzzie.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quzzie.R
import com.example.quzzie.adapters.QuizAdapter
import com.example.quzzie.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var quizRecycleView : RecyclerView
    lateinit var  firestore : FirebaseFirestore
    private var quizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizRecycleView = findViewById(R.id.quiz_recyclerView)
        //populateData()
        setupViwes()
        setupFirestore()
    }

    private fun setupFirestore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener{
            value, error ->
            if(value ==null || error !=null){
                Toast.makeText(this,"Error while fetching the data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
//            Log.d("qapp", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }
//    private fun populateData(){
//        quizList.add(Quiz("07-10-2021","07-10-2021"))
//        quizList.add(Quiz("08-10-2021","08-10-2021"))
//        quizList.add(Quiz("09-10-2021","09-10-2021"))
//        quizList.add(Quiz("10-10-2021","10-10-2021"))
//        quizList.add(Quiz("11-10-2021","11-10-2021"))
//    }

    private fun setupViwes() {
        setUpDrawerLayout()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = QuizAdapter(this,quizList)
        quizRecycleView.layoutManager = GridLayoutManager(this,2)
        quizRecycleView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(findViewById(R.id.appBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, findViewById(R.id.main_drawer),
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}