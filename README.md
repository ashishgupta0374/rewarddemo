### DB  : MySql
#### DB Script
```shell script
create database reward;

use reward;

CREATE TABLE Customer (EMAIL VARCHAR(255) NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(255) NOT NULL, LAST_NAME VARCHAR(255)) ;

+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| EMAIL      | varchar(255) | NO   | PRI | NULL    |       |
| FIRST_NAME | varchar(255) | NO   |     | NULL    |       |
| LAST_NAME  | varchar(255) | YES  |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+


CREATE TABLE Transaction (TRANSACTION_ID int AUTO_INCREMENT NOT NULL,
EMAIL VARCHAR(255), AMOUNT int, REWARD_POINT int, UPDATE_TS DATETIME,
PRIMARY KEY (TRANSACTION_ID),
CONSTRAINT FK_Cust FOREIGN KEY (EMAIL) REFERENCES Customer(EMAIL));

+----------------+--------------+------+-----+---------+----------------+
| Field          | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| TRANSACTION_ID | int          | NO   | PRI | NULL    | auto_increment |
| EMAIL          | varchar(255) | YES  | MUL | NULL    |                |
| AMOUNT         | int          | YES  |     | NULL    |                |
| REWARD_POINT   | int          | YES  |     | NULL    |                |
| UPDATE_TS      | datetime     | YES  |     | NULL    |                |
+----------------+--------------+------+-----+---------+----------------+

```

### Build/Run microservice
```shell script
mvn clean install
java  -jar ./target/rewarddemo-0.0.1-SNAPSHOT.jar
```

```shell script
API documention with Sample REST endpoint

```

#### Add/Update Customer
```shell script
 POST : http://localhost:8080/service/customer
 PUT : http://localhost:8080/service/customer
```
##### Sample Request
 ```json
{  
  "FIRST_NAME" : "Ashish",
  "LAST_NAME" : "Gupta",
  "EMAIL" : "test@gmail.com" 
}
```

##### Sample Response
 ```json
{
    "EMAIL": "test@gmail.com",
    "FIRST_NAME": "Ashish",
    "LAST_NAME": "Gupta",
    "TOTAL_REWARD": 0
}
```
#### Success Response
```json
{
    "status": "SUCCESS",
    "requestId": "4ae7455b-0565-4d68-a23d-238104b8fb7b",
    "meta": {
        "ServiceExecTime": 35
    },
    "result": {
        "EMAIL": "test@gmail.com",
        "FIRST_NAME": "Ashish",
        "LAST_NAME": "Gupta",
        "TOTAL_REWARD": 0
    }
}
```

#### Sample Failure Response
```json

{
    "status": "FAILURE",
    "requestId": "a779fa0d-2f95-4483-9c89-c234ef2b740d",
    "meta": {
        "ServiceExecTime": 101
    },
    "error": {
        "code": "C003",
        "message": "Customer email already present :  Invalid input : 'test@gmail.com'"
    }
}

```

#### Get Customer
```shell script
 GET : http://localhost:8080/service/customer/test@gmail.com
```

##### Sample Response
 ```json
{
    "status": "SUCCESS",
    "requestId": "972788a2-9adf-417f-9c2c-2a3e8795f6bd",
    "meta": {
        "ServiceExecTime": 107
    },
    "result": {
        "EMAIL": "test@gmail.com",
        "FIRST_NAME": "Ashish",
        "LAST_NAME": "Gupta"
    }
}

```
#### Add Transaction
```shell script
POST: http://localhost:8080/service/transaction
```
##### Sample  Request
 ```json
 {  
   "EMAIL" : "test@gmail.com",
   "AMOUNT" : 120
 }
```
##### Sample Success Response
```json
{
    "status": "SUCCESS",
    "requestId": "139a8c07-6c83-4c9e-ac91-9fa7b59613dc",
    "meta": {
        "ServiceExecTime": 43
    },
    "result": {
        "EMAIL": "test@gmail.com",
        "AMOUNT": 120,
        "REWARD_POINT": 90
    }
}

```

##### Sample  Request
 ```json
 {  
    "EMAIL" : "notfoundtest@gmail.com",
    "AMOUNT" : 120
  }
```
##### Sample Failure Response
```json
{
    "status": "FAILURE",
    "requestId": "4910a37e-ae81-4cca-b953-71acd40c7edc",
    "error": {
        "code": "T002",
        "message": "Customer not found with email : 'notfoundtest@gmail.com'"
    }
}


```

### Get Customer Transaction  for month/day
```shell script
POST: http://localhost:8080/service/customer/reward
```
##### Sample Request
 ```json
{  
  "EMAIL" : "test@gmail.com",
  "MONTHS" : 3
}
```


##### Sample Success Response
```json
{
    "status": "SUCCESS",
    "requestId": "5000a1ce-da4b-4d67-9dc6-c0c00c786dbe",
    "meta": {
        "ServiceExecTime": 9
    },
    "result": {
        "EMAIL": "test@gmail.com",
        "FIRST_NAME": "Ashish",
        "LAST_NAME": "Gupta",
        "TRANSACTIONS": [
            {
                "AMOUNT": 240,
                "REWARD_POINT": 330,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:13"
            },
            {
                "AMOUNT": 120,
                "REWARD_POINT": 90,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:06"
            },
            {
                "AMOUNT": 234,
                "REWARD_POINT": 318,
                "TRANSACTION_DATE": "2020-May-06 11:37:52"
            },
            {
                "AMOUNT": 100,
                "REWARD_POINT": 50,
                "TRANSACTION_DATE": "2020-Jul-04 11:51:04"
            },
            {
                "AMOUNT": 56,
                "REWARD_POINT": 50,
                "TRANSACTION_DATE": "2020-May-06 11:37:55"
            },
            {
                "AMOUNT": 120,
                "REWARD_POINT": 90,
                "TRANSACTION_DATE": "2020-Jul-06 14:10:28"
            },
            {
                "AMOUNT": 25,
                "REWARD_POINT": 0,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:55"
            },
            {
                "AMOUNT": 56,
                "REWARD_POINT": 50,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:23"
            }
        ],
        "TOTAL_REWARD": 978
    }
}


```

#### Sample Sucess Request
```json
{  
  "EMAIL" : "test@gmail.com",
  "DAYS" : 3
}

```

##### Sample Success Response
```json
{
    "status": "SUCCESS",
    "requestId": "dfec066c-e32c-465d-b48f-be1c5986c9a4",
    "meta": {
        "ServiceExecTime": 316
    },
    "result": {
        "EMAIL": "test@gmail.com",
        "FIRST_NAME": "Ashish",
        "LAST_NAME": "Gupta",
        "TRANSACTIONS": [
            {
                "AMOUNT": 240,
                "REWARD_POINT": 330,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:13"
            },
            {
                "AMOUNT": 120,
                "REWARD_POINT": 90,
                "TRANSACTION_DATE": "2020-Jul-06 14:10:28"
            },
            {
                "AMOUNT": 120,
                "REWARD_POINT": 90,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:06"
            },
            {
                "AMOUNT": 100,
                "REWARD_POINT": 50,
                "TRANSACTION_DATE": "2020-Jul-04 11:51:04"
            },
            {
                "AMOUNT": 56,
                "REWARD_POINT": 50,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:23"
            },
            {
                "AMOUNT": 25,
                "REWARD_POINT": 0,
                "TRANSACTION_DATE": "2020-Jul-06 10:47:55"
            }
        ],
        "TOTAL_REWARD": 610
    }
}

```

#### Sample Failure Request/Response

```json
{  
  "EMAIL" : "notfound@gmail.com",
  "MONTHS" : 3
}

```

```json
{
    "status": "FAILURE",
    "requestId": "039626b3-f7bd-4eba-ab3e-b5e026df3691",
    "error": {
        "code": "C004",
        "message": "Customer not found with email : 'notfound@gmail.com'"
    }
}


```

