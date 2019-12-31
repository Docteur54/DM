package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.network.TasksRepository
import kotlinx.android.synthetic.main.activity_task_form.*


class TaskFormActivity : AppCompatActivity() {

    private val tasksRepository = TasksRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        val okButton: Button = findViewById(R.id.okButton)
        val backButton: Button = findViewById(R.id.backButton)
        val descriptionString: String?
        val titleString: String?
        val idString: String?
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                descriptionString = null
                titleString = null
                idString = null
            } else {
                descriptionString = extras.getString("TASK_DESCRIPTION")
                descriptionText.setText(descriptionString)

                titleString = extras.getString("TASK_TITLE")
                titleText.setText(titleString)

                idString = extras.getString("TASK_ID")
            }
        } else {
            descriptionString = savedInstanceState.getSerializable("TASK_DESCRIPTION") as String
            descriptionText.setText(descriptionString)

            titleString = savedInstanceState.getSerializable("TASK_TITLE") as String
            titleText.setText(titleString)

            idString = savedInstanceState.getSerializable("TASK_ID") as String
        }

        okButton.setOnClickListener {
            validateTask(
                findViewById(R.id.descriptionText),
                findViewById(R.id.titleText),
                idString) }
        backButton.setOnClickListener { cancelForm() }
    }


    private fun cancelForm() {
        val intent = Intent( this  ,MainActivity::class.java)
        startActivity(intent)
    }

    private fun validateTask(descriptionString: EditText?, titleString: EditText?, idString: String?) {
        val intent = Intent( this  ,MainActivity::class.java)
        if(idString == null)
        {
            tasksRepository.createTask(Task("",titleString?.text.toString(),descriptionString?.text.toString()))
        }
        else
        {
            tasksRepository.editTask(Task(idString,titleString?.text.toString(),descriptionString?.text.toString()))
        }
        startActivity(intent)
    }
}
