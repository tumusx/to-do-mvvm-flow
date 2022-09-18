package com.github.tumusx.todo.project.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.todo.project.common.ResultDataSourceUtil
import com.github.tumusx.todo.project.data.repository.TaskRepository
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import com.github.tumusx.todo.project.presenter.state.ActionsListsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ActionsListViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val state: MutableStateFlow<ActionsListsState> =
        MutableStateFlow(ActionsListsState.IsLoadingRequest)
    val stateActions: StateFlow<ActionsListsState> = state

    fun insertTasks(taskVO: TaskVO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.insertTasks(taskVO).onEach { resultInsert ->
                state.value = ActionsListsState.SuccessInsert(resultInsert)
            }
        }
    }

    fun listsTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = ActionsListsState.IsLoadingRequest
            taskRepository.listsTasks().onEach { resultDataSourceUtil ->
                when (resultDataSourceUtil) {
                    is ResultDataSourceUtil.SuccessResultDataSourceDataSourceUtil -> {
                        resultDataSourceUtil.dataResult?.let {
                            state.value = ActionsListsState.ResultRequest(resultDataSourceUtil.dataResult)
                        }
                    }

                    is ResultDataSourceUtil.ErrorResultDataSourceDataSourceUtil -> {
                        state.value = ActionsListsState.MessageErrorLoadData(resultDataSourceUtil.message.toString())
                    }
                }
            }
        }
    }
}