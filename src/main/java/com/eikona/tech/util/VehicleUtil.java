package com.eikona.tech.util;

import static com.eikona.tech.constants.ApplicationConstants.BEARER;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eikona.tech.constants.ApplicationConstants;
import com.eikona.tech.entity.Vehicle;

@Component
public class VehicleUtil {
	
	@Autowired
	private RequestExecutionUtil requestExecutionUtil;
	
	@Autowired
	private LoginUtil loginUtil;
	
	public String getVehicleDetailsByVehicleNo(Vehicle entity) {
		try {
			
//			JSONObject requestObj = getJsonObjectByVehicleNo(entity);
//			String url = ApplicationConstants.HTTPS_COLON_DOUBLE_SLASH + "";
//
//			String authHeader = BEARER + loginUtil.getBearerToken("https://www.ulip.dpiit.gov.in/ulip/v1.0.0/user/login");
//			String responeData = requestExecutionUtil.executeHttpsPostRequest(requestObj, url, authHeader);
//
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonResponse = (JSONObject) jsonParser.parse(responeData);
//			JSONArray  jsonResponseArray = (JSONArray) jsonResponse.get("response");
//			JSONObject jsonResponseObj = (JSONObject) jsonResponseArray.get(0);
//			
//			String responseStr = (String) jsonResponseObj.get("response");
//			
//			System.out.println(jsonResponse);
			
			String url = ApplicationConstants.HTTP_COLON_DOUBLE_SLASH + "localhost:8082";

			String authHeader = BEARER + loginUtil.getBearerToken("http://localhost:8082/apis/v1/authenticate");
			String responeData = requestExecutionUtil.executeHttpsGetRequest(url, "/get-vehicle-by"+entity.getVehicleRegnNo(), authHeader);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonResponse = (JSONObject) jsonParser.parse(responeData);
			JSONArray  jsonResponseArray = (JSONArray) jsonResponse.get("response");
			JSONObject jsonResponseObj = (JSONObject) jsonResponseArray.get(0);
			
			String responseStr = (String) jsonResponseObj.get("response");
			
			System.out.println(jsonResponse);
			
			return responseStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "Connection Refused";
		}
	}
	
	public String getVehicleDetailsByChasisNo(Vehicle entity) {
		try {
			
			JSONObject requestObj = getJsonObjectByChasisNo(entity);
			String url = ApplicationConstants.HTTPS_COLON_DOUBLE_SLASH + "";

			String authHeader = BEARER + "";
			String responeData = requestExecutionUtil.executeHttpsPostRequest(requestObj, url, authHeader);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonResponse = (JSONObject) jsonParser.parse(responeData);
			
			System.out.println(jsonResponse);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getVehicleDetailsByEngineNo(Vehicle entity) {
		try {
			
			JSONObject requestObj = getJsonObjectByEngineNo(entity);
			String url = ApplicationConstants.HTTPS_COLON_DOUBLE_SLASH + "";

			String authHeader = BEARER + "";
			String responeData = requestExecutionUtil.executeHttpsPostRequest(requestObj, url, authHeader);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonResponse = (JSONObject) jsonParser.parse(responeData);
			
			System.out.println(jsonResponse);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByVehicleNo(Vehicle entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("vehiclenumber", entity.getVehicleRegnNo());
		
		return requestObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByChasisNo(Vehicle entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("chasisnumber", entity.getChasisNumber());
		
		return requestObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByEngineNo(Vehicle entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("enginenumber", entity.getEngineNumber());
		
		return requestObj;
	}

}
