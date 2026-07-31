# Implementation Plan - Robust Certificate Upload

The goal is to fix the "Object does not exist" error during certificate upload by using the recommended Firebase task chaining pattern and adding better diagnostic information.

## User Action Required

> [!CAUTION]
> This error often occurs due to **Firebase Storage Rules**. Please verify your rules in the Firebase Console:
> 1. Go to **Storage** -> **Rules**.
> 2. Ensure they allow **read** access for authenticated users. Example:
> ```
> service firebase.storage {
>   match /b/{bucket}/o {
>     match /{allPaths=**} {
>       allow read, write: if request.auth != null;
>     }
>   }
> }
> ```

## Proposed Changes

### [Activities]

#### [MODIFY] [ApplyLeaveActivity.kt](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/java/com/molmuripranavi/educloud/activities/ApplyLeaveActivity.kt)
- **Task Chaining**: Use `continueWithTask` to chain `putFile` and `getDownloadUrl`. This ensures that errors in either step are caught in a single `onCompleteListener`.
- **File Extensions**: Add appropriate file extensions to the uploaded files based on their MIME type for better compatibility.
- **Improved Logging**: Log the storage path and URI to the console for easier debugging if the error persists.

## Verification Plan

### Automated Tests
- Run `gradle assembleDebug` to ensure compilation.

### Manual Verification
1. Open **Apply Leave** as a Student.
2. Upload a certificate.
3. If it fails, check the Toast message for a more specific error.
4. If it succeeds, verify the file exists in the Firebase Console under the `leave_documents/` folder.
