package cz.milatova.budhasdiet.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.data.Meal
import cz.milatova.budhasdiet.data.MealAdapter
import cz.milatova.budhasdiet.data.MealType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealsList.layoutManager = LinearLayoutManager(this)

        val meals = ArrayList<Meal>()
        meals.add(Meal(1, MealType.snídaně, "2. 5. 2019", "7:43", 1))
        meals.add(Meal(1, MealType.svačina, "2. 5. 2019", "10:02", 1))
        meals.add(Meal(1, MealType.oběd, "2. 5. 2019", "12:06", 1))

        mealsList.adapter = MealAdapter(meals, this);
    }



}