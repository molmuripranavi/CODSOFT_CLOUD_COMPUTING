# Walkthrough - Fix Compilation Error (cannot find symbol class shape)

I have resolved the compilation error caused by the misplaced `bg_splash.xml` file.

## Changes

### [app]

#### [DELETE] [bg_splash.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/bg_splash.xml)
Removed the duplicate drawable file from the `res/layout` directory. This stops ViewBinding from attempting to generate a binding class for a non-layout XML file, which was causing the `import android.widget.shape;` error.

## Verification Results

### Automated Tests
- Executed `./gradlew :app:assembleDebug`.
- **Result**: `Build finished successfully.`

### Manual Verification
- The correctly placed drawable at `app/src/main/res/drawable/bg_splash.xml` remains in the project and is correctly used as a background in `activity_splash.xml`.
