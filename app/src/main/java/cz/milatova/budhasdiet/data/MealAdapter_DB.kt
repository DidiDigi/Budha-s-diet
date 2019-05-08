package cz.milatova.budhasdiet.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import cz.milatova.budhasdiet.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.meal_item.view.*

class MealAdapter_DB(val db: FirebaseFirestore, val context: Context): RecyclerView.Adapter<MealAdapter_DB.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meals = db.collection("melas").document()
        meals.get().addOnSuccessListener { documentSnapshot ->
            val meal = documentSnapshot.toObject(Meal::class.java)
            holder.mealItemName.text = meal?.type
            holder.mealItemTime.text = meal?.time
        }

        /*db.collection("cities")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }*/
    }
    override fun getItemCount(): Int {
        return arrayOf(db.collection("melas").get()).count()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mealItemName = itemView.findViewById<TextView>(R.id.mealItemName)
        val mealItemTime = itemView.findViewById<TextView>(R.id.mealItemTime)
    }
}