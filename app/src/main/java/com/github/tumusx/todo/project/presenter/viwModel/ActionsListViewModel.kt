package com.github.tumusx.todo.project.presenter.viwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.todo.project.data.repository.TasksRepository
import com.github.tumusx.todo.project.data.repository.model.TasksVO
import com.github.tumusx.todo.project.presenter.state.ActionsListsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActionsListViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    private val state: MutableStateFlow<ActionsListsState> = MutableStateFlow(ActionsListsState.IsLoadingRequest)
    val stateActions: StateFlow<ActionsListsState> = state

    init {
        listsTasks()
    }

    fun insertTasks(tasksVO: TasksVO) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tasksRepository.insertTasks(tasksVO)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

     private fun listsTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = ActionsListsState.IsLoadingRequest
            tasksRepository.listsTasks().onSuccess {
                state.value = ActionsListsState.ResultRequest(it)
            }.onFailure {
                state.value = ActionsListsState.MessageErrorLoadData(it.localizedMessage ?: "Erro ao localizar lista")
                it.printStackTrace()
            }
        }
    }
}