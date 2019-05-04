package cz.milatova.budhasdiet.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.milatova.budhasdiet.R

class MealAdapter(val mealList: ArrayList<Meal>, val context: Context): RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.meal_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mealItemName?.text = mealList[position].name.toString()
        holder.mealItemTime?.text = mealList[position].time.toString()
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mealItemName = itemView.findViewById<TextView>(R.id.mealItemName)
        val mealItemTime = itemView.findViewById<TextView>(R.id.mealItemTime)
    }

}