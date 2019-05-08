package cz.milatova.budhasdiet.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.data.Meal
import cz.milatova.budhasdiet.data.MealAdapter
import cz.milatova.budhasdiet.ui.meal.MealDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.meal_item.*
import kotlinx.android.synthetic.main.meal_item.view.*

class MainActivity : AppCompatActivity() {

    internal val REQUEST_CODE_PRIDAT = 100
    lateinit var meals: ArrayList<Meal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealsList.layoutManager = LinearLayoutManager(this)

        meals = ArrayList<Meal>()
        meals.add(Meal(1, "snídaně", "2. 5. 2019", "7:43", 1))
        meals.add(Meal(2, "svačina", "2. 5. 2019", "10:02", 1))
        meals.add(Meal(3, "oběd", "2. 5. 2019", "12:06", 1))

        mealsList.adapter = MealAdapter(meals, this);
    }

    override fun onStart() {
        super.onStart()
        addButton.setOnClickListener()
        {
            addMeal(it)
        }
        mealsList.adapter = MealAdapter(meals, this);
    }

    private fun addMeal(v: View) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivityForResult(intent, REQUEST_CODE_PRIDAT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PRIDAT && resultCode == RESULT_OK) {
            val mealType = data?.getStringExtra("mealType").toString()
            val mealTime = data?.getStringExtra("mealTime").toString()
            val mealDate = data?.getStringExtra("mealDate").toString()
            meals.add(Meal(4, mealType, mealDate, mealTime, 1))
        }
        super.onActivityResult(resultCode, resultCode, data)
    }
}