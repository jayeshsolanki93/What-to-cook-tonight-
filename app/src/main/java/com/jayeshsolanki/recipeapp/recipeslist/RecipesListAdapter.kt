package com.jayeshsolanki.recipeapp.recipeslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jayeshsolanki.recipeapp.R
import com.jayeshsolanki.recipeapp.data.model.Recipe
import com.jayeshsolanki.recipeapp.tools.glide.GlideApp
import kotlinx.android.synthetic.main.recipeslist_item.view.title
import kotlinx.android.synthetic.main.recipeslist_item.view.image

class RecipesListAdapter(
    private val itemClickListener: (recipeId: String) -> Unit
) : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    private var recipesList: MutableList<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipeslist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(recipesList[position], itemClickListener)
    }

    override fun getItemCount() = recipesList.size

    fun setAdapterData(recipesList: List<Recipe>) {
        this.recipesList.clear()
        this.recipesList.addAll(recipesList)
        // TODO: DiffUtil
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(recipe: Recipe, itemClickListener: (recipeId: String) -> Unit) {
            itemView.title.text = recipe.title
            GlideApp.with(view.context)
                    .load(recipe.image)
                    .centerCrop()
                    .into(itemView.image)
            itemView.setOnClickListener { itemClickListener(recipe.id) }
        }
    }
}