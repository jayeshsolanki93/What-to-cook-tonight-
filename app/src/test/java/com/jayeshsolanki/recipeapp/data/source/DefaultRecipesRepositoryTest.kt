package com.jayeshsolanki.recipeapp.data.source

import com.jayeshsolanki.recipeapp.data.Result.Success
import com.jayeshsolanki.recipeapp.data.Result.Error
import com.jayeshsolanki.recipeapp.data.api.MealsApi
import com.jayeshsolanki.recipeapp.data.model.Meal
import com.jayeshsolanki.recipeapp.data.model.MealsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class DefaultRecipesRepositoryTest {

    private lateinit var mealsApi: MealsApi
    private lateinit var recipesRepository: RecipesRepository

    @Before
    fun setup() {
        mealsApi = mockk()
        recipesRepository = DefaultRecipesRepository(mealsApi)
    }

    @Test
    fun `DefaultRecipesRepository should returns some data`() = runBlockingTest {
        coEvery { mealsApi.mealsByIngredient(any(), any()) } returns MealsResponse(listOf(
            Meal("1", "Salt", ""),
            Meal("2", "Sugar", "")
        ))

        val result = recipesRepository.getRecipesList("avocado")

        coVerify { mealsApi.mealsByIngredient(any(), any()) }
        assertTrue(result is Success)
        assertEquals((result as Success).data.size, 2)
    }

    @Test
    fun `DefaultRecipesRepository should return empty data`() = runBlockingTest {
        coEvery { mealsApi.mealsByIngredient(any(), any()) } returns MealsResponse(emptyList())

        val result = recipesRepository.getRecipesList("avocado")

        coVerify { mealsApi.mealsByIngredient(any(), any()) }
        assertTrue(result is Success)
        assertTrue((result as Success).data.isEmpty())
    }

    @Test
    fun `DefaultRecipesRepository handles IOException and returns error`() = runBlockingTest {
        coEvery { mealsApi.mealsByIngredient(any(), any()) } throws IOException()

        val result = recipesRepository.getRecipesList("avocado")

        coVerify { mealsApi.mealsByIngredient(any(), any()) }
        assertTrue(result is Error)
    }
}
