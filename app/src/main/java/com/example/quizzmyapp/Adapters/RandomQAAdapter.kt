package com.example.quizzmyapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.RandomQAReponseItem
import com.example.quizzmyapp.R

class RandomQAAdapter(
    private val data: List<RandomQAReponseItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RandomQAAdapter.ViewHolder>() {
    //Error
    private var selectedAnswer: RandomQAReponseItem.AllAnswer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardAnswer = itemView.findViewById<CardView>(R.id.CardAnswer)
        private val textViewAnswer = itemView.findViewById<TextView>(R.id.textViewAnswer)
        //Error
        fun bind(item: RandomQAReponseItem) {
            textViewAnswer.text = item.answerText

            cardAnswer.setOnClickListener {
                selectedAnswer = item.allAnswers[adapterPosition]
                notifyDataSetChanged()
                listener.onItemClick(selectedAnswer!!)
            }

            if (selectedAnswer != null && selectedAnswer!!.answerId == item.allAnswers[0].answerId) {
                cardAnswer.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
            } else {
                cardAnswer.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
        }
    }
    //Error
    interface OnItemClickListener {
        fun onItemClick(selectedAnswer: RandomQAReponseItem.AllAnswer)
    }

    fun setAnswers(question: RandomQAReponseItem) {
        selectedAnswer = null
        notifyDataSetChanged()
    }
}
