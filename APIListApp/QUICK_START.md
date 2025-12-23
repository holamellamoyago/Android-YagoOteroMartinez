# Quick Start Guide - API List App

## What This App Does

This Android application demonstrates a complete **List → Detail** navigation pattern:

1. **Fetches data** from a public REST API (JSONPlaceholder)
2. **Displays posts** in a scrollable list
3. **Shows details** when you tap any item
4. **Handles errors** with retry functionality

## Opening the Project

### Option 1: Android Studio (Recommended)

1. **Open Android Studio**
2. Click **"Open an Existing Project"** or **File → Open**
3. Navigate to: `Android-YagoOteroMartinez/APIListApp/`
4. Click **"OK"**
5. Wait for Gradle sync to complete (this downloads dependencies)
6. Click the **green play button** ▶️ to run

### Option 2: Command Line

```bash
cd APIListApp
./gradlew assembleDebug
./gradlew installDebug  # If you have a device connected
```

## System Requirements

- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **JDK**: Version 8 or higher
- **Android SDK**: API Level 34 installed
- **Internet**: Required for initial build and app functionality
- **Device/Emulator**: Android 7.0 (API 24) or higher

## First Run

When you run the app for the first time:

1. **Gradle will download dependencies** (~50MB)
   - Retrofit (HTTP client)
   - Gson (JSON parser)
   - Material Design components
   
2. **The app will compile** (~30 seconds on first run)

3. **App launches** on your device/emulator

4. **You'll see**:
   - Loading spinner
   - Then a list of 100 posts from JSONPlaceholder API
   - Tap any post to see full details

## What You'll See

### Main Screen
```
┌─────────────────────────┐
│   API List App         │  ← Toolbar
├─────────────────────────┤
│ Post Title 1            │
│ Body preview text...    │
│ ID: 1 | User: 1        │
├─────────────────────────┤
│ Post Title 2            │
│ Body preview text...    │
│ ID: 2 | User: 1        │
├─────────────────────────┤
│ Post Title 3            │
│ Body preview text...    │
│ ID: 3 | User: 1        │
└─────────────────────────┘
```

### Detail Screen
```
┌─────────────────────────┐
│ ← Post Details         │  ← Toolbar with back button
├─────────────────────────┤
│ Post ID: 1              │
│ User ID: 1              │
│                         │
│ Post Title Here         │  ← Large, bold
│                         │
│ Full post body text     │
│ goes here. Can be       │
│ multiple lines and      │
│ scrolls if needed.      │
│                         │
└─────────────────────────┘
```

## Troubleshooting

### Problem: Gradle sync fails
**Solution**: 
- Check internet connection
- File → Invalidate Caches / Restart
- Update Android Studio to latest version

### Problem: "SDK not found"
**Solution**:
- Open SDK Manager (Tools → SDK Manager)
- Install Android SDK Platform 34
- Install Android Build Tools 34.x.x

### Problem: App shows error message
**Solution**:
- Check device/emulator has internet connection
- Try the Retry button
- API might be temporarily down (rare)

### Problem: Build fails with "Java version" error
**Solution**:
- File → Project Structure → SDK Location
- Set JDK to version 8 or higher
- Or update `gradle.properties` to match your JDK

## Project Files Overview

```
APIListApp/
├── app/src/main/
│   ├── java/.../
│   │   ├── MainActivity.java      ← List screen
│   │   ├── DetailActivity.java    ← Detail screen
│   │   ├── Post.java              ← Data model
│   │   ├── ApiService.java        ← API interface
│   │   ├── ApiClient.java         ← HTTP client
│   │   └── PostAdapter.java       ← List adapter
│   │
│   ├── res/layout/
│   │   ├── activity_main.xml      ← List layout
│   │   ├── activity_detail.xml    ← Detail layout
│   │   └── item_post.xml          ← List item layout
│   │
│   └── AndroidManifest.xml        ← App config
│
├── build.gradle.kts               ← Build settings
└── README.md                      ← Full documentation
```

## Testing the App

### Manual Test Steps

1. ✅ **Launch app** → Should show loading spinner
2. ✅ **Wait for load** → Should display list of posts
3. ✅ **Scroll list** → Should smoothly scroll through all posts
4. ✅ **Tap any post** → Should open detail screen
5. ✅ **Read details** → Should show full title and body
6. ✅ **Press back** → Should return to list
7. ✅ **Turn off WiFi** → Should show error with retry button
8. ✅ **Tap retry** → Should reload data

### Expected Behavior

- **Loading time**: 1-3 seconds (depending on network)
- **Number of posts**: 100 items
- **Smooth scrolling**: Yes
- **Back navigation**: Works
- **Error handling**: Shows message and retry button

## API Information

**Service**: JSONPlaceholder  
**URL**: https://jsonplaceholder.typicode.com/posts  
**Method**: GET  
**Response**: JSON array of post objects  
**Free**: Yes, no authentication needed  
**Reliable**: Very high uptime  

**Sample Response**:
```json
[
  {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat...",
    "body": "quia et suscipit..."
  },
  ...
]
```

## Customizing the App

### Change API Endpoint
Edit `ApiClient.java`:
```java
private static final String BASE_URL = "https://your-api.com/";
```

### Change Colors
Edit `res/values/colors.xml`:
```xml
<color name="purple_500">#YourColorHere</color>
```

### Change App Name
Edit `res/values/strings.xml`:
```xml
<string name="app_name">Your App Name</string>
```

### Add More Fields
1. Add fields to `Post.java`
2. Update layouts to display new fields
3. Modify adapter to show new data

## Next Steps

After running the app successfully:

1. **Explore the code** - Start with `MainActivity.java`
2. **Modify layouts** - Change colors, sizes, fonts
3. **Add features** - Search, filters, favorites
4. **Try different APIs** - Pokemon API, GitHub API, etc.
5. **Improve architecture** - Add ViewModel, Repository pattern

## Need Help?

- **Documentation**: See `README.md` for full details
- **Technical**: See `TECHNICAL_OVERVIEW.md` for architecture
- **Android Docs**: https://developer.android.com/
- **Retrofit Docs**: https://square.github.io/retrofit/

## Summary

This is a **complete, working Android app** that:
- ✅ Connects to a REST API
- ✅ Displays data in a list
- ✅ Shows detail view on item click
- ✅ Handles errors gracefully
- ✅ Uses modern libraries (Retrofit, Gson, Material Design)
- ✅ Follows Android best practices
- ✅ Is fully documented and ready to run

**Just open in Android Studio and run!** 🚀
