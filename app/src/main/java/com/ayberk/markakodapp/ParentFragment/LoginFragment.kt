package com.ayberk.markakodapp.ParentFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false
    private val animationDuration = 1000L // 3 saniye
    private var isAnimationPlayed  = false


    @SuppressLint("RestrictedApi")
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
        if (!isAnimationPlayed) {

            setupAnim()
            isAnimationPlayed = true

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

    private fun setupAnim() {
        binding.animationView.setAnimation(R.raw.progressbar)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()
        binding.animationView.speed = 2.0F

        Handler().postDelayed({
            binding.animationView.pauseAnimation()
            binding.animationView.visibility = View.GONE
        }, animationDuration)
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
