# github-usercontent-android

Android app test to fetch users from [gist.githubusercontent.com](https://gist.githubusercontent.com/dsandin/c8ed6c5a9486f311f4725f221b912220/raw/8c6d2d8e1f339d02e0ff3d2990560a4862c4beae/users_page_list)

Using Jetpack Compose for faster development of views

Using MVVM with repository pattern to get data from API or DB

# What is done
- Main architecture for the app based on MVVM using tools like hilt, retrofit, Jetpack Compose and Room.
- Main Screen (get list pages and the first users).
- Dependenci Injection for viewmodel, repositories and data sources.
- Offline fetching (is getting all values from the DB)

# What is missing
- Splash Screen
- Sorting on Main Screen
- Implement the ability to filter out users that have `null` *`avatar_large`* on the Home screen.
- LoadMore and pagination on Main Screen 
- Detail Screen
- option to change view to Grid 