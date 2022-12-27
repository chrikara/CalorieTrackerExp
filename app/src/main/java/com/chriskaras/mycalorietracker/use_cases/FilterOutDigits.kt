package com.chriskaras.mycalorietracker.use_cases

class FilterOutDigits {

    operator fun invoke(text : String) : String {
        return text.filter { it.isDigit() }
    }
}