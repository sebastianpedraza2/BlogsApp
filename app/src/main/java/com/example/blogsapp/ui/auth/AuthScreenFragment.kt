package com.example.blogsapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.blogsapp.R
import com.example.blogsapp.databinding.FragmentAuthScreenBinding
import com.google.firebase.auth.FirebaseAuth


class AuthScreenFragment : Fragment(R.layout.fragment_auth_screen) {
    private lateinit var binding: FragmentAuthScreenBinding
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthScreenBinding.bind(view)
        isUserLoggedin()
        getUserAndPassword()

    }

    private fun isUserLoggedin() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_authScreenFragment_to_homeScreenFragment)
        }
    }

    private fun getUserAndPassword() {
        binding.btnSignin.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateAuthInfo(username, password)
            signinUser(username, password)
        }

    }

    private fun validateAuthInfo(username: String, password: String) {
        if (username.isEmpty()) {
            binding.editTextUsername.error = "Please enter a value"
            return
        }
        if (password.isEmpty()) {
            binding.editTextPassword.error = "Please enter a password"
            return
        }
    }

    private fun signinUser(username: String, password: String) {
        TODO("Not yet implemented")
    }


}