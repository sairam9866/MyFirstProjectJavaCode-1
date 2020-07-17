package com.fedex.ZDServer.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static com.fedex.ZDServer.utils.ConfigFileReader.ZipToDestAPIProp;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fedex.ZDServer.ServiceValidation.AddAPIDetails;
import com.fedex.ZDServer.ServiceValidation.DeleteAPIDetails;
import com.fedex.ZDServer.ServiceValidation.ModifyAPIDetails;
import com.fedex.ZDServer.ServiceValidation.Resources;
import com.fedex.ZDServer.cucumber.SpringBootBaseIT;

//@Ignore
public class ZipToDestAPIStepDefinition extends SpringBootBaseIT{

	
	Response response;
	ValidatableResponse json;
	int zipcode, zipcodeModify;
	
	public static Logger log = LoggerFactory.getLogger(ZipToDestAPIStepDefinition.class.getName()); 
	
    @Given("^The base URI is up and running$")
    public void the_base_URI_is_up_and_running() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("DatagridAPI.lcl.get");
    	log.info("Base URL is up and Running: " + ZipToDestAPIProp.getProperty("DatagridAPI.lcl.get"));
    }
    
    @Then("^User should see the status code (\\d+)$")
	public void user_should_see_the_status_code(int statuscode) {
   	
		json = response.then().assertThat().statusCode(statuscode);
		json.log().all().extract();
				
	}
    
    @When("^User verifies \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" for first json \"([^\"]*)\"$")
    public void user_verifies_json(String network, String countryCode,
    		String zipcode, String state, String destination, String creation_user, int row) {
        
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	String network_json = response.jsonPath().getString("network["+row+"]");
    	System.out.println("Network  is: "+network_json);
    	assertEquals(network, network_json);
    	
    	String country_json = response.jsonPath().getString("countryCode["+row+"]");
    	System.out.println("country code is: "+country_json);
    	assertEquals(countryCode, country_json);
    	
    	String zip = response.jsonPath().getString("zipCode["+row+"]");
    	System.out.println("Zip code is: "+zip);
    	assertEquals(zipcode,zip);
    	
    	String state_json = response.jsonPath().getString("state["+row+"]");
    	System.out.println("State is: "+state_json);
    	assertEquals(state,state_json);
    	
    	String dest = response.jsonPath().getString("destinationTerminal["+row+"]");
    	System.out.println("destionation is: "+dest);
    	assertEquals(destination, dest);
    	
    	String creation_user_json = response.jsonPath().getString("creationUser["+row+"]");
    	System.out.println("Creation User is: "+creation_user_json);
    	assertEquals(creation_user, creation_user_json);	
    	
    	
    	
    }
    
    @When("^user performs invalid request$")
	public void user_perform_invalid_request() {
   	
       response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get(ZipToDestAPIProp.getProperty("GridAPIInvalidParameter"));
    
	}
    
    @Given("^The base URI is up and running for Add API$")
    public void the_base_URI_is_up_and_running_add() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.loadIseries");
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.post");
    	
    }
    
    
    
    @Given("^The base URI is up and running for Modify API$")
    public void the_base_URI_is_up_and_running_modify() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.put");
    	
    }
    
    
    
    @Given("^The base URI is up and running for Delete API$")
    public void the_base_URI_is_up_and_running_delete() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.delete");
    	
    }
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to add details$")
   	public void user_sends_add_details_US_country(String countryCode, String creationUser, String destination,
   			 String network, String state, String timezone) {
       	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999 - 10000) + 10000;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
       	response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
   				.body(AddAPIDetails.createAddRequest(countryCode, creationUser, destination, effectiveDate, network,state,timezone,zipcode)).when()
   				.post(Resources.add_request);
   		
   	}
    
    @When("^User should delete the data which has been added \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" for US Country on \"([^\"]*)\"$")
   	public void user_delete_request_with_body_add_US(String deletedUser, String network, String timezone, int range) {
    	
    	
    	int zipfrom = zipcode;
    	int zipto = zipcode + range;
    	
    	System.out.println("Zipfrom is :" +zipfrom);
    	System.out.println("Zipto is :" +zipto);
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(DeleteAPIDetails.deleteRequest(deletedUser, effectiveDate, network,timezone,zipfrom,zipto)).when()
				.delete(Resources.delete_request);  
       	
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to add details for Country Canada$")
   	public void user_sends_add_details_canada_country(String countryCode, String creationUser, String destination,
   			 String network, String state, String timezone, String zipcode) {
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
       	response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
   				.body(AddAPIDetails.createAddRequestCanada(countryCode, creationUser, destination, effectiveDate, network,state,timezone,zipcode)).when()
   				.post(Resources.add_request);
   		
   	}
    
    @When("^User should delete the data which has been added \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" for Canada Country$")
   	public void user_delete_request_with_body_add_canada(String deletedUser, String zipcodeCanada, String network, String timezone) {
    	
    	
    	String zipfrom = zipcodeCanada;
        String zipto = zipcodeCanada;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(DeleteAPIDetails.deleteRequestCanada(deletedUser, effectiveDate, network,timezone,zipfrom,zipto)).when()
				.delete(Resources.delete_request);  
       	
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to add details invalid$")
   	public void user_sends_add_details_invalid(String countryCode, String creationUser, String destination,
   			 String network, String state, String timezone) {
    	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999 - 10000) + 10000;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
        response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
   				.body(AddAPIDetails.createAddRequest(countryCode, creationUser, destination, effectiveDate, network,state, timezone,zipcode)).when()
   				.post(Resources.add_request_invalid);
   		
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to add details for invalid data$")
   	public void user_sends_add_details_invalid_entry(String countryCode, String creationUser, String effectiveDate, String destination,
   			 String network, String state, String timezone) {
       	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999 - 10000) + 10000;
    	
       	response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
   				.body(AddAPIDetails.createAddRequest(countryCode, creationUser, destination, effectiveDate, network,state,timezone,zipcode)).when()
   				.post(Resources.add_request);
   		
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to modify details with specific \"([^\"]*)\" of zipcode$")
   	public void user_sends_Modifies_details_US_country(String countryCode, String modifiedUser, String destination,
   			 String network, String state, String timezone, int range) {
       	
    	int zipfrom = zipcode;
    	int zipto = zipcode + range;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
        response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(ModifyAPIDetails.modifyRequest(destination, effectiveDate, modifiedUser, network, timezone,zipfrom,zipto)).when()
				.put(Resources.modify_request);  
   		
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" \"([^\"]*)\" to modify details for canada country$")
   	public void user_sends_Modifies_details_Canada_country(String countryCode, String modifiedUser, String destination,
   			 String network, String state, String timezone, String zipcodeCanada) {
       	
    	String zipfrom = zipcodeCanada;
    	String zipto = zipcodeCanada;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
        response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(ModifyAPIDetails.modifyRequestCanada(destination, effectiveDate, modifiedUser, network, timezone,zipfrom,zipto)).when()
				.put(Resources.modify_request);  
   		
   	}
    
    
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to modify details invalid URL$")
   	public void user_sends_Modifies_details_US_country_invalidURL(String countryCode, String modifiedUser, String destination,
   			 String network, String state, String timezone) {
       	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999);
    	
    	int zipfrom = zipcode;
    	int zipto = zipcode;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
        response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(ModifyAPIDetails.modifyRequest(destination, effectiveDate, modifiedUser, network, timezone,zipfrom,zipto)).when()
				.put(Resources.modify_request_invalid);  
   		
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to modify details with specific of zipcode for invalid data$")
   	public void user_sends_Modifies_details_US_country_invalid_data(String countryCode, String modifiedUser, String destination,
   			 String network, String state, String timezone) {
       	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999);
    	
    	int zipfrom = zipcode;
    	int zipto = zipcode;
    	
    	ZonedDateTime zdt = ZonedDateTime.now();
        String effectiveDate = zdt.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    	
        response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(ModifyAPIDetails.modifyRequest(destination, effectiveDate, modifiedUser, network, timezone,zipfrom,zipto)).when()
				.put(Resources.modify_request);  
   		
   	}
    
    @When("^User sends create request with body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" to modify details for invalid date$")
   	public void user_sends_modify_details_invalid_date(String countryCode, String modifiedUser, String effectiveDate, String destination,
   			 String network, String state, String timezone) {
       	
    	Random r = new Random(System.currentTimeMillis());
    	zipcode= r.nextInt(99999);
    	
    	int zipfrom = zipcode;
    	int zipto = zipcode;
    	
    	 response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
 				.body(ModifyAPIDetails.modifyRequest(destination, effectiveDate, modifiedUser, network, timezone,zipfrom,zipto)).when()
 				.put(Resources.modify_request);  
   		
   	}
    
    @Given("^The base URI is up and running for Network API$")
    public void the_base_URI_is_up_and_running_network() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.loadIseries");
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.network.lcl.get");
    	log.info("Base URL is up and Running: " + ZipToDestAPIProp.getProperty("Redis.network.lcl.get"));
    	
    }
    
    @When("^User verify network \"([^\"]*)\"$")
    public void user_verifies_json_network(String network) {
        
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	for(int row=0; row<14; row++) {
    		
    		String network_json = response.jsonPath().getString("data["+row+"]");
    		System.out.println("Network is: "+network_json);
    		
    		if(network.equals(network_json)) {
    			break;
    		}
    	}
    	
    	
    }
    
    @When("^user performs invalid request for Network controller$")
   	public void user_perform_invalid_request_network() {
      	
          response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get(ZipToDestAPIProp.getProperty("Redis.network.lcl.get.invalid"));
       
   	}
    
    @Then("^User should see the status code for Network (\\d+)$")
	public void user_should_see_the_status_code_network(int statuscode) {
   	
		json = response.then().assertThat().statusCode(statuscode);
		json.log().all().extract();
				
	}
    
    @Given("^The base URI is up and running for state API$")
    public void the_base_URI_is_up_and_running_state() {
        
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.lcl.loadIseries");
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	RestAssured.baseURI = ZipToDestAPIProp.getProperty("Redis.state.lcl.get");
    	log.info("Base URL is up and Running: " + ZipToDestAPIProp.getProperty("Redis.state.lcl.get"));
    }
    
    @When("^User verifies \"([^\"]*)\" in particular \"([^\"]*)\" and country code \"([^\"]*)\"$")
    public void user_verifies_json(String state, int row, String code) {
        
    	response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get();
    	
    	String state_json = response.jsonPath().getString("data["+row+"].state");
    	System.out.println("State is: "+state_json);
    	assertEquals(state, state_json);
    	
    	String countryCode_json = response.jsonPath().getString("data["+row+"].code");
    	System.out.println("Country code is: "+countryCode_json);
    	assertEquals(code, countryCode_json);
    	
    }
    
    @When("^user performs invalid request for State-province controller$")
   	public void user_perform_invalid_request_state() {
      	
          response = given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get(ZipToDestAPIProp.getProperty("Redis.state.lcl.get.invalid"));
       
   	}
    
    @Then("^User should see the status code for State (\\d+)$")
	public void user_should_see_the_status_code_state(int statuscode) {
   	
		json = response.then().assertThat().statusCode(statuscode);
		json.log().all().extract();
				
	}
    
    
    
}