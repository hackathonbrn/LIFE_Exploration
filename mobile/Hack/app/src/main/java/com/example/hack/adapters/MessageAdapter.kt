package com.example.hack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_VIEW_END
import android.view.View.TEXT_ALIGNMENT_VIEW_START
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.hack.R
import com.example.hack.entity.GameInfo
import com.example.hack.entity.Message

class MessageAdapter(
    context: Context,
    private val messages: ArrayList<Message> = ArrayList(),
    private val whoAmId: Int
) : BaseAdapter() {
    private val gInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        if (convertView == null) {
            view = gInflater.inflate(R.layout.message_item_layout, parent, false)
        }

        val message: Message = getItem(position) as Message

        val messageItem = view!!.findViewById<TextView>(R.id.messageItem)
        messageItem!!.text = message.message
        if (message.id_first == whoAmId) {
            messageItem.textAlignment = TEXT_ALIGNMENT_VIEW_END
        } else {
            messageItem.textAlignment = TEXT_ALIGNMENT_VIEW_START
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return messages.size
    }
}