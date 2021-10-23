package com.example.quzzie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quzzie.R
import com.example.quzzie.models.Question

class OptionAdapter(val context: Context,val question: Question):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {
    private var options : List<String> = listOf(question.option1,question.option2,question.option3,question.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return  OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionText.text = options[position]
        holder.itemView.setOnClickListener{
            Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show();
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position]){
//            Toast.makeText(context,"SHOW RED Border",Toast.LENGTH_SHORT).show()
            holder.optionView.setBackgroundResource(R.drawable.selected_option)
        }else{
//            Toast.makeText(context,"SHOW SIMPLE border",Toast.LENGTH_SHORT).show()
            holder.optionView.setBackgroundResource(R.drawable.simple_option)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var optionText = itemView.findViewById<TextView>(R.id.optionText)
        var optionView = itemView.findViewById<LinearLayout>(R.id.optionView)
    }
}