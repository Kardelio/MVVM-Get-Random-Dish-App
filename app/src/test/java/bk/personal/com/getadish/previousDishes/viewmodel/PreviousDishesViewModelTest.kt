package bk.personal.com.getadish.previousDishes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import bk.personal.com.getadish.IDishRepository
import bk.personal.com.getadish.randomDish.model.Dish
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import testingUtils.argumentCaptor
import testingUtils.capture
import testingUtils.classMock
import testingUtils.whenever

class PreviousDishesViewModelTest {

    private lateinit var viewmodel: PreviousDishesViewModel

    private val repo: IDishRepository = classMock()
    private val listOfDishesLiveData: MutableLiveData<List<Dish>> = MutableLiveData()
    private val previousDishObserver: Observer<List<Dish>> = classMock()
    private val previousDishArgumentCaptor: ArgumentCaptor<List<Dish>> = argumentCaptor()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        whenever(repo.getAllPreviousDishes()).thenReturn(listOfDishesLiveData)
        viewmodel = PreviousDishesViewModel(repo)
        viewmodel.previousDishes.observeForever(previousDishObserver)
    }

    @Test
    fun `previous dish list matches repo list in this case empty`() {
        listOfDishesLiveData.postValue(emptyList())
        verify(previousDishObserver).onChanged(capture(previousDishArgumentCaptor))
        assertEquals(0, previousDishArgumentCaptor.value.size)
    }

    @Test
    fun `previous dish list matches repo list in this case one dish`() {
        listOfDishesLiveData.postValue(
            listOf(Dish())
        )
        verify(previousDishObserver).onChanged(capture(previousDishArgumentCaptor))
        assertEquals(1, previousDishArgumentCaptor.value.size)
    }

    @Test
    fun `previous dish list matches repo list in this case multiple dishes`() {
        listOfDishesLiveData.postValue(
            listOf(
                Dish(),
                Dish(),
                Dish()
            )
        )
        verify(previousDishObserver).onChanged(capture(previousDishArgumentCaptor))
        assertEquals(3, previousDishArgumentCaptor.value.size)
    }
}