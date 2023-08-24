import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.AllAnswer
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.Api.RandomQAReponse
import com.example.quizzmyapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedQuestionsFragment : Fragment() {

    private val TAG = "SelectedQuestionsFragme"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RandomQAAdapter
    private val datos: ArrayList<RandomQAReponse> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recetasBundle =
            arguments?.getSerializable("questionInfo") as? QuizzesResponse.QuizzesResponseItem

        loadRandomQuestionsAndAnswers(recetasBundle!!.quizId)



        adapter = RandomQAAdapter(datos)


        recyclerView = view.findViewById(R.id.recyclerViewAnswers)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter


    }

    private fun loadRandomQuestionsAndAnswers(quizId: Int) {
        val call = ApiRest.service.getRandomQuestionsAndAnswers(quizId)
        call.enqueue(object : Callback<RandomQAReponse> {
            override fun onResponse(
                call: Call<RandomQAReponse>,
                response: Response<RandomQAReponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    // Limpia la lista actual de datos

                    // Agrega el objeto completo a la lista
                    datos.add(body)
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e(
                        TAG,
                        "Error response: ${response.errorBody()?.string() ?: "Error en la llamada"}"
                    )
                }
            }

            override fun onFailure(
                call: Call<RandomQAReponse>,
                t: Throwable
            ) {
                Log.e(TAG, "API call failed: ${t.message ?: "Error en la llamada"}")
            }
        })
    }


}


