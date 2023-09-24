import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.AnalyzedInstruction
//import com.example.myapplication.data.NewApiSteps
//import com.example.myapplication.data.Response.FullInformationRecipe.Instructions.Steps
import com.example.myapplication.data.Step

class InstructionViewPagerAdapter : RecyclerView.Adapter<InstructionViewPagerAdapter.InstructionPagerViewHolder>() {

    private var items = ArrayList<Step>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        InstructionPagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.instruction_view_pager, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InstructionPagerViewHolder, position: Int) {
        val step = items[position]
        holder.bind(step)
    }

    fun updateItems(newSteps: List<Step>) {
        items.clear()
        items.addAll(newSteps)
        notifyDataSetChanged()
        Log.d("UPDATING INSTRUCTION", items.toString())
    }

    class InstructionPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepNumber = view.findViewById<TextView>(R.id.step_number)
        val instruction = view.findViewById<TextView>(R.id.instruction)

        fun bind(step: Step) {
            stepNumber.text = step.number.toString()
            instruction.text = step.step

            Log.d("BINDING INSTRUCTION", step.toString())
        }
    }
}
