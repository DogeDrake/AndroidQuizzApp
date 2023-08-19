package com.example.quizzmyapp.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.R

class SelectedQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recetasBundle =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(
                    "questionInfo", //Key del Serializable de la clase AgentFragment
                    QuizzesResponse.QuizzesResponseItem::class.java
                ) as? QuizzesResponse.QuizzesResponseItem
            } else {
                arguments?.getSerializable("questionInfo") as? QuizzesResponse.QuizzesResponseItem
            }
    }


}