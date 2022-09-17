package com.github.tumusx.todo.project.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.todo.project.data.repository.TaskRepository
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import com.github.tumusx.todo.project.presenter.state.ActionsListsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActionsListViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val state: MutableStateFlow<ActionsListsState> = MutableStateFlow(ActionsListsState.IsLoadingRequest)
    val stateActions: StateFlow<ActionsListsState> = state

    init {
        listsTasks()
    }

    fun insertTasks(taskVO: TaskVO) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskRepository.insertTasks(taskVO)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

     private fun listsTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = ActionsListsState.IsLoadingRequest
            taskRepository.listsTasks().onSuccess {
                state.value = ActionsListsState.ResultRequest(it)
            }.onFailure {
                state.value = ActionsListsState.MessageErrorLoadData(it.localizedMessage ?: "Erro ao localizar lista")
                it.printStackTrace()
            }
        }
    }
}