package com.example.quizzmyapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.R

class QuizAdapter(
    private val datos: ArrayList<QuizzesResponse.QuizzesResponseItem>,
    val onClick: (QuizzesResponse.QuizzesResponseItem) -> Unit
) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private val quizzesList: ArrayList<QuizzesResponse.QuizzesResponseItem> = ArrayList()

    fun setData(data: List<QuizzesResponse.QuizzesResponseItem>) {
        quizzesList.clear()
        quizzesList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = datos.get(position)
        holder.bind(quiz)
    }

    override fun getItemCount(): Int =
        datos.size


    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewQuizName: TextView = itemView.findViewById(R.id.textViewQuizName)
        private val card = itemView.findViewById<CardView>(R.id.CardTema)

        fun bind(quiz: QuizzesResponse.QuizzesResponseItem) {
            Log.e("Se escribe", "Se escribe " + quiz.quizName)
            textViewQuizName.text = quiz.quizName
            card.setOnClickListener {
               onClick(quiz)
            }
        }
    }
}