package cz.milatova.budhasdiet.ui.meal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
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

        val date = Date()
        editMealDate.setText(date.toString())

        editMealCancel.setOnClickListener() {
            val intent = Intent(this@MealDetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

        editMealOk.setOnClickListener() {
            if (editMealType.text.isNullOrEmpty()) {
                Toast.makeText(
                    this@MealDetailActivity, "Please enter meal type.",
                    Toast.LENGTH_SHORT
                ).show();
                return@setOnClickListener
            }
            if (editMealDate.text.isNullOrEmpty()) {
                Toast.makeText(
                    this@MealDetailActivity, "Please enter meal date.",
                    Toast.LENGTH_SHORT
                ).show();
                return@setOnClickListener
            }
            if (editMealTime.text.isNullOrEmpty()) {
                Toast.makeText(
                    this@MealDetailActivity, "Please enter meal time.",
                    Toast.LENGTH_SHORT
                ).show();
                return@setOnClickListener
            }
            addMealToList(it, editMealType.text.toString(), editMealDate.text.toString(), editMealTime.text.toString())
        }
    }

    private fun addMealToList(v: View, mealType: String, mealTime: String, mealDate: String) {
        val intent = Intent() //nemusím vědět, kam to posílám
        intent.putExtra("mealType", mealType)
        intent.putExtra("mealDate", mealDate)
        intent.putExtra("mealTime", mealTime)
        setResult(RESULT_OK, intent)
        finish()
    }
}

    /*            //insert data to DB
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
*/
