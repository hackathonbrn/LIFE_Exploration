package com.example.hack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.hack.R
import com.example.hack.entity.Game
import com.example.hack.entity.GameInfo
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class GameItemAdapter(
    context: Context,
    private val games: ArrayList<GameInfo> = ArrayList()
    ) : BaseAdapter() {
    private val gInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = gInflater.inflate(R.layout.game_item_layout, parent, false)
        }

        val game: GameInfo = getItem(position) as GameInfo

        val gameIcon = view?.findViewById<ImageView>(R.id.gameIcon)
        Picasso.get()
            .load(game.game_logo)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .into(gameIcon)

        val gameCheckBox = view!!.findViewById<CheckBox>(R.id.gameCheckBox)
        gameCheckBox!!.text = game.game_name

        val rank = view.findViewById<TextView>(R.id.rank)
        rank.text = "Ранг в игре: " + game.rank.toString()

        val hours = view.findViewById<TextView>(R.id.hours_game)
        hours.text = "Наиграно: " + game.hours_game.toString() + " часов"

        val number = view.findViewById<TextView>(R.id.number_matches)
        number.text = "Матчей сыграно: " + game.number_matches.toString()

        val cost = view.findViewById<TextView>(R.id.cost_lesson)
        cost.text = "Цена урока: "+  game.cost_lesson.toString() + " руб."

        return view
    }

    override fun getItem(position: Int): Any {
        return games[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return games.size
    }
}