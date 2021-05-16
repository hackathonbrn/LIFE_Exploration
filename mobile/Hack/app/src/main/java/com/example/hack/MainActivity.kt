package com.example.hack

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.cherryup.RetrofitCommon
import com.google.gson.internal.LinkedTreeMap
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.helper.StringUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.logging.Logger
import kotlin.properties.Delegates


class MainActivity : Activity() {

    private val requestService =
        RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    lateinit var memory: SharedPreferences
    var token: String = UUID.randomUUID().toString()
    val logger: Logger = Logger.getLogger("ggg")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.get()
            .load(R.mipmap.logo)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(1000, 1000)
            .into(logo)




        memory = getSharedPreferences("hack", MODE_PRIVATE)

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!StringUtil.isBlank(token)) {
                    getSteamId()
                    handler.postDelayed(this, 15_000)
                }
            }
        }, 15_000)


        signIn.setOnClickListener {
            val myWebView: WebView = findViewById(R.id.webview)
            myWebView.loadUrl("http://45.80.71.200:8000/auth/login?token=" + token)
        }
    }


    private fun getSteamId() {
        requestService.getSteamId(token).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Что-то пошло не так при получении steamId",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {
                if (response.body() == null) {
                    logger.info("body is null")
                    return
                }
                val body = response?.body() as LinkedTreeMap<*, *>
                if (body.containsKey("steamId") && !StringUtil.isBlank(body["steam_id"].toString())) {
                    logger.info("steamId is null")
                    return
                }
                val intent = Intent(applicationContext, ProfileActivity::class.java)
                val edit = memory.edit()
                edit.putString("steamId", body["steam_id"].toString())
                edit.apply()
                startActivity(intent)
                finish()
                token = ""
            }
        })
    }


}