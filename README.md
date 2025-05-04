# RecyclePosAPI
A comprehensive management system for secondhand shops, developed to help shop owners and staff efficiently manage sales, products, members, and various reports.

## Features
- Point of Sale (POS): Record sales, calculate prices, and print receipts
- Product Management: Add, edit, delete, and search products
- Membership System: Manage member information and special benefits (member pricing)
- Employee Management: Manage system users with different roles
- Reporting System: Summarize sales, inventory, and other important data
- Reselling Attempt Tracking: Record and track attempts to resell products

## Technologies Used
- Backend: Spring Boot 3.4.5 (Java 17)
- Database: PostgresSQL
- Docker
- Jasper Report
- [unsuccessful] Authentication: JWT (JSON Web Token)

## Prerequisites
- Java 17+
- Docker & Docker Compose
- PostgresSQL <- docker is open auto
- (Optional) JasperStudio for editing report templates


## Project Structure
```
sellWebAppAPI/
├── src/
│   ├── main/
│   │   ├── java/com/example/sellWebApp/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── enums/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   ├── util/
│   │   │   ├── validation/
│   │   │   └── SellWebApp
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── message.properties
│   │       ├── fonts/
│   │       └── reports/     # Jasper report templates
├── compose.yml              # from spring init
└── README.md
```
