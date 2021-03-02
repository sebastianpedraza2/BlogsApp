package com.example.blogsapp.repository.auth

import com.example.blogsapp.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

class LogInRepoImpl(private val dataSource: LoginDataSource) : LogInRepo {
    override suspend fun signIn(user: String, password: String): FirebaseUser? =
        dataSource.signIn(user, password)
}