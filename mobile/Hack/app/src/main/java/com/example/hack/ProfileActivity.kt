package com.example.hack

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cherryup.RetrofitCommon
import com.example.hack.adapters.GameItemAdapter
import com.example.hack.entity.GameInfo
import com.example.hack.entity.UserProfile
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ProfileActivity : AppCompatActivity() {

    private val requestService =
        RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    private val logger: Logger = Logger.getLogger("ggg")
    lateinit var memory: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        memory = getSharedPreferences("hack", Activity.MODE_PRIVATE)
        val whoAmI = intent.getIntExtra("WhoAmI", 0)
        val steamId = memory.getString("steamId", "")


        if (whoAmI == 0) {
            requestService.getUserself(steamId!!).enqueue(object : Callback<UserProfile> {
                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Что-то пошло не так при получении игр",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    val body = response.body()
                    nickname.text = body?.nickname
                    Picasso.get()
                        .load(body?.imageUrl)
                        .transform(RoundedCornersTransformation(30, 0))
                        .resize(220, 220)
                        .into(avatar)
                    spinner.setSelection(if (body?.type?.equals(true)!!) 0 else 1)
                    status.text = if (body.verification) "Подтвержден" else "Не подтвержден"
                    money.text = body.money
                    if (body.gamesInfo.isNullOrEmpty()) {
                        getAllGames()
                    } else {
                        val gameItemAdapter = GameItemAdapter(
                            applicationContext,
                            body.gamesInfo as ArrayList<GameInfo>
                        )
                        gameList.adapter = gameItemAdapter
                    }
                }

            })
        } else if (whoAmI == 1) {
            requestService.getUser(whoAmI.toString()).enqueue(object : Callback<UserProfile> {
                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Что-то пошло не так при получении игр",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    val body = response.body()
                    nickname.text = body?.nickname
                    Picasso.get()
                        .load(body?.imageUrl)
                        .transform(RoundedCornersTransformation(30, 0))
                        .resize(220, 220)
                        .into(avatar)
                    spinner.setSelection(if (body?.type?.equals(true)!!) 0 else 1)
                    status.text = if (body.verification) "Подтвержден" else "Не подтвержден"
                    money.text = body.money
                    val gameItemAdapter = GameItemAdapter(
                        applicationContext,
                        body.gamesInfo as ArrayList<GameInfo>
                    )
                    gameList.adapter = gameItemAdapter

                }

            })
        }


        search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllGames() {
        requestService.getGames().enqueue(object : Callback<List<GameInfo>> {
            override fun onFailure(call: Call<List<GameInfo>>, t: Throwable) {
                logger.info(t.cause.toString())
                Toast.makeText(
                    applicationContext,
                    "Что-то пошло не так при получении игр",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<List<GameInfo>>,
                response: Response<List<GameInfo>>
            ) {
                val games = response.body()
                val gameItemAdapter =
                    GameItemAdapter(applicationContext, games as ArrayList<GameInfo>)

                gameList.adapter = gameItemAdapter
            }

        })
    }
}