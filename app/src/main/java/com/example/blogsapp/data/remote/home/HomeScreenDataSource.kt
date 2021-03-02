package com.example.blogsapp.data.remote.home

import android.util.Log
import com.example.blogsapp.core.Resource
import com.example.blogsapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource() {
    suspend fun getLatestPosts(): Resource<List<Post>> {
        val postslist = mutableListOf<Post>()
       /*val user = hashMapOf(
            "profile_name" to "Sebastian Pedraza",
            "profile_picture" to "https://www.ecestaticos.com/image/clipping/cda4e0d08b4de3844f1a70d979a8f5cc/por-que-debemos-ser-optimistas-con-2021-segun-bill-gates.jpg",
             "post_img" to "https://www.muycomputer.com/wp-content/uploads/2020/07/windows-10-2004.jpg"
        )
        val user2 = hashMapOf(
            "profile_name" to "Elon Musk",
            "profile_picture" to "https://www.motor.com.co/files/article_main/files/crop/uploads/2019/08/12/5d51da70cd7b1.r_1591736078809.370-302-1534-884.jpeg",
            "post_img" to "https://www.cityam.com/wp-content/uploads/2021/01/Elon-Musk-Awarded-With-Axel-Springer-Award-In-Berlin-1229892421-960x668.jpg"
        )
        val db = FirebaseFirestore.getInstance()
// Add a new document with a generated ID
        db.collection("posts")
            .add(user2)
            .addOnSuccessListener { documentReference ->
                Log.d("Firebase1", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firebase1", "Error adding document", e)
            }




        */



        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()


        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let { post ->
                postslist.add(post)

            }
        }



        Log.d("Firebase1", "getLatestPosts: $postslist")
        return Resource.Success(postslist)
    }
}