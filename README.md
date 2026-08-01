# ☁️ Cloud File Storage Backend

A RESTful Cloud File Storage Backend built with **Spring Boot** that enables users to upload, view, download, and delete files through REST APIs.

## 🔗 Live Links

- 🌐 Frontend: https://your-project.vercel.app
- ⚙️ Backend API: https://codsoft-cloud-computing.onrender.com
- 📂 Frontend Repository: https://github.com/molmuripranavi/CloudVault-Frontend
- 📂 Backend Repository: https://github.com/molmuripranavi/CloudVault
---

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![React](https://img.shields.io/badge/React-19-61DAFB)
![Render](https://img.shields.io/badge/Backend-Render-blueviolet)
![Vercel](https://img.shields.io/badge/Frontend-Vercel-black)
---
## 📖 Overview

This project provides a simple file management system where users can:

- Upload one or multiple files
- View all uploaded files
- Download files
- Delete files

The backend is built using Spring Boot and tested using Postman.

---

## ✨ Features

- 📤 Upload multiple files
- 📋 View uploaded files
- 📥 Download files
- 👁️ Preview supported files
- 🗑️ Delete files
- 🔍 Search files
- 📂 Filter files by type
- 📊 Storage dashboard
- 📁 Automatic uploads folder creation
- ⚡ RESTful APIs
- 💾 MySQL Integration
- 🌐 Deployed on Render & Vercel

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3
- Maven
- Spring Web
- Spring Data JPA
- MySQL
- Postman
---
## 📂 Project Structure

```
backend
│
├── src
│   └── main
│       ├── java
│       │   └── com.codesoft.cloud_file_storage
│       │       ├── config
│       │       ├── controller
│       │       ├── dto
│       │       └── CloudFileStorageApplication.java
│       │
│       └── resources
│           └── application.properties
│
├── uploads
├── pom.xml
└── mvnw.cmd
```
## 🏗️ System Architecture

```
React Frontend (Vercel)
        │
        │ Axios REST API
        ▼
Spring Boot Backend (Render)
        │
        ▼
      MySQL Database
```

---

# 🚀 Getting Started

## Prerequisites

Install the following before running the project:

- Java 17 or later
- Maven
- MySQL Server
- MySQL Workbench
- Visual Studio Code
- Postman

---

## Database Setup

Create a MySQL database named:

```sql
CREATE DATABASE cloud_storage_db;
```

Open

```
src/main/resources/application.properties
```

Configure your database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cloud_storage_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Application

Open the backend project in Visual Studio Code.

Start the Spring Boot application using:

```
mvnw.cmd spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---
# 📡 API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/files/upload` | Upload files |
| GET | `/api/files` | List files |
| GET | `/api/files/storage` | Storage statistics |
| GET | `/api/files/view/{filename}` | Preview file |
| GET | `/api/files/download/{filename}` | Download file |
| DELETE | `/api/files/{filename}` | Delete file |

---

# 📤 Upload Files

### Request

**POST**

```
http://localhost:8080/api/files/upload
```

### Postman Steps

1. Select **POST**
2. Enter the URL
3. Click **Body**
4. Select **form-data**
5. Add a key named

```
file
```

6. Change its type from **Text** to **File**
7. Select a file from your computer.
8. To upload multiple files, add multiple rows with the same key (`file`).
9. Click **Send**

### Success Response

```
Files uploaded successfully:
molmuripranaviresume.pdf
BH23_AIML_Syllabus_Complete.pdf
Declaration Form English.pdf
```

---

# 📋 List Uploaded Files

### Request

**GET**

```
http://localhost:8080/api/files
```

### Sample Response

```json
[
    "BH23_AIML_Syllabus_Complete.pdf",
    "Declaration Form English.pdf",
    "molmuripranaviresume.pdf"
]
```

---

# 📥 Download File

### Request

**GET**

```
http://localhost:8080/api/files/download/molmuripranaviresume.pdf
```

The requested file is downloaded to the user's system. Depending on your API client (such as Postman), you may be prompted to save the file or view its binary content.

---

# 🗑️ Delete File

### Request

**DELETE**

```
http://localhost:8080/api/files/molmuripranaviresume.pdf
```

### Response

```
File deleted successfully.
```

---

# 📂 Upload Directory

All uploaded files are stored inside:

```
backend/
└── uploads/
```

The folder is automatically created when the application starts.

---

# 🧪 API Testing

All APIs were tested successfully using **Postman**.

Verified functionalities:

- ✅ Upload File
- ✅ Upload Multiple Files
- ✅ List Files
- ✅ Download File
- ✅ Delete File

---

# ☁️ Deployment

| Service | Platform |
|----------|----------|
| Frontend | Vercel |
| Backend | Render |
| Database | MySQL |

---


## 🚀 Future Scope

- JWT Authentication
- AWS S3 Integration
- Azure Blob Storage
- Cloudinary Integration
- Role-based Access Control
- File Sharing using Secure Links
- Docker & Kubernetes Deployment
- Email Notifications
---
## 👩‍💻 Author

**Molmuri Pranavi**

- B.Tech, Computer Science & Engineering (AI & ML)
- BVRIT Hyderabad College of Engineering for Women
- GitHub: https://github.com/molmuripranavi
---

