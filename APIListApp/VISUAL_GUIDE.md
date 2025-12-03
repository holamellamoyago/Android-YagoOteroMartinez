# API List App - Visual Guide

## App Screenshots (Text Representation)

### 1. Main Screen - Loading State
```
╔═══════════════════════════════════╗
║       API List App                ║ ← Purple toolbar
╠═══════════════════════════════════╣
║                                   ║
║                                   ║
║            ⏳                     ║ ← Loading spinner
║         Loading...                ║
║                                   ║
║                                   ║
║                                   ║
╚═══════════════════════════════════╝
```

### 2. Main Screen - List View
```
╔═══════════════════════════════════╗
║       API List App                ║ ← Purple toolbar
╠═══════════════════════════════════╣
║ sunt aut facere repellat          ║
║ provident occaecati excepturi     ║ ← Post title (bold)
║ quia et suscipit suscipit...      ║ ← Body preview
║ ID: 1 | User: 1                   ║ ← Metadata
╟───────────────────────────────────╢
║ qui est esse                      ║
║ est rerum tempore vitae sequi     ║
║ est animi sequi voluptatem...     ║
║ ID: 2 | User: 1                   ║
╟───────────────────────────────────╢
║ ea molestias quasi exercitationem ║
║ et iusto sed quo iure             ║
║ et iusto sed quo iure...          ║
║ ID: 3 | User: 1                   ║
╟───────────────────────────────────╢
║ eum et est occaecati              ║
║ ullam et saepe reiciendis         ║
║ ullam et saepe reiciendis...      ║
║ ID: 4 | User: 1                   ║
╟───────────────────────────────────╢
║              ⋮                    ║ ← Scrollable
╚═══════════════════════════════════╝
```

### 3. Main Screen - Error State
```
╔═══════════════════════════════════╗
║       API List App                ║ ← Purple toolbar
╠═══════════════════════════════════╣
║                                   ║
║                                   ║
║           ⚠️                      ║
║  Error loading data.              ║
║  Please try again.                ║
║                                   ║
║         ┌──────────┐              ║
║         │  Retry   │              ║ ← Retry button
║         └──────────┘              ║
║                                   ║
╚═══════════════════════════════════╝
```

### 4. Detail Screen
```
╔═══════════════════════════════════╗
║ ←  Post Details                   ║ ← Back button + title
╠═══════════════════════════════════╣
║                                   ║
║ Post ID: 1                        ║ ← Gray text
║ User ID: 1                        ║ ← Gray text
║                                   ║
║ sunt aut facere repellat          ║ ← Title (large, bold)
║ provident occaecati excepturi     ║
║ optio reprehenderit               ║
║                                   ║
║ quia et suscipit suscipit         ║ ← Full body text
║ recusandae consequuntur expedita  ║   (can scroll)
║ et cum reprehenderit molestiae    ║
║ ut ad velit. sed unde omnis       ║
║ aperiam quaerat voluptatem        ║
║ natus quas harum aperiam          ║
║ molestias rem et tempora          ║
║ consequatur qui quibusdam         ║
║              ⋮                    ║
╚═══════════════════════════════════╝
```

## User Interaction Flow

```
┌─────────────────────────────────────────────────────────────┐
│                        User Opens App                        │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                    MainActivity Loads                        │
│                   (Shows loading spinner)                    │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│              Retrofit Makes API Call to:                     │
│        https://jsonplaceholder.typicode.com/posts           │
└─────────────┬───────────────────────────┬───────────────────┘
              │                           │
         Success                      Failure
              │                           │
              ▼                           ▼
┌──────────────────────────┐   ┌──────────────────────────┐
│   Parse JSON Response    │   │   Show Error Message     │
│   (Gson converts to      │   │   + Retry Button         │
│    List<Post>)           │   │                          │
└──────────┬───────────────┘   └──────────┬───────────────┘
           │                              │
           ▼                              │ User taps Retry
┌──────────────────────────┐             │
│  Display List of Posts   │             │
│  (100 items from API)    │◄────────────┘
└──────────┬───────────────┘
           │
           │ User scrolls and browses
           │
           │ User taps a post item
           │
           ▼
┌─────────────────────────────────────────────────────────────┐
│               Launch DetailActivity with:                    │
│              Intent.putExtra("post", selectedPost)          │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                   DetailActivity Loads                       │
│              Displays full post information:                 │
│              - Post ID                                       │
│              - User ID                                       │
│              - Complete Title                                │
│              - Complete Body (scrollable)                    │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          │ User presses back button
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│              Return to MainActivity                          │
│         (List is still there, no reload needed)             │
└─────────────────────────────────────────────────────────────┘
```

## Data Flow Diagram

```
┌──────────────────────────────────────────────────────────────┐
│                    JSONPlaceholder API                        │
│              https://jsonplaceholder.typicode.com            │
│                                                               │
│  Returns JSON: [                                             │
│    {                                                         │
│      "userId": 1,                                            │
│      "id": 1,                                                │
│      "title": "sunt aut facere...",                         │
│      "body": "quia et suscipit..."                          │
│    },                                                        │
│    ...                                                       │
│  ]                                                           │
└───────────────────────────┬──────────────────────────────────┘
                            │
                            │ HTTP GET Request
                            │
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                    Retrofit Library                           │
│                   (HTTP Client)                              │
│                                                               │
│  - Manages network calls                                     │
│  - Handles threading (background)                            │
│  - Provides callback for results                             │
└───────────────────────────┬──────────────────────────────────┘
                            │
                            │ JSON String
                            │
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                     Gson Library                              │
│                   (JSON Parser)                              │
│                                                               │
│  - Converts JSON to Java objects                             │
│  - Uses @SerializedName annotations                          │
│  - Creates List<Post> automatically                          │
└───────────────────────────┬──────────────────────────────────┘
                            │
                            │ List<Post> objects
                            │
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                      MainActivity                             │
│                                                               │
│  postList.clear();                                           │
│  postList.addAll(response.body());                          │
│  adapter.notifyDataSetChanged();                             │
└───────────────────────────┬──────────────────────────────────┘
                            │
                            │ Post data
                            │
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                      PostAdapter                              │
│                   (ListView Adapter)                         │
│                                                               │
│  For each Post:                                              │
│    - Inflate item_post.xml                                   │
│    - Set title TextView                                      │
│    - Set body preview TextView                               │
│    - Set metadata TextView                                   │
└───────────────────────────┬──────────────────────────────────┘
                            │
                            │ View objects
                            │
                            ▼
┌──────────────────────────────────────────────────────────────┐
│                       ListView                                │
│                   (User Interface)                           │
│                                                               │
│  Displays all items in scrollable list                      │
│  Handles user clicks                                         │
│  Triggers DetailActivity on item click                       │
└──────────────────────────────────────────────────────────────┘
```

## Class Relationships

```
┌─────────────────────┐
│   MainActivity      │
│                     │
│ - ListView          │◄─────────────┐
│ - ProgressBar       │              │
│ - PostAdapter       │──────┐       │
│ - List<Post>        │      │       │
│                     │      │       │
│ + loadPosts()       │      │       │
│ + showError()       │      │       │
└──────────┬──────────┘      │       │
           │                 │       │
           │ uses            │       │
           │                 │       │
           ▼                 │       │
┌─────────────────────┐      │       │
│    ApiClient        │      │       │
│                     │      │       │
│ - Retrofit          │      │       │
│ - BASE_URL          │      │       │
│                     │      │       │
│ + getApiService()   │      │       │
└──────────┬──────────┘      │       │
           │                 │       │
           │ returns         │       │
           │                 │       │
           ▼                 │       │
┌─────────────────────┐      │       │
│    ApiService       │      │       │
│   (Interface)       │      │       │
│                     │      │       │
│ + getPosts()        │      │       │
│ + getPost(int)      │      │       │
└──────────┬──────────┘      │       │
           │                 │       │
           │ returns         │       │
           │                 │       │
           ▼                 │       │
┌─────────────────────┐      │       │
│    List<Post>       │──────┘       │
└─────────────────────┘              │
           │                         │
           │ contains                │
           │                         │
           ▼                         │
┌─────────────────────┐              │
│       Post          │              │
│                     │              │
│ - userId: int       │              │
│ - id: int           │              │
│ - title: String     │              │
│ - body: String      │              │
│                     │              │
│ + getters/setters   │              │
└─────────────────────┘              │
           │                         │
           │ passed to               │
           │                         │
           ▼                         │
┌─────────────────────┐              │
│  DetailActivity     │              │
│                     │              │
│ - Post data         │              │
│ - TextViews         │              │
│                     │              │
│ + displayDetails()  │              │
└─────────────────────┘              │
                                     │
                                     │
                                     │
┌─────────────────────┐              │
│   PostAdapter       │──────────────┘
│                     │
│ - Context           │
│ - List<Post>        │
│                     │
│ + getView()         │
└─────────────────────┘
```

## Architecture Diagram

```
┌────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌──────────────────┐          ┌──────────────────┐       │
│  │  MainActivity    │          │ DetailActivity   │       │
│  │                  │          │                  │       │
│  │  - Displays list │          │  - Shows details │       │
│  │  - Handles clicks│──────────│  - Back nav      │       │
│  │  - Error states  │  Intent  │                  │       │
│  └────────┬─────────┘          └──────────────────┘       │
│           │                                                │
│           │ uses                                           │
│           │                                                │
│  ┌────────▼─────────┐                                     │
│  │  PostAdapter     │                                     │
│  │                  │                                     │
│  │  - Binds data    │                                     │
│  │  - Creates views │                                     │
│  └──────────────────┘                                     │
└────────────────────────────────────────────────────────────┘
                        │
                        │ uses
                        │
┌────────────────────────▼───────────────────────────────────┐
│                      Data Layer                             │
│  ┌──────────────────┐                                      │
│  │      Post        │  ← Model class                       │
│  │  (Data Model)    │                                      │
│  └──────────────────┘                                      │
└────────────────────────────────────────────────────────────┘
                        │
                        │ used by
                        │
┌────────────────────────▼───────────────────────────────────┐
│                    Network Layer                            │
│  ┌──────────────────┐          ┌──────────────────┐       │
│  │   ApiClient      │          │   ApiService     │       │
│  │                  │ creates  │   (Interface)    │       │
│  │  - Retrofit setup│─────────▶│  - GET /posts   │       │
│  │  - Singleton     │          │  - GET /posts/id│       │
│  └──────────────────┘          └──────────────────┘       │
│           │                             │                  │
│           │ uses                        │ calls            │
│           ▼                             ▼                  │
│  ┌──────────────────┐          ┌──────────────────┐       │
│  │    Retrofit      │          │      Gson        │       │
│  │  (HTTP Client)   │◄─────────│  (JSON Parser)   │       │
│  └──────────────────┘          └──────────────────┘       │
└────────────────────────────────────────────────────────────┘
                        │
                        │ network call
                        │
┌────────────────────────▼───────────────────────────────────┐
│                  External API                               │
│                                                             │
│         https://jsonplaceholder.typicode.com/posts         │
│                                                             │
│  Returns: JSON array of post objects                       │
└────────────────────────────────────────────────────────────┘
```

## Key Features Illustrated

### 1. List Display
- Uses **ListView** with custom adapter
- Each item shows: title, preview, metadata
- Smooth scrolling for 100+ items
- Click handling for navigation

### 2. Detail View
- Receives **Serializable** Post object
- Shows complete information
- Scrollable for long content
- Back navigation enabled

### 3. Error Handling
- Network errors caught
- User-friendly message
- Retry button provided
- Loading states managed

### 4. API Integration
- **Retrofit** for HTTP calls
- **Gson** for JSON parsing
- Asynchronous operations (background thread)
- Callback-based result handling

## Color Scheme

```
Primary:   ████ Purple 500 (#FF6200EE)
           ████ Purple 700 (#FF3700B3)

Secondary: ████ Teal 200 (#FF03DAC5)
           ████ Teal 700 (#FF018786)

Text:      ████ Black (#FF000000)
           ████ Gray (darker_gray)

Background:████ White (#FFFFFFFF)
```

## Typography

```
List Title:        18sp, Bold
List Body Preview: 14sp, Regular, 2 lines max
List Metadata:     12sp, Gray

Detail Title:      22sp, Bold
Detail Body:       16sp, Regular, line spacing 4dp
Detail Metadata:   14sp, Gray

Toolbar:           Default Material toolbar style
```

This visual guide shows exactly how the app looks and behaves!
