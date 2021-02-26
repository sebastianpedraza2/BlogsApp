package com.example.blogsapp.repository

import com.example.blogsapp.core.Resource
import com.example.blogsapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts() : Resource<List<Post>>
}