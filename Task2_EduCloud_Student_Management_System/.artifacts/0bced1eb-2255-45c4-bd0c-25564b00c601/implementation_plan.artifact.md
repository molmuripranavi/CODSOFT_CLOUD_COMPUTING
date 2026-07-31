# Fix Crashes and Restore Toolbars

The app is crashing because the `Toolbar` was missing from the `activity_profile.xml` and `activity_courses.xml` layouts, while the Kotlin code was still trying to initialize them. I will also resolve the "black bar" issue once and for all.

## Proposed Changes

### 1. Fix Crashes (Restore Missing Views)

#### [MODIFY] [activity_profile.xml](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/res/layout/activity_profile.xml)
- Re-add the `MaterialToolbar` inside the `CollapsingToolbarLayout` so `ProfileActivity.kt` doesn't crash when finding it.
- Fix the layout structure to support a professional collapsing header.

#### [MODIFY] [activity_courses.xml](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/res/layout/activity_courses.xml)
- Re-add the `MaterialToolbar` at the top of the layout.

### 2. Final "Black Bar" Fix
The black bar is caused by `fitsSystemWindows="true"` being applied to a root layout that doesn't have a background drawing into the system area.

- **Standardized Approach**:
    - Set the `statusBarColor` to `#1565C0` (EduCloud Blue) as a theme default.
    - Set `fitsSystemWindows="false"` on the root layouts and instead use it on the components that actually need it (like the `AppBarLayout`).
    - Remove `enableEdgeToEdge()` from the activities if it's causing conflicts, or implement it with proper inset listeners.

## Verification Plan

### Manual Verification
1.  **Crash Check**: Open Profile and Courses; ensure they no longer crash.
2.  **UI Audit**: Confirm that the blue header is consistent and reaches the top of the screen without a black bar.
