package edu.uca.roman.roompractica.ui.view.adapter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uca.roman.roompractica.R
import edu.uca.roman.roompractica.CiudadApplication
import edu.uca.roman.roompractica.databinding.FragmentCiudadBinding
import edu.uca.roman.roompractica.ui.view.adapter.CiudadListAdapter
import edu.uca.roman.roompractica.ui.view.viewmodel.CiudadViewModel
import edu.uca.roman.roompractica.ui.view.viewmodel.CiudadViewModelFactory

class CiudadFragment : Fragment() {

    private val viewModel: CiudadViewModel by activityViewModels {
        CiudadViewModelFactory(
            (activity?.application as CiudadApplication).database
                .CiudadDao()
        )
    }

    //el view binding
    private var _binding: FragmentCiudadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCiudadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CiudadListAdapter();

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.fabAgregar.setOnClickListener {
            it.findNavController().navigate(R.id.action_ciudadFragment_to_addCiudadFragment)
        }

        viewModel.allCiudad.observe(this.viewLifecycleOwner) { ciudad ->
            ciudad.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}