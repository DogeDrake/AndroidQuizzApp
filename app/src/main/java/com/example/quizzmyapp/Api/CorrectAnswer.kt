package com.example.quizzmyapp.Api

import java.io.Serializable

data class CorrectAnswer(
    val answerId: Int,
    val answerText: String,
    val isCorrect: Boolean,
    val question: Question
): Serializable