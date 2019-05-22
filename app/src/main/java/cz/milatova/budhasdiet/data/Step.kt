package cz.milatova.budhasdiet.data

import org.joda.time.DateTime

class Step (
    val stepId: Int,
    val eatingHours: Int,
    val nubmerOfSucceesfullDays: Int,
    val numberOfCheatDays: Int,
    val lastCheatDayDate: DateTime,
    val nextStepId: Int,
    val canGoToNextStep: Boolean) {

}