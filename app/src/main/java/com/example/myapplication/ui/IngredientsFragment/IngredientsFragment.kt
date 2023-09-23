import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.ItemClickListener
import com.example.myapplication.adapters.OptionRecyclerAdapter
import com.example.myapplication.adapters.TextPredictionAdapter
import com.example.myapplication.data.FrontFood
import com.example.myapplication.data.TextPredictor
import com.example.myapplication.databinding.FragmentIngredientSearchBinding
import com.example.myapplication.ui.IngredientsFragment.IngredientsViewModel

class IngredientsFragment:Fragment() {

    private var _binding: FragmentIngredientSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIngredientSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientsViewModel = ViewModelProvider(this)[IngredientsViewModel::class.java]
        val textPredictionAdapter = TextPredictionAdapter().apply {
            itemClickListener = object : ItemClickListener<TextPredictor> {
                override fun onItemClicked(item: TextPredictor, itemPosition: Int) {
                    binding.textInput.setQuery(item.name, true)
                }
            }
        }
        val itemAdapter = OptionRecyclerAdapter().apply {
            itemClickListener = object : ItemClickListener<FrontFood> {
                override fun onItemClicked(item: FrontFood, itemPosition: Int) {
                    Log.d("SENDING ID", item.id.toString())
                    findNavController().navigate(
                        R.id.action_fragmentDishName_to_blahblah,
                        bundleOf("recipe_id" to item.id)
                    )
                }
            }
        }


        ingredientsViewModel._foods.observe(viewLifecycleOwner) {}
            binding.apply {
                textPredictionRecycler.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = textPredictionAdapter
                }
                itemRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = itemAdapter
                }
                textInput.apply {
                    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            query?.let {
                                ingredientsViewModel.fetchIngredients(query)
                                Log.d("FOUND INGREDIENTS LOL", query.toString())
                            }
                            textPredictionRecycler.visibility = View.GONE

                            return true
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            query?.let {
                                textPredictionRecycler.visibility = View.VISIBLE
                                handler.postDelayed({
                                    ingredientsViewModel.fetchPredictionText(query)
                                }, 1000)
                            }
                            return true
                        }

                    })
                }
                ingredientsViewModel.autoCompleteText.observe(viewLifecycleOwner) {
                    Log.d("AutoCompleteData", it.toString())
                    binding.apply {
                        textPredictionAdapter.updateItems(it as ArrayList<TextPredictor>)

                    }
                }
                ingredientsViewModel.recipes.observe(viewLifecycleOwner) {
                    binding.apply {
                        itemAdapter.updateItems(it)
                    }
                }
            }


        }

}