package com.example.hack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hack.adapters.TeacherItemAdapter
import com.example.hack.entity.UserProfile
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val teachers = arrayListOf<UserProfile>()
        val teacherItemAdapter =
            TeacherItemAdapter(this, teachers)

        teacherList.adapter = teacherItemAdapter

        profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}