package com.example.quizzmyapp.Api


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuestionWithAnswersResponse(
    val answers: List<Answer>,
    val question: Question
) {
    data class Answer(
        val answerId: Int,
        val answerText: String,
        val isCorrect: Boolean,
        val question: Question
    ) : Serializable {
        data class Question(
            val questionId: Int,
            val questionText: String,
            val quiz: Quiz
        ) : Serializable {
            data class Quiz(
                val quizId: Int,
                val quizName: String
            ) : Serializable
        }
    }

    data class Question(
        val questionId: Int,
        val questionText: String,
        val quiz: Quiz
    ) : Serializable {
        data class Quiz(
            val quizId: Int,
            val quizName: String
        ) : Serializable
    }
}