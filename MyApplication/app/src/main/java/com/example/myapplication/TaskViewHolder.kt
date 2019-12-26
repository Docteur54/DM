package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val title: TextView = itemView.findViewById(R.id.task_title)
    private val description: TextView = itemView.findViewById(R.id.task_description)
    private val id: TextView = itemView.findViewById(R.id.task_id)

    fun bind(task : Task){
        if(task == null)
        {
            val resources = itemView.resources
            title.text = resources.getString(R.string.loading_message)
        }
        else
        {
            title.text = task.title
            title.visibility = View.VISIBLE
            description.text = task.description
            description.visibility = View.VISIBLE
            id.text = task.id
            id.visibility = View.VISIBLE
        }
    }

}