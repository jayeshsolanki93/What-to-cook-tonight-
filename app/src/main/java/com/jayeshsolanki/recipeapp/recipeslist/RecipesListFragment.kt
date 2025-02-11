package com.jayeshsolanki.recipeapp.recipeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayeshsolanki.recipeapp.R
import com.jayeshsolanki.recipeapp.recipedetails.RecipeDetailsFragment
import kotlinx.android.synthetic.main.recipeslist_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class RecipesListFragment : Fragment() {

    private val viewModel: RecipesListViewModel by viewModel()

    private lateinit var recipesListAdapter: RecipesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipeslist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        list_recipes.layoutManager = layoutManager
        recipesListAdapter = RecipesListAdapter(::openRecipeDetails)
        list_recipes.adapter = recipesListAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            recipesListAdapter.setAdapterData(it)
        })
        viewModel.command.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun openRecipeDetails(recipeId: String) {
        val recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipeId)
        activity!!.supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, recipeDetailsFragment, "DetailsFragment")
            addToBackStack("DetailsFragment")
            commit()
        }
    }

    override fun onDestroyView() {
        list_recipes.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = RecipesListFragment()
    }
}
