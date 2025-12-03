# API List App

A complete Android application that demonstrates API integration, list display, and detail view navigation.

## Features

- **API Integration**: Connects to JSONPlaceholder API (https://jsonplaceholder.typicode.com/) to fetch posts
- **List View**: Displays a list of posts using Android's ListView component
- **Detail View**: Shows complete post information when a list item is tapped
- **Material Design**: Uses Material Design components for a modern UI
- **Error Handling**: Includes loading states, error messages, and retry functionality
- **Internet Permission**: Configured to access network resources

## Architecture

### Data Layer
- **Post.java**: Data model representing a post with fields: userId, id, title, body
- **ApiService.java**: Retrofit interface defining API endpoints
- **ApiClient.java**: Singleton class managing Retrofit instance and API service

### Presentation Layer
- **MainActivity.java**: Main screen displaying the list of posts
- **DetailActivity.java**: Detail screen showing full post information
- **PostAdapter.java**: Custom adapter for ListView to display post items

### Resources
- **activity_main.xml**: Layout for the main list screen with ListView, ProgressBar, and error state
- **activity_detail.xml**: Layout for the detail screen showing complete post information
- **item_post.xml**: Custom layout for individual list items
- **strings.xml**: Centralized string resources
- **themes.xml**: Material Design theme configuration
- **colors.xml**: Color palette

## API Information

This app uses the **JSONPlaceholder API**, a free fake REST API for testing and prototyping.

**Base URL**: https://jsonplaceholder.typicode.com/

**Endpoints Used**:
- `GET /posts` - Retrieves a list of all posts (100 items)
- `GET /posts/{id}` - Retrieves a specific post by ID

**Sample Post Object**:
```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit..."
}
```

## Dependencies

- **AndroidX AppCompat**: 1.7.0 - Backward compatibility support
- **Material Components**: 1.12.0 - Material Design UI components
- **ConstraintLayout**: 2.1.4 - Flexible layout manager
- **Retrofit**: 2.9.0 - Type-safe HTTP client for Android
- **Gson Converter**: 2.9.0 - JSON serialization/deserialization
- **Gson**: 2.10.1 - JSON parsing library

## Build Configuration

- **Compile SDK**: 34 (Android 14)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34
- **Java Version**: 1.8
- **Gradle Plugin**: 8.6.1

## How to Build

1. Open the project in Android Studio
2. Sync Gradle dependencies
3. Run on an emulator or physical device

```bash
./gradlew assembleDebug
```

## How It Works

1. **App Launch**: MainActivity is launched and immediately calls `loadPosts()`
2. **Loading State**: ProgressBar is shown while fetching data
3. **API Call**: Retrofit makes a GET request to `/posts` endpoint
4. **Success**: Posts are displayed in a ListView using the custom PostAdapter
5. **Item Click**: User taps a post, launching DetailActivity with the selected post data
6. **Detail View**: DetailActivity displays all post information (ID, User ID, Title, Body)
7. **Back Navigation**: User can return to the list using the back button or toolbar

## Error Handling

- Network errors show an error message with a retry button
- Loading states prevent user interaction during API calls
- Empty states are handled gracefully

## User Interface

### Main Screen
- **Toolbar**: Shows app name
- **ListView**: Displays all posts with title, body preview, and metadata
- **Loading Indicator**: Spinner shown during data fetch
- **Error State**: Message with retry button on failure

### Detail Screen
- **Toolbar**: Shows "Post Details" with back button
- **ScrollView**: Allows scrolling for long content
- **Post Information**: Post ID, User ID, Title (bold, large), and full Body text

## Future Enhancements

Potential improvements for this app:
- Pull-to-refresh functionality
- Pagination for large datasets
- Search and filter options
- Caching with Room database
- Dark theme support
- RecyclerView instead of ListView for better performance
- View binding or data binding
- MVVM architecture with ViewModel and LiveData
- Dependency injection with Dagger/Hilt

## License

This is a sample project for educational purposes.

## Credits

- API: [JSONPlaceholder](https://jsonplaceholder.typicode.com/) by [typicode](https://github.com/typicode)
- Icon: Default Android launcher icon
