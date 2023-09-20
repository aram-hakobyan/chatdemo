package com.aramhakobyan.demo.chat.feature.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.aramhakobyan.demo.chat.R

class NumberAdapter(
    private val size: Int
) : RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: AppCompatTextView

        init {
            textView = view.findViewById(R.id.number)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_numbers, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = (position + 1).toString()
    }

    override fun getItemCount() = size
}
