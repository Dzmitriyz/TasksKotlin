package com.example.taskskotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskskotlin.databinding.TaskItemBinding

class TaskItemAdapter(val clickListener:(taskId: Long)->Unit)
    : ListAdapter<Task,TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    //class TaskItemViewHolder(val rootView: CardView):RecyclerView.ViewHolder(rootView) {
    class TaskItemViewHolder(val binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root){
//        val taskName = rootView.findViewById<TextView>(R.id.task_name)
//        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                    //val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
    fun bind(item: Task, clickListener: (taskId: Long) -> Unit){
        binding.task = item
        binding.root.setOnClickListener{
            clickListener(item.taskId)
        }
    }
    }
}