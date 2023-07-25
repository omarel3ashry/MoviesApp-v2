![MoviesApp-v2 Banner](https://github.com/omarel3ashry/MoviesApp-v2/blob/master/art/app_cover.jpg)
# MoviesApp-v2
MoviesApp-v2 is an upgraded version of my MoviesApp, showcasing Clean Architecture principles, the Repository Pattern, and MVVM pattern. an Android application that allows users to discover the latest movies, view movie details, and save their favorite movies locally. This app is built with modern Android development tools and libraries, including Navigation Component, Material Components, Hilt, Kotlin Coroutines, Retrofit, and Room. It seamlessly integrates with The Movie Database (TMDB) API to fetch and display movie data.

## Table of Contents

- [Features](#features)
- [Libraries Used](#libraries-used)
- [Installation](#installation)
- [API Access Token](#api-access-token)
- [Contributing](#contributing)
- [Todo](#todo)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Features

- Discover the latest and most popular movies.
- View detailed information about a specific movie, including an overview, ratings, cast, production companies, and similar movies.
- Save movies to a favorites list for offline viewing.
- Filter movies based on genres, ratings, and release date.
- Smooth and intuitive user experience with Navigation Component and Material Components.
- Efficiently manage network requests and background operations with Kotlin Coroutines.
- Store and retrieve movie data locally using Room, an Android SQLite database library.
- Seamlessly integrate with The Movie Database (TMDB) API for up-to-date movie information.

## Libraries Used

MoviesApp-v2 utilizes the following libraries and tools:

- [Navigation Component](https://developer.android.com/guide/navigation) - Android's official library for implementing navigation between destinations.
- [Material Components](https://material.io/develop/android/docs/getting-started/) - Material Design components for Android apps, providing beautiful and consistent UI elements.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - A dependency injection library for Android that simplifies dependency injection and provides compile-time safety.
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A powerful framework for managing background threads with simplified code.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java that makes it easy to consume RESTful web services.
- [Room](https://developer.android.com/training/data-storage/room) - A persistence library for Android that provides an abstraction layer over SQLite.

## Installation

To run the MoviesApp-v2 on your Android device or emulator, follow these steps:

1. Open your Android Studio then go to File > New > Project from Version Control

2. In the Version control choose Git from the drop-down menu.

3. Paste `https://github.com/omarel3ashry/MoviesApp-v2.git` in the URL and choose your Directory. Click on the Clone button and you are done.

4. Build the project in Android Studio.

5. Run the app on your Android device or emulator.

## API Access Token

To use this app, you'll need to provide your own API Access Token for The Movie Database (TMDB) API. Follow these steps:

1. Visit the [TMDB website](https://www.themoviedb.org/) and sign up for an account.

2. Once logged in, navigate to your account settings and create an API Access Token for your app.

3. Open the `common/Secrets.kt` file in the app directory of the project.

4. Add your API Access Token to the file in the following format:

```
const val TMDB_TOKEN = "YOUR_TOKEN"
```

## Todo

- [x] Add cast and production companies to movie details.
- [ ] Search movies by keywords.
- [ ] Add movie trailers.
- [ ] Handle similar movies onClick to repopulate the same fragment without backstack issues.
- [ ] Implement list pagging.
    - [ ] MoviesListFragment
    - [ ] SearchFragment

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License. Feel free to modify and distribute the code as long as you include the original license file.

## Acknowledgments
* App design inspired by this [project](https://www.behance.net/gallery/159271337/UIUX-Movie-Application)
