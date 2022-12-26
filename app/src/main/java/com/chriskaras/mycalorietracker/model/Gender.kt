package com.chriskaras.mycalorietracker.model

sealed class Gender(val name : String){
    object Male : Gender("male")
    object Female : Gender("female")

    companion object{
        fun returnGender(genderString : String) : Gender{
            return if(genderString == "male") Male else Female
        }


    }
}
