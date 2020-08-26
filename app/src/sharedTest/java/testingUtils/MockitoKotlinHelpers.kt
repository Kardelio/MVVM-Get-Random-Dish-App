package testingUtils


/**
 * Helper functions that are workarounds to kotlin Runtime Exceptions when using kotlin.
 */

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber


fun <T> Stubber.whenever(mock: T): T = `when`(mock)
fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)!!

fun <T> same(obj: T): T = Mockito.same<T>(obj)

fun <T> any(): T = Mockito.any<T>()
fun anyBoolean(): Boolean = Mockito.anyBoolean()

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)
inline fun <reified T : Any> classMock(): T = Mockito.mock(T::class.java)
