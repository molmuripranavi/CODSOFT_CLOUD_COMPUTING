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

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 39 47 PM" src="https://github.com/user-attachments/assets/73d1d84f-d0e3-4d3b-8588-7da2cc26dd5b" />


---

## 2. Student Dashboard

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 37 23 PM" src="https://github.com/user-attachments/assets/62dd059e-5b09-411f-ba94-d22862d723e0" />


---

## 3. Student Profile

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 38 07 PM" src="https://github.com/user-attachments/assets/65917b4a-39ac-4b25-b506-7368cdfb5ced" />

---
## 4.Courses

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 39 41 PM" src="https://github.com/user-attachments/assets/ac72a1ef-8ccd-4551-bc41-afde6068a0c8" />

---

## 5.Attendance

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 40 49 PM" src="https://github.com/user-attachments/assets/898a988e-37d1-434c-9d6c-b4939098b8fa" />
---

## 6.Grades

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 41 42 PM" src="https://github.com/user-attachments/assets/8c2d558d-171c-42dd-9b36-e121f2b6f4a5" />
---

## 7.Announcements

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 42 24 PM" src="https://github.com/user-attachments/assets/bb7996f2-5083-4af3-9eaa-430bfb6c31d7" />


---

## 8. Apply Leave

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 42 29 PM" src="https://github.com/user-attachments/assets/700e6b2a-0729-4e4f-befb-de8fd664b273" />
<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 43 03 PM" src="https://github.com/user-attachments/assets/93dffe3b-43ae-4b93-98f0-cf9b26277a28" />
<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 43 34 PM" src="https://github.com/user-attachments/assets/ff7102ef-0eff-48c0-8acf-a5a487346bf1" />



---

## 9. Leave History

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 44 01 PM" src="https://github.com/user-attachments/assets/a0fb2276-7764-4467-b718-5a3366aeb526" />


---

## 10. Teacher Dashboard
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 20 09 PM" src="https://github.com/user-attachments/assets/43c81aa7-16d7-469b-8939-d53b36b47350" />
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 20 36 PM" src="https://github.com/user-attachments/assets/6510b5f3-8553-4768-9e34-59bbe9c6868a" />

---

## 11.Teacher Profile

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 46 09 PM" src="https://github.com/user-attachments/assets/ac6b84dc-2e5b-4ad8-b336-9f18649d652e" />

---

## 12.Manage Students
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 21 57 PM" src="https://github.com/user-attachments/assets/5f9b6344-1985-4fb3-9a0e-77323f143627" />

<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 22 21 PM" src="https://github.com/user-attachments/assets/31e385cf-d4ea-4cb1-9ce9-65ff2752469e" />

---

## 13. Teacher Pending Requests

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 45 37 PM" src="https://github.com/user-attachments/assets/ba5f6f3a-4e7f-4028-b465-10ba8ecaf6a1" />

---

## 14. Teacher Approved Requests
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 24 14 PM" src="https://github.com/user-attachments/assets/27762756-7d98-46c1-b58c-09aabf331add" />


---

## 15. Teacher Rejected Requests

<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 24 39 PM" src="https://github.com/user-attachments/assets/867d939a-a096-4d4c-abe7-7205f59c20a9" />

---

## 16. HOD Dashboard
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 25 52 PM" src="https://github.com/user-attachments/assets/8177082e-fe33-438b-a531-8cc9b9afce6a" />
<img width="200" height="500" alt="WhatsApp Image 2026-08-01 at 1 26 12 PM" src="https://github.com/user-attachments/assets/3de5e8e0-8031-4f8c-a79c-7dffa570f50b" />

---

## 17.HOD Profile

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 53 39 PM" src="https://github.com/user-attachments/assets/c98b57ac-a9c9-4042-bc98-7d30312fa0e1" />

---

## 18.HOD Analytics Dashboard

<img width="200" height="500" alt="WhatsApp Image 2026-07-31 at 3 54 32 PM" src="https://github.com/user-attachments/assets/7f3f64e5-1c36-4347-af64-9591197be40e" />

---

## 19. HOD Pending Requests

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 47 20 PM" src="https://github.com/user-attachments/assets/7a6b13f3-24a6-424f-95ad-64635ceb9cd3" />

---

## 20. HOD Approved Requests

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 47 45 PM" src="https://github.com/user-attachments/assets/844d6655-3d76-4bfa-b8b9-d08bba227ea8" />

---

## 21. HOD Rejected Requests

<img width="200" height="500" alt="WhatsApp Image 2026-07-25 at 5 48 05 PM" src="https://github.com/user-attachments/assets/4d4f8a8a-794e-4c11-bdfd-dff9ceee5f35" />

---

## 22. Firebase Firestore – LeaveRequests

<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/06bd3ad8-6d33-488a-8222-beae1d38029c" />


---

## 23. Firebase Firestore – StudentProfiles

<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/d61889aa-ef98-4fab-9931-67cac4c518a3" />

---

## 24.Firestore – Attendance
<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/526d34b2-463f-4062-b96c-7cb397587a2c" />

---

## 25.Firestore – Grades

<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/6f1496ec-6240-4540-9d16-3779d55d51fe" />
---

## 26.Firestore – Courses

<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/3d39143f-0e0d-444f-94d1-6b21679d9d82" />

---

## 27.Firestore – Announcements

<img width="600" height="300" alt="image" src="https://github.com/user-attachments/assets/693eae82-e133-4b6a-99c5-155d24caeea6" />

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
# 🚀 Future Enhancements
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
# 👩‍💻 Author

**Molmuri Pranavi**

**B.Tech – Computer Science & Engineering (AI & ML)**

**BVRIT Hyderabad College of Engineering for Women**

**GitHub:** https://github.com/molmuripranavi
