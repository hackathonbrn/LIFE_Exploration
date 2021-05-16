package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cherryup.RetrofitCommon
import com.example.hack.adapters.GameItemAdapter
import com.example.hack.adapters.RequestToTeacherAdapter
import com.example.hack.entity.GameInfo
import com.example.hack.entity.RequestToTeacher
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    lateinit var memory: SharedPreferences
    private val requestService = RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        memory = getSharedPreferences("hack", Activity.MODE_PRIVATE)

        val coachId = memory.getInt("idUser", 0)
        requestService.getRequestsToTeacher(coachId.toString()).enqueue(object : Callback<List<RequestToTeacher>> {
            override fun onFailure(call: Call<List<RequestToTeacher>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<RequestToTeacher>>,
                response: Response<List<RequestToTeacher>>
            ) {
                val requestItemAdapter = RequestToTeacherAdapter(
                    applicationContext,
                    response.body() as ArrayList<RequestToTeacher>
                )
                requestList.adapter = requestItemAdapter
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


    }
}