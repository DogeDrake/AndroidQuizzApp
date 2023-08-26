import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.Api.RandomQAReponse
import com.example.quizzmyapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnswerAdapter
    val TAG = "QuizFragment"
    var datos: List<RandomQAReponse.AllAnswer> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        val recetasBundle =
            arguments?.getSerializable("questionInfo") as? QuizzesResponse.QuizzesResponseItem

        recyclerView = view.findViewById(R.id.recyclerViewRespuestas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = AnswerAdapter(datos.toMutableList())
        recyclerView.adapter = adapter

        loadQuizQuestion(recetasBundle!!.quizId)

        return view
    }

    private fun loadQuizQuestion(id: Int) {
        val call = ApiRest.service.getQuizQuestion(id)
        call.enqueue(object : Callback<RandomQAReponse> {
            override fun onResponse(
                call: Call<RandomQAReponse>,
                response: Response<RandomQAReponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    datos = body.allAnswers // Actualiza los datos con las respuestas obtenidas
                    adapter.setData(datos) // Establece los datos en el adaptador
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Porto")
                }
            }

            override fun onFailure(
                call: Call<RandomQAReponse>,
                t: Throwable
            ) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}
