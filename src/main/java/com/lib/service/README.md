📚 Online Library Management System (Spring Boot)

This is a simple Online Library Management System built using Spring Boot and MySQL.
The project allows users to:
* Register and manage users
* Add and manage books
* Issue and return books
* Handle user memberships
* Track book transactions
* Generate useful reports (usage, issued books, transactions, etc.)
This project is designed to demonstrate real-world backend logic like memberships, book availability, overdue returns, and reporting.

🛠️ Tech Stack Used
* Java 17
* Spring Boot
* Spring Data JPA(Hibernate)
* MySQL
* REST APIs
* Lombok
* Maven

🚀 How to Run the Project
1. Clone the repository
2. Configure MySQL database in application.properties
3. Create a database (example: library_db)
4. Run the application
The application will start on:

http://localhost:7070

👤 USER APIs
1️⃣ Get all users

GET /api/v1/users
2️⃣ Get user by ID

GET /api/v1/users/{userId}

3️⃣ Delete user

DELETE /api/v1/users/{userId}
Example:

DELETE /api/v1/users/1

📘 BOOK APIs
4️⃣ Get all books

GET /api/v1/books
5️⃣ Get book by ID

GET /api/v1/books/{bookId}
Example:

GET /api/v1/books/3
6️⃣ Check if book is available

GET /api/v1/books/{bookId}/available
Example:

GET /api/v1/books/2/available
7️⃣ Add a new book

POST /api/v1/books
📌 If the same book already exists (same name + author), the API will return an error.
8️⃣ Update book details / stock

PUT /api/v1/books/{bookId}
9️⃣ Delete a book

DELETE /api/v1/books/{bookId}

📖 BOOK ISSUE & RETURN APIs
🔟 Issue a book to a user

POST /api/v1/books/issue?userId={userId}&bookId={bookId}&days={days}
Example:

POST /api/v1/books/issue?userId=3&bookId=1&days=7
✔ Checks:
* User membership validity
* Book availability

1️⃣1️⃣ Return a book

POST /api/v1/books/return?userId={userId}&bookId={bookId}
Example:

POST /api/v1/books/return?userId=3&bookId=1
📌 If the book is returned late, the user will see a message like:

You returned the book after the due date by 3 days. You will be charged with a fine.
(Fine amount is handled internally and not shown.)

👑 MEMBERSHIP APIs 
1️⃣2️⃣ Start membership

POST /api/v1/memberships/start?userId={userId}&months={months}
Example:

POST /api/v1/memberships/start?userId=3&months=1

1️⃣3️⃣ Renew membership

POST /api/v1/memberships/renew?userId={userId}&months={months}
Example:

POST /api/v1/memberships/renew?userId=3&months=1

1️⃣4️⃣ Get remaining membership days

GET /api/v1/memberships/remaining-days/{userId}
Example:

GET /api/v1/memberships/remaining-days/3

1️⃣5️⃣ Expire membership manually

POST /api/v1/memberships/expire/{userId}
Example:

POST /api/v1/memberships/expire/2

📊 REPORT APIs 
1️⃣6️⃣ Category usage report
Shows which book categories are read by how many users (in percentage).

GET /api/v1/reports/category-usage

1️⃣7️⃣ Get all currently issued books

GET /api/v1/reports/issued-books

1️⃣8️⃣ User-wise issued books (last N days)

GET /api/v1/reports/users/{userId}/issued-books?days={days}

1️⃣9️⃣ All book transactions in last N days
Includes ISSUED and RETURNED transactions.

GET /api/v1/reports/transactions?days={days}

⚠️ Error Handling
The project uses global exception handling to return clean error messages.
Example error response:

{
  "status": 404,
  "message": "Book not found",
  "timestamp": "2025-12-29T11:30:00"
}

✅ Key Features
* Clean REST APIs
* Membership-based book issuing
* Late return handling
* Stock management
* Transaction history
* Reporting APIs
* Centralized exception handling
* Real-world business logic

📌 Notes
* This project is backend-only
* APIs can be tested using Postman
* Designed for learning Spring Boot + JPA best practices

🙌 Conclusion
This project demonstrates how a real library system backend works, including:
* User management
* Book lifecycle
* Membership validation
* Reports & analytics
Feel free to fork, improve, and extend it 🚀
