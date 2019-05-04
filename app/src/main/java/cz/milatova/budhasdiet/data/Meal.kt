package cz.milatova.budhasdiet.data


data class Meal(val id : Int, val name: MealType, val date: String, val time: String, val imageId : Int) {

}

enum class MealType() {
    snídaně, svačina, oběd, večeře, pamlsek
}
