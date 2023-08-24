package com.example.quizzmyapp.Api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    /*
    @POST("questions")
    fun createQuestion(@Body question: Question): Call<Question>

    @GET("{questionId}")
    fun getQuestion(@Path("questionId") questionId: Long): Call<Question>

    @GET("questions/{questionId}/details")
    fun getQuestionWithAnswers(@Path("questionId") questionId: Long): Call<QuestionWithAnswers>
    */

    /*
    @POST("questions/{questionId}/answers")
    fun createAnswerForQuestion(
        @Path("questionId") questionId: Long,
        @Body answer: QuestionWithAnswersResponse.Answer
    ): Call<QuestionWithAnswersResponse.Answer>
     */

    @GET("quizzes/quizzes")
    fun getQuizzes(): Call <QuizzesResponse>


    @GET("questions/{questionId}/answers")
    fun getAnswersForQuestion(@Path("questionId") questionId: Long): Call<List<QuestionWithAnswersResponse.Answer>>


    @GET("quizzes/quizzes/{quizId}/themed-random-question")
    fun getRandomQuestionsAndAnswers(
        @Path("quizId") quizId: Int
    ): Call<RandomQAReponse>





}
