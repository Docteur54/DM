package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.network.Api
import com.example.myapplication.network.TasksRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_fragment.*
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.textView
import kotlinx.android.synthetic.main.task_fragment.view.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class TaskFragment : Fragment() {

    /*private val tasks = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )*/

    private val tasksRepository = TasksRepository()
    //private val tasks = mutableListOf<Task>()

    private val tasks = mutableListOf<Task>()

    private val taskAdapter = TaskAdapter(tasks,
        onDeleteClickListener = { task ->
            tasks.remove(task)
            tasksRepository.deleteTask(task.id)
            view?.tasks_recycler_view?.adapter?.notifyDataSetChanged()
        },
        onEditClickListener = { task ->
            //tasks.remove(task)
            //tasksRepository.deleteTask(task.id)
            val intent = Intent( this.context  ,TaskFormActivity::class.java)
            intent.putExtra("TASK_ID",task.id)
            intent.putExtra("TASK_TITLE",task.title)
            intent.putExtra("TASK_DESCRIPTION",task.description)
            //intent.putExtra("TASK_EDIT",true)
            startActivity(intent)
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.task_fragment, container, false)

        tasksRepository.getTasks().observe(this, Observer {
            if (it != null) {
                tasks.clear()
                tasks.addAll(it)
                taskAdapter.notifyDataSetChanged()
            }
        })

/*
        view.tasks_recycler_view.adapter = TaskAdapter(tasks,
        onDeleteClickListener = { task ->
            tasks.remove(task)
            view.tasks_recycler_view.adapter?.notifyDataSetChanged()},
        onEditClickListener = { task ->
            tasks.remove(task)
            view.tasks_recycler_view.adapter?.notifyDataSetChanged()})
*/

        view.tasks_recycler_view.adapter = taskAdapter

        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)



        return view
    }

/*
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

    }*/

}