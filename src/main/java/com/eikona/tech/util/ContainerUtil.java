package com.eikona.tech.util;

import static com.eikona.tech.constants.ApplicationConstants.BEARER;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eikona.tech.constants.ApplicationConstants;
import com.eikona.tech.entity.Container;

@Component
public class ContainerUtil {

	@Autowired
	private RequestExecutionUtil requestExecutionUtil;

	@Autowired
	private LoginUtil loginUtil;

	public String getContainerDetails(Container entity) {

		try {

			JSONObject requestObj = getJsonObjectForContainer(entity);
			String url = ApplicationConstants.HTTPS_COLON_DOUBLE_SLASH + "";

			String authHeader = BEARER
					+ loginUtil.getBearerToken("https://www.ulip.dpiit.gov.in/ulip/v1.0.0/user/login");
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
	private JSONObject getJsonObjectForContainer(Container entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("sbillNo", entity.getBoeNo());
		
		return requestObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByVcnNo(Container entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("vcnNo", entity.getContainerNo());
		
		return requestObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByBeNo(Container entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("beNo", entity.getBoeNo());
		return requestObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonObjectByShippingBillNo(Container entity) {
		
		JSONObject requestObj = new JSONObject();
		requestObj.put("shippingBillNo", entity.getBoeNo());
		return requestObj;
	}
}
