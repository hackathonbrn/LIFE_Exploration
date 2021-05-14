package com.example.hack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class GameItemAdapter(
    context: Context,
    private val games: ArrayList<Game> = ArrayList()
    ) : BaseAdapter() {

    private val gInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = gInflater.inflate(R.layout.game_item_layout, parent, false)
        }

        val game: Game = getItem(position) as Game

        val gameIcon = view?.findViewById<ImageView>(R.id.gameIcon)
        Picasso.get()
            .load(game.url)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .into(gameIcon)

        val gameCheckBox = view!!.findViewById<CheckBox>(R.id.gameCheckBox)
        gameCheckBox!!.text = game.title

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