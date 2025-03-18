# Library Management System

A Spring Boot-based backend application designed to manage and automate the operations of a library. This system allows librarians and users to interact with the library’s resources efficiently, supporting **CRUD operations** (Create, Read, Update, Delete) for managing books, users, and transactions.

---

## Features

### Book Management
- Add new books to the library.
- Update book details (e.g., title, author, availability).
- Delete books from the library.
- View a list of all available books.

### User Management
- Register new users (e.g., students, librarians).
- Update user details (e.g., name, email).
- Delete users.
- View a list of all registered users.

### Transaction Management
- Borrow a book (link a user to a book).
- Return a book (update the book’s availability).
- View all transactions (e.g., borrowed books, due dates).

### Search and Filter
- Search for books by title, author, or availability.
- Filter books by category or status (available/borrowed).

### Error Handling
- Handle exceptions gracefully (e.g., book not found, user not found).
- Provide meaningful error messages to users.

### API Endpoints
- Expose RESTful APIs for all operations (e.g., `/books`, `/users`, `/transactions`).
- Allow external systems or frontends to interact with the backend.

---

## Technologies Used

- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **Build Tool**: Maven
- **Java Version**: 21
- **Dependencies**:
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - MySQL Connector/J
    

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or later
- MySQL Server (for database)
- Maven (for dependency management)
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/salahudin-hub/Library-management-system.git
   cd Library-management-system
