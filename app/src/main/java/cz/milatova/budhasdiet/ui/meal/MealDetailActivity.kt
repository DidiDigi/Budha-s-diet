package cz.milatova.budhasdiet.ui.meal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cz.milatova.budhasdiet.R
import cz.milatova.budhasdiet.ui.main.MainActivity
import kotlinx.android.synthetic.main.meal_item_detail.*
import org.joda.time.DateTime

class MealDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_item_detail)
        editMealDate.setText(DateTime.now().toString("d.M.yyyy"))
        editMealTime.setText(DateTime.now().toString("H:mm"))
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
            addMealConfirm(it, editMealType.text.toString(), editMealDate.text.toString(), editMealTime.text.toString())
        }
    }

    private fun addMealConfirm(v: View, mealType: String, mealDate: String, mealTime: String) {
        val intent = Intent()
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
            meal["dateTime"] = editMealDate
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
