# Walkthrough - Enhanced Firebase Storage Setup

I have further strengthened the Firebase Storage implementation to address the "Object does not exist" error and added critical logging for debugging.

## Key Changes

### [ApplyLeaveActivity.kt](file:///E:/CODSOFT_CLOUD_COMPUTING/Task2_EduCloud_Student_Management_System/app/src/main/java/com/molmuripranavi/educloud/activities/ApplyLeaveActivity.kt)
- **Explicit Bucket Initialization**: I am now initializing `FirebaseStorage` using the specific bucket URL from your config (`gs://educloud-9e013.firebasestorage.app`). This ensures that the app is connecting to the correct storage container.
- **Improved Sequence**: Switched to a nested Success Listener pattern. This ensures that we only attempt to fetch the `downloadUrl` once the `putFile` operation has definitively reported success.
- **Debug Logging**: Added detailed logs tagged with "EduCloud". You can now see:
    - The local file URI being used.
    - The destination path in Firebase.
    - Detailed error messages if either the upload or URL retrieval fails.

## How to Debug if it fails again

If you still see an error, please check the **Logcat** window in Android Studio:
1.  Connect your device.
2.  In the Logcat search bar, type `EduCloud`.
3.  Look for any red (error) or green (debug) messages when you click upload.
4.  If it says "Download URL retrieved", then it worked!

> [!IMPORTANT]
> **Check your Firebase Storage Rules again.**
> If you can see the file in the Firebase Console but the app says "Object does not exist", your rules are likely blocking the app from reading the file's metadata.
>
> **Make sure your rules have `allow read`:**
> ```
> allow read, write: if request.auth != null;
> ```

## Verification Results

### Automated Tests
- Successfully ran `gradle assembleDebug`.
