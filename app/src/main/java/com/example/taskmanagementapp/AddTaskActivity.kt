package com.example.taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    // Declare variables for data binding and database helper
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = TaskDatabaseHelper(this)

        binding.saveButton.setOnClickListener {

            // Retrieve title and content from EditText fields
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            // Create a Task object with the retrieved data
            val task = Task(0, title, content)

            // Insert the task into the database
            db.insertTask(task)

            // Finish the activity
            finish()

            // Display a message indicating that the task is saved
            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
        }
    }
}