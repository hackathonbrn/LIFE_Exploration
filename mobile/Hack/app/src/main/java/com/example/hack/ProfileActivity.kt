package com.example.hack

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cherryup.RetrofitCommon
import com.example.hack.adapters.GameItemAdapter
import com.example.hack.entity.GameInfo
import com.example.hack.entity.UserProfile
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_bottom_navigator.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.dialog_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ProfileActivity : AppCompatActivity() {

    private val requestService =
        RetrofitCommon().getClient("http://45.80.71.200:8000").create(RetrofitService::class.java)
    private val logger: Logger = Logger.getLogger("ggg")
    lateinit var memory: SharedPreferences
    var idUser: Int = 0
    var idGame: Int = 0
    var costLesson: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        memory = getSharedPreferences("hack", Activity.MODE_PRIVATE)
        val edit = memory.edit()
        edit.putString("steamId", "76561198083623460")
        edit.apply()
        val whoAmI = intent.getIntExtra("WhoAmI", 0)
        val steamId = memory.getString("steamId", "")


        Picasso.get()
            .load("https://i.yapx.ru/MSMFS.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .placeholder(R.color.white)
            .into(ask)

        Picasso.get()
            .load("https://i.yapx.ru/MSMFJ.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .placeholder(R.color.white)
            .into(mark)

        mark.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_layout)

            dialog.show()

            val sendReview = dialog.findViewById<Button>(R.id.sendReview)
            val ratingEdit = dialog.findViewById<EditText>(R.id.ratingEdit)
            val sociabilityEdit = dialog.findViewById<EditText>(R.id.sociabilityEdit)
            val adequacyEdit = dialog.findViewById<EditText>(R.id.adequacyEdit)
            val qualificationEdit = dialog.findViewById<EditText>(R.id.qualificationEdit)
            sendReview.setOnClickListener {
                val params = mapOf(Pair("id_coach", memory.getInt("idAnotherUser", 0)),
                    Pair("rating", ratingEdit.text.toString().toInt()),
                    Pair("sociability", sociabilityEdit.text.toString().toInt()),
                    Pair("adequacy", adequacyEdit.text.toString().toInt()),
                    Pair("qualification", qualificationEdit.text.toString().toInt()),
                    Pair("id_learner", memory.getInt("idUser", 0))
                )
                requestService.setReview(params).enqueue(object : Callback<Any> {
                    override fun onFailure(call: Call<Any>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        Toast.makeText(applicationContext, "Ваша оценка отправлена", Toast.LENGTH_LONG).show()
                    }
                })

                dialog.dismiss()
            }
        }

        Picasso.get()
            .load("https://i.yapx.ru/MSMFT.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .placeholder(R.color.white)
            .into(send)

        ask.setOnClickListener {
            val params = mapOf(Pair("id_coach", memory.getInt("idAnotherUser", 0)),
                Pair("id_learner", memory.getInt("idUser", 0)),
                Pair("cost_lesson", 150),
                Pair("count_lessons", 1),
                Pair("id_game", idGame)
            )
            requestService.addRequest(params).enqueue(object : Callback<Any>{
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    logger.info("Что-то пошло не так пока отправлял запрос на обучение")
                }

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Toast.makeText(applicationContext, "Запрос на обучение отправлен учителю", Toast.LENGTH_LONG).show()
                }
            })
        }


        if (whoAmI == 0) {
            requestService.getUserself(steamId!!).enqueue(object : Callback<UserProfile> {
                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Что-то пошло не так при получении самого себя",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    val body = response.body()
                    setProfile(body, true)

                }

            })
        } else {
            requestService.getUser(whoAmI.toString()).enqueue(object : Callback<UserProfile> {
                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Что-то пошло не так при получении кого то",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    setProfile(response.body(), false)
                }

            })
        }


        search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("idUser", memory.getInt("idUser", 0))
            startActivity(intent)
        }

        list.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("idUser", memory.getInt("idUser", 0))
            startActivity(intent)
        }

        send.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("i", memory.getInt("idUser", 0))
            intent.putExtra("noi", memory.getInt("idAnotherUser", 0))
            startActivity(intent)
        }

        chat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("i", memory.getInt("idUser", 0))
            intent.putExtra("noi", memory.getInt("idAnotherUser", 0))
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

    private fun setProfile(body: UserProfile?, whoAmI: Boolean) {
        val edit = memory.edit()
        if (whoAmI) {
            idUser = body?.id!!
            edit.putInt("idUser", idUser)
            edit.apply()
        } else {
            edit.putInt("idAnotherUser", body?.id!!)
            edit.apply()
        }
        nickname.text = body.username
        Picasso.get()
            .load(body.avatar_url)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(220, 220)
            .into(avatar)
        spinner.setSelection(if (body.acc_status) 1 else 0)
        status.text = if (body.verification) "Подтвержден" else "Не подтвержден"
        if (whoAmI) {
            money.text = body.money
            money.visibility = View.VISIBLE
            mark.visibility = View.GONE
            ask.visibility = View.GONE
            send.visibility = View.GONE
        } else {
            mark.visibility = View.VISIBLE
            ask.visibility = View.VISIBLE
            send.visibility = View.VISIBLE
            money.visibility = View.GONE
        }

        if (body.acc_status) {
            rating.text = "Рейтинг: " + body.le_params.rating.toString()
            sociability.text = "Социальные навыки: " +  body.le_params.sociability.toString()
            adequacy.text = "Адекватность: " + body.le_params.adequacy.toString()
            qualification.text = "Квалификация: " + body.le_params.qualification.toString()
        } else {
            rating.text = "Рейтинг: " + body.co_params.rating.toString()
            sociability.text = "Социальные навыки: " + body.co_params.sociability.toString()
            adequacy.text = "Уровень учителя: " + body.co_params.adequacy.toString()
            qualification.text = "Кваликация: " + body.co_params.qualification.toString()
        }

        val gameItemAdapter = GameItemAdapter(
            applicationContext,
            body.games_user as ArrayList<GameInfo>
        )
        gameList.adapter = gameItemAdapter
    }

}