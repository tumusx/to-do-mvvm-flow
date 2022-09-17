package com.github.tumusx.todo.project.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.tumusx.todo.project.databinding.ActivityMainBinding
import com.github.tumusx.todo.project.presenter.view.fragments.ListTasksFragment

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