package com.ayberk.markakodapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentPersonContractBinding


class PersonContractFragment : Fragment() {

    private var _binding: FragmentPersonContractBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonContractBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtBack.setOnClickListener {
            findNavController().navigate(R.id.action_personContractFragment_to_profileFragment)
        }
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()

        return view
    }
}