package Api_Tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Api_Endpoints.UserEndpoints2;
import Api_Payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass 
	public void setUpData() {
	    faker = new Faker();
	    userPayload = new User();
	    
	    userPayload.setId(faker.idNumber().hashCode());
	    userPayload.setUsername(faker.name().username());
	    userPayload.setFirstName(faker.name().firstName());
	    userPayload.setLastName(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	    userPayload.setPassword(faker.internet().password(5, 10));
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
	    
	    //Logs
	    logger=LogManager.getLogger(this.getClass());
	    
	    	
	
	}

	
	@Test(priority=1)
	public void testPostUser(){
		 logger.debug("This is a debug message");
		logger.info("*********Creating User*********");
		Response response=UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Created*********");
	}	

	@Test(priority=2)
	public void testGetUser(){
		 logger.debug("This is a debug message");
		logger.info("*********Reading User*********");
		Response response=UserEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********Userinfo is displayed*********");
	}	
	
	@Test(priority=3)
	public void testUpdateUser(){
		 logger.debug("This is a debug message");
		logger.info("*********Updating User*********");
		
		userPayload.setFirstName(faker.name().firstName());
	    userPayload.setLastName(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	    userPayload.setPassword(faker.internet().password(5, 10));
	    
		Response response=UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********User is Updated*********");
	}

	
	@Test(priority=4)
	public void testDeleteUser(){
		 logger.debug("This is a debug message");
		logger.info("*********Deleting User*********");
		
		Response response=UserEndpoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********User Deleted*********");
	}	
}
