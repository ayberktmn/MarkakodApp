package com.ayberk.markakodapp.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentRegisterBinding
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        view.translationY = view.height.toFloat()
        view.alpha = 0f
        view.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(750)
            .start()
        binding.animationCommunication2.visibility = View.GONE

        binding.txtContract.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_contractFragment)

        }
        binding.btnRegister1.setOnClickListener {
           performRegister()

        }
        binding.btnLogin2.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.txtBackRegister.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    // Bu fonksiyon, email'in geçerli olup olmadığını kontrol eder.

    private fun isValidName(name: CharSequence): Boolean {
        // İsim sadece harf ve boşluk karakteri içermeli
        val namePattern = Pattern.compile("^[a-zA-Z\\s]+$")
        return namePattern.matcher(name).matches()
    }
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

    // Bu fonksiyon, kayıt butonuna tıklandığında çağrılır.
    private fun performRegister() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val passwordAgain = binding.editTextPasswordAgain.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val checkbox = binding.checkBox

        var isValid = true


        if (!isValidName(name)) {
            binding.editTextName.error = getString(R.string.name_error)
            binding.textInputName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            isValid = false
        } else {
            binding.textInputName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Email kontrolü
        if (!isValidEmail(email)) {
            binding.editTextEmail.error = getString(R.string.Email_error)
            binding.textInputEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            isValid = false
        }else {
            binding.textInputEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Şifre kontrolü
        if (!isValidPassword(password)) {
            binding.textInputPasswordRegister.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextPassword.error = getString(R.string.password_error)
            isValid = false
        } else {
            binding.textInputPasswordRegister.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Güçlü şifre kontrolü
        if (!isStrongPassword(password)) {
            binding.textInputPasswordRegister.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextPassword.error = getString(R.string.strong_password)
            isValid = false
        }  else {
            binding.textInputPasswordRegister.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Telefon numarası kontrolü
        if (!isValidPhoneNumber(phoneNumber)) {
            binding.textInputTelephone.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextPhoneNumber.error = getString(R.string.telephone_error)
            isValid = false
        } else {
            binding.textInputTelephone.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Şifre eşleşme kontrolü
        if (!checkPasswordMatch(password, passwordAgain)) {
            binding.textInputPasswordRegister2.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.Red)
            binding.editTextPasswordAgain.error = getString(R.string.passwordagain_error)
            isValid = false
        } else {
            binding.textInputPasswordRegister2.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.yellow)
        }

        // Sözleşme onay kontrolü
        if (!checkbox.isChecked) {
            Toast.makeText(requireContext(), getString(R.string.contract_error), Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (isValid) {

            val animationView = binding.animationCommunication2
            binding.animationCommunication2.visibility = View.VISIBLE
            // Animasyonu başlatın
            animationView.playAnimation()

            // Belirli bir süre sonra animasyonu durdurmak için Handler kullanın
            val handler = Handler(Looper.getMainLooper())
            val delayMillis = 2150L

            handler.postDelayed({
                // Animasyonu durdurun
                animationView.cancelAnimation()
                findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
            }, delayMillis)


        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}