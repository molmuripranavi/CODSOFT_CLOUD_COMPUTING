# Implementation Plan - Reduce Dashboard Card Sizes

The user wants to make the dashboard cards in the Teacher Portal smaller. To maintain consistency, I will also apply similar changes to the HOD Portal as they share the same design pattern.

## Proposed Changes

### [Component] Layouts

#### [MODIFY] [activity_teacher_dashboard.xml](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/res/layout/activity_teacher_dashboard.xml)
- **Leaves Statistics Cards**: Reduce `android:layout_height` from `145dp` to `125dp`.
- **Quick Action Cards**: Reduce `android:layout_height` from `190dp` to `160dp`.
- **Action Card Icons**: Reduce `ImageView` dimensions from `70dp x 70dp` to `55dp x 55dp`.
- **Action Card Text**: Reduce `textSize` from `22sp` to `19sp` to fit the smaller cards.

#### [MODIFY] [activity_hod_dashboard.xml](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/res/layout/activity_hod_dashboard.xml)
- Apply identical size reductions to maintain consistency between HOD and Teacher portals:
    - Statistics Cards: `145dp` -> `125dp`.
    - Admin Action Cards: `190dp` -> `160dp`.
    - Icons: `70dp` -> `55dp`.
    - Text: `22sp` -> `19sp`.

## Verification Plan

### Automated Tests
- Run `:app:assembleDebug` to ensure layout changes don't cause build issues (unlikely for XML changes).

### Manual Verification
- Deploy the app and navigate to both the **Teacher Dashboard** and **HOD Dashboard**.
- Verify that the cards are smaller and that the icons and text are properly scaled and aligned.
- Ensure the overall layout remains balanced and legible on different screen sizes.
