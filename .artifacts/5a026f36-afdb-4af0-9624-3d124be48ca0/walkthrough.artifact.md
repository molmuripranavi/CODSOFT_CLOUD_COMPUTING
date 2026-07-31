# Walkthrough - Fix Unresolved Reference 'etFrom'

I have fixed the build error `Unresolved reference 'etFrom'` by implementing the missing UI layout in `activity_search_bus.xml`.

## Changes Made

### app component

#### [MODIFY] [activity_search_bus.xml](file:///C:/Users/VISHNUVARDHAN S/SmartBusCloud/app/src/main/res/layout/activity_search_bus.xml)
- Implemented a complete UI layout for the Search Bus screen.
- Added the following views required by `SearchBusActivity.kt`:
    - `etFrom` (AutoCompleteTextView)
    - `etTo` (AutoCompleteTextView)
    - `etDate` (TextInputEditText)
    - `etPassengers` (AutoCompleteTextView)
    - `btnSearch` (MaterialButton)
- Used `MaterialCardView` and `TextInputLayout` for a modern, consistent design.

## Verification Results

### Automated Tests
- Ran `./gradlew :app:compileDebugKotlin` and confirmed that the build is now successful.

```text
> Task :app:compileDebugKotlin
BUILD SUCCESSFUL in 5s
```
