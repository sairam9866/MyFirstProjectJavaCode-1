package com.fedex.ZDServer.ServiceValidation;

public class ModifyAPIDetails {
	
	/**
	 * method: editRequest
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String modifyRequest(String destination,String effectiveDate, String modifiedUser,String network, 
			String timezone,int zipfrom, int zipto) {
		
		String modify= "{   \r\n" + 
				"    \"destinationTerminal\":\""+destination+"\",\r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"modifiedUser\":\""+modifiedUser+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\",\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return modify;
		
	}
	
	/**
	 * method: editRequest
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String modifyRequestCanada(String destination,String effectiveDate, String modifiedUser,String network, 
			String timezone,String zipfrom, String zipto) {
		
		String modify= "{   \r\n" + 
				"    \"destinationTerminal\":\""+destination+"\",\r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"modifiedUser\":\""+modifiedUser+"\",\r\n" +
				"    \"network\":\""+network+"\",\r\n" +
				"    \"timeZone\":\""+timezone+"\",\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\",\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return modify;
		
	}
	
	/**
	 * method: editRequestNoComma
	 * @param destination
	 * @param effectiveDate
	 * @param network
	 * @param zipfrom
	 * @param zipto
	 * @return
	 */
	public static String editRequestNoComma(String destination,
			String effectiveDate, String network, String zipfrom, String zipto) {
		
		String modify= "{   \r\n" + 
				"    \"destinationTerminal\":\""+destination+"\",\r\n" + 
				"    \"effectiveDate\":\""+effectiveDate+"\",\r\n" +
				"    \"network\":\""+network+"\"\r\n" +
				"    \"zipFrom\":\""+zipfrom+"\"\r\n" +
				"    \"zipTo\":\""+zipto+"\"\r\n" +
				"}";
		return modify;
		
	}


	
}
