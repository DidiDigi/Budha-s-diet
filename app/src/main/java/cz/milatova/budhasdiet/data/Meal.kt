package cz.milatova.budhasdiet.data

import org.joda.time.DateTime

data class Meal(
    val id : Int,
    val type: String,
    val dateTime: DateTime,
    val imageId : Int) {


}


