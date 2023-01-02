package com.chriskaras.mycalorietracker

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var i = 0

        val list = mutableListOf<Int>(1,2,3,4,5,6)

        for (index in 0 until list.size){
            println(list[index] == 3)
            if(list[index] == 3){
                i = index
                break
            } else {
                i= -1
            }
        }

        println(i)

    }
}