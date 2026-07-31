# Implementation Plan - Fix build and compilation errors

Fix a compilation error due to a typo and resolve Kotlin version incompatibility and Gradle sync issues.

## User Review Required

> [!IMPORTANT]
> The build is failing because the Kotlin version (2.0.21) is incompatible with the metadata of some dependencies (like Firebase Auth), which expect a newer Kotlin compiler (2.1+ or newer). I will upgrade Kotlin to the latest stable version (2.4.10).
> I will also ensure the Kotlin plugin is properly declared in the top-level build file to resolve issues with Kotlin-specific configuration blocks.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/VISHNUVARDHAN%20S/SmartBusCloud/gradle/libs.versions.toml)
- Upgrade `kotlin` version from `2.0.21` to `2.4.10`.
- (Optional but recommended) Upgrade `agp` to `9.3.1` to match the latest stable release.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/VISHNUVARDHAN%20S/SmartBusCloud/build.gradle.kts)
- Add `alias(libs.plugins.kotlin.android) apply false` to the `plugins` block.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/VISHNUVARDHAN%20S/SmartBusCloud/app/build.gradle.kts)
- Ensure the `kotlinOptions` block is valid, or migrate it to the newer `kotlin` extension if issues persist.
- I will also fix a minor warning about a missing comma in `proguardFiles`.

### Activities

#### [MODIFY] [BookingConfirmationActivity.kt](file:///C:/Users/VISHNUVARDHAN%20S/SmartBusCloud/app/src/main/java/com/molmuripranavi/smartbuscloud/activities/passenger/BookingConfirmationActivity.kt)
- Fix typo: change `MyBookingActivity::class.java` to `MyBookingsActivity::class.java` (Note: User has already started this, I will ensure it's complete).

## Verification Plan

### Automated Tests
- Run `gradle_sync` to ensure the project structure is healthy.
- Run `gradle_build :app:assembleDebug` to verify the compilation and version compatibility fixes.
- Use `analyze_file` on `app/build.gradle.kts` to check if the `kotlinOptions` resolution issue is resolved.
