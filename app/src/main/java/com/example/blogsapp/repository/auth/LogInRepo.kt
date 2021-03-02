package com.example.blogsapp.repository.auth

import com.google.firebase.auth.FirebaseUser


interface LogInRepo {
    suspend fun signIn(user: String, password: String) : FirebaseUser?
}