package com.ayberk.markakodapp.Fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentRegisterBinding
import dagger.multibindings.StringKey

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtContract.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_contractFragment)
        }
        binding.btnRegister1.setOnClickListener {
            performRegister()
        }
        binding.btnLogin2.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    // Bu fonksiyon, email'in geçerli olup olmadığını kontrol eder.
    private fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Bu fonksiyon, şifrenin geçerli olup olmadığını kontrol eder.
    private fun isValidPassword(password: CharSequence): Boolean {
        return password.length >= 6 // Şifrenin en az 6 karakter olmasını isteyelim.
    }

    // Bu fonksiyon, telefon numarasının geçerli olup olmadığını kontrol eder.
    private fun isValidPhoneNumber(phoneNumber: CharSequence): Boolean {
        return Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length == 11
    }

    // Bu fonksiyon, iki şifrenin eşleşip eşleşmediğini kontrol eder.
    private fun checkPasswordMatch(password: CharSequence, passwordAgain: CharSequence): Boolean {
        return password.toString() == passwordAgain.toString()
    }


    // Bu fonksiyon, kayıt butonuna tıklandığında çağrılır.
    private fun performRegister() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val passwordAgain = binding.editTextPasswordAgain.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val checkbox = binding.checkBox

        var isValid = true

        // Email kontrolü
        if (!isValidEmail(email)) {
            binding.editTextEmail.error = getString(R.string.Email_error)
            isValid = false
        }

        // Şifre kontrolü
        if (!isValidPassword(password)) {
            binding.editTextPassword.error = getString(R.string.password_error)
            isValid = false
        }

        // Telefon numarası kontrolü
        if (!isValidPhoneNumber(phoneNumber)) {
            binding.editTextPhoneNumber.error = getString(R.string.telephone_error)
            isValid = false
        }

        // Şifre eşleşme kontrolü
        if (!checkPasswordMatch(password, passwordAgain)) {
            binding.editTextPasswordAgain.error = getString(R.string.passwordagain_error)
            isValid = false
        }
        // sözleşme onay kontrolü
        if (!checkbox.isChecked) {
            Toast.makeText(requireContext(), getString(R.string.contract_error), Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (isValid) {
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }
}
