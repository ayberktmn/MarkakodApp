package com.ayberk.markakodapp.ParentFragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentLoginBinding
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onResume() {
        super.onResume()

    }
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
            binding.textInputEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextemail.error = getString(R.string.Email_error)
            isValid = false
        }  else {
            binding.textInputEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Şifre kontrolü
        if (!isValidPassword(password)) {
            binding.textInputPassword.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextsifre.error =getString(R.string.password_error)
            isValid = false
        } else {
            binding.textInputPassword.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        if (isValid) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        }
    }

    // Bu fonksiyon, şifrenin güçlü olup olmadığını kontrol eder.
    private fun isStrongPassword(password: CharSequence): Boolean {
        // En az 6 karakter uzunluğunda olmalı
        if (password.length < 6) {
            return false
        }

        // En az bir büyük harf içermeli
        val uppercasePattern = Pattern.compile("[A-Z]")
        if (!uppercasePattern.matcher(password).find()) {
            return false
        }

        // En az bir küçük harf içermeli
        val lowercasePattern = Pattern.compile("[a-z]")
        if (!lowercasePattern.matcher(password).find()) {
            return false
        }

        // En az bir sayı içermeli
        val digitPattern = Pattern.compile("[0-9]")
        if (!digitPattern.matcher(password).find()) {
            return false
        }

        // En az bir özel karakter içermeli
        val specialCharPattern = Pattern.compile("[!@#$%^&*()_+=|<>?{}\\[\\]~.-]")
        if (!specialCharPattern.matcher(password).find()) {
            return false
        }
        return true
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
