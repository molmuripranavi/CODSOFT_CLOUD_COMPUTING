# Fix "cannot find symbol class shape" compilation error

The build error is caused by a drawable resource file (`bg_splash.xml`) incorrectly placed in the `res/layout/` directory. ViewBinding attempts to generate a binding class for every file in `res/layout/`. Since the root element of this file is `<shape>`, the generated Java code contains `import android.widget.shape;` and uses `shape` as a class name, neither of which are valid in Android.

## Proposed Changes

### [app]

#### [DELETE] [bg_splash.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/bg_splash.xml)

The file `bg_splash.xml` already exists in `res/drawable/`, which is its correct location. Deleting the duplicate in `res/layout/` will stop ViewBinding from attempting to generate the problematic `BgSplashBinding` class.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure the project compiles successfully.

### Manual Verification
- Verify that the splash screen background still appears correctly when running the app (since it's correctly referenced as `@drawable/bg_splash`).
