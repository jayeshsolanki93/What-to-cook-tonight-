package com.jayeshsolanki.recipeapp.recipedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jayeshsolanki.recipeapp.R
import com.jayeshsolanki.recipeapp.data.model.Recipe
import com.jayeshsolanki.recipeapp.tools.glide.GlideApp
import kotlinx.android.synthetic.main.recipedetails_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipedetails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*viewModel = ViewModelProvider(this,
                ViewModelFactory.getInstance(context!!.applicationContext as WhatToCookTonight))
                .get(RecipeDetailsViewModel::class.java)*/
        viewModel.start(arguments!!.getString(ARG_RECIPE_ID)!!)
        viewModel.item.observe(viewLifecycleOwner, Observer {
            bindData(it)
        })
        viewModel.command.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun bindData(recipe: Recipe) {
        title.text = recipe.title
        GlideApp.with(context!!)
                .load(recipe.image)
                .centerCrop()
                .into(image)
        category.text = recipe.category
        from.text = recipe.from
        instructions.text = recipe.instructions
        instructions_label.visibility = View.VISIBLE
        ingredients.text = formatIngredientsData(recipe.ingredients, recipe.measurements)
        ingredients_label.visibility = View.VISIBLE
    }

    private fun formatIngredientsData(
        ingredients: List<String>,
        measurements: List<String>
    ): String {
        val ingredientMeasurements = ingredients.mapIndexed { index, ingredient ->
            val measurement = measurements[index]
            "$measurement $ingredient"
        }
        return ingredientMeasurements.joinToString("\n")
    }

    companion object {
        private const val ARG_RECIPE_ID = "ARG_RECIPE_ID"

        fun newInstance(recipeId: String) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RECIPE_ID, recipeId)
                }
            }
    }
}
