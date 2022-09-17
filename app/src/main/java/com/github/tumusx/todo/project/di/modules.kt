package com.github.tumusx.todo.project.di

import android.app.Application
import androidx.room.Room
import com.github.tumusx.todo.project.data.dao.TasksDAO
import com.github.tumusx.todo.project.data.database.TasksDatabase
import com.github.tumusx.todo.project.data.repository.TasksRepository
import com.github.tumusx.todo.project.presenter.viwModel.ActionsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listTaskModule = module {
    fun provideDatabaseManager(application: Application): TasksDatabase {
        return Room.databaseBuilder(application, TasksDatabase::class.java, "tasks.db")
            .fallbackToDestructiveMigration().build()
    }

    fun provideDao(database: TasksDatabase): TasksDAO = database.tasksDAO()

    factory { provideDao(get()) }
    factory { provideDatabaseManager(androidApplication()) }


    factory {
        TasksRepository(get())
    }
    viewModel {
        ActionsListViewModel(get())
    }
}