package com.github.tumusx.todo.project.presenter.state

import android.app.Notification.Action
import com.github.tumusx.todo.project.data.repository.model.TaskVO

sealed class ActionsListsState {
    data class ResultRequest(val taskVOItems: List<TaskVO>) : ActionsListsState()
    data class MessageErrorLoadData(val messageError: String = "") : ActionsListsState()
    object IsLoadingRequest : ActionsListsState()

    data class SuccessInsert(val tasksSuccess: Boolean = false) : ActionsListsState()
}