package edu.uca.roman.roompractica.ui.view.adapter

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import edu.uca.roman.roompractica.CiudadApplication
import edu.uca.roman.roompractica.R
import edu.uca.roman.roompractica.databinding.FragmentAddCiudadBinding
import edu.uca.roman.roompractica.ui.view.viewmodel.CiudadViewModel
import edu.uca.roman.roompractica.ui.view.viewmodel.CiudadViewModelFactory

class AddCiudadFragment : Fragment() {

    private val viewModel: CiudadViewModel by activityViewModels {
        CiudadViewModelFactory(
            (activity?.application as CiudadApplication).database
                .CiudadDao()
        )
    }

    //el view binding
    private var _binding: FragmentAddCiudadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddCiudadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAgregar.isEnabled = false

            btnAgregar.setOnClickListener {
                agregarNuevaCiudad()
                limpiar()

                it.findNavController().navigate(R.id.action_ciudadFragment_to_addCiudadFragment)
            }

            etCiudad.addTextChangedListener(ciudadTextWatcher)
        }
    }

    private fun limpiar() {
        with (binding) {
            etCiudad.text = null
            etCiudad.requestFocus()
        }
    }

    //Funcion para validar que los Edit Texts no esten vacios
    private fun entradasValidas(): Boolean {
        return viewModel.entradasValidas(
            binding.etCiudad.text.toString()
        )
    }

    //Funcion para mandar a agregar una palabra
    private fun agregarNuevaCiudad() {
        if (entradasValidas()) {
            viewModel.agregarCiudad(
                binding.etCiudad.text.toString()
            )
        }
    }

    private val ciudadTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.btnAgregar.isEnabled = entradasValidas()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}