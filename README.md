> Sample Spring Boot project that implements solutions for PUT and PATCH requests

#### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

The server runs on `http://localhost:8080`

#### PUT
The entire resource is replaced.

##### Example
```
// Existing product
{
  "id": 1,
  "name": "product1",
  "description": "description1"
}

// Request
PUT /products/1

// Request Body
{
  "name": "updatedProduct1"
}

// Result
{
  "id": 1,
  "name": "updatedProduct1",
  "description": null
}
```

The value of omitted fields will be set to null.

A `PUT` request does not modify the resource ID.

#### PATCH
The resource is partially updated.

##### Example
```
// Existing product
{
  "id": 1,
  "name": "product1",
  "description": "description1"
}

// Request
PATCH /products/1

// Request Body
{
  "name": "updatedProduct1"
}

// Result
{
  "id": 1,
  "name": "updatedProduct1",
  "description": "description1"
}
```

The value of omitted fields will not change.

A `PATCH` request does not modify the resource ID.
