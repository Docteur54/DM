package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.TaskViewHolder

class TaskAdapter(private val tasks: Array<Task>) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun getItemCount(): Int {return tasks.size}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        Log.d("ViewHolder",tasks[position].id)
        holder.bind(tasks[position].id)
        //holder.itemView.button_delete.setOnClickListener { onDeleteClickListener(tasks[position]) }
        //holder.itemView.edit.setOnClickListener { onEditClickListener(tasks[position])}
    }
}