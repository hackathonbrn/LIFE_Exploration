package com.example.hack

import com.example.hack.entity.Game
import com.example.hack.entity.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/auth/login")
    fun authLogin(@Query("token") token: String) : Call<Any>

    @GET("/user/get_user")
    fun getUser(@Query("user_id") userId: String) : Call<UserProfile>

    @GET("/games/get_games")
    fun getGames() : Call<List<Game>>

    @GET("/user/cabinet")
    fun getUserself(@Query("steam_id") steamId: String) : Call<UserProfile>

}