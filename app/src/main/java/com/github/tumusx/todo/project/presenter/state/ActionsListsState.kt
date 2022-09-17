package com.github.tumusx.todo.project.presenter.state

import com.github.tumusx.todo.project.data.repository.model.TaskVO

sealed class ActionsListsState {
    data class ResultRequest(val taskVOItems: List<TaskVO>) : ActionsListsState()
    data class MessageErrorLoadData(val messageError: String = "") : ActionsListsState()
    object IsLoadingRequest : ActionsListsState()
}