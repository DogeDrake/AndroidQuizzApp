package com.example.quizzmyapp.Api

import java.io.Serializable

data class AllAnswer(
    val answerId: Int,
    val answerText: String,
    val isCorrect: Boolean,
    val question: Question
): Serializable