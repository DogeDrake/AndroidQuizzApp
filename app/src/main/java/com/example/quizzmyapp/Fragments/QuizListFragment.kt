import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Adapters.QuizAdapter
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizListFragment : Fragment() {

    val TAG = "MainActivity"
    var datos: ArrayList<QuizzesResponse.QuizzesResponseItem> = ArrayList()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        loadQuizzes()

        adapter = QuizAdapter(datos) { question ->
            activity?.let {
                val fragment = QuizFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("questionInfo", question)

                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.replace(R.id.container, fragment)?.commit()
            }
        }
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter


    }

    private fun loadQuizzes() {
        val call = ApiRest.service.getQuizzes()
        call.enqueue(object : Callback<QuizzesResponse> {
            override fun onResponse(
                call: Call<QuizzesResponse>,
                response: Response<QuizzesResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    datos.clear()
                    datos.addAll(body)
                    Log.i(TAG, datos.toString())
                    for (a in datos) {
                        Log.i(TAG, "entroooo!!!!")
                        //InfoRutinas.add(a.attributes.titulorutina)
                        //InfoRutinas.add(a.attributes.publishedAt)
                    }
                    // Imprimir aqui el listado con logs
                    //Set Data Adapter Method
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Porto")
                }
            }

            override fun onFailure(
                call: Call<QuizzesResponse>,
                t: Throwable
            ) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}
