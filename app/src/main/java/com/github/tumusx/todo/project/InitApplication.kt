package com.github.tumusx.todo.project

import android.app.Application
import com.github.tumusx.todo.project.di.listTaskModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InitApplication : Application (){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@InitApplication)
            modules(listTaskModule)
        }
    }
}