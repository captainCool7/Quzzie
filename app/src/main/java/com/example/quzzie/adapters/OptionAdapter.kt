package com.example.quzzie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quzzie.R
import com.example.quzzie.models.Question

class OptionAdapter(val context: Context,val question: Question):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {
    private var options : List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
    inner class OptionViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        //TODO
        var optionView = itemView.findViewById<TextView>(R.id.optionView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return  OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
    }

    override fun getItemCount(): Int {
        return options.size
    }
}