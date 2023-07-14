package com.ayberk.markakodapp.ParentFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment() : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        view.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(750)
                .start()
        }

        binding.txtPersonContract.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_personContractFragment)

        }

        binding.txtAboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)
        }

        binding.txtPersonSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
            val bottomNav =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.selectedItemId = R.id.settings // Alt çubukta "settings" seçeneğini işaretle
        }

        binding.txtPersonExit.setOnClickListener {
            showLogoutDialog()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
        // setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                // uyarı mesajı evet denirse yapılacak işlemler *showLogoutDialog* kısmında gerçekleştirilir
                showLogoutDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SuspiciousIndentation", "ResourceType")
    private fun showLogoutDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.alert_title)

        alertDialogBuilder.setMessage(getString(R.string.alert_message))
        alertDialogBuilder.setPositiveButton(R.string.positive_button) { dialog, _ ->
            // Çıkış yapılacak işlemleri burada gerçekleştir

            val action = ProfileFragmentDirections.actionProfileFragmentToMainActivity()
            findNavController().navigate(action)

        }

        alertDialogBuilder.setNegativeButton(R.string.negative_button) { dialog, _ ->
            // İptal edildiğinde yapılacak işlemleri burada gerçekleştir
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.RED)
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.BLACK)

        }
        alertDialog.show()
    }
}


    // İletişim kanalını tanımlama

