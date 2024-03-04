package Api_Endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Api_Payload.User;     //This package is imported for accessing POJO class.


public class UserEndpoints {

	public static Response createUser(User PayLoad) {
		
	Response response=given()
				.contentType("application/json")
				.accept("application/json")
				.body(PayLoad)
			.when()
				.post(Routes.post_URL);
	return response;
	}
	
	public static Response readUser(String userName) {
		
		Response response=given()
					.pathParam("username", userName)
				.when()
					.get(Routes.get_URL);
		return response;
	}
	public static Response updateUser(String userName, User PayLoad) {
		
		Response response=given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", userName)
					.body(PayLoad)
				.when()
					.put(Routes.put_URL);
		return response;
		}
	
	public static Response deleteUser(String userName) {
		
		Response response=given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.delete_URL);
		return response;
		}
	
}
