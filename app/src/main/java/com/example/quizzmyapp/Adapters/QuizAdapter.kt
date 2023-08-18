import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzmyapp.Api.QuizzesResponse
import com.example.quizzmyapp.R

class QuizAdapter : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private val quizzesList: ArrayList<QuizzesResponse.QuizzesResponseItem> = ArrayList()

    fun setData(data: List<QuizzesResponse.QuizzesResponseItem>) {
        quizzesList.clear()
        quizzesList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizzesList.get(position)
        holder.bind(quiz)
    }

    override fun getItemCount(): Int {
        return quizzesList.size
    }

    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewQuizName: TextView = itemView.findViewById(R.id.textViewQuizName)

        fun bind(quiz: QuizzesResponse.QuizzesResponseItem) {
            Log.e("Se escribe", "Se escribe " + quiz.quizName)
            textViewQuizName.text = quiz.quizName
        }
    }
}