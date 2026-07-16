# ☁️ Cloud File Storage Backend

A RESTful Cloud File Storage Backend developed using **Spring Boot**. This application allows users to upload, list, download, and delete files through REST APIs. Uploaded files are stored locally in the `uploads` directory.
## 🔗 Live Links
- 🌐 Frontend: https://codsoft-cloud-computing-h15blvs31-molmuripranavis-projects.vercel.app
- ⚙️ Backend API: https://codsoft-cloud-computing.onrender.com

---

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![React](https://img.shields.io/badge/React-19-61DAFB)
![Render](https://img.shields.io/badge/Backend-Render-blueviolet)
![Vercel](https://img.shields.io/badge/Frontend-Vercel-black)
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
│       │       ├── controller
│       │       │      FileController.java
│       │       └── CloudFileStorageApplication.java
│       │
│       └── resources
│              application.properties
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
<img width="1364" height="717" alt="image" src="https://github.com/user-attachments/assets/687431c0-10e6-4a7a-863c-2117d95b2a91" />

### Success Response

```
Files uploaded successfully:
molmuripranaviresume.pdf
BH23_AIML_Syllabus_Complete.pdf
Declaration Form English.pdf
```
<img width="1365" height="722" alt="image" src="https://github.com/user-attachments/assets/6d95dd71-8673-4de9-b8c4-2b7cf4917ba3" />

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
<img width="1364" height="720" alt="image" src="https://github.com/user-attachments/assets/b0c0a31b-aad3-4234-9873-c2f5c0ddf6fb" />

---

# 📥 Download File

### Request

**GET**

```
http://localhost:8080/api/files/download/molmuripranaviresume.pdf
```

The requested file is downloaded to the user's system. Depending on your API client (such as Postman), you may be prompted to save the file or view its binary content.
<img width="1360" height="711" alt="image" src="https://github.com/user-attachments/assets/f21e51db-b0ca-4a9e-a3d8-b7e5ee3d9b48" />

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
<img width="1365" height="720" alt="image" src="https://github.com/user-attachments/assets/32249d32-1f53-4023-a14f-0ce4a77ab4e7" />

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

# 📸 Screenshots

Add screenshots of:

- Spring Boot application running
  <img width="1365" height="678" alt="image" src="https://github.com/user-attachments/assets/177b1022-0817-4342-a740-8d5722d38727" />
---

# ☁️ Deployment

| Service | Platform |
|----------|----------|
| Frontend | Vercel |
| Backend | Render |
| Database | MySQL |
## 👩‍💻 Author

**Molmuri Pranavi**

- B.Tech, Computer Science & Engineering (AI & ML)
- BVRIT Hyderabad College of Engineering for Women
- GitHub: https://github.com/molmuripranavi
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
