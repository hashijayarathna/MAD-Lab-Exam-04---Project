package com.example.taskmanagementapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView to display tasks
class TaskAdapter(private var task: List<Task>, context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val db: TaskDatabaseHelper = TaskDatabaseHelper(context)


    // ViewHolder for the task items in the RecyclerView
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = task.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = task[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content

        // Set click listener for the update button
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateTaskActivity::class.java).apply {
                putExtra("task_id", task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for the delete button
        holder.deleteButton.setOnClickListener {
            // Delete task from the database
            db.deleteTask(task.id)
            refreshData(db.getAllTask())

            // Show a toast message for task deletion
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to refresh data
    fun refreshData(newTask: List<Task>){
        task = newTask
        notifyDataSetChanged()
    }


}