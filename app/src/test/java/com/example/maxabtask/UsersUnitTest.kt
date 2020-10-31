package com.example.maxabtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.example.maxabtask.datamodel.MainRepository
import com.example.maxabtask.ui.mainscreen.MainViewModel
import com.example.maxabtask.util.ViewModelState
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class UsersUnitTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var disposable: CompositeDisposable

    @Mock
    lateinit var observer: Observer<ViewModelState<DiffUtil.DiffResult>>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(mainRepository, disposable)
        viewModel.userState.observeForever(observer)
    }

    @Test
    fun testGetUser() {
//        Mockito.`when`(mainRepository.fetchUsers()).thenReturn(loginResponse.userToken)
    }

}