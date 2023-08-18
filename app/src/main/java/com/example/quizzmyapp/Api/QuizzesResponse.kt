package com.example.quizzmyapp.Api


import com.google.gson.annotations.SerializedName

class QuizzesResponse : ArrayList<QuizzesResponse.QuizzesResponseItem>(){
    data class QuizzesResponseItem(
        val quizId: Int,
        val quizName: String
    )
}