package com.example.blogsapp.repository.home

import com.example.blogsapp.core.Resource
import com.example.blogsapp.data.model.Post
import com.example.blogsapp.data.remote.home.HomeScreenDataSource
import com.example.blogsapp.repository.home.HomeScreenRepo

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource) : HomeScreenRepo {
    override suspend fun getLatestPosts() : Resource<List<Post>> = dataSource.getLatestPosts()
}