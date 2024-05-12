package com.example.taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementapp.databinding.ActivityUpdateTaskBinding

// Activity for updating a task
class UpdateTaskActivity : AppCompatActivity() {

    // Declare variables for data binding, database helper, and task ID
    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        // Get the task ID passed from the previous activity
        taskId = intent.getIntExtra("task_id", -1)
        // If task ID is invalid, finish the activity
        if (taskId == -1){
            finish()
            return
        }

        // Retrieve the task details from the database and move into the EditText fields
        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)

        // Set click listener for the save button
        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedTask = Task(taskId, newTitle, newContent)

            // Update the task in the database
            db.updateTask(updatedTask)
            finish()
            // Display a message when changes are saved
            Toast.makeText(this, "Changes Saved.", Toast.LENGTH_SHORT).show()
        }
    }
}