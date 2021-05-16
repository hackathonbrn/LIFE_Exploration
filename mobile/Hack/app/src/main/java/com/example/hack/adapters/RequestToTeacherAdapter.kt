package com.example.hack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.hack.R
import com.example.hack.entity.RequestToTeacher

class RequestToTeacherAdapter (context: Context,
                               private val requests: ArrayList<RequestToTeacher> = ArrayList()
) : BaseAdapter() {
    private val rInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = rInflater.inflate(R.layout.item_request_to_teacher, parent, false)
        }

        val request: RequestToTeacher = getItem(position) as RequestToTeacher
        val usernameRequest = view?.findViewById<TextView>(R.id.usernameReview)
        usernameRequest?.text  = request.username + " запросил обучение."

        val gameReview = view?.findViewById<TextView>(R.id.gameReview)
        gameReview?.text  = "Игра: " + request.game_name

        val countReview = view?.findViewById<TextView>(R.id.countReview)
        countReview?.text  = "Количество занятий: "  + request.count_lessons.toString()

        return view!!
    }

    override fun getItem(position: Int): Any {
        return requests[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return requests.size
    }

}