package com.example.quizzmyapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Adapters.RandomQAAdapter
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.Api.RandomQAReponseItem
import com.example.quizzmyapp.R

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedQuestionsFragment : Fragment(), RandomQAAdapter.OnItemClickListener {

    private val TAG = "SelectedQuestions"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RandomQAAdapter
    private val datos: ArrayList<RandomQAReponseItem> = ArrayList()
    private var currentQuestionIndex = 0
    private lateinit var currentQuestion: RandomQAReponseItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recetasBundle = arguments?.getSerializable("questionInfo") as? QuizzesResponse.QuizzesResponseItem

        adapter = RandomQAAdapter(datos, this)
        recyclerView = view.findViewById(R.id.recyclerViewAnswers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadRandomQuestionsAndAnswers(recetasBundle?.quizId ?: 0)
    }

    private fun loadRandomQuestionsAndAnswers(quizId: Int) {
        val call = ApiRest.service.getRandomQuestionsAndAnswers(quizId)
        call.enqueue(object : Callback<List<RandomQAReponseItem>> {
            override fun onResponse(
                call: Call<List<RandomQAReponseItem>>,
                response: Response<List<RandomQAReponseItem>>
            ) {
                if (response.isSuccessful) {
                    val randomQAResponse = response.body()
                    randomQAResponse?.let {
                        datos.addAll(it)
                        if (datos.isNotEmpty()) {
                            currentQuestion = datos[currentQuestionIndex]
                            adapter.setAnswers(currentQuestion)
                        }
                    }
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Error en la llamada")
                }
            }

            override fun onFailure(
                call: Call<List<RandomQAReponseItem>>,
                t: Throwable
            ) {
                Log.e(TAG, t.message ?: "Error en la llamada")
            }
        })
    }
    //Error
    override fun onItemClick(selectedAnswer: RandomQAReponseItem.AllAnswer) {
        if (selectedAnswer.isCorrect) {
            currentQuestionIndex++
            if (currentQuestionIndex < datos.size) {
                currentQuestion = datos[currentQuestionIndex]
                adapter.setAnswers(currentQuestion)
                resetUI()
            }
        }
    }
    //Error
    private fun resetUI() {
        adapter.clearSelection()
    }
}
