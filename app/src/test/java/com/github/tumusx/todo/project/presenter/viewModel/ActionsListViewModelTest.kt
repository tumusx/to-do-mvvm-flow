package com.github.tumusx.todo.project.presenter.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.tumusx.todo.project.data.entity.Task
import com.github.tumusx.todo.project.data.repository.TaskRepository
import com.github.tumusx.todo.project.data.repository.model.TaskVO
import com.github.tumusx.todo.project.presenter.state.ActionsListsState
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

@DisplayName("class for test viewModel")
class ActionsListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<TaskRepository>()
    private val onDataLoad = mockk<Observer<ActionsListsState>>(relaxed = true)

    @MockK
    private val viewModel = ActionsListViewModel(repository)

    @Test
    fun whenChangedObservableViewModel() {
        val listMock = mutableListOf<TaskVO>(
            TaskVO(
                1,
                "murillo",
                "jose",
                "20-12-2001"
            )
        )

        every { repository.listsTasks() } returns listMock
        viewModel.listsTasks()
        viewModel.stateActions.observeForever(onDataLoad)
        verify { repository.listsTasks() }
        verify {
            onDataLoad.onChanged(
                ActionsListsState.ResultRequest(listMock)
            )
        }
    }

    @Test
    fun whenInsertUser() {
        val result = MutableStateFlow<Boolean>(true)
        val taskVO = TaskVO(
            1,
            "murillo",
            "jose",
            "20-12-2001"
        )
        every { repository.insertTasks(taskVO) } returns result
        viewModel.insertTasks(taskVO)
        viewModel.stateActions.observeForever(onDataLoad)

        verify { repository.insertTasks(taskVO) }
        verify { onDataLoad.onChanged(ActionsListsState.SuccessInsert(true)) }
    }
}