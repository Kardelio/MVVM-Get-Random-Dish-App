package bk.personal.com.getadish.randomDish.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import bk.personal.com.getadish.IDishRepository
import bk.personal.com.getadish.config.IAppStatusModel
import bk.personal.com.getadish.randomDish.model.Dish
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import testingUtils.*


class RandomDishViewModelTest {

    private lateinit var viewModel: RandomDishViewModel

    private val model: IAppStatusModel = classMock()
    private val repo: IDishRepository = classMock()
    private val randomDishObserver: Observer<Dish> = classMock()
    private val randomDishArgumentCaptor: ArgumentCaptor<Dish> = argumentCaptor()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RandomDishViewModel(model, repo)
        viewModel.currentDish.observeForever(randomDishObserver)
    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `test get random dish returns a dish and timestamp updated`() = runBlockingTest{
        whenever(repo.getARandomDish()).thenReturn(Dish(name = "test"))
        viewModel.getRandomDish()
        verify(model).lastTimeStampButtonPressed = anyLong()
        verify(randomDishObserver).onChanged(capture(randomDishArgumentCaptor))
        assertEquals("test", randomDishArgumentCaptor.value.name)
    }
}