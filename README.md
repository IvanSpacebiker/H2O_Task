# PC Store App

The `ProductController` is a REST controller that manages the `Product` entity in the system. It provides endpoints for retrieving, adding, and updating products. The controller is mapped to the `/products` base URL.

## How to Run

### Dependencies
- Docker and docker-compose

### Start App

1. Open terminal in project directory (path should be `.../H2O_Task`)  
2. Run `docker-compose up`  

After you see: 
```
INFO 21348 --- [app] [main] com.kzkv.app.AppApplication: Started AppApplication in *** seconds (process running for ***)
```

App is ready to get requests on `http://localhost:8080`

## Endpoints

### 1. Get Product by ID

**URL:** `/products/{id}`  
**Method:** `GET`  
**Description:** Retrieves a product by its unique ID.

**Path Variables:**
- `id` (UUID): The unique identifier of the product.

**Response:**
- `200 OK`: Returns the `Product` if found.
- `404 Not Found`: If the product with the specified ID does not exist.

**Example Request:**
```bash
GET /products/6e4f7a8b-9c1d-2e3f-4a5b-7d8e9f1a2b3c
```

### 2. Get All Products

**URL:** `/products/`  
**Method:** `GET`  
**Description:** Retrieves all products in the system.

**Response:**
- `200 OK`: Returns a list of all Product entities.

**Example Request:**
```bash
GET /products
```

### 3. Get All Products by Category ID

**URL:** `/products/category{categoryId}`  
**Method:** `GET`  
**Description:** Retrieves all products that belong to a specific category.

**Path Variables:**
- `categoryId` (Long): The ID of the category.

**Response:**
- `200 OK`: Returns a list of `Product` entities that belong to the specified category.

**Example Request:**
```bash
GET /products/category1
```

### 4. Add a New Product

**URL:** `/products`  
**Method:** `POST`  
**Description:** Adds a new product to the system.

**Request Body:**
- `Product` (JSON): The product data to be added.

**Response:**
- `200 OK`: Returns the newly created `Product`.

**Example Request:**
```bash
POST /products
Content-Type: application/json

{
    "serialNumber": "SN12345",
    "manufacturer": "Brand",
    "price": 1500.0,
    "stockQuantity": 10,
    "category": {
        "id": 1
    },
    "productCategoryFields": [
        {
            "categoryField": {
                "id": 1
            },
            "value": "desktop"
        }
    ]
}
```

### 5. Update an Existing Product

**URL:** `/products/{id}`  
**Method:** `PUT`  
**Description:** Updates an existing product.

**Path Variables:**
- `id` (UUID): The unique identifier of the product.

**Request Body:**
- `Product` (JSON): The updated product data.

**Response:**
- `200 OK`: Returns the updated `Product`.
- `404 Not Found`: If the product with the specified ID does not exist.

**Example Request:**
```bash
PUT /products/6e4f7a8b-9c1d-2e3f-4a5b-7d8e9f1a2b3c
Content-Type: application/json

{
    "serialNumber": "SN12345",
    "manufacturer": "Brand",
    "price": 1500.0,
    "stockQuantity": 10,
    "category": {
        "id": 1
    },
    "productCategoryFields": [
        {
            "categoryField": {
                "id": 1
            },
            "value": "desktop"
        }
    ]
}
```

