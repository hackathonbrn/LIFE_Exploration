package com.example.hack.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.hack.ProfileActivity
import com.example.hack.R
import com.example.hack.entity.Teacher
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class TeacherItemAdapter(
    context: Context,
    private val teachers: ArrayList<Teacher> = ArrayList()
): BaseAdapter() {

    private val tInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = tInflater.inflate(R.layout.teacher_item_layout, parent, false)
        }

        val teacher: Teacher = getItem(position) as Teacher

        val teacherIcon = view?.findViewById<ImageView>(R.id.teacherIcon)
        Picasso.get()
            .load(teacher.avatar_url)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .into(teacherIcon)

        val teacherNickname = view!!.findViewById<TextView>(R.id.teacherNickname)
        val firstGame = view!!.findViewById<TextView>(R.id.firstGame)
        teacherNickname!!.text = teacher.username

        if (teacher.id_game == 1) {
            firstGame.text  = "CS:GO"
        } else {
            firstGame.text  = "DOTA2"
        }

        val cost = view.findViewById<TextView>(R.id.costTeacher)
        cost.text = teacher.cost_lesson.toString()
        val costIcon = view.findViewById<ImageView>(R.id.costIcon)
        Picasso.get()
            .load("https://i.yapx.ru/MR3nF.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(100, 100)
            .into(costIcon)

        val matches = view.findViewById<TextView>(R.id.matchesTeacher)
        matches.text = teacher.number_matches.toString()
        val matcheIcon = view.findViewById<ImageView>(R.id.matchIcon)
        Picasso.get()
            .load("https://i.yapx.ru/MR3eu.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(100, 100)
            .into(matcheIcon)

        val rank = view.findViewById<TextView>(R.id.rankTeacher)
        rank.text = teacher.rank.toString()
        val rankIcon = view.findViewById<ImageView>(R.id.rankIcon)
        Picasso.get()
            .load("https://i.yapx.ru/MR3qW.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(100, 100)
            .into(rankIcon)

        val hours = view.findViewById<TextView>(R.id.hoursTeacher)
        hours.text = teacher.hours_game.toString()
        val timIcon = view.findViewById<ImageView>(R.id.hourIcon)
        Picasso.get()
            .load("https://i.yapx.ru/MR3yL.png")
            .transform(RoundedCornersTransformation(30, 0))
            .resize(100, 100)
            .into(timIcon)

        val teacherItem = view.findViewById<LinearLayout>(R.id.teacherItem)
        teacherItem.setOnClickListener {
            val intent = Intent(view.context.applicationContext, ProfileActivity::class.java)
            intent.putExtra("WhoAmI", teacher.id)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(intent)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return teachers[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return teachers.size
    }
}