package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cherryup.RetrofitCommon
import com.example.hack.adapters.TeacherItemAdapter
import com.example.hack.entity.Teacher
import com.example.hack.entity.UserProfile
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private val requestService =
        RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    lateinit var memory: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        memory = getSharedPreferences("hack", Activity.MODE_PRIVATE)

        requestService.getTeachers(mapOf(Pair("id", intent.getIntExtra("idUser", 0)))).enqueue(object :
            Callback<List<Teacher>> {
            override fun onFailure(call: Call<List<Teacher>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Что-то пошло не так при получении учителей",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<List<Teacher>>,
                response: Response<List<Teacher>>
            ) {
                val teachers = response.body()
                val teacherItemAdapter = TeacherItemAdapter(applicationContext,
                    teachers as ArrayList<Teacher>
                )

                teacherList.adapter = teacherItemAdapter
            }

        })

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