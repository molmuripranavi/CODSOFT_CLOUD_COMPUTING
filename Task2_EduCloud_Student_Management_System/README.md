# 📱 EduCloud – Cloud-Based Student Management System

> **CodSoft Cloud Computing Internship – Task 2**

EduCloud is a cloud-based Student Management System developed using Kotlin and Firebase.

The application enables students, teachers, and Heads of Department (HODs) to securely access academic information, manage attendance, grades, courses, announcements, and digital leave requests through a centralized cloud platform.

Firebase Authentication ensures secure login while Cloud Firestore provides real-time cloud synchronization across all modules.
---

![Android](https://img.shields.io/badge/Android-Kotlin-3DDC84?logo=android)
![Firebase](https://img.shields.io/badge/Firebase-Firestore-orange?logo=firebase)
![Firebase Auth](https://img.shields.io/badge/Firebase-Authentication-yellow?logo=firebase)
![Material Design](https://img.shields.io/badge/UI-Material%20Design-blue)
![Cloud Computing](https://img.shields.io/badge/Cloud-Firebase-red)

---

# 📖 Overview

Traditional student management systems often rely on multiple manual processes for maintaining academic records, attendance, grades, announcements, and leave approvals, making them time-consuming and difficult to manage.

EduCloud provides a cloud-based solution where:

- Students can securely access their academic profiles.
- Students can view courses, attendance, grades, and announcements.
- Students can submit leave requests digitally and track their status in real time.
- Teachers can review, approve, or reject leave requests.
- HODs can provide final approval and monitor academic analytics.
- Student records and leave information are securely stored in the cloud.
- Academic data is synchronized in real time across all user roles.

The application uses **Firebase Authentication** for secure role-based login and **Cloud Firestore** for real-time cloud data synchronization, providing a centralized, secure, and paperless student management system.

# 🎯 Project Objectives

- Digitize student academic management.
- Eliminate paper-based leave processing.
- Provide secure cloud-based authentication.
- Enable real-time academic data access.
- Improve communication between students, teachers, and HODs.
- Provide centralized cloud storage for academic records.

# ✨ Features

## 👨‍🎓 Student Module

- Secure Google Sign-In
- Student Dashboard
- Student Profile
- View Courses
- View Attendance
- View Grades
- View Announcements
- Apply Leave
- Leave History
- Real-Time Leave Status Tracking

---

## 👩‍🏫 Teacher Module

- Secure Teacher Login
- Teacher Dashboard
- Student Management
- Review Leave Requests
- Approve Leave Requests
- Reject Leave Requests
- View Pending Requests
- View Approved Requests
- View Rejected Requests
- Teacher Profile
- Dashboard Statistics
---

## 👨‍💼 HOD Module

- Secure HOD Login
- HOD Dashboard
- Analytics Dashboard
- Student Statistics
- Attendance Analytics
- Grade Analytics
- Review Teacher Approved Requests
- Final Leave Approval
- Reject Leave Requests
- Dashboard Statistics
- View Pending Requests
- View Approved Requests
- View Rejected Requests
- HOD Profile

---

# ☁️ Cloud Services Used

- Firebase Authentication
- Google Sign-In
- Cloud Firestore Database
- Firebase Realtime Sync

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
- ConstraintLayout
- Material Toolbar
- Intent Navigation
- Firebase SDK

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
# Attendance
```
Attendance
│
├── studentId
├── subject
├── attendedClasses
├── totalClasses
└── percentage
```

# Grades
```
Grades
│
├── studentId
├── subject
├── marks
├── grade
└── semester
```

# Courses
```
Courses
│
├── courseCode
├── courseName
├── faculty
├── credits
└── semester
```

# Announcements
```
Announcements
│
├── title
├── description
├── date
└── createdBy
---
```
# 🏗️ System Architecture

```
                         +-----------------------+
                         |   Firebase Auth       |
                         | (Google Sign-In/Login)|
                         +-----------+-----------+
                                     |
                                     ▼
                      Role-Based Authentication
                                     |
         +---------------------------+---------------------------+
         |                           |                           |
         ▼                           ▼                           ▼
+-------------------+      +-------------------+      +-------------------+
|     Student       |      |      Teacher      |      |        HOD        |
+-------------------+      +-------------------+      +-------------------+
| • Profile         |      | • Dashboard       |      | • Dashboard       |
| • Courses         |      | • Student Mgmt    |      | • Analytics       |
| • Attendance      |      | • Pending Leaves  |      | • Pending Leaves  |
| • Grades          |      | • Approve/Reject  |      | • Final Approval  |
| • Announcements   |      | • Profile         |      | • Statistics      |
| • Apply Leave     |      +-------------------+      | • Profile         |
| • Leave History   |                 |               +-------------------+
+-------------------+                 |                         |
         |                            |                         |
         +----------------------------+-------------------------+
                                      |
                                      ▼
                         Firebase Cloud Firestore
                                      |
      +----------------+----------------+----------------+----------------+
      |                |                |                |                |
      ▼                ▼                ▼                ▼                ▼
StudentProfiles    LeaveRequests    Attendance      Grades      Announcements
      |
      ▼
 Real-Time Cloud Synchronization
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
---

## 👨‍🎓 Student Module

- Secure Google Sign-In
- Student Profile
- Courses
- Attendance
- Grades
- Announcements
- Apply Leave
- Leave History
- Leave Details
- Real-time Leave Status Tracking
---

## Teacher

- Teacher Dashboard
- Pending Requests
- Approved Requests
- Rejected Requests
---

## 👨‍💼 HOD Module

- Dashboard
- Analytics Dashboard
- Student Statistics
- Attendance Analytics
- Grade Analytics
- Pending Requests
- Approved Requests
- Rejected Requests
- Final Leave Approval
---

## ☁️ Firebase Backend

- Firebase Authentication
- Cloud Firestore – StudentProfiles
- Cloud Firestore – LeaveRequests
- Cloud Firestore – Attendance
- Cloud Firestore – Grades
- Cloud Firestore – Courses
- Cloud Firestore – Announcements

---

# 📸 Screenshots

---

## 1. Role Selection

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 39 47 PM" src="https://github.com/user-attachments/assets/6f6d387c-8ff7-4b11-aec4-6381490c0f15" />


---

## 2. Student Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 37 23 PM" src="https://github.com/user-attachments/assets/201577b4-6e06-4846-842d-250ca79b100c" />


---

## 3. Student Profile

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 38 07 PM" src="https://github.com/user-attachments/assets/8d53c91e-6420-4b2b-89c7-09170f83b910" />

---
## 4.Courses

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 39 41 PM" src="https://github.com/user-attachments/assets/77663c76-29d8-4a31-9f7b-1fe02fd99ba1" />

---

## 5.Attendance

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 40 49 PM" src="https://github.com/user-attachments/assets/571f32fb-9ec8-4444-9793-6783750d3157" />
---

## 6.Grades

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 41 42 PM" src="https://github.com/user-attachments/assets/c3e10002-2650-40ed-8c1c-8d5089c6d8c6" />
---

## 7.Announcements

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 42 24 PM" src="https://github.com/user-attachments/assets/93edabad-33bd-4240-b13d-d2bf6d33cc11" />


---

## 8. Apply Leave

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 42 29 PM" src="https://github.com/user-attachments/assets/e07b7305-e281-4ee3-82ad-5f52f3d237da" />

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 43 03 PM" src="https://github.com/user-attachments/assets/27191ce9-7c66-42ee-807c-1bac91306bf7" />
<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 43 34 PM" src="https://github.com/user-attachments/assets/6d7eeb8f-29c2-4890-bd86-ebe133924a4d" />



---

## 9. Leave History

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 44 01 PM" src="https://github.com/user-attachments/assets/c11e4a6e-9160-4818-8b92-2df6ccea40cc" />


---

## 10. Teacher Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 45 02 PM" src="https://github.com/user-attachments/assets/15fc2754-e233-4754-8812-d1724131003c" />
<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 45 33 PM" src="https://github.com/user-attachments/assets/6622b122-cf17-4ce3-b2cb-62f7e01eee50" />
---

## 11.Teacher Profile

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 46 09 PM" src="https://github.com/user-attachments/assets/f96fb8d8-9a25-4c9c-a0a1-2ea0971a7807" />

---

## 12.Manage Students

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 47 52 PM" src="https://github.com/user-attachments/assets/0ba38881-6cf0-49c5-a1cb-1c3817122a51" />
<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 48 27 PM" src="https://github.com/user-attachments/assets/a4cee016-8138-408c-8aef-0465cf2d8db6" />

---

## 13. Teacher Pending Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 45 37 PM" src="https://github.com/user-attachments/assets/ecf67bf2-318b-48fa-9bc9-3d10d44bf9db" />

---

## 14. Teacher Approved Requests
<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 46 10 PM" src="https://github.com/user-attachments/assets/38089973-630f-44f9-84e3-d5f3af1b8338" />


---

## 15. Teacher Rejected Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 46 32 PM" src="https://github.com/user-attachments/assets/6990106e-79ff-4a16-94c9-9960bbe2f557" />

---

## 16. HOD Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 50 10 PM" src="https://github.com/user-attachments/assets/53260bd1-37b3-4be3-83ce-2c6e3a649cd8" />
<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 50 37 PM" src="https://github.com/user-attachments/assets/1249055c-4565-4e44-aeba-7be6372ef0ed" />
---

## 17.HOD Profile

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 53 39 PM" src="https://github.com/user-attachments/assets/4731abf4-35f9-473f-8a6c-3138629d0a6e" />

---

## 18.HOD Analytics Dashboard

<img width="738" height="1600" alt="WhatsApp Image 2026-07-31 at 3 54 32 PM" src="https://github.com/user-attachments/assets/5d49e92c-3981-4f10-9b58-e3453281034c" />

---

## 19. HOD Pending Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 47 20 PM" src="https://github.com/user-attachments/assets/c2403b49-efd9-474b-82e6-7457822ac4c2" />

---

## 20. HOD Approved Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 47 45 PM" src="https://github.com/user-attachments/assets/7929f2dd-bef8-4bcc-a7e0-d6924bc391ba" />

---

## 21. HOD Rejected Requests

<img width="738" height="1600" alt="WhatsApp Image 2026-07-25 at 5 48 05 PM" src="https://github.com/user-attachments/assets/c63bae61-09bc-4f14-bc38-9d936ebe21c8" />

---

## 22. Firebase Firestore – LeaveRequests

<img width="1312" height="627" alt="image" src="https://github.com/user-attachments/assets/724e2228-8f8e-4d32-9745-3ae5f1a8811e" />


---

## 23. Firebase Firestore – StudentProfiles

<img width="1107" height="530" alt="image" src="https://github.com/user-attachments/assets/30ffcd70-c209-4c31-9433-785cae80b89b" />

---

## 24.Firestore – Attendance

<img width="996" height="548" alt="image" src="https://github.com/user-attachments/assets/1238ade4-f3b0-4a38-8852-41f38ae94158" />
---

## 25.Firestore – Grades

<img width="1007" height="570" alt="image" src="https://github.com/user-attachments/assets/41bd124a-cb10-4760-acdf-044fd55e61c1" />
---

## 26.Firestore – Courses

<img width="1013" height="573" alt="image" src="https://github.com/user-attachments/assets/7f86ca0f-248a-40ce-8846-f74e30113c56" />

---

## 27.Firestore – Announcements

<img width="1009" height="568" alt="image" src="https://github.com/user-attachments/assets/8455374e-40f9-4b11-9017-eab0d0e65347" />

---

# 🚀 Getting Started
---

## Prerequisites

Before running the project, make sure you have:

- Android Studio (Latest Version)
- JDK 11 or above
- Kotlin Support
- Firebase Project
- Android Emulator or Physical Android Device
- Internet Connection

---

## Firebase Configuration

1. Create a Firebase project.
2. Register your Android application.
3. Download the **google-services.json** file.
4. Place the file inside the **app/** directory.
5. Enable the following Firebase services:
   - Firebase Authentication (Google Sign-In)
   - Cloud Firestore Database

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/molmuripranavi/CODSOFT_CLOUD_COMPUTING.git
```

2. Open the project in **Android Studio**.

3. Sync the Gradle files.

4. Connect an Android device or start an emulator.

5. Click **Run ▶️** to build and launch the application.

---

## Default User Roles
---

The application supports three types of users:

- 👨‍🎓 Student
- 👩‍🏫 Teacher
- 👨‍💼 HOD (Head of Department)

---

## Build Tools

- Android Studio
- Kotlin
- Gradle
- Firebase SDK
- Material Design Components
  
---

# 📊 Database Collections

## StudentProfiles

```text
StudentProfiles
│
├── email
├── name
├── rollNumber
├── department
├── year
├── section
├── studentType
└── phone
```

---

## LeaveRequests

```text
LeaveRequests
│
├── studentName
├── email
├── rollNumber
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
├── approvedBy
├── rejectedBy
└── timestamp
```

---

## Attendance

```text
Attendance
│
├── studentId
├── studentName
├── subject
├── attendedClasses
├── totalClasses
└── percentage
```

---

## Grades

```text
Grades
│
├── studentId
├── studentName
├── subject
├── marks
├── grade
└── semester
```

---

## Courses

```text
Courses
│
├── courseCode
├── courseName
├── faculty
├── credits
└── semester
```

---

## Announcements

```text
Announcements
│
├── title
├── description
├── date
└── createdBy
```

---

## Users

```
Users
│
├── email
├── name
└── role
```
```
```
# 🌟 Key Highlights
```
- 📱 Cloud-Based Student Management System developed using **Kotlin** and **Firebase**.
- 🔐 Secure role-based authentication for **Students, Teachers, and HODs** using **Firebase Authentication**.
- ☁️ Real-time cloud data synchronization with **Firebase Cloud Firestore**.
- 👨‍🎓 Comprehensive Student Module with Profile, Courses, Attendance, Grades, Announcements, and Leave Management.
- 👩‍🏫 Teacher Module for reviewing, approving, and rejecting student leave requests.
- 👨‍💼 HOD Module with final leave approval, dashboard statistics, and analytics.
- 📊 Analytics Dashboard displaying student count, attendance insights, and grade distribution.
- 📝 Automated multi-level leave approval workflow (Student → Teacher → HOD).
- 📚 Centralized cloud storage for student academic and leave records.
- 🎨 Modern and responsive Material Design user interface.
- 🔄 Real-time updates across all user roles without manual refresh.
- 📂 Well-structured Android project following modular architecture and reusable components.
- 📈 Scalable Firebase backend for efficient cloud-based data management.
---
```
# 🚀 Future Enhancements
```
- 🔔 Push Notifications for leave status updates and announcements.
- 📧 Email Notifications for leave approvals, rejections, and important notices.
- 📂 Firebase Storage integration for uploading medical certificates and supporting documents.
- 📄 PDF generation for leave applications, attendance reports, and grade reports.
- 📊 Advanced Analytics Dashboard with charts and graphical insights.
- 📅 Academic Calendar and Event Management.
- 📚 Online Study Materials and Course Resources.
- 💬 In-App Chat between Students, Teachers, and HODs.
- 📌 Assignment and Homework Management.
- 📝 Online Examination and Quiz Module.
- 📈 Attendance Trends and Performance Analysis.
- 👨‍💼 Admin Module for complete system administration.
- 🔐 Enhanced Role-Based Access Control (RBAC).
- 🌙 Dark Mode support for improved user experience.
- 🌐 Multi-language support.
- ☁️ Offline data caching and synchronization.

---
```
# 👩‍💻 Author

**Molmuri Pranavi**

**B.Tech – Computer Science & Engineering (AI & ML)**

**BVRIT Hyderabad College of Engineering for Women**

**GitHub:** https://github.com/molmuripranavi
