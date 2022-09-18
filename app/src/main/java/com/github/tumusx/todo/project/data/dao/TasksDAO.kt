package com.github.tumusx.todo.project.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.tumusx.todo.project.data.entity.Task

@Dao
interface TasksDAO {

    @Insert
    suspend fun insertTasks(vararg tasks: Task)


    @Query("SELECT *FROM tasks")
     fun listTasks(): List<Task>

}