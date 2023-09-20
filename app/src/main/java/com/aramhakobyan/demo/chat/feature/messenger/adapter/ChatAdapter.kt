package com.aramhakobyan.demo.chat.feature.messenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.aramhakobyan.demo.chat.data.model.Message
import com.aramhakobyan.demo.chat.R

class ChatAdapter(
    private val dataSet: MutableList<Message>
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: AppCompatTextView

        init {
            textView = view.findViewById(R.id.message)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_chat, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].text
    }

    override fun getItemCount() = dataSet.size

    fun addMessage(item: Message) {
        dataSet.add(item)
        notifyItemInserted(dataSet.size - 1)
    }

    fun updateMessageText() {
        if (dataSet.isEmpty()) {
            return
        }

        val position = dataSet.size - 1
        val text = dataSet[position].text
        dataSet[position].text = "$text +"
        notifyItemChanged(position)
    }
}
