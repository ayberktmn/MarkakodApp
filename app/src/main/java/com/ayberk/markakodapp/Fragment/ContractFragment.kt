package com.ayberk.markakodapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.databinding.FragmentContractBinding



class ContractFragment : Fragment() {

    private var _binding: FragmentContractBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentContractBinding.inflate(inflater, container, false)
         val view = binding.root

        binding.txtBack.setOnClickListener {
            //Parent fragment a dönmesini sağlar
            findNavController().navigateUp()
        }

         return view
    }
}