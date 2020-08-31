package bk.personal.com.getadish.randomDish.ui

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class RandomDishFragmentTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Before
    fun setup(){
        rule.inject()
    }

    @After
    fun teardown(){

    }

}