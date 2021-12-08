Feature: Verify all the get operations for book API using RestAssured

Scenario: Verify status value for book API
	Given I perform Get operation for "/status"
	Then I should see status value as "OK"
	
Scenario: Verify author of the book using get Request
	Given I perform Get operation for "/books/{bookId}" with path parameter "1"
	Then  I should see author name in response

Scenario: Filter all the books having type as fiction
	Given I perform Get operation for "/books" with Query parameters below
				|QueryParameter | value  |
				|type           | fiction|

	Then  I should see all the books having type as fiction
	
	
#Scenario: Verify Post opertaion with bearer authentication token
# Given I performed authentication using post operation for "/api-clients" with body
#				|clientName|clientEmail      |
#				|Roshan    |Roshan@example.com|
# Then I should see the accessToken 
 
Scenario: Verify Get Operation to get orders resources using bearer token
	Given I Perform get Opertaion for "/orders" using bearer token
	Then  dispay all the orders details
	
Scenario: Verify POST Operation to generate order using bearer token authentication
	Given I Perform POST Opertaion for "/orders" using authentication and below details
					|bookId    |customerName   |
					|1   			 |James Anderson |
	Then verify that order id is generated


Scenario: Verify delete order endpoint for book API after creating order
	Given I perform POST Opertaion using endpoint "/orders" using authentication and below details
					|bookId    |customerName   |
					|5  			 |Johny donjopa  |
	And   Perform DELETE operation using endpoint "/orders/{orderId}"
	And   Perform GET operation using endpoint "/orders/{orderId}"
	Then I should not see order info for customer "No order with id"
	
	
Scenario: Verify delete order endpoint for book API after creating order
	Given I perform POST Opertaion using endpoint "/orders" using authentication and below details
					|bookId    |customerName   |
					|6      	 |Johny donjopa  |
	And   Perform PATCH operation using endpoint "/orders/{orderId}" to update customer name
					|customer name|   
					|John Cena    |	
	And   Perform GET operation using endpoint "/orders/{orderId}"
	Then I should be able see order info for with customer "John Cena" and boookId "6"
	
Scenario: Get all the books available
		Given  I perform GET Opertaion using endpoint "/books"
		Then I should be able to get type of book name "Just as I Am"
	
	

	




