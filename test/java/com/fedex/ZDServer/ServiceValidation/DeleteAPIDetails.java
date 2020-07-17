package com.fedex.ZDServer.ServiceValidation;

public class DeleteAPIDetails {
	
	/**
	 * method: deleteRequest
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String deleteRequest(String deletedUser,String effectiveDate, String network, String timezone, int zipfrom, int zipto) {
		
		String delete= "{   \r\n" + 
				"    \"deletedUser\":\""+deletedUser+"\",\r\n" +
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\",\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return delete;
		
	}
	
	/**
	 * method: deleteRequest
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String deleteRequestCanada(String deletedUser,String effectiveDate, String network, String timezone, String zipfrom, String zipto) {
		
		String delete= "{   \r\n" + 
				"    \"deletedUser\":\""+deletedUser+"\",\r\n" +
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\",\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return delete;
		
	}
	
	/**
	 * method: deleteRequest
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String deleteRequestInvalidData(String effectiveDate, String network, String zipfrom, String zipto) {
		
		String delete= "{   \r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\"\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\"\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return delete;
		
	}
	
}
