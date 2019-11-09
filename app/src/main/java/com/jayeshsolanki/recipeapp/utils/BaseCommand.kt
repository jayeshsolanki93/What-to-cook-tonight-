package com.jayeshsolanki.recipeapp.utils

/**
 * Borrowed from https://medium.com/@abhishektiwari_51145/how-to-use-singleliveevent-in-mvvm-architecture-component-b7c04ed8705
 */
sealed class BaseCommand(val message: String) {

    class Error(errorString: String) : BaseCommand(errorString)

    class Success(toastMessage: String) : BaseCommand(toastMessage)
}
