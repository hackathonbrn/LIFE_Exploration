package com.example.hack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val teachers = arrayListOf(
            UserProfile("Пупкин", true, emptyList(), "https://img2.freepng.ru/20181125/pjy/kisspng-computer-icons-portable-network-graphics-user-icon-my-b-tab-svg-png-icon-free-download-8-584-onl-5bfa376045bc20.3622044515431248322856.jpg"),
            UserProfile("Лупкин", true, emptyList(), "https://img2.freepng.ru/20181125/pjy/kisspng-computer-icons-portable-network-graphics-user-icon-my-b-tab-svg-png-icon-free-download-8-584-onl-5bfa376045bc20.3622044515431248322856.jpg"),
            UserProfile("Купкин", true, emptyList(), "https://img2.freepng.ru/20181125/pjy/kisspng-computer-icons-portable-network-graphics-user-icon-my-b-tab-svg-png-icon-free-download-8-584-onl-5bfa376045bc20.3622044515431248322856.jpg"),
            UserProfile("Супкин", true, emptyList(), "https://img2.freepng.ru/20181125/pjy/kisspng-computer-icons-portable-network-graphics-user-icon-my-b-tab-svg-png-icon-free-download-8-584-onl-5bfa376045bc20.3622044515431248322856.jpg")
        )
        val teacherItemAdapter = TeacherItemAdapter(this, teachers)

        teacherList.adapter = teacherItemAdapter

        profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}