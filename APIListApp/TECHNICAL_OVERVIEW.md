# API List App - Technical Overview

## Project Structure

```
APIListApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/apilistapp/
│   │       │   ├── MainActivity.java          # Main list screen
│   │       │   ├── DetailActivity.java        # Detail view screen
│   │       │   ├── Post.java                  # Data model
│   │       │   ├── ApiService.java            # Retrofit API interface
│   │       │   ├── ApiClient.java             # Retrofit client singleton
│   │       │   └── PostAdapter.java           # ListView adapter
│   │       │
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml      # Main screen layout
│   │       │   │   ├── activity_detail.xml    # Detail screen layout
│   │       │   │   └── item_post.xml          # List item layout
│   │       │   ├── values/
│   │       │   │   ├── strings.xml            # String resources
│   │       │   │   ├── colors.xml             # Color palette
│   │       │   │   └── themes.xml             # App theme
│   │       │   ├── mipmap-*/                  # App launcher icons
│   │       │   └── xml/
│   │       │       ├── backup_rules.xml
│   │       │       └── data_extraction_rules.xml
│   │       │
│   │       └── AndroidManifest.xml            # App configuration
│   │
│   ├── build.gradle.kts                       # App module build config
│   └── proguard-rules.pro                     # ProGuard rules
│
├── gradle/
│   ├── libs.versions.toml                     # Version catalog
│   └── wrapper/                               # Gradle wrapper
│
├── build.gradle.kts                           # Root build config
├── settings.gradle.kts                        # Project settings
├── gradle.properties                          # Gradle properties
└── README.md                                  # Documentation

```

## Application Flow

```
[App Launch]
     ↓
[MainActivity]
     ↓
[onCreate() called]
     ↓
[Initialize Views & Adapter]
     ↓
[loadPosts() called]
     ↓
[Show ProgressBar]
     ↓
[API Call via Retrofit]
     ↓
     ├──→ [Success] ──→ [Parse JSON with Gson]
     │                        ↓
     │                  [Update Adapter]
     │                        ↓
     │                  [Show ListView]
     │                        ↓
     │                  [User Taps Item]
     │                        ↓
     │                  [Launch DetailActivity]
     │                        ↓
     │                  [Show Post Details]
     │
     └──→ [Failure] ──→ [Show Error Message]
                              ↓
                        [Show Retry Button]
                              ↓
                        [User Taps Retry]
                              ↓
                        [Call loadPosts() again]
```

## Data Flow

```
JSONPlaceholder API
         ↓
    HTTP GET /posts
         ↓
   [Retrofit Client]
         ↓
    [Gson Converter]
         ↓
   [List<Post> objects]
         ↓
    [PostAdapter]
         ↓
     [ListView]
         ↓
   [User Interface]
```

## Components Overview

### 1. MainActivity
- **Purpose**: Display list of posts
- **Key Methods**:
  - `onCreate()`: Initialize UI components
  - `loadPosts()`: Fetch data from API
  - `showError()`: Display error state
- **UI Elements**:
  - MaterialToolbar (app title)
  - ListView (displays posts)
  - ProgressBar (loading indicator)
  - TextView (error message)
  - Button (retry action)

### 2. DetailActivity
- **Purpose**: Show full post details
- **Key Methods**:
  - `onCreate()`: Initialize and receive post data
  - `displayPostDetails()`: Populate UI with post data
  - `onSupportNavigateUp()`: Handle back navigation
- **UI Elements**:
  - MaterialToolbar (with back button)
  - ScrollView (for scrollable content)
  - TextViews (display post information)

### 3. Post (Model)
- **Fields**:
  - `userId` (int): ID of the user who created the post
  - `id` (int): Unique post identifier
  - `title` (String): Post title
  - `body` (String): Post content
- **Annotations**: Uses Gson's `@SerializedName` for JSON parsing
- **Implements**: Serializable (for passing between activities)

### 4. ApiService (Interface)
- **Methods**:
  - `getPosts()`: Returns `Call<List<Post>>` for fetching all posts
  - `getPost(int id)`: Returns `Call<Post>` for fetching single post
- **Annotations**: Retrofit's `@GET` and `@Path`

### 5. ApiClient (Singleton)
- **Purpose**: Provide single Retrofit instance
- **Base URL**: https://jsonplaceholder.typicode.com/
- **Converter**: GsonConverterFactory for JSON parsing
- **Method**: `getApiService()` returns ApiService instance

### 6. PostAdapter (ArrayAdapter)
- **Purpose**: Convert Post objects to list item views
- **Key Method**: `getView()` inflates and populates item_post.xml
- **Displays**: Title, body preview (2 lines max), post ID, user ID

## Network Communication

### Retrofit Configuration
```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();
```

### API Call Pattern
```java
ApiService service = ApiClient.getApiService();
Call<List<Post>> call = service.getPosts();

call.enqueue(new Callback<List<Post>>() {
    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        // Handle success
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        // Handle failure
    }
});
```

## Permissions

### AndroidManifest.xml
- `INTERNET`: Required for network access
- `ACCESS_NETWORK_STATE`: Optional, for checking network connectivity
- `usesCleartextTraffic="true"`: Allows HTTP (not just HTTPS)

## UI Design

### Material Design Elements
- MaterialToolbar for consistent header
- Material color scheme (purple primary, teal secondary)
- Elevation and shadows for depth
- Consistent padding and margins (16dp)

### Typography
- **List Item Title**: 18sp, bold, black
- **List Item Body**: 14sp, regular, black
- **List Item Metadata**: 12sp, gray
- **Detail Title**: 22sp, bold, black
- **Detail Body**: 16sp, regular, black, with line spacing

### Colors
- Primary: Purple 500 (#FF6200EE)
- Primary Variant: Purple 700 (#FF3700B3)
- Secondary: Teal 200 (#FF03DAC5)
- Background: White
- Text: Black

## Error Handling Strategy

1. **Network Errors**: Show error message with retry button
2. **Empty Response**: Show "No posts available" (if implemented)
3. **JSON Parsing Errors**: Caught by Retrofit/Gson
4. **Null Safety**: Check response.body() != null
5. **Loading States**: ProgressBar visibility management

## Testing the App

### Manual Testing Checklist
1. ✓ App launches successfully
2. ✓ Loading indicator appears
3. ✓ Posts load from API
4. ✓ List displays correctly
5. ✓ Tapping item opens detail view
6. ✓ Detail view shows all information
7. ✓ Back button returns to list
8. ✓ Network error shows error message
9. ✓ Retry button reloads data

### Test Scenarios
- **Happy Path**: Normal app flow with working internet
- **Network Error**: Airplane mode or no connectivity
- **Slow Network**: Test loading states
- **Rotation**: Test if state is preserved (may need improvement)

## Build Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 8 or higher
- Android SDK 34
- Internet connection for initial Gradle sync

### Build Commands
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug

# Run all checks
./gradlew check
```

### Expected Output
- APK file: `app/build/outputs/apk/debug/app-debug.apk`
- Size: Approximately 3-5 MB

## Dependencies Breakdown

| Library | Version | Purpose |
|---------|---------|---------|
| androidx.appcompat | 1.7.0 | Backward compatibility |
| material | 1.12.0 | Material Design components |
| constraintlayout | 2.1.4 | Flexible layouts |
| retrofit | 2.9.0 | HTTP client |
| converter-gson | 2.9.0 | JSON converter for Retrofit |
| gson | 2.10.1 | JSON parsing |

## Known Limitations

1. **No Caching**: Data is fetched on every app launch
2. **No Offline Mode**: Requires internet connection
3. **No Pagination**: Loads all 100 posts at once
4. **Basic Error Handling**: Generic error messages
5. **No Search/Filter**: No way to find specific posts
6. **ListView**: Using older ListView instead of RecyclerView
7. **No Architecture Pattern**: Direct API calls in Activity (no MVVM/MVP)

## Possible Improvements

1. **RecyclerView**: Better performance and flexibility
2. **Room Database**: Offline caching and faster loads
3. **ViewModel & LiveData**: Survive configuration changes
4. **ViewBinding**: Type-safe view access
5. **Pagination**: Load posts in chunks
6. **Search**: Filter posts by title or content
7. **Dependency Injection**: Hilt or Dagger
8. **Unit Tests**: Test business logic
9. **UI Tests**: Espresso tests
10. **Pull-to-Refresh**: SwipeRefreshLayout
11. **Images**: Add user avatars
12. **Comments**: Show post comments
