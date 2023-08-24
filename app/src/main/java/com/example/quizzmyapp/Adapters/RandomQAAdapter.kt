import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Adapters.QuizAdapter
import com.example.quizzmyapp.Api.AllAnswer
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.Api.RandomQAReponse
import com.example.quizzmyapp.R

class RandomQAAdapter(
    private val datos: ArrayList<RandomQAReponse>,
    /*val onClick: (RandomQAReponse) -> Unit*/
) : RecyclerView.Adapter<RandomQAAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val item = datos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = datos.size

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewQuestion = itemView.findViewById<TextView>(R.id.textViewQuestion)
        var card = itemView.findViewById<CardView>(R.id.CardAnswer)
        var textViewAnswer = itemView.findViewById<TextView>(R.id.textViewAnswer)

        fun bind(item: RandomQAReponse) {
            Log.e("Se escribe", "Se escribe " + item.questionText)

           // textViewQuestion.text = item.questionText
            // Suponiendo que las respuestas se almacenan en allAnswers
            // Aquí solo se muestra la primera respuesta, puedes ajustarlo según tus necesidades
            if (item.allAnswers.isNotEmpty()) {
                textViewAnswer.text = item.allAnswers[0].answerText
            } else {
                textViewAnswer.text = "No hay respuestas disponibles"
            }

            card.setOnClickListener {
                //onClick(item)
            }
        }
    }
}
