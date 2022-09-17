package com.github.tumusx.todo.project.presenter.state

import com.github.tumusx.todo.project.data.repository.model.TasksVO

sealed class ActionsListsState {
    data class ResultRequest(val tasksVOItems: List<TasksVO>) : ActionsListsState()
    data class MessageErrorLoadData(val messageError: String = "") : ActionsListsState()
    object IsLoadingRequest : ActionsListsState()
}