package Api_Endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import Api_Payload.User; //This package is imported for accessing POJO class.

 //UserEndpoints.java
//Created for perform Create, Read, Update, Delete requests to the user API.

public class UserEndpoints2 {
	
	//Method created for getting URL's from properties file 
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load properties file //Name of the properties file
		return routes;
	}
	

	public static Response createUser(User PayLoad) {
	String post_url=getURL().getString("post_URL");  // This is statement which will give the actual URL from .Properties file
	Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(PayLoad)
			.when()
				.post(post_url);
	return response;
	}
	
	public static Response readUser(String userName) {
		
		Response response=given()
					.pathParam("username", userName)
				.when()
					.get(getURL().getString("get_URL"));
		return response;
		}
	
	public static Response updateUser(String userName, User PayLoad) {
		
		Response response=given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", userName)
					.body(PayLoad)
				.when()
					.put(getURL().getString("put_URL"));
		return response;
		}
	
	public static Response deleteUser(String userName) {
		
		Response response=given()
					.pathParam("username", userName)
				.when()
					.delete(getURL().getString("delete_URL"));
		return response;
		}
	
}
