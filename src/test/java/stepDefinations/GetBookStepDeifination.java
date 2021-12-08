package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.StringContains;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import pojo.Books;
import utilities.RestAssuredExtention;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.MatcherAssert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class GetBookStepDeifination {


	public static ResponseOptions<Response> response;
	public static Response res;
	public static String accessToken="003e1f0538e586d7ed1787af939000ac5c96d666c4b5a9cc898fcca5efbb4692";
	public static String orderId;

	@Given("I perform Get operation for {string}")
	public void i_perform_get_operation_for(String uri) {
		RestAssuredExtention.buildRequest();
		response=RestAssuredExtention.httpGetCallWithoutParameters(uri);

	}

	@Then("I should see status value as {string}")
	public void i_should_see_status_value_as(String status) {

		System.out.println("\n"+response.getBody().asPrettyString());
		assertThat("Status Check",response.getBody().jsonPath().get("status").toString(), is("OK"));
	}



	@Given("I perform Get operation for {string} with path parameter {string}")
	public void i_perform_get_operation_for_with_path_parameter(String uri, String value ) {
		RestAssuredExtention.buildRequest();
		Map<String, String> pathParam =new HashMap<String, String>();
		pathParam.put("bookId", value);
		response=RestAssuredExtention.httpGetCallWithPathParameters(uri,pathParam);
		
	}

	@Then("I should see author name in response")
	public void i_should_see_author_name_in_response() {
		
	    String responseBody=response.getBody().asString();
	    System.out.println(responseBody);
	    assertThat(responseBody ,matchesJsonSchemaInClasspath("getBook.json"));
	    
	    


		assertThat("Verifying author name for bookId", response.getBody().jsonPath().get("author").toString(), is("James Patterson and James O. Born"));
		System.out.println("\n"+response.getBody().asPrettyString());


	}


	@Given("I perform Get operation for {string} with Query parameters below")
	public void i_perform_get_operation_for_with_query_parameters_below(String string, io.cucumber.datatable.DataTable dataTable) {
		RestAssuredExtention.buildRequest();
		Map<String, String> queryParam =new HashMap<String, String>();
		queryParam.put(dataTable.cell(1, 0), dataTable.cell(1, 1));
		response=RestAssuredExtention.httpGetCallWithQueryParameters(queryParam);
	}


	@Then("I should see all the books having type as fiction")
	public void i_should_see_all_the_books_having_type_as_fiction() {
		System.out.println("\n"+response.getBody().asPrettyString());
		String responseBody=response.getBody().asString();
	    System.out.println(responseBody);
	    assertThat(responseBody ,matchesJsonSchemaInClasspath("getBooks.json"));


	}
	
	
	@Given("I perform GET Opertaion using endpoint {string}")
	public void i_perform_get_opertaion_using_endpoint(String uri) {
	    RestAssuredExtention.buildRequest();
	    response=RestAssuredExtention.httpGetCallWithoutParameters(uri);
	}

	@Then("I should be able to get type of book name {string}")
	public void i_should_be_able_to_get_of_book_name( String name) {
	    System.out.println(response.getStatusCode()+ "\t"+ response.getStatusLine());
	    System.out.println(response.getBody().asPrettyString());
	    Books [] books=response.getBody().as(Books[].class);
	    for (Books book : books) {
			if(book.getName().equals(name)) {
				System.out.println(book.getType());
				break;
			}
		}
	    
	}



	/*
	 * @Given("I performed authentication using post operation for {string} with body"
	 * ) public void
	 * i_performed_authentication_using_post_operation_for_with_body(String uri,
	 * io.cucumber.datatable.DataTable dataTable) {
	 * 
	 * 
	 * List<Map<String, String> > data=dataTable.asMaps(); System.out.println(data);
	 * System.out.println(data.get(0).get("clientName"));
	 * 
	 * HashMap<String,String> body=new HashMap<String, String>();
	 * body.putAll(data.get(0));
	 * response=RestAssuredExtention.httpPostCallWithBodyToGetToken(uri, body);
	 *   }
	 * 
	 * @Then("I should see the accessToken") public void
	 * i_should_see_the_access_token() { System.out.println(response.statusCode());
	 * System.out.println(response.getBody().asPrettyString());
	 * accessToken=response.getBody().jsonPath().getString("accessToken");
	 * System.out.println(response.statusCode()); System.out.println(accessToken);
	 * 
	 * }
	 */

	@Given("I Perform get Opertaion for {string} using bearer token")
	public void i_perform_get_opertaion_for_using_bearer_token(String uri) {
		RestAssuredExtention.buildRequest();
		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);
		res=RestAssuredExtention.httpGetCallWithBearerToken(uri, token);  

	}



	@Then("dispay all the orders details")
	public void dispay_all_the_orders_details() {
		System.out.println(res.statusCode() + res.statusLine());
		
		String responseBody=res.getBody().asString();
	    System.out.println(responseBody);
	    assertThat(responseBody ,matchesJsonSchemaInClasspath("getOrders.json"));

	}

	@Given("I Perform POST Opertaion for {string} using authentication and below details")
	public void i_perform_post_opertaion_for_using_authentication_and_below_details(String uri, io.cucumber.datatable.DataTable dataTable) {
		RestAssuredExtention.buildRequest();

		List<Map<String,String>> data=dataTable.asMaps();

		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);

		HashMap<String, String> payload=new HashMap<String, String>();
		payload.putAll(data.get(0));

		response=RestAssuredExtention.httpPostCallWithPayload(uri,token,payload);
	}

	@Then("verify that order id is generated")
	public void verify_that_order_id_is_generated() {
		System.out.println(res.statusCode() + res.statusLine());
		System.out.println("\n"+response.getBody().asPrettyString());
	}


	@Given("I perform POST Opertaion using endpoint {string} using authentication and below details")
	public void i_perform_post_opertaion_using_endpoint_using_authentication_and_below_details(String uri, io.cucumber.datatable.DataTable dataTable) {

		RestAssuredExtention.buildRequest();
		//Bearer Token setup in headers
		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);

		//Iterate over dataTable for order details

		List<Map<String,String>> rows =dataTable.asMaps();
		System.out.println(rows);

		HashMap<String, String> payload=new HashMap<String, String>(); 	

		for(int i=0;i<rows.size();i++) {
			payload.putAll(rows.get(i));
			response=RestAssuredExtention.httpPostCallWithPayload(uri, token, payload );

		} 
		orderId=response.getBody().jsonPath().getString("orderId");	
		System.out.println(response.getBody().asPrettyString());
		assertThat("\n\nStatus code ",response.getStatusCode() ,is(201));


	}

	@Given("Perform DELETE operation using endpoint {string}")
	public void perform_delete_operation_using_endpoint(String uri) {

		RestAssuredExtention.buildRequest();

		//Bearer Token setup in headers
		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);

		Map<String, String> pathParam =new HashMap<String, String>();
		pathParam.put("orderId", orderId);

		response=RestAssuredExtention.httpDeleteCallWithBearerToken(uri,token,pathParam);

		System.out.println(response.getStatusCode()+" "+ response.statusLine());
		assertThat("Status code ",response.getStatusCode() ,is(204));

	}

	@Given("Perform GET operation using endpoint {string}")
	public void perform_get_operation_using_endpoint(String uri) {

		RestAssuredExtention.buildRequest();

		//Bearer Token setup in headers
		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);

		Map<String, String> pathParam =new HashMap<String, String>();
		pathParam.put("orderId", orderId);


		response=RestAssuredExtention.httpGetCallWithBearerToken(uri,token, pathParam);
		System.out.println("Status code "+response.getStatusCode());
		//	assertThat("Status code ",response.getStatusCode() ,is(404));

	}

	@Then("I should not see order info for customer {string}")
	public void i_should_not_see_order_info_for_customer(String string) {
		System.out.println("Error : "+response.getBody().jsonPath().getString("error"));
		assertThat("Status code ",response.getBody().jsonPath().getString("error") ,StringContains.containsStringIgnoringCase("No order with id"));
	}


	@Given("Perform PATCH operation using endpoint {string} to update customer name")
	public void perform_patch_operation_using_endpoint_to_update_customer_name(String uri, io.cucumber.datatable.DataTable dataTable) {

		RestAssuredExtention.buildRequest();

		//List<Map<String,String>> data=dataTable.asMaps();

		HashMap<String,String> token=new HashMap<String, String>();
		token.put("Authorization", "Bearer "+accessToken);

		HashMap<String, String> payload=new HashMap<String, String>();
		payload.put(dataTable.cell(0, 0),dataTable.cell(1, 0));

		HashMap<String, String> pathParam =new HashMap<String, String>();
		pathParam.put("orderId", orderId);

		System.out.println(payload);
		response=RestAssuredExtention.httpPatchCallWithPayload(uri, token, pathParam , payload );
		System.out.println(response.statusCode());

	}

	@Then("I should be able see order info for with customer {string} and boookId {string}")
	public void i_should_be_able_see_order_info_for_with_customer_and_boook_id(String customerName, String bookId) {
		System.out.println("Status Code"+response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		//assertThat("Verify customer info for "+ orderId +" - ",response.getBody().jsonPath().getString("customerName") ,is(customerName));
		assertThat("Verify ordered book id", response.getBody().jsonPath().getString("bookId"),is(bookId));


	}




}
