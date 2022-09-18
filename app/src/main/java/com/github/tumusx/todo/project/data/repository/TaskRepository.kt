package com.github.tumusx.todo.project.data.repository

import com.github.tumusx.todo.project.common.ResultDataSourceUtil
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

    fun listsTasks(): Flow<ResultDataSourceUtil<List<TaskVO>>> = flow {
        val tasksListVO = mutableListOf<TaskVO>()
        try {
            tasksDatabase.tasksDAO().listTasks().forEach {
                if (it.idTaskInfo != null) {
                    tasksListVO.add(TaskVO(it.idTaskInfo, it.tittleInfo, it.descriptionInfo))
                }
                emit(ResultDataSourceUtil.SuccessResultDataSourceDataSourceUtil(tasksListVO))
            }
        } catch (exception: Exception) {
            emit(
                ResultDataSourceUtil.ErrorResultDataSourceDataSourceUtil(
                    exception.message ?: "Erro ao buscar dados do servidor local"
                )
            )
            exception.printStackTrace()
        }
    }
}