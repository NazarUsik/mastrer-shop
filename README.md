# Personal Development Plan

## Technologies

- Java 8
- Spring:
    - Spring Boot
    - Spring JPA
    - Spring MVC
    - Spring REST
    - Spring Security
- Angular:
    - Bootstrap 4
    - HTML
    - CSS
    - JavaScript
- Gradle
- Oracle DB

## Task description

### The following entities are implemented in the project:

- Client
  - Users of this project (for example: administrator, seller, customer)
- Role
  - Permission for each user depending on their role
- Shop
- Product
- Photo
  - Use for avatar in user, product have photo, and shop have logo/banner
- ProductType
  - product categories
- Purchase
  - When buying a product, an order is created with all the details
- ReviewProduct
  - The user has the opportunity to write a comment on the purchased product
- Status
  - Order status (for example: paid, awaiting_payment, canceled)
  
#### Each entity described above has a REST controller that performs a standard CRUD
#### For Photo, when reading by ID or when reading by name Photo is returned to the image in jpg or png
#### When saving a Photo, we upload the file itself to Google Driveusing the Google Drive API and then just take it from the disk
