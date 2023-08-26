package com.example.quizzmyapp.Api

import java.io.Serializable


data class RandomQAReponse(
    val allAnswers: List<AllAnswer>,
    val correctAnswerId: Int,
    val questionId: Int,
    val questionText: String,
    val quizId: Int,
    val quizName: String
) : Serializable {
    data class AllAnswer(
        val answerId: Int, val answerText: String, val isCorrect: Boolean
    ) : Serializable
}