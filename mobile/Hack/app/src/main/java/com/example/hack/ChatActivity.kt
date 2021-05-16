package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.cherryup.RetrofitCommon
import com.example.hack.adapters.MessageAdapter
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ChatActivity : AppCompatActivity() {

    private val requestService = RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    lateinit var memory: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val logger: Logger = Logger.getLogger("ggg")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        memory = getSharedPreferences("hack", Activity.MODE_PRIVATE)
        requestService.getChat(1, 2).enqueue(object : Callback<List<com.example.hack.entity.Message>> {
            override fun onFailure(call: Call<List<com.example.hack.entity.Message>>, t: Throwable) {
                logger.info(t.message)
            }

            override fun onResponse(call: Call<List<com.example.hack.entity.Message>>, response: Response<List<com.example.hack.entity.Message>>) {
                val messageAdapter = MessageAdapter(
                    applicationContext,
                    response.body() as ArrayList<com.example.hack.entity.Message>,
                    intent.getIntExtra("i", 0)
                )
                messageList.adapter = messageAdapter
            }
        })


        search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("idUser", memory.getInt("idUser", 0))
            startActivity(intent)
        }

        profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        list.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("idUser", memory.getInt("idUser", 0))
            startActivity(intent)
        }
    }
}