package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cherryup.RetrofitCommon
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.logging.Logger


class MainActivity : Activity() {

    private val requestService = RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    lateinit var memory: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val logger: Logger = Logger.getLogger("ggg")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memory = getSharedPreferences("chery_up_memory", MODE_PRIVATE)
        val token: String = UUID.randomUUID().toString()

        signIn.setOnClickListener {
//            requestService.authLogin(token).enqueue(object : Callback<Any> {
//                override fun onFailure(call: Call<Any>, t: Throwable) {
//                    Toast.makeText(applicationContext, "Что-то пошло не так при получении пользователя", Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<Any>, response: Response<Any>) {
//                    logger.info("response " + response.toString())
//                    Toast.makeText(applicationContext, "Все пошло так при получении пользователя", Toast.LENGTH_LONG).show()
//                }
//
//            })
//
//            val intent = Intent(applicationContext, ProfileActivity::class.java)
//            startActivity(intent)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://45.80.71.200:8000/auth/login?token="+token)
            startActivityForResult(intent, RESULT_OK)

        }
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // запишем в лог значения requestCode и resultCode
        Log.d("myLogs", "requestCode = $requestCode, resultCode = $resultCode")
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            Log.d("success", "requestCode = $requestCode, resultCode = $resultCode")
            // если вернулось не ОК
        } else {
            Log.d("fail", "requestCode = $requestCode, resultCode = $resultCode")
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show()
        }
    }
}