package com.jayeshsolanki.recipeapp.recipeslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayeshsolanki.recipeapp.R
import com.jayeshsolanki.recipeapp.ViewModelFactory
import com.jayeshsolanki.recipeapp.WhatToCookTonight
import kotlinx.android.synthetic.main.recipeslist_fragment.*

class RecipesListFragment : Fragment() {

    private lateinit var viewModel: RecipesListViewModel

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
        recipesListAdapter = RecipesListAdapter { recipeId ->
            // TODO: Start second screen with the recipe id
        }
        list_recipes.adapter = recipesListAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(context!!.applicationContext as WhatToCookTonight)).get(RecipesListViewModel::class.java)
        viewModel.items.observe(this, Observer {
            recipesListAdapter.setAdapterData(it)
        })
        viewModel.command.observe(this, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun newInstance() = RecipesListFragment()
    }
}
