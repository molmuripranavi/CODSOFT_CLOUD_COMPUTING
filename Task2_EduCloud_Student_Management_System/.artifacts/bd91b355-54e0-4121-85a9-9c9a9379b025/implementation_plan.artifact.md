# Implementation Plan - Fix TeacherUpdateRecordActivity Crash

The `TeacherUpdateRecordActivity` is likely crashing on startup because it attempts to access a `MaterialToolbar` with ID `toolbar` which is missing from its layout file (`activity_teacher_update_record.xml`).

## Proposed Changes

### [Component] Layouts

#### [MODIFY] [activity_teacher_update_record.xml](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/res/layout/activity_teacher_update_record.xml)
- Add a `MaterialToolbar` with ID `toolbar` at the top of the root `LinearLayout`.
- Set the background to `#1565C0` and title text color to white to match the app's theme.

## Verification Plan

### Automated Tests
- Run `:app:compileDebugKotlin` to ensure no compilation errors.

### Manual Verification
- Open the Teacher Dashboard.
- Navigate to **Manage Students**.
- Click on any student in the list.
- Verify that `TeacherUpdateRecordActivity` opens successfully and displays the toolbar and student information.
