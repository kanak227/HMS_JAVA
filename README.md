## 🏥 Hospital Management System (HMS)

A simple yet functional web-based Hospital Management System built using **Java (JSP + Servlets)**, **MySQL**, and **Bootstrap**.
This system enables patients to book appointments with doctors and doctors to view/manage their scheduled appointments.

---

### 🚀 Features

#### 👤 Patient Module

* Secure login for patients
* View list of all registered doctors
* Filter doctors by specialization
* Book an appointment instantly
* Success popup notification on booking

#### 🩺 Doctor Module

* Secure login for doctors
* View scheduled appointments
* Patient details with contact information

#### ⚙️ Platform Features

* Session-based authentication
* Clean, responsive UI using Bootstrap 5
* Proper database relations & constraints
* Modular & scalable architecture

---

## 🛠️ Tech Stack

| Layer    | Technology                  |
| -------- | --------------------------- |
| Frontend | JSP, HTML, CSS, Bootstrap 5 |
| Backend  | Java Servlets (Jakarta EE)  |
| Server   | Apache Tomcat               |
| Database | MySQL                       |
| IDE      | NetBeans                    |

---

## 📁 Project Structure

```
PBL_Project/
 ├─ src/main/java/com/hms/
 │   ├─ model/       → POJO classes: User, Doctor, Patient, Appointment
 │   ├─ dao/         → Data access objects for DB operations
 │   └─ servlet/     → Login, Appointment Booking, etc.
 ├─ src/main/webapp/
 │   ├─ login.jsp
 │   ├─ patient/home.jsp
 │   ├─ doctor/home.jsp
 │   └─ WEB-INF/
 │       └─ web.xml
 └─ README.md
```

---

## 🗄️ Database Setup

Run the following inside MySQL:

```sql
CREATE DATABASE hms;
USE hms;
```

Then import schema + demo data (users, doctors, patients, specializations).
Foreign key relationships ensure data consistency.

---

## 🔐 Default Login Accounts

| Role    | Username     | Password |
| ------- | ------------ | -------- |
| Doctor  | doc1 – doc10 | test123  |
| Patient | pat1         | test123  |
| Patient | pat2         | qwerty   |

---

## ▶️ Running the Project

1️⃣ Clone the repository

```sh
git clone https://github.com/<your-username>/HMS.git
```

2️⃣ Open in **NetBeans / IntelliJ / Eclipse**
3️⃣ Configure **Apache Tomcat Server**
4️⃣ Update DB credentials in `DBConnection.java`
5️⃣ Run Project → Application launches at:

👉 `http://localhost:8080/HMS/`

---

## 🧩 Future Enhancements (Suggested Roadmap)

✔ Patient appointment history
✔ Doctor status update (Completed/Cancelled)
✔ Admin role for management
✔ Email / SMS notifications
✔ Doctor schedule & time slots
✔ Patient profile editing
✔ Prescription and medical reports

Contributions are welcome!


