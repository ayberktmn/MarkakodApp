package com.ayberk.markakodapp.ParentFragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }

    // Email'in geçerli olup olmadığını kontrol eder.
    private fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Şifrenin geçerli olup olmadığını kontrol eder.
    private fun isValidPassword(password: CharSequence): Boolean {
        return password.length >= 6 // Şifrenin en az 6 karakter olmasını isteyelim.
    }

    // Giriş butonuna tıklandığında çağrılır.
    private fun performLogin() {
        val email = binding.editTextemail.text.toString()
        val password = binding.editTextsifre.text.toString()

        var isValid = true

        // Email kontrolü
        if (!isValidEmail(email)) {
            binding.editTextemail.error = getString(R.string.Email_error)
            isValid = false
        }

        // Şifre kontrolü
        if (!isValidPassword(password)) {
            binding.editTextsifre.error =getString(R.string.password_error)
            isValid = false
        }

        if (isValid) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
