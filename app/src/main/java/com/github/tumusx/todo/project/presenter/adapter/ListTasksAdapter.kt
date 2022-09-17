package com.github.tumusx.todo.project.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import com.github.tumusx.todo.project.databinding.ContainerItemTasksListBinding

class ListTasksAdapter : RecyclerView.Adapter<ListTasksAdapter.ListsTasksViewHolder>() {

    private var tasksLists = emptyList<TaskVO>()

    class ListsTasksViewHolder(private val binding: ContainerItemTasksListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun configUI(taskVO: TaskVO) {
            with(binding) {
                txtDateInsertTask.text = taskVO.dataDescriptionInsert
                txtDescriptionTask.text = taskVO.descriptionInfo
                txtTittleTasks.text = taskVO.tittleInfo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsTasksViewHolder {
        return ListsTasksViewHolder(ContainerItemTasksListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = tasksLists.size

    override fun onBindViewHolder(holder: ListsTasksViewHolder, position: Int) {
        holder.configUI(tasksLists[position])
    }

    fun updateLists(tasksListsUpdate: List<TaskVO>) {
        tasksLists = tasksListsUpdate
        notifyDataSetChanged()
    }
}