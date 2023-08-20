package com.example.quizzmyapp

import QuizListFragment
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.quizzmyapp.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.Api.RandomQAReponseItem
import com.example.quizzmyapp.Fragments.SelectedQuestionsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<*>
    private var value = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        goToFragment(QuizListFragment())


        //Revisar
        val quizInfo = intent.getSerializableExtra("quizInfo") as? QuizzesResponse.QuizzesResponseItem

        if (quizInfo != null) {
            val fragment = SelectedQuestionsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("questionInfo", quizInfo)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }



    }
//Error
    fun onUserAnswer(selectedAnswer: RandomQAReponseItem.AllAnswer) {
        // Aquí puedes implementar la lógica para manejar la respuesta del usuario
        // y mostrar la siguiente pregunta si es correcta
    }

    fun FragmentActivity.findNavigationController(@IdRes host: Int): NavController {
        try {
            val navHostFragment = supportFragmentManager.findFragmentById(host) as NavHostFragment
            return navHostFragment.findNavController()
        } catch (e: Exception) {
            throw IllegalStateException("Activity $this does not have a NavController set on $host")
        }
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}