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
import org.joda.time.DateTime
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    internal val REQUEST_CODE_PRIDAT = 100
    lateinit var meals: ArrayList<Meal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealsList.layoutManager = LinearLayoutManager(this)

        meals = ArrayList<Meal>()
        meals.add(Meal(1, "snídaně", DateTime.now(),1))
        meals.add(Meal(2, "svačina", DateTime.now(),1))
        meals.add(Meal(3, "oběd", DateTime.now(),1))

        mealsList.adapter = MealAdapter(meals, this);
    }

    override fun onStart() {
        super.onStart()
        addButton.setOnClickListener()
        {
            addMeal(it)
        }
        mealsList.adapter = MealAdapter(meals, this)
        dayDate.text = DateTime.now().toString("d.M.yyyy")
        stepName.setText("Krok 1")
        dayEatingHours.setText("12 hodin")
        stepDaysCount.setText("10 dnů")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PRIDAT && resultCode == RESULT_OK) {
            val mealType = data?.getStringExtra("mealType").toString()
            val mealTime = data?.getStringExtra("mealTime").toString()
            val mealDate = data?.getStringExtra("mealDate").toString()
            val mealDateTime = formatIntoDateTime(mealDate, mealTime)
            meals.add(Meal(4, mealType, mealDateTime, 1))
        }
        super.onActivityResult(resultCode, resultCode, data)
    }

    private fun formatIntoDateTime(mealDate: String, mealTime: String): DateTime {
        val year = mealDate.substring(mealDate.length - 4, mealDate.length).toInt()
        val monthOfYear = mealDate.substringBeforeLast(".", "").substringAfter(".").toInt()
        val dayOfMonth = mealDate.substringBefore(".").toInt()
        val hourOfDay = mealTime.substringBefore(":").toInt()
        val minuteOfHour = mealTime.substringAfter(":").toInt()
        return DateTime(year.toInt(), monthOfYear, dayOfMonth, hourOfDay, minuteOfHour)
    }

    private fun addMeal(v: View) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent.putExtra("mealDate", DateTime.now().toString("d.M.yyyy"))
        intent.putExtra("mealTime", DateTime.now().toString("H:mm"))
        startActivityForResult(intent, REQUEST_CODE_PRIDAT)
    }
}