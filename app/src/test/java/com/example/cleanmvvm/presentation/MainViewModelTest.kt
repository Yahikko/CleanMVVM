package com.example.cleanmvvm.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.cleanmvvm.domain.models.SaveUserNameParam
import com.example.cleanmvvm.domain.usecase.GetUserNameUseCase
import com.example.cleanmvvm.domain.usecase.SaveUserNameUseCase
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class MainViewModelTest {

    private val getUserNameUseCase = mock<GetUserNameUseCase>()
    private val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
    }

    @Test
    fun `should save user name and return true`() {

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        val saveResult = true
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        viewModel.save(text = testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value

        Assertions.assertEquals(expected, actual)
        Mockito.verify(saveUserNameUseCase, times(1)).execute(param = testParams)
    }

    @Test
    fun `should save user name and return false`() {

    }

    @Test
    fun `should load username`() {

    }
}