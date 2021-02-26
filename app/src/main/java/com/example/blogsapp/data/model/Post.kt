package com.example.blogsapp.data.model

import com.google.firebase.Timestamp

data class Post(
    val profile_name: String = "",
    val profile_picture: String = "",
    val post_timestamp: Timestamp? = null,
    val post_img: String = ""
)