package com.github.tumusx.todo.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.tumusx.todo.project.data.entity.Tasks

@Dao
interface TasksDAO {

    @Insert
    fun insertTasks(vararg tasks: Tasks)


    @Query("SELECT *FROM tasks")
    fun listTasks(): List<Tasks>

}