# retail-store
**Swagger Docs:**
http://localhost:8081/swagger-ui/index.html

**System Prerequisites:**
- Docker Engine
- Maven

**Technology Stack:**
- Spring Boot 3
- Mongo DB
- Drools (Rules Engine)
- Spring Doc & Swagger
- Junit & Mockito
- Maven

Application Assumptions/Notes:
- to implement desired business rule , I added 4 type of users:
  - employee
  - affiliate
  - guest
  - another_type
- view init-mongo.js file for further details
- Mongo DB already preloaded with 4 users of these types as below:
  - Employee user to get 30% discount --> userId: 1
  - Affiliate user to get 10% discount --> userId: 2
  - Guest user with creation date less than 2 yesrs so no percentage allowed to him --> userId: 3
  - Another_Type user with creation date greater than 2 years so he gets 5% discount --> userId: 4
- Flat Rate discount for every 100 is calculated after Percentage discount (if any).
- User can get only one percentage discount


**APIs:**
- 2 Sets of APIs available
  - Bill APIs
    - GET: Lists all bills in DB , initially no bills until you submit bill.
    - POST: Submit new bill where it calculates the discount and save Bill to DB:
    - **Request:**
      {
      "id": "2",
      "code": "bill_code",
      "userId": "1",
      "date": "2024-11-30T19:37:33.196Z",
      "items": [
      {
      "itemId": "item1",
      "name": "item1",
      "quantity": 1,
      "price": 50,
      "grocery": true
      },
      {
      "itemId": "item2",
      "name": "item2",
      "quantity": 1,
      "price": 50,
      "grocery": false
      }
      ]
      } 
    
    - **Response:**
      {
      "id": "2",
      "code": "bill_code",
      "userId": "1",
      "date": "2024-11-30T19:37:33.196+00:00",
      "items": [
      {
      "itemId": "item1",
      "name": "item1",
      "quantity": 1,
      "price": 50,
      "grocery": true
      },
      {
      "itemId": "item2",
      "name": "item2",
      "quantity": 1,
      "price": 50,
      "grocery": false
      }
      ],
      **"percentageDiscount": 30,
      "percentageDiscountValue": 15,
      "flatRateDiscountValue": 0,
      "totalDiscount": 15,
      "totalAmount": 100,
      "totalAmountNonGrocery": 50,
      "totalAmountAfterDiscount": 85**
      }
  - Users API:
    - GET: Return all users in DB , initially the 4 users preloaded in DB.
    - POST: Save new user in DB.

**Deployment/Run Steps:**
- Checkout code. 
- cd to local code directory 
- Run "docker compose up"

**To Run Test Cases:**
- mvn test 
- mvn clean jacoco:prepare-agent test jacoco:report

      
