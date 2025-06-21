package com.devforge.fitguard.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devforge.fitguard.R
import com.devforge.fitguard.data.RecordEntity
import com.devforge.fitguard.ui.result.ResultActivity
import com.devforge.fitguard.utils.DateHelper

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ActivityViewHolder>() {

    private val items = mutableListOf<RecordEntity>()

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text)
        val date: TextView = itemView.findViewById(R.id.date_text)
        val time: TextView = itemView.findViewById(R.id.time_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.id.toString()
        holder.date.text = item.date?.let { DateHelper.formatDateToIndo(it) }
        holder.time.text = item.date?.let { DateHelper.formatTimeToIndo(it) }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ResultActivity::class.java)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(newList: List<RecordEntity>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}