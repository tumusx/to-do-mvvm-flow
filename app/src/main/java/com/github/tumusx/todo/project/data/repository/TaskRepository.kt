package com.github.tumusx.todo.project.data.repository

import com.github.tumusx.todo.project.data.database.TasksDatabase
import com.github.tumusx.todo.project.data.entity.Task
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepository(private val tasksDatabase: TasksDatabase) {
     fun insertTasks(taskVO: TaskVO): Flow<Boolean> = flow {
        try {
            tasksDatabase.tasksDAO().insertTasks(
                Task(tittleInfo = taskVO.tittleInfo, descriptionInfo = taskVO.descriptionInfo)
            )
            emit(true)
        } catch (exception: Exception) {
            emit(false)
        }
    }

    fun listsTasks(): List<TaskVO> {
        val tasksListVO = mutableListOf<TaskVO>()
        tasksDatabase.tasksDAO().listTasks().forEach {
            if (it.idTaskInfo != null) {
                tasksListVO.add(TaskVO(it.idTaskInfo, it.tittleInfo, it.descriptionInfo))
            }
        }
        return tasksListVO
    }
}