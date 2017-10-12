package com.skytala.eCommerce.domain.content.relations.webUserPreference.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;

public class WebUserPreferenceMapper  {


	public static Map<String, Object> map(WebUserPreference webuserpreference) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(webuserpreference.getUserLoginId() != null ){
			returnVal.put("userLoginId",webuserpreference.getUserLoginId());
}

		if(webuserpreference.getPartyId() != null ){
			returnVal.put("partyId",webuserpreference.getPartyId());
}

		if(webuserpreference.getVisitId() != null ){
			returnVal.put("visitId",webuserpreference.getVisitId());
}

		if(webuserpreference.getWebPreferenceTypeId() != null ){
			returnVal.put("webPreferenceTypeId",webuserpreference.getWebPreferenceTypeId());
}

		if(webuserpreference.getWebPreferenceValue() != null ){
			returnVal.put("webPreferenceValue",webuserpreference.getWebPreferenceValue());
}

		return returnVal;
}


	public static WebUserPreference map(Map<String, Object> fields) {

		WebUserPreference returnVal = new WebUserPreference();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("webPreferenceTypeId") != null) {
			returnVal.setWebPreferenceTypeId((String) fields.get("webPreferenceTypeId"));
}

		if(fields.get("webPreferenceValue") != null) {
			returnVal.setWebPreferenceValue((String) fields.get("webPreferenceValue"));
}


		return returnVal;
 } 
	public static WebUserPreference mapstrstr(Map<String, String> fields) throws Exception {

		WebUserPreference returnVal = new WebUserPreference();

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("webPreferenceTypeId") != null) {
			returnVal.setWebPreferenceTypeId((String) fields.get("webPreferenceTypeId"));
}

		if(fields.get("webPreferenceValue") != null) {
			returnVal.setWebPreferenceValue((String) fields.get("webPreferenceValue"));
}


		return returnVal;
 } 
	public static WebUserPreference map(GenericValue val) {

WebUserPreference returnVal = new WebUserPreference();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setWebPreferenceTypeId(val.getString("webPreferenceTypeId"));
		returnVal.setWebPreferenceValue(val.getString("webPreferenceValue"));


return returnVal;

}

public static WebUserPreference map(HttpServletRequest request) throws Exception {

		WebUserPreference returnVal = new WebUserPreference();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("userLoginId")) {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("webPreferenceTypeId"))  {
returnVal.setWebPreferenceTypeId(request.getParameter("webPreferenceTypeId"));
}
		if(paramMap.containsKey("webPreferenceValue"))  {
returnVal.setWebPreferenceValue(request.getParameter("webPreferenceValue"));
}
return returnVal;

}
}
