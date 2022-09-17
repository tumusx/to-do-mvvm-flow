package com.github.tumusx.todo.project.data.repository

import com.github.tumusx.todo.project.data.database.TasksDatabase
import com.github.tumusx.todo.project.data.entity.Tasks
import com.github.tumusx.todo.project.data.repository.model.TasksVO

class TasksRepository(private val tasksDatabase: TasksDatabase) {
    suspend fun insertTasks(tasksVO: TasksVO) {
        tasksDatabase.tasksDAO().insertTasks(
            Tasks(tittleInfo = tasksVO.tittleInfo, descriptionInfo = tasksVO.descriptionInfo)
        )
    }

    suspend fun listsTasks(): Result<List<TasksVO>> {
        return runCatching {
            val tasksListVO = mutableListOf<TasksVO>()
            tasksDatabase.tasksDAO().listTasks().forEach {
                if (it.idTaskInfo != null) {
                    tasksListVO.add(TasksVO(it.idTaskInfo, it.tittleInfo, it.descriptionInfo))
                }
            }
            tasksListVO
        }
    }
}