package com.skytala.eCommerce.domain.product.relations.facilityContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;

public class FacilityContentMapper  {


	public static Map<String, Object> map(FacilityContent facilitycontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitycontent.getFacilityId() != null ){
			returnVal.put("facilityId",facilitycontent.getFacilityId());
}

		if(facilitycontent.getContentId() != null ){
			returnVal.put("contentId",facilitycontent.getContentId());
}

		if(facilitycontent.getFromDate() != null ){
			returnVal.put("fromDate",facilitycontent.getFromDate());
}

		if(facilitycontent.getThruDate() != null ){
			returnVal.put("thruDate",facilitycontent.getThruDate());
}

		return returnVal;
}


	public static FacilityContent map(Map<String, Object> fields) {

		FacilityContent returnVal = new FacilityContent();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FacilityContent mapstrstr(Map<String, String> fields) throws Exception {

		FacilityContent returnVal = new FacilityContent();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static FacilityContent map(GenericValue val) {

FacilityContent returnVal = new FacilityContent();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FacilityContent map(HttpServletRequest request) throws Exception {

		FacilityContent returnVal = new FacilityContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
