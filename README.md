# Data Entry Web Application

A full-stack web application built with **Java 21** and **Spring Boot 3.5.4** that demonstrates modern web development practices, clean architecture, and professional coding standards.

## 🎯 Project Overview

This application fulfills the coding exercise requirements by providing:
- **Data Entry Form**: Single form with inputs for Name, Age, Title, and Hometown
- **Validation**: Required field validation for Name and Title with error handling
- **Confirmation Page**: Displays submitted information and previous entries table
- **Modern UI**: Responsive design with clean CSS styling
- **Full CRUD Operations**: Create, Read, Update, and Delete functionality
- **Professional Architecture**: Layered architecture with proper separation of concerns

## 🚀 Tech Stack

### Backend
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.5.4** - Enterprise-grade framework for rapid development
- **Spring Data JPA** - Data access layer with repository pattern
- **Spring Validation** - Bean validation for form inputs
- **Spring Web** - RESTful web services and MVC
- **Thymeleaf** - Server-side templating engine

### Database
- **PostgreSQL 17** - Production-ready relational database
- **H2 Database** - In-memory database for development/testing
- **JPA/Hibernate** - Object-relational mapping

### Frontend
- **HTML5** - Semantic markup
- **CSS3** - Modern styling with responsive design
- **TypeScript** - Enhanced JavaScript for better maintainability
- **Thymeleaf** - Server-side templating

### DevOps & Testing
- **Docker** - Containerization for consistent deployment
- **Docker Compose** - Multi-container orchestration
- **Maven** - Dependency management and build automation
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework for testing

## 🏗️ Architecture

The application follows **clean architecture principles** with clear separation of concerns:

```
src/main/java/olis/codingexercise/
├── controller/     # REST endpoints and form handling
├── service/        # Business logic layer
├── repository/     # Data access layer
├── model/          # Entity classes
├── dto/            # Data Transfer Objects
├── mapper/         # Object mapping utilities
└── exception/      # Custom exception handling
```

## 🌐 API Endpoints

The application provides a comprehensive set of endpoints for managing data entries:

### **Data Entry & Management**
| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `GET` | `/entries/entry-form` | Display the main data entry form | None |
| `POST` | `/entries/confirm` | Submit new entry data | `Entry` object (Name, Age, Title, Hometown) |
| `GET` | `/entries/confirm` | Show confirmation page with submitted data | `newEntryId` (Long) |
| `GET` | `/entries/all-entries` | Display all submitted entries in a table | None |

### **CRUD Operations**
| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `GET` | `/entries/edit/{entryId}` | Show edit form for existing entry | `entryId` (Long) |
| `POST` | `/entries/edit/{entryId}` | Update existing entry | `entryId` (Long), `EntryUpdateRequest` |
| `GET` | `/entries/delete/{entryId}` | Show delete confirmation page | `entryId` (Long) |
| `POST` | `/entries/delete/{entryId}` | Delete existing entry | `entryId` (Long), `_method=delete` |

### **Data Model**
The Entry entity includes:
- **`entryId`** (Long, auto-generated) - Unique identifier
- **`firstName`** (String, required) - First name of the person
- **`lastName`** (String, required) - Last name of the person  
- **`age`** (Integer, optional) - Age of the person
- **`title`** (String, required) - Job title or role
- **`hometown`** (String, optional) - Person's hometown

### **Validation Rules**
- **Required Fields**: `firstName`, `lastName`, `title`
- **Optional Fields**: `age`, `hometown`
- **Error Handling**: Form validation with user-friendly error messages
- **Data Persistence**: All entries are stored in PostgreSQL database

### Key Design Patterns
- **Repository Pattern** - Abstracted data access
- **Service Layer** - Business logic encapsulation
- **DTO Pattern** - Clean data transfer between layers
- **Exception Handling** - Comprehensive error management
- **Validation** - Input validation with meaningful error messages

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.9+
- Docker and Docker Compose
- PostgreSQL (optional, H2 is used by default)

### Quick Start with Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/EdwardA-II/codingexercise-entrada
   cd codingexercise-entrada
   ```

2. **Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **Access the application**
   - Main application: http://localhost:8080 or http://localhost:8080/home
   - Entry form: http://localhost:8080/entries/entry-form
   - All entries: http://localhost:8080/entries/all-entries

### Local Development

1. **Install dependencies**
   ```bash
   mvn clean install
   ```

2. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**
   - Main application: http://localhost:8080

## 🧪 Testing

The application includes comprehensive unit tests demonstrating testing best practices:

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn test jacoco:report
```

### Test Coverage
- **Service Layer**: 100% method coverage with Mockito
- **Exception Handling**: Custom exception testing
- **Validation**: Input validation testing
- **Business Logic**: CRUD operation testing

## 📊 Features

### Core Functionality
- ✅ **Data Entry Form** - Single form with all required fields
- ✅ **Field Validation** - Required field validation (Name, Title)
- ✅ **Error Handling** - User-friendly error messages
- ✅ **Confirmation Page** - Submitted data display
- ✅ **Previous Entries Table** - Historical data viewing
- ✅ **Responsive Design** - Modern, mobile-friendly UI

### Advanced Features
- ✅ **Full CRUD Operations** - Create, Read, Update, Delete
- ✅ **Data Persistence** - PostgreSQL database integration
- ✅ **Form Validation** - Server-side and client-side validation
- ✅ **Exception Handling** - Comprehensive error management
- ✅ **Clean Architecture** - Professional code organization
- ✅ **Unit Testing** - Comprehensive test coverage

## 🐳 Docker Configuration

The application is fully containerized for consistent deployment:

### **Multi-Stage Docker Build**
- **Builder Stage**: Uses Maven 3.9.11 with Eclipse Temurin 21 for dependency resolution and compilation
- **Runtime Stage**: Lightweight OpenJDK 21 image for production deployment
- **Optimized**: Only runtime dependencies are included in final image

### **PostgreSQL Database Container**
- **Image**: PostgreSQL 17 (latest stable version)
- **Port**: 5432 (mapped to host)
- **Credentials**: 
  - Database: `postgres`
  - Username: `postgres`
  - Password: `123`
- **Health Checks**: Automated readiness checks every 10 seconds
- **Volume Persistence**: Data stored in named volume `postgres-data`
- **Restart Policy**: `unless-stopped` for reliability

### **Application Container**
- **Port**: 8080 (mapped to host)
- **Dependencies**: Waits for PostgreSQL to be healthy before starting
- **Build Context**: Uses local Dockerfile for custom image creation
- **Container Name**: `olis-entrada`

### **Docker Compose Features**
- **Service Orchestration**: Manages both app and database containers
- **Health Check Integration**: App starts only after database is ready
- **Volume Management**: Persistent database storage across container restarts
- **Network Isolation**: Internal communication between services

## 🔧 Configuration

### Application Properties
- **Database**: Configurable between H2 (dev) and PostgreSQL (prod)
- **Port**: Configurable application port (default: 8080)
- **Validation**: Bean validation enabled
- **Thymeleaf**: Template caching configuration

### Environment Variables
- `SPRING_PROFILES_ACTIVE` - Profile selection
- `DATABASE_URL` - Database connection string
- `DATABASE_USERNAME` - postgres
- `DATABASE_PASSWORD` - 123

## 📈 Performance & Scalability

- **Connection Pooling** - Optimized database connections
- **Transaction Management** - ACID compliance with rollback support
- **Lazy Loading** - Efficient data fetching
- **Caching** - Template and static resource caching
- **Async Processing** - Non-blocking operations where applicable

## 🛡️ Security & Best Practices

- **Input Validation** - Comprehensive form validation
- **SQL Injection Prevention** - JPA/Hibernate protection
- **XSS Protection** - Thymeleaf auto-escaping
- **Error Handling** - Secure error messages
- **Dependency Management** - Regular security updates

## 🚀 Deployment

### Production Deployment
1. **Build the application**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Deploy with Docker**
   ```bash
   docker-compose up -d
   ```

3. **Environment Configuration**
   - Set production database credentials
   - Configure logging levels
   - Set appropriate JVM options

## 🔍 Code Quality

- **Clean Code Principles** - Readable, maintainable code
- **SOLID Principles** - Single responsibility, dependency injection
- **Consistent Naming** - Clear, descriptive naming conventions
- **Comprehensive Documentation** - Inline code documentation
- **Error Handling** - Graceful error management

## 📚 Learning Outcomes

This project demonstrates:
- **Modern Java Development** - Java 21 features and Spring Boot 3
- **Full-Stack Development** - Frontend and backend integration
- **Database Design** - Relational database modeling and JPA
- **Testing Practices** - Unit testing with JUnit and Mockito
- **DevOps Skills** - Docker containerization and deployment
- **Clean Architecture** - Professional code organization
- **API Design** - RESTful endpoint design and validation

## 🤝 Contributing

This is a coding exercise submission. For questions or feedback, please contact ekabusiness2023@gmail.com.

## 📄 License

This project is created for educational and demonstration purposes.

---

**Built with ❤️ using Spring Boot, Java 21, and other associated modern web technologies**

