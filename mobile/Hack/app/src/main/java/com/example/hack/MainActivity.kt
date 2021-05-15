package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.os.postDelayed
import com.example.cherryup.RetrofitCommon
import com.example.hack.entity.SteamId
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.helper.StringUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.logging.Logger


class MainActivity : Activity() {

    private val requestService =
        RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    lateinit var memory: SharedPreferences
    lateinit var token: String
    val logger: Logger = Logger.getLogger("ggg")

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memory = getSharedPreferences("hack", MODE_PRIVATE)

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!StringUtil.isBlank(token)) {
                    requestService.getSteamId(token).enqueue(object : Callback<SteamId> {
                        override fun onFailure(call: Call<SteamId>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Что-то пошло не так при получении steamId",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<SteamId>,
                            response: Response<SteamId>
                        ) {
                            val intent = Intent(applicationContext, ProfileActivity::class.java)
                            val edit = memory.edit()
                            edit.putString("steamId", response.body()?.steamId)
                            edit.apply()
                            startActivity(intent)
                        }
                    })
                }
                handler.postDelayed(this, 15_000)
            }
        }, 15_000)



        signIn.setOnClickListener {
            token = UUID.randomUUID().toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://45.80.71.200:8000/auth/login?token=" + token)
            startActivity(intent)
        }
    }


}