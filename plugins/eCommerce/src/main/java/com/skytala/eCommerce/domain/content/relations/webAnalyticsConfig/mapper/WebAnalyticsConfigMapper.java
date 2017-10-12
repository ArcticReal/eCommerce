package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;

public class WebAnalyticsConfigMapper  {


	public static Map<String, Object> map(WebAnalyticsConfig webanalyticsconfig) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(webanalyticsconfig.getWebSiteId() != null ){
			returnVal.put("webSiteId",webanalyticsconfig.getWebSiteId());
}

		if(webanalyticsconfig.getWebAnalyticsTypeId() != null ){
			returnVal.put("webAnalyticsTypeId",webanalyticsconfig.getWebAnalyticsTypeId());
}

		if(webanalyticsconfig.getWebAnalyticsCode() != null ){
			returnVal.put("webAnalyticsCode",webanalyticsconfig.getWebAnalyticsCode());
}

		return returnVal;
}


	public static WebAnalyticsConfig map(Map<String, Object> fields) {

		WebAnalyticsConfig returnVal = new WebAnalyticsConfig();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("webAnalyticsTypeId") != null) {
			returnVal.setWebAnalyticsTypeId((String) fields.get("webAnalyticsTypeId"));
}

		if(fields.get("webAnalyticsCode") != null) {
			returnVal.setWebAnalyticsCode((String) fields.get("webAnalyticsCode"));
}


		return returnVal;
 } 
	public static WebAnalyticsConfig mapstrstr(Map<String, String> fields) throws Exception {

		WebAnalyticsConfig returnVal = new WebAnalyticsConfig();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("webAnalyticsTypeId") != null) {
			returnVal.setWebAnalyticsTypeId((String) fields.get("webAnalyticsTypeId"));
}

		if(fields.get("webAnalyticsCode") != null) {
			returnVal.setWebAnalyticsCode((String) fields.get("webAnalyticsCode"));
}


		return returnVal;
 } 
	public static WebAnalyticsConfig map(GenericValue val) {

WebAnalyticsConfig returnVal = new WebAnalyticsConfig();
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setWebAnalyticsTypeId(val.getString("webAnalyticsTypeId"));
		returnVal.setWebAnalyticsCode(val.getString("webAnalyticsCode"));


return returnVal;

}

public static WebAnalyticsConfig map(HttpServletRequest request) throws Exception {

		WebAnalyticsConfig returnVal = new WebAnalyticsConfig();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webSiteId")) {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
}

		if(paramMap.containsKey("webAnalyticsTypeId"))  {
returnVal.setWebAnalyticsTypeId(request.getParameter("webAnalyticsTypeId"));
}
		if(paramMap.containsKey("webAnalyticsCode"))  {
returnVal.setWebAnalyticsCode(request.getParameter("webAnalyticsCode"));
}
return returnVal;

}
}
