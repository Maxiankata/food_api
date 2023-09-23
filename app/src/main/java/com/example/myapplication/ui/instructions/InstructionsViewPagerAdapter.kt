import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Response
//import com.example.myapplication.data.Response.Steps

class InstructionViewPagerAdapter : RecyclerView.Adapter<InstructionViewPagerAdapter.InstructionPagerViewHolder>() {
    class InstructionPagerViewHolder (view: View): RecyclerView.ViewHolder(view){
        val stepNumber = view.findViewById<TextView>(R.id.step_number)
        val instruction = view.findViewById<TextView>(R.id.instruction)
        fun bind(instructions: Response.FullInformationRecipe.Steps){
            stepNumber.text =instructions.number.toString()
            instruction.text = instructions.step
        }

    }
    var items = ArrayList<Response.FullInformationRecipe.Steps>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= InstructionPagerViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.instruction_view_pager, parent, false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InstructionPagerViewHolder, position: Int) {
        holder.bind(items[position])
    }
    fun updateItems(newInstructions: List<Response.FullInformationRecipe.Steps>?){
        items.clear()
        items.addAll(newInstructions!!)
        notifyDataSetChanged()
    }
}
