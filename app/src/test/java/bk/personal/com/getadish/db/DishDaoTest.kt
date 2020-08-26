package bk.personal.com.getadish.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import bk.personal.com.getadish.randomDish.model.Dish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DishDaoTest {

    private lateinit var dao: DishDao
    private lateinit var database: GetADishDatabase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    //Remember runs before each test
    //so there will be a fresh DB each test
    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, GetADishDatabase::class.java).build()
        dao = database.getDishDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB(){
        database.close()
    }

    @Test
    fun `test add a single dish into empty DB`() = runBlocking(Dispatchers.IO){
        val res = dao.addDish(Dish(name = "simple"))
        //Adds into first row of DB not 0 indexed
        assertEquals(1, res)
    }

    @Test
    fun `test add a single dish get all dishes afterwards`() = runBlocking(Dispatchers.IO){
        dao.addDish(Dish(name = "simple"))
        val dishes = dao.getAllDishes()
        assertEquals(1, dishes.size)
    }
}