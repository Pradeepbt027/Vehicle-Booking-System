package com.eikona.tech.util;

import static com.eikona.tech.constants.ApplicationConstants.BEARER;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eikona.tech.constants.ApplicationConstants;
import com.eikona.tech.entity.Driver;

@Component
public class DriverUtil {
	
	@Autowired
	private RequestExecutionUtil requestExecutionUtil;
	
	@Autowired
	private LoginUtil loginUtil;
	
	public String getDriverDetails(Driver entity) {
		try {
			
			JSONObject requestObj = getJsonObjectForDriver(entity);
			String url = ApplicationConstants.HTTP_COLON_DOUBLE_SLASH + "localhost:8082";

			String authHeader = BEARER + loginUtil.getBearerToken("http://localhost:8082/apis/v1/authenticate");//BEARER + ""https://www.ulip.dpiit.gov.in/ulip/v1.0.0/user/login"";
			String responeData = requestExecutionUtil.executeHttpsGetRequest(url, "/get-driver-by/"+entity.getDlNo()+"/"+entity.getDobStr(), authHeader);

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
	private JSONObject getJsonObjectForDriver(Driver entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("dlnumber", entity.getDlNo());
		requestObj.put("dob", entity.getDateOfBirth());
		
		return requestObj;
	}
}

	
