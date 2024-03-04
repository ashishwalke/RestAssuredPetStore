package Api_Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Api_Endpoints.UserEndpoints;
import Api_Payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;   //for logs

	@BeforeClass
	public void setup() {    //this method used for generate data and logging 
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// Logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.debug("This is a debug message");
		logger.info("*********Creating User*********");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		
		//logger.info("*********User Created*********");
	}

	@Test(priority = 2)
	public void testGetUser() {
		logger.debug("This is a debug message");
		logger.info("*********Reading User info*********");
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User info is displayed*********");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		logger.debug("This is a debug message");
		logger.info("*********Updating User*********");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));

		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("*********User is Updated*********");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		logger.debug("This is a debug message");
		logger.info("*********Deleting User*********");

		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("*********User Deleted*********");
	}
}

