# Sample Request and Response Format

### Login
POST - 
	
	http://localhost:8080/perfect/rest/user/authenticate
Request - 

	{
		"emailAddress":"admin@almightyjava.com",
		"password":"123456"
	}
Response -

	{
    "name": "admin@almightyjava.com",
    "authorities": [
        "ADMIN",
        "USER"
    ],
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB3aGFkYXRpbWUuY29tIiwiYXV0aCI6IkFETUlOIiwiaWF0IjoxNjI4ODYwOTczLCJleHAiOjE2Mjg4NzE3NzN9.6QUjv-MPhiyLN_2ceERraNMIOb3wRRCbPwNoMxqnF8M"
	}
### Employee
POST -

	http://localhost:8080/perfect/rest/employee
Request - Authentication token should be a part of header
	
	{
		"employeeId":"WT00009",
		"emailAddress":"jeet@almightyjava.com",
		"firstName":"Jeet",
		"middleName":"Singh",
		"lastName":"Parmar"
	}
Response -
	
	{
	    "employeeUuid": "8deaf45b-4889-41f8-a5c2-4348219995df",
	    "employeeId": "WT00009",
	    "emailAddress": "jeet@almightyjava.com",
	    "firstName": "Jeet",
	    "middleName": "Singh",
	    "lastName": "Parmar"
	}
GET - Authentication token should be a part of header

	http://localhost:8080/perfect/rest/employee
Response -

	[
	    {
	        "employeeUuid": "b4c7d644-0aa7-4164-b096-183966c95ec4",
	        "employeeId": "WT00001",
	        "emailAddress": "admin@almightyjava.com",
	        "firstName": "system",
	        "middleName": null,
	        "lastName": "admin"
	    },
	    {
	        "employeeUuid": "8deaf45b-4889-41f8-a5c2-4348219995df",
	        "employeeId": "WT00009",
	        "emailAddress": "jeet@almightyjava.com",
	        "firstName": "Jeet",
	        "middleName": "Singh",
	        "lastName": "Parmar"
	    }
	]
### User
GET - 

	http://localhost:8080/perfect/rest/user/
	
Response -

	[
	    {
	        "userUuid": "d5cad9a0-cc4c-4581-a296-25b10a13c74b",
	        "emailAddress": "admin@almightyjava.com",
	        "password": "$2a$10$Po76.Ip0dGVRcJMLMoiT2.N54CvD7DBiE8dosKzCOMyfK5iF0pY2O"
	    },
	    {
	        "userUuid": "51aafb85-5e75-4c50-ae47-b064edfd8429",
	        "emailAddress": "jeet@almightyjava.com",
	        "password": "$2a$10$hysZ0Ov68OUsAfG0jFRSpeuxBF6L4MF5QbBSx2xEl8XeIDhehw6OC"
	    }
	]