package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;

public class SalesOpportunityTrckCodeMapper  {


	public static Map<String, Object> map(SalesOpportunityTrckCode salesopportunitytrckcode) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunitytrckcode.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunitytrckcode.getSalesOpportunityId());
}

		if(salesopportunitytrckcode.getTrackingCodeId() != null ){
			returnVal.put("trackingCodeId",salesopportunitytrckcode.getTrackingCodeId());
}

		if(salesopportunitytrckcode.getReceivedDate() != null ){
			returnVal.put("receivedDate",salesopportunitytrckcode.getReceivedDate());
}

		return returnVal;
}


	public static SalesOpportunityTrckCode map(Map<String, Object> fields) {

		SalesOpportunityTrckCode returnVal = new SalesOpportunityTrckCode();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("receivedDate") != null) {
			returnVal.setReceivedDate((Timestamp) fields.get("receivedDate"));
}


		return returnVal;
 } 
	public static SalesOpportunityTrckCode mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityTrckCode returnVal = new SalesOpportunityTrckCode();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("trackingCodeId") != null) {
			returnVal.setTrackingCodeId((String) fields.get("trackingCodeId"));
}

		if(fields.get("receivedDate") != null) {
String buf = fields.get("receivedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReceivedDate(ibuf);
}


		return returnVal;
 } 
	public static SalesOpportunityTrckCode map(GenericValue val) {

SalesOpportunityTrckCode returnVal = new SalesOpportunityTrckCode();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setTrackingCodeId(val.getString("trackingCodeId"));
		returnVal.setReceivedDate(val.getTimestamp("receivedDate"));


return returnVal;

}

public static SalesOpportunityTrckCode map(HttpServletRequest request) throws Exception {

		SalesOpportunityTrckCode returnVal = new SalesOpportunityTrckCode();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("trackingCodeId"))  {
returnVal.setTrackingCodeId(request.getParameter("trackingCodeId"));
}
		if(paramMap.containsKey("receivedDate"))  {
String buf = request.getParameter("receivedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReceivedDate(ibuf);
}
return returnVal;

}
}
