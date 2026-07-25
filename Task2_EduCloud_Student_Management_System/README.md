# 📱 EduCloud – Cloud-Based Student Leave Management System

> **CodSoft Cloud Computing Internship – Task 2**

EduCloud is a cloud-based Android application developed using **Kotlin** and **Firebase** that digitizes the leave management process in educational institutions. The application enables students to submit leave requests, teachers to review them, and Heads of Department (HODs) to provide final approval. All leave records are securely stored and synchronized using Firebase Cloud Firestore, providing a seamless and paperless workflow.

---

![Android](https://img.shields.io/badge/Android-Kotlin-3DDC84?logo=android)
![Firebase](https://img.shields.io/badge/Firebase-Firestore-orange?logo=firebase)
![Firebase Auth](https://img.shields.io/badge/Firebase-Authentication-yellow?logo=firebase)
![Material Design](https://img.shields.io/badge/UI-Material%20Design-blue)
![Cloud Computing](https://img.shields.io/badge/Cloud-Firebase-red)

---

# 📖 Overview

Traditional leave approval systems often rely on paper forms and manual verification, making the process time-consuming and difficult to manage.

EduCloud provides a cloud-based solution where:

- Students can submit leave requests digitally.
- Teachers can review and approve or reject requests.
- HODs provide the final approval.
- Leave records are securely stored in the cloud.
- Students can track the real-time status of their applications.

The application uses Firebase Authentication for secure login and Cloud Firestore for real-time data synchronization.

---

# ✨ Features

## 👨‍🎓 Student Module

- Secure Google Sign-In
- Student Profile Management
- Apply Leave
- Automatic Leave Duration Calculation
- View Leave History
- View Detailed Leave Information
- Real-time Leave Status Tracking

---

## 👩‍🏫 Teacher Module

- View Pending Leave Requests
- Approve Leave Requests
- Reject Leave Requests
- View Approved Requests
- View Rejected Requests

---

## 👨‍💼 HOD Module

- View Teacher Approved Requests
- Final Leave Approval
- Reject Leave Requests
- Dashboard Statistics
- View Approved Requests
- View Rejected Requests

---

# ☁️ Cloud Services Used

- Firebase Authentication
- Cloud Firestore
- Google Sign-In

> **Optional:** Firebase Storage can be integrated for uploading medical certificates.

---

# 🛠️ Technologies Used

- Kotlin
- Android Studio
- Firebase Authentication
- Cloud Firestore
- Material Design Components
- RecyclerView
- CardView
- XML Layouts
- Gradle

---

# 📂 Project Structure

```
EduCloud
│
├── app
│   ├── activities
│   ├── adapters
│   ├── models
│   ├── layouts
│   ├── drawable
│   └── AndroidManifest.xml
│
├── gradle
├── screenshots
└── README.md
```

---

# 🏗️ System Architecture

```
               Firebase Authentication
                         │
                         ▼
                  Student Login
                         │
                         ▼
                 Apply Leave Request
                         │
                         ▼
               Firebase Cloud Firestore
                         │
        ┌────────────────┴────────────────┐
        ▼                                 ▼
 Teacher Dashboard                  HOD Dashboard
        │                                 │
        ▼                                 ▼
 Teacher Review                   Final Approval
                         │
                         ▼
             Student Leave History Updated
```

---

# 🔄 Leave Approval Workflow

```
Student
   │
   ▼
Apply Leave
   │
   ▼
Teacher Review
   │
 ┌─┴─────────────┐
 │               │
Reject        Approve
                  │
                  ▼
            HOD Review
             ┌────┴────┐
             │         │
          Reject   Final Approval
                    │
                    ▼
       Student Tracks Leave Status
```

---

# 📱 Application Screens

## Student

- Role Selection
- Student Dashboard
- Student Profile
- Apply Leave
- Leave History
- Leave Details

## Teacher

- Teacher Dashboard
- Pending Requests
- Approved Requests
- Rejected Requests

## HOD

- HOD Dashboard
- Pending Requests
- Approved Requests
- Rejected Requests

---

# 📸 Screenshots


## 1. Role Selection

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 39 47 PM" src="https://github.com/user-attachments/assets/6f6d387c-8ff7-4b11-aec4-6381490c0f15" />


---

## 2. Student Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 40 30 PM" src="https://github.com/user-attachments/assets/b4b37d6e-e80b-4d8d-b559-d21dc0021660" />


---

## 3. Student Profile

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 40 59 PM" src="https://github.com/user-attachments/assets/899ef0ac-2f01-4169-9ae7-e05713e38c2a" />


---

## 4. Apply Leave

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 42 29 PM" src="https://github.com/user-attachments/assets/e07b7305-e281-4ee3-82ad-5f52f3d237da" />

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 43 03 PM" src="https://github.com/user-attachments/assets/27191ce9-7c66-42ee-807c-1bac91306bf7" />
<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 43 34 PM" src="https://github.com/user-attachments/assets/6d7eeb8f-29c2-4890-bd86-ebe133924a4d" />



---

## 5. Leave History

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 44 01 PM" src="https://github.com/user-attachments/assets/c11e4a6e-9160-4818-8b92-2df6ccea40cc" />


---

## 6. Teacher Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 45 14 PM" src="https://github.com/user-attachments/assets/a6e60f5f-ab08-43b8-a942-2b1e3318c7ca" />


---

## 7. Teacher Pending Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 45 37 PM" src="https://github.com/user-attachments/assets/ecf67bf2-318b-48fa-9bc9-3d10d44bf9db" />

---

## 8. Teacher Approved Requests
<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 46 10 PM" src="https://github.com/user-attachments/assets/38089973-630f-44f9-84e3-d5f3af1b8338" />


---

## 9. Teacher Rejected Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 46 32 PM" src="https://github.com/user-attachments/assets/6990106e-79ff-4a16-94c9-9960bbe2f557" />

---

## 10. HOD Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 46 53 PM" src="https://github.com/user-attachments/assets/968a6132-4bc3-48d6-a27e-0aec31991b0e" />

---

## 11. HOD Pending Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 47 20 PM" src="https://github.com/user-attachments/assets/c2403b49-efd9-474b-82e6-7457822ac4c2" />

---

## 12. HOD Approved Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 47 45 PM" src="https://github.com/user-attachments/assets/7929f2dd-bef8-4bcc-a7e0-d6924bc391ba" />

---

## 13. HOD Rejected Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 48 05 PM" src="https://github.com/user-attachments/assets/c63bae61-09bc-4f14-bc38-9d936ebe21c8" />

---

## 14. Firebase Firestore – LeaveRequests

<img width="1312" height="627" alt="image" src="https://github.com/user-attachments/assets/724e2228-8f8e-4d32-9745-3ae5f1a8811e" />


---

## 15. Firebase Firestore – StudentProfiles

<img width="1107" height="530" alt="image" src="https://github.com/user-attachments/assets/30ffcd70-c209-4c31-9433-785cae80b89b" />

---

# 🚀 Getting Started

## Prerequisites

- Android Studio
- Kotlin
- Firebase Project
- Android Emulator or Physical Device

---

## Firebase Configuration

Enable the following Firebase services:

- Firebase Authentication
- Cloud Firestore

Download the **google-services.json** file and place it inside the **app** directory.

---

## Installation

Clone the repository.

```bash
git clone https://github.com/molmuripranavi/CODSOFT_CLOUD_COMPUTING.git
```

Open the project in Android Studio.

Sync Gradle.

Run the application on an Android device or emulator.

---

# 📊 Database Collections

## StudentProfiles

```
StudentProfiles
│
├── email
├── name
├── department
├── year
├── section
└── studentType
```

---

## LeaveRequests

```
LeaveRequests
│
├── studentName
├── email
├── department
├── year
├── section
├── studentType
├── leaveType
├── fromDate
├── toDate
├── totalDays
├── reason
├── status
└── timestamp
```

---

# 🚀 Future Enhancements

- Push Notifications
- Email Notifications
- Attendance Integration
- Admin Dashboard
- PDF Report Generation
- Analytics Dashboard
- Role-Based Access Control
- Firebase Storage Integration for Document Uploads

---

# 👩‍💻 Author

**Molmuri Pranavi**

**B.Tech – Computer Science & Engineering (AI & ML)**

**BVRIT Hyderabad College of Engineering for Women**

**GitHub:** https://github.com/molmuripranavi
