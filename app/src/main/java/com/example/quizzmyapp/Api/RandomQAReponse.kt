package com.example.quizzmyapp.Api
import java.io.Serializable
data class RandomQAReponse(
    val allAnswers: List<AllAnswer>,
    val correctAnswer: CorrectAnswer,
    val questionId: Int,
    val questionText: String,
    val quiz: QuizXX
):Serializable