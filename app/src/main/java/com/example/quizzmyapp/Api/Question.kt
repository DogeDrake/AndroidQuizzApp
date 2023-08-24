package com.example.quizzmyapp.Api

import java.io.Serializable

data class Question(
    val questionId: Int,
    val questionText: String,
    val quiz: QuizXX
): Serializable