package com.ayberk.markakodapp.Loading

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.ayberk.markakodapp.databinding.LoadingregistercompletedItemBinding

class LoadingRegisterCompleted(private val myFragment: Fragment) {
    private var dialog: AlertDialog? = null

    fun startLoading() {
        val inflater = LayoutInflater.from(myFragment.requireContext())
        val binding = LoadingregistercompletedItemBinding.inflate(inflater)

        val builder = AlertDialog.Builder(myFragment.requireContext())
        builder.setView(binding.root)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()
        binding.animationView.speed = 1f
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}
