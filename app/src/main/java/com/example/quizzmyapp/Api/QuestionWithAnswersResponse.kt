package com.example.quizzmyapp.Api


import com.google.gson.annotations.SerializedName

data class QuestionWithAnswersResponse(
    val answers: List<Answer>,
    val question: Question
) {
    data class Answer(
        val answerId: Int,
        val answerText: String,
        val isCorrect: Boolean,
        val question: Question
    ) {
        data class Question(
            val questionId: Int,
            val questionText: String,
            val quiz: Quiz
        ) {
            data class Quiz(
                val quizId: Int,
                val quizName: String
            )
        }
    }

    data class Question(
        val questionId: Int,
        val questionText: String,
        val quiz: Quiz
    ) {
        data class Quiz(
            val quizId: Int,
            val quizName: String
        )
    }
}