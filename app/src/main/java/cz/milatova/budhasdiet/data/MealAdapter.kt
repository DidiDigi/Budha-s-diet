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

class MealAdapter(
    val meals: ArrayList<Meal>,
    val context: Context
    ): RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mealItemName.text = meals[position].type
        holder.mealItemTime.text = meals[position].time
        }

    override fun getItemCount(): Int {
        return meals.count()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mealItemName = itemView.findViewById<TextView>(R.id.mealItemName)
        val mealItemTime = itemView.findViewById<TextView>(R.id.mealItemTime)
    }
}