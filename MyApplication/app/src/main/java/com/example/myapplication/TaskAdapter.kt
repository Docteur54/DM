package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.TaskViewHolder
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onDeleteClickListener: (id: Task) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun getItemCount(): Int {return tasks.size}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        Log.d("ViewHolder",task.id)
        holder.bind(task)
        holder.itemView.button_delete.setOnClickListener { onDeleteClickListener(task) }
        //holder.itemView.edit.setOnClickListener { onEditClickListener(tasks[position])}
    }
}