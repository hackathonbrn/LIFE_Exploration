package com.example.hack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.hack.R
import com.example.hack.entity.UserProfile
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class TeacherItemAdapter(
    context: Context,
    private val teachers: ArrayList<UserProfile> = ArrayList()
): BaseAdapter() {

    private val tInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = tInflater.inflate(R.layout.teacher_item_layout, parent, false)
        }

        val teacher: UserProfile = getItem(position) as UserProfile

        val teacherIcon = view?.findViewById<ImageView>(R.id.teacherIcon)
        Picasso.get()
            .load(teacher.imageUrl)
            .transform(RoundedCornersTransformation(30, 0))
            .resize(200, 200)
            .into(teacherIcon)

        val teacherNickname = view!!.findViewById<TextView>(R.id.teacherNickname)
        teacherNickname!!.text = teacher.nickname

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