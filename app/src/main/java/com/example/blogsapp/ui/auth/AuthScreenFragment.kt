package com.example.blogsapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogsapp.R
import com.example.blogsapp.core.Resource
import com.example.blogsapp.data.remote.auth.LoginDataSource
import com.example.blogsapp.databinding.FragmentAuthScreenBinding
import com.example.blogsapp.presentation.auth.LoginScreenViewModel
import com.example.blogsapp.presentation.auth.LoginScreenViewModelFactory
import com.example.blogsapp.repository.auth.LogInRepoImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthScreenFragment : Fragment(R.layout.fragment_auth_screen) {
    private lateinit var binding: FragmentAuthScreenBinding
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val loginViewModel by viewModels<LoginScreenViewModel> {
        LoginScreenViewModelFactory(
            LogInRepoImpl(
                LoginDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthScreenBinding.bind(view)
        isUserLoggedin(firebaseAuth.currentUser)
        getUserAndPassword()

    }

    private fun isUserLoggedin(firebaseUser: FirebaseUser?) {
        firebaseUser?.let {
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
        loginViewModel.login(username, password).observe(viewLifecycleOwner, Observer { t ->
            when (t) {
                is Resource.Loading -> {
                    binding.btnSignin.isEnabled = false
                    binding.progressCircularSignin.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressCircularSignin.visibility = View.GONE
                    isUserLoggedin(t.data)

                }
                is Resource.Failure -> {
                    binding.progressCircularSignin.visibility = View.GONE
                    binding.btnSignin.isEnabled = true
                    Toast.makeText(requireContext(), "Error: ${t.exception}", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        })
    }


}