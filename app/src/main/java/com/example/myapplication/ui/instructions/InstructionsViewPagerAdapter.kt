import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Response
import com.example.myapplication.data.Response.FullInformationRecipe.Instructions.Steps

class InstructionViewPagerAdapter : RecyclerView.Adapter<InstructionViewPagerAdapter.InstructionPagerViewHolder>() {
    class InstructionPagerViewHolder (view: View): RecyclerView.ViewHolder(view){
        val stepNumber = view.findViewById<TextView>(R.id.step_number)
        val instruction = view.findViewById<TextView>(R.id.instruction)
        fun bind(instructions: Steps){
            stepNumber.text =instructions.number.toString()
            instruction.text = instructions.step
            Log.d("BINDING INSTRUCTION", instructions.toString())
        }
    }
    var items = ArrayList<Steps>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= InstructionPagerViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.instruction_view_pager, parent, false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InstructionPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }
    fun updateItems(newInstructions: List<Steps>?){
        items.clear()
        items.addAll(newInstructions!!)
        notifyDataSetChanged()
        Log.d("UPDATING INSTRUCTION", items.toString())

    }
}
