# Implementation Plan - Fix Build Errors in Splash Screen

The project is currently failing to build due to two main issues:
1.  **Unbound Namespace Prefix**: In `activity_splash.xml`, the `app` namespace was used for `CardView` attributes but not declared in the root element.
2.  **Wrong Resource Directory**: `bg_splash.xml` (a drawable shape) was incorrectly placed in the `res/layout` directory. This caused View Binding to attempt to generate a binding class for it, which failed because `<shape>` is not a valid View.

## Proposed Changes

### [Component Name] UI Resources

#### [MODIFY] [activity_splash.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/activity_splash.xml)
- Already partially fixed in memory, but ensure it has `xmlns:app="http://schemas.android.com/apk/res-auto"`.

#### [NEW] [bg_splash.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/drawable/bg_splash.xml)
- Create the background shape in the correct `res/drawable` directory.

#### [DELETE] [bg_splash.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/bg_splash.xml)
- Remove the misplaced file from the `res/layout` directory to stop View Binding from trying to process it.

## Verification Plan

### Automated Tests
- Run `:app:assembleDebug` to verify the build succeeds.

### Manual Verification
- Check the Splash screen in the Layout Preview.
