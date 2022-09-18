package com.github.tumusx.todo.project.presenter.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import com.github.tumusx.todo.project.databinding.ListsTasksFragmentBinding
import com.github.tumusx.todo.project.presenter.adapter.ListTasksAdapter
import com.github.tumusx.todo.project.presenter.state.ActionsListsState
import com.github.tumusx.todo.project.presenter.viewModel.ActionsListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListTasksFragment : Fragment() {

    private val viewModelAction: ActionsListViewModel by viewModel()
    private lateinit var binding: ListsTasksFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ListsTasksFragmentBinding.inflate(layoutInflater)
        onConfigState()
        return binding.root
    }

    private fun onConfigAdapter(tasks: List<TaskVO>){
        val listTasksAdapter = ListTasksAdapter()
        binding.rvListsTasks.adapter = listTasksAdapter
        listTasksAdapter.updateLists(tasks)
    }

    private fun onConfigState() {
        viewLifecycleOwner.lifecycleScope.launch {
/*            viewModelAction.stateActions.collect { state ->
                when (state) {
                    is ActionsListsState.ResultRequest -> {
                        onConfigAdapter(state.taskVOItems)
                        println(state.taskVOItems)
                    }

                    is ActionsListsState.IsLoadingRequest -> {
                        println(state)
                    }

                    is ActionsListsState.MessageErrorLoadData -> {
                        Snackbar.make(binding.root, state.messageError, Snackbar.LENGTH_LONG).show()
                    }
                }
            }*/
        }
    }

}