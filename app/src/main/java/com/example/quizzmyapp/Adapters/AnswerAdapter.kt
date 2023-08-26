import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.RandomQAReponse
import com.example.quizzmyapp.R

class AnswerAdapter(private val answerList: MutableList<RandomQAReponse.AllAnswer>) :
    RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_answer, parent, false)
        return AnswerViewHolder(view)
    }

    fun setData(data: List<RandomQAReponse.AllAnswer>) {
        answerList.clear()
        answerList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = answerList[position]
        holder.bind(answer)
    }

    override fun getItemCount(): Int = answerList.size

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answerText = itemView.findViewById<TextView>(R.id.answerText)
       // val questionrText = itemView.findViewById<TextView>(R.id.questionText)
        fun bind(answer: RandomQAReponse.AllAnswer) {
           // questionrText.text = answer.answerText

            answerText.text = answer.answerText

            // Puedes agregar más lógica aquí para mostrar otros detalles de la respuesta en la tarjeta
        }
    }
}
