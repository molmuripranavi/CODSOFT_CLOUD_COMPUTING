# Fix Compilation Errors and Standardize Manage Passengers Screen

The user is experiencing a compilation error in `AdminDashboardActivity.kt` due to an incorrect variable reference. Additionally, the recently added "Manage Passengers" feature contains some configuration errors in `AndroidManifest.xml` and doesn't follow the established header design pattern.

## Proposed Changes

### [Admin Feature](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/java/com/molmuripranavi/smartbuscloud/activities/admin)

#### [MODIFY] [AdminDashboardActivity.kt](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/java/com/molmuripranavi/smartbuscloud/activities/admin/AdminDashboardActivity.kt)
- Fix the unresolved reference `cardManagePassengers` by renaming it to `cardPassengers` to match its definition.

#### [MODIFY] [ManagePassengersActivity.kt](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/java/com/molmuripranavi/smartbuscloud/activities/admin/ManagePassengersActivity.kt)
- Add back button (`btnBack`) initialization and functional navigation (`finish()`).

### [Layouts](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout)

#### [MODIFY] [activity_manage_passengers.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/activity_manage_passengers.xml)
- Update the header to the standardized blue compact design:
    - Add top padding (`32dp`) to clear the status bar.
    - Put back arrow and "Manage Passengers" title on the same line.
    - Add a descriptive subtitle.

### [System Configuration](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main)

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/AndroidManifest.xml)
- Remove incorrect `<activity>` entries for `.models.Passenger` and `.adapters.PassengerAdapter`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to ensure all compilation errors are resolved.

### Manual Verification
1. Open the Admin Dashboard.
2. Click on "Manage Passengers".
3. Verify that the screen opens correctly with the new compact blue header.
4. Verify that the back button works and returns to the dashboard.
5. Test the search functionality to ensure it correctly filters passengers by name or email.
