package utilities;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtention {

	public static RequestSpecification request;

	static {
		baseURI="https://simple-books-api.glitch.me";
	}

	public RestAssuredExtention() {		

		RestAssuredExtention.request=null;
	}

	public static RequestSpecification buildRequest() {

		RequestSpecBuilder reqSpecBuilder =new RequestSpecBuilder();
		reqSpecBuilder.setContentType(ContentType.JSON);
		RequestSpecification reqSpec=reqSpecBuilder.build();
		request=given().spec(reqSpec);
		return request;

	}
	public static ResponseOptions<Response> httpGetCallWithoutParameters(String uri) {
		return request.log().all().get(uri);				
	}


	public static ResponseOptions<Response> httpGetCallWithPathParameters(String uri, Map<String, String> pathParams) {

		request.pathParams(pathParams);
		request.log().all();
		return request.get(uri);

	}

	public static ResponseOptions<Response> httpGetCallWithQueryParameters(Map<String, String> QueryParams) {
		request.queryParams(QueryParams);
		request.log().all();
		return request.get("/books");				
	}

	public static ResponseOptions<Response> httpPostCallWithBodyToGetToken(String uri,Map<String, String> body) {

		request.body(body);
		request.log().all();
		return request.post(uri);				
	}

	public static Response httpGetCallWithBearerToken(String uri, Map<String,String> accessToken)
	{
		request.headers(accessToken);
		request.log().all();
		return request.get(uri);

	}

	public static Response httpGetCallWithBearerToken(String uri, Map<String,String> accessToken , Map<String,String> pathParam )
	{
		request.headers(accessToken);
		request.pathParams(pathParam);
		request.log().all();
		return request.get(uri);

	}

	public static Response httpDeleteCallWithBearerToken(String uri, Map<String,String> accessToken , Map<String,String> pathParam )
	{
		request.headers(accessToken);
		request.pathParams(pathParam);
		request.log().all();
		return request.delete(uri);

	}

	public static ResponseOptions<Response> httpPostCallWithPayload(String uri, HashMap<String, String> token, HashMap<String, String> orderDetails)
	{

		request.log().all();
		request.headers(token);		
		request.body(orderDetails);
		return request.post(uri);		
	}

	public static ResponseOptions<Response> httpDeleteCallWithPathParameter(String uri,String orderid)
	{
		request.pathParam("orderId",orderid);		
		return request.delete(uri);
	}

	public static ResponseOptions<Response> httpPatchCallWithPayload(String uri, HashMap<String, String> token, HashMap<String, String> pathparam,HashMap<String, String> orderDetails)
	{

		request.log().all();
		request.headers(token);		
		request.pathParams(pathparam);
		request.body(orderDetails);
		return request.patch(uri);		
	}


}
