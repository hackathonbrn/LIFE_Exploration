package com.example.hack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
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
            .load(game.gameLogo)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .into(gameIcon)

        val gameCheckBox = view!!.findViewById<CheckBox>(R.id.gameCheckBox)
        gameCheckBox!!.text = game.gameName

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