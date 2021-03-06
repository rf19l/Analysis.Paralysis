package analysis.paralysis

import analysis.paralysis.databinding.FragmentHomeBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    val regex:Regex = Regex("[\\r\\n]+")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<String>()

        binding!!.apply{
            btSubmit.setOnClickListener {
                Submit(list)
            }
            imageButton.setOnClickListener {
                activity?.finishAndRemoveTask()
            }
        }
    }

    private fun Submit(list:ArrayList<String>){
        val items = binding?.etInput?.text?.split(regex)
        if (items != null) {
            for(item in items)
                if (item.trim().isNotEmpty() && ! list.contains(item))
                    list.add(item)
        }
        if(list.size > 0){
            list.shuffle()
            val action = HomeFragmentDirections.actionHomeFragmentToSortFragment(list.toTypedArray())
            findNavController().navigate(action)
        }
    }

}