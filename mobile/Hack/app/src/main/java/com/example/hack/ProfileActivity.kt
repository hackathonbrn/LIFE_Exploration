package com.example.hack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val games = arrayListOf(
            Game("CS:GO", "https://www.meme-arsenal.com/memes/d86fe462696d9b51e7fdd88ffbfc2174.jpg"),
            Game("Dota 2", "https://www.meme-arsenal.com/memes/89ab53e6d20d0a1b6d9ead1723311cd0.jpg"),
            Game("Warcraft", "https://i.pinimg.com/originals/e3/7c/d3/e37cd3930a204055b51365f7c813ab03.jpg")
        )
        val gameItemAdapter = GameItemAdapter(this, games)

        gameList.adapter = gameItemAdapter

        search.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}