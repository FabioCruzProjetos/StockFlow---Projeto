# StockFlow — Inventory Management System

StockFlow is a desktop application for inventory management, designed to simplify product management, stock entries and exits, and inventory movement tracking.

This project is being developed as a personal portfolio project to strengthen my knowledge of object-oriented programming, graphical user interface development, relational databases, and Java–MySQL integration.

## Project Goals

- Apply Java programming knowledge to a practical project.
- Develop a well-structured and maintainable application.
- Create an intuitive graphical user interface.
- Learn how to integrate a Java application with a MySQL database.
- Practise software testing, data validation, error handling, and version control using Git.
- Build a portfolio project to showcase in future applications for software development internships.

## Technologies

| Technology | Purpose |
|---|---|
| Java | Application logic and business rules |
| JavaFX | Graphical user interface |
| MySQL | Persistent data storage |
| JDBC | Database connectivity |
| Git and GitHub | Version control and project management |
| JUnit 4 | Automated unit testing |

*Technologies and tools will be integrated progressively throughout development.*

## Planned Features

### 1. Product Management

- Create products with an automatically generated unique ID, name, supplier, description, price, current stock, and minimum stock level.
- View and list products.
- Edit product information.
- Search for products by ID or name.
- Set minimum stock levels.
- Deactivate products without deleting their movement history.
- Validate input data and prevent duplicate product IDs.

### 2. Stock Control

- Register stock entries.
- Register stock exits.
- Check the available quantity of each product.
- Prevent stock exits that exceed available inventory.
- Identify products below their minimum stock level.
- View the stock movement history for each product.
- Record the date, movement type, quantity, and an optional note for each movement.

### 3. Search, Filtering, and Sorting

- Search by product ID or name.
- Filter active and inactive products.
- Filter products with low stock.
- Sort products by name, price, or quantity.
- Display messages when no results are found.

### 4. Statistics and Reports

- View the number of active products.
- Identify products below their minimum stock level.
- Calculate the total value of the inventory.
- View recent stock movements.
- Export the product list to a CSV file.

### 5. Graphical User Interface

- Display products in a table.
- Provide forms to add and edit products.
- Allow users to register stock entries and exits.
- Display confirmation and error messages.
- Refresh the interface after each operation.

### 6. Data Persistence

- Store products in a MySQL database.
- Store and retrieve stock movement history.
- Preserve data when the application is closed and reopened.
- Use primary keys, foreign keys, and unique constraints.
- Use parameterised queries through `PreparedStatement`.

## Planned Architecture

The application will follow a layered architecture, separating the graphical interface, business logic, and data access.

```text
StockFlow/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   └── resources/
│   └── test/
│       └── java/
├── database/
│   └── schema.sql
├── screenshots/
├── .gitignore
└── README.md
```

This is an indicative structure. The final organisation may change during development.

**Main responsibilities:**

- **Model:** represents products and stock movements.
- **Service:** implements business rules and validation.
- **Repository/DAO:** manages database access.
- **View/Controller:** displays the interface and handles user interactions.

## Business Rules

The application must enforce the following rules:

1. Each product must have a unique ID.
2. Prices and stock quantities cannot be negative.
3. The quantity recorded in a stock movement must be greater than zero.
4. A stock exit cannot exceed the available quantity.
5. Every stock entry or exit must update the current stock and be recorded in the movement history.
6. Products with movement history should be deactivated rather than permanently deleted.
7. Database errors must be handled without compromising data consistency.

## Development Roadmap

The project will be developed in stages, allowing each technology to be learned and applied before moving on to the next.

- [ ] **Phase 1 — Java:** implement the data models, business rules, and stock operations using in-memory data.
  - [x] Product model and validation.
  - [x] Product management.
  - [x] Stock movement model.
  - [x] Stock entry and exit operations.
  - [x] Custom exception handling.
  - [x] Unit tests for core classes using JUnit 4.
  - [x] JavaDoc documentation for main classes.
  - [ ] Console-based interface.

- [ ] **Phase 2 — JavaFX:** build the graphical interface and connect it to the existing application logic.
- [ ] **Phase 3 — MySQL and JDBC:** create the database and implement data persistence.
- [ ] **Phase 4 — Testing:** expand test coverage, fix bugs, and test edge cases.
- [ ] **Phase 5 — Finalisation:** complete reports, improve documentation, and prepare a project demonstration.

## Testing and Code Quality

Testing is used throughout development to verify the application's functionality and reliability.

Current unit tests cover:

- The creation of valid products and rejection of invalid product data.
- Price and stock quantity validation.
- Product management operations.
- Correct stock entry and exit operations.
- Prevention of negative stock.
- Stock movement history.
- Handling of invalid product IDs and quantities.

Further tests will be added as new features are implemented, including database persistence and graphical interface interactions.

## Installation and Setup

Detailed installation and execution instructions will be added once the project structure is complete.

The final setup will include:

1. Installing a compatible version of Java.
2. Configuring JavaFX.
3. Installing and configuring MySQL.
4. Creating the database using the SQL script.
5. Configuring the JDBC connection.
6. Running the application from an IDE or the chosen build system.

Database credentials must not be committed to a public repository.

## Initial Scope

The first version will focus on product management and stock control.

Features such as supplier management, invoicing, sales management, multiple warehouses, user authentication, and synchronisation across computers are outside the initial scope. They may be considered for future versions.

## Project Status

**Phase 1 — Java: In progress.**

The core business logic has been implemented, including:

- Product management and validation.
- Stock entry and exit operations.
- Stock movement history.
- Custom exception handling.
- Unit tests using JUnit 4.
- JavaDoc documentation for the main classes.

The next step is to complete the documentation and develop a console-based interface before moving on to JavaFX.

This README will be updated as new features are implemented and tested.

## Author

Personal project developed to practise and strengthen software development skills.
