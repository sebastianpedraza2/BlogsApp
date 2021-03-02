package com.example.blogsapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blogsapp.R
import com.example.blogsapp.core.Resource
import com.example.blogsapp.data.remote.home.HomeScreenDataSource
import com.example.blogsapp.databinding.FragmentHomeScreenBinding
import com.example.blogsapp.presentation.HomeScreenViewModel
import com.example.blogsapp.presentation.HomeScreenViewModelFactory
import com.example.blogsapp.repository.home.HomeScreenRepoImpl
import com.example.blogsapp.ui.home.adapter.HomeScreenAdapter


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private val ViewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(
            HomeScreenRepoImpl(
                HomeScreenDataSource()
            )
        )
    }
    private lateinit var binding: FragmentHomeScreenBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)
        ViewModel.fetchlastestPosts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressMainMenu.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("Firebase1", "Resultados : ${result.data.size}")
                    binding.progressMainMenu.visibility = View.GONE
                    binding.rvHomePage.adapter = HomeScreenAdapter(result.data)

                }
                is Resource.Failure -> {
                    binding.progressMainMenu.visibility = View.GONE
                    Toast.makeText(
                        context,
                        "Ha ocurrido un erro: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        })


    }


}