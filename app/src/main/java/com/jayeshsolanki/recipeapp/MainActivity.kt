package com.jayeshsolanki.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jayeshsolanki.recipeapp.recipeslist.RecipesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val recipesListFragment = RecipesListFragment.newInstance()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, recipesListFragment)
                commit()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }
}
