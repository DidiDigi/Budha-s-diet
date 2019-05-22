package cz.milatova.budhasdiet.data

import org.joda.time.DateTime

class Day (
    val date: DateTime,
    val stepId: Int,
    val eatingHours: Int,
    val daySuccesfull: Boolean // false = cheatday
) {

}