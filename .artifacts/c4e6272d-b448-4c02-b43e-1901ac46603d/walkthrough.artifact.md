# Walkthrough - Search Bus Navigation Fix

I have fixed the issue where the "Search Bus" button was not opening the corresponding activity. The primary cause was a cluttered `AndroidManifest.xml` with multiple conflicting activity declarations and incorrect package mappings.

## Changes Made

### 1. Manifest Cleanup
I completely restructured and cleaned up `AndroidManifest.xml`.
- Removed all duplicate activity entries.
- Grouped activities logically by their functional area (Authentication, Passenger, Admin).
- Corrected package paths for several activities (e.g., moved authentication-related activities to `.activities.authentication`).

### 2. Dashboard Activity Optimization
- Updated `PassengerDashboardActivity.kt` to use `MaterialCardView` instead of the legacy `CardView` to match the layout file (`activity_passenger_dashboard.xml`).
- Verified that the `Intent` correctly targets `SearchBusActivity::class.java`.

### 3. Build Verification
- Successfully ran `gradlew app:assembleDebug` to ensure no manifest conflicts or compilation errors exist.

## Verification Results

### Automated Tests
- **Build Status**: Success.
- **Manifest Validation**: All activities are now uniquely defined with correct package mappings.

### Manual Verification Path (Recommended)
1. Launch the app and login as a Passenger.
2. Tap the "Search Bus" card on the dashboard.
3. The app should now transition smoothly to the `SearchBusActivity`.

> [!TIP]
> If you still encounter issues, please check the Logcat for any runtime exceptions that might be specific to your device's environment.

> [!IMPORTANT]
> The duplicate entries in the manifest were likely causing the Android system to fail in resolving which activity component to start, leading to the silent failure reported.
