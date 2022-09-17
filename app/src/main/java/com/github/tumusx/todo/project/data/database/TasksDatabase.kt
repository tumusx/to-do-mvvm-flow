package com.github.tumusx.todo.project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.tumusx.todo.project.data.dao.TasksDAO
import com.github.tumusx.todo.project.data.entity.Tasks

@Database(entities = [Tasks::class], version = 2, exportSchema = false)
abstract class TasksDatabase : RoomDatabase(){
    abstract fun tasksDAO(): TasksDAO
}