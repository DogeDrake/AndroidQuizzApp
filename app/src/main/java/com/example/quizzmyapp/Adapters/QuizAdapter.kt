package com.example.quizzmyapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.R

class QuizAdapter : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private val quizzes = mutableListOf<QuizzesResponse.QuizzesResponseItem>()

    fun setData(data: List<QuizzesResponse.QuizzesResponseItem>) {
        quizzes.clear()
        quizzes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        return QuizViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizzes[position]
        holder.bind(quiz)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewQuizName: TextView = itemView.findViewById(R.id.textViewQuizName)

        fun bind(quiz: QuizzesResponse.QuizzesResponseItem) {
            textViewQuizName.text = quiz.quizName
            // Set an onClickListener here if you want to handle item clicks
        }
    }
}
