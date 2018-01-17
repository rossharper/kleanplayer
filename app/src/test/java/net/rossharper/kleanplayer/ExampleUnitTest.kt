package net.rossharper.kleanplayer

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    sealed class TestSealedClass {
        class OneOption(val bob: String) : TestSealedClass()
        object OtherOption : TestSealedClass()
    }

    @Test
    fun addition_isCorrect() {

        val testthing : TestSealedClass = if(Math.random() > 0.5) {
            TestSealedClass.OneOption("string")
        } else {
            TestSealedClass.OtherOption
        }



        test(testthing)
    }

    fun test(option: TestSealedClass): Boolean {
        return when(option) {
            is TestSealedClass.OneOption -> true
            is TestSealedClass.OtherOption -> false
        }
    }
}
