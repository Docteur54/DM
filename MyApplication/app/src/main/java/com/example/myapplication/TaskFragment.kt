package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.view.*


class TaskFragment : Fragment() {

    private val tasks = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)
        view.tasks_recycler_view.adapter = TaskAdapter(tasks)
        { task ->
            tasks.remove(task)
            view.tasks_recycler_view.adapter?.notifyDataSetChanged()}

        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList("tasks",ArrayList(tasks))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arrayList = savedInstanceState?.getParcelableArrayList<Task>("tasks")
        if(arrayList!=null) {
            tasks.clear()
            tasks.addAll(arrayList!!)
        }

    }

}