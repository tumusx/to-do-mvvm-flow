package com.github.tumusx.todo.project.data.repository

import com.github.tumusx.todo.project.data.database.TasksDatabase
import com.github.tumusx.todo.project.data.entity.Task
import com.github.tumusx.todo.project.data.repository.model.TaskVO

class TaskRepository(private val tasksDatabase: TasksDatabase) {
    suspend fun insertTasks(taskVO: TaskVO) {
        tasksDatabase.tasksDAO().insertTasks(
            Task(tittleInfo = taskVO.tittleInfo, descriptionInfo = taskVO.descriptionInfo)
        )
    }

    suspend fun listsTasks(): Result<List<TaskVO>> {
        return runCatching {
            val tasksListVO = mutableListOf<TaskVO>()
            tasksDatabase.tasksDAO().listTasks().forEach {
                if (it.idTaskInfo != null) {
                    tasksListVO.add(TaskVO(it.idTaskInfo, it.tittleInfo, it.descriptionInfo))
                }
            }
            tasksListVO
        }
    }
}