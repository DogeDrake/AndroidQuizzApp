package com.example.quizzmyapp.Api


import java.io.Serializable
data class AllAnswer(
    val answerId: Int,
    val answerText: String,
    val isCorrect: Boolean,
    val question: Question
):Serializable

data class CorrectAnswer(
    val answerId: Int,
    val answerText: String,
    val isCorrect: Boolean,
    val question: Question
):Serializable


data class Question(
    val questionId: Int,
    val questionText: String,
    val quiz: QuizXX
):Serializable


data class QuizXX(
    val quizId: Int,
    val quizName: String
):Serializable


data class RandomQAReponseItem(
    var allAnswers: List<AllAnswer>,
    val correctAnswer: CorrectAnswer,
    val questionId: Int,
    val questionText: String,
    val quiz: QuizXX
):Serializable

