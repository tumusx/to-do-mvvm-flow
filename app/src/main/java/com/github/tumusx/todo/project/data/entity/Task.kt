package com.github.tumusx.todo.project.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_task_info")
    var idTaskInfo: Long? = null,

    @ColumnInfo(name = "tittle_info")
    var tittleInfo: String = "",

    @ColumnInfo(name = "description_info")
    var descriptionInfo: String = "",
) : java.io.Serializable{
}