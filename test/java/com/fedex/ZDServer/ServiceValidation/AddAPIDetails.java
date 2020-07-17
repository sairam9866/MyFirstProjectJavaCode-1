package com.fedex.ZDServer.ServiceValidation;

public class AddAPIDetails {
	
	/**
	 * method: createAddRequest
	 * @param countryCode
	 * @param creationUser
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param state
	 * @param zipcode
	 * @return
	 */
	public static String createAddRequest(String countryCode, String creationUser, String destination,
			String effectiveDate, String network, String state, String timezone, int zipcode) {
		
		String add= "{   \r\n" + 
				"    \"countryCode\":\""+countryCode+"\",\r\n" + 
				"    \"creationUser\": \""+creationUser+"\",\r\n" + 
				"    \"destinationTerminal\":\""+destination+"\",\r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"state\":\""+state+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipCode\":\""+zipcode+"\"\r\n" +
				"}";
		return add;
		
	}
	
	/**
	 * method: createAddRequest
	 * @param countryCode
	 * @param creationUser
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param state
	 * @param zipcode
	 * @return
	 */
	public static String createAddRequestCanada(String countryCode, String creationUser, String destination,
			String effectiveDate, String network, String state, String timezone, String zipcode) {
		
		String add= "{   \r\n" + 
				"    \"countryCode\":\""+countryCode+"\",\r\n" + 
				"    \"creationUser\": \""+creationUser+"\",\r\n" + 
				"    \"destinationTerminal\":\""+destination+"\",\r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"state\":\""+state+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipCode\":\""+zipcode+"\"\r\n" +
				"}";
		return add;
		
	}
	
}
