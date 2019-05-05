package cz.milatova.budhasdiet.ui.meal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.data.Meal
import cz.milatova.budhasdiet.data.MealAdapter
import cz.milatova.budhasdiet.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.meal_item_detail.*
import java.util.*

class MealDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_item_detail)

        editMealCancel.setOnClickListener() {
            val intent = Intent(this@MealDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val date = Date()
        editMealDate.setText(date.toString())

        editMealOk.setOnClickListener() {
            if (editMealType.text.isNullOrEmpty()) {
                Toast.makeText(this@MealDetailActivity, "Please enter meal type.",
                    Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            if (editMealDate.text.isNullOrEmpty()) {
                Toast.makeText(this@MealDetailActivity, "Please enter meal date.",
                    Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
            if (editMealTime.text.isNullOrEmpty()) {
                Toast.makeText(this@MealDetailActivity, "Please enter meal time.",
                    Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            //insert data to DB
            val db = FirebaseFirestore.getInstance()
            val meal = HashMap<String, Any>()
            meal["type"] = editMealType
            meal["date"] = editMealDate
            meal["time"] = editMealTime

            val meals= db.collection("meals")
            meals
                .add(meal)
                .addOnSuccessListener { documentReference ->
                    Log.d("LUDMILA", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("LUDMILA", "Error adding document", e)
                }

            val intent = Intent(this@MealDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}