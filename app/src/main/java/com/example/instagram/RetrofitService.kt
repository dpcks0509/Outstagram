package com.example.instagram

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

class User(
    val id: Int,
    val username: String,
    val token: String
)

class Post(
    val id: Int,
    val content: String,
    val image: String,
    val owner_profile: OwnerProfile
)

class OwnerProfile(
    var username: String,
    val image: String?
)

class UserInfo(
    val id: Int,
    val username: String,
    val profile: OwnerProfile
)

interface RetrofitService {
    @POST("user/login/")
    @FormUrlEncoded
    fun login(
        @FieldMap params: HashMap<String, Any>
    ): Call<User>

    @POST("user/signup/")
    @FormUrlEncoded
    fun join(
        @FieldMap params: HashMap<String, Any>
    ): Call<User>

    @GET("instagram/post/list/all/")
    fun getPosts(

    ):Call<ArrayList<Post>>

    @POST("instagram/post/like/{post_id}")
    fun postLike(
        @Path("post_id") post_id: Int
    ): Call<Any>

    @POST("instagram/post/")
    @Multipart
    fun uploadPost(
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part,
        @Part("content") content: RequestBody
    ): Call<Any>

    @GET("user/userInfo/")
    fun getUserInfo(
        @HeaderMap headers: Map<String, String>
    ): Call<UserInfo>

    @PUT("user/profile/{user_id}/")
    @Multipart
    fun changeProfile(
        @Path("user_id") userId: Int,
        @HeaderMap headers: HashMap<String, String>,
        @Part image: MultipartBody.Part,
        @Part("user") user: RequestBody,
    ): Call<Any>
}