package bookApiCollection;
import static io.restassured.RestAssured.*;

import java.net.http.HttpResponse.BodyHandler;

public class GetBookAPIStatus {
	
	public static void main(String[] args) {
		
		baseURI="https://simple-books-api.glitch.me";
		
		
		/*
		 * given().log().all().when().get("/status").then().log().all().assertThat().
		 * statusCode(200);
		 * 
		 * given().queryParam("type",
		 * "fiction").log().all().when().get("/books").then().log().all().assertThat().
		 * statusCode(200);
		 * 
		 * given().log().all().pathParam("bookId",
		 * 1).when().get("/books/{bookId}").then().log().all().assertThat().statusCode(
		 * 200);
		 */
		given().header("Content-Type", "Application/json");
		if(given().header("Content-Type", "Application/json")!=null) {
			System.out.println("Inside If");
		}
		System.out.println(given().head().getHeader("Content-Type"));
		System.out.println("Hello");
	}

}
