package com.example.quzzie.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quzzie.R
import com.example.quzzie.models.Quiz
import com.example.quzzie.utils.ColorPicker
import com.example.quzzie.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes: List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_element,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text  = quizzes[position].title
        holder.cardContainer.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.imgView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.quiz_title)
        var imgView: ImageView = itemView.findViewById(R.id.quiz_img)
        var cardContainer: CardView = itemView.findViewById(R.id.element_card)
    }
}