General Decision on the app design:

1. The UI is pretty basic and also there's no UI for selecting the ingredient for the recipe.
   I randomly select an ingredient from a list to fetch recipes.
2. The app follow the MVVM pattern. I could have used UseCases and make it follow Clean Architecture,
   but the requirements were simple and decided against it.
3. I am making network request using OkHttp and Retrofit.
4. Have used coroutines for dealing with async.
5. Tried using Koin for DI.