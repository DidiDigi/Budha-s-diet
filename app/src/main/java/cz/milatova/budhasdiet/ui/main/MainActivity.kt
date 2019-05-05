package cz.milatova.budhasdiet.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.data.Meal
import cz.milatova.budhasdiet.data.MealAdapter
import cz.milatova.budhasdiet.ui.meal.MealDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealsList.layoutManager = LinearLayoutManager(this)

        val meals = ArrayList<Meal>()
        meals.add(Meal(1, "snídaně", "2. 5. 2019", "7:43", 1))
        meals.add(Meal(2, "svačina", "2. 5. 2019", "10:02", 1))
        meals.add(Meal(3, "oběd", "2. 5. 2019", "12:06", 1))

        mealsList.adapter = MealAdapter(meals, this);
   }

    override fun onStart() {
        super.onStart()
        mealsList.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            /* val intent = Intent(this@MainActivity, MealDetailActivity::class.java);
            startActivity(intent)
            Log.w("LUDMILA", "mealItem setOnClickListener nastaven")*/
        }

        addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MealDetailActivity::class.java);
            startActivity(intent)
        }
    }


}