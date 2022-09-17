package com.github.tumusx.todo.project.presenter.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.tumusx.todo.project.R
import com.github.tumusx.todo.project.data.repository.model.TasksVO
import com.github.tumusx.todo.project.databinding.ActivityMainBinding
import com.github.tumusx.todo.project.presenter.view.fragments.ListTasksFragment
import com.github.tumusx.todo.project.presenter.viwModel.ActionsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        replaceFragment()
        setContentView(binding.root)
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, ListTasksFragment(), ListTasksFragment().tag)
            .commit()
    }
}