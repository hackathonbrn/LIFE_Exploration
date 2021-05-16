package com.example.hack

import com.example.hack.entity.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("/auth/login")
    fun authLogin(@Query("token") token: String) : Call<Any>

    @GET("/auth/get_steam_id")
    fun getSteamId(@Query("token") token: String) : Call<Any>

    @GET("/user/get_user")
    fun getUser(@Query("user_id") userId: String) : Call<UserProfile>

    @GET("/games/get_games")
    fun getGames() : Call<List<GameInfo>>

    @GET("/user/cabinet")
    fun getUserself(@Query("steam_id") steamId: String) : Call<UserProfile>

    @POST("/user/get_coach")
    fun getTeachers(@Body params: Map<String, Int>) : Call<List<Teacher>>

    @POST("/user/set_reviews")
    fun setReview(@Body params: Map<String, Int>) : Call<Any>

    @GET("/request/coach")
    fun getRequestsToTeacher(@Query("id_coach") coachId: String) : Call<List<RequestToTeacher>>

    @GET("/chat/get_chat")
    fun getChat(@Query("id_first") idFirst: Int, @Query("id_second") idSecond: Int) : Call<List<Message>>
}