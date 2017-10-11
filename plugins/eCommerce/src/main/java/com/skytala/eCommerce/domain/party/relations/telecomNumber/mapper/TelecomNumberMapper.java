package com.skytala.eCommerce.domain.party.relations.telecomNumber.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;

public class TelecomNumberMapper  {


	public static Map<String, Object> map(TelecomNumber telecomnumber) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(telecomnumber.getContactMechId() != null ){
			returnVal.put("contactMechId",telecomnumber.getContactMechId());
}

		if(telecomnumber.getCountryCode() != null ){
			returnVal.put("countryCode",telecomnumber.getCountryCode());
}

		if(telecomnumber.getAreaCode() != null ){
			returnVal.put("areaCode",telecomnumber.getAreaCode());
}

		if(telecomnumber.getContactNumber() != null ){
			returnVal.put("contactNumber",telecomnumber.getContactNumber());
}

		if(telecomnumber.getAskForName() != null ){
			returnVal.put("askForName",telecomnumber.getAskForName());
}

		return returnVal;
}


	public static TelecomNumber map(Map<String, Object> fields) {

		TelecomNumber returnVal = new TelecomNumber();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("countryCode") != null) {
			returnVal.setCountryCode((String) fields.get("countryCode"));
}

		if(fields.get("areaCode") != null) {
			returnVal.setAreaCode((String) fields.get("areaCode"));
}

		if(fields.get("contactNumber") != null) {
			returnVal.setContactNumber((String) fields.get("contactNumber"));
}

		if(fields.get("askForName") != null) {
			returnVal.setAskForName((String) fields.get("askForName"));
}


		return returnVal;
 } 
	public static TelecomNumber mapstrstr(Map<String, String> fields) throws Exception {

		TelecomNumber returnVal = new TelecomNumber();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("countryCode") != null) {
			returnVal.setCountryCode((String) fields.get("countryCode"));
}

		if(fields.get("areaCode") != null) {
			returnVal.setAreaCode((String) fields.get("areaCode"));
}

		if(fields.get("contactNumber") != null) {
			returnVal.setContactNumber((String) fields.get("contactNumber"));
}

		if(fields.get("askForName") != null) {
			returnVal.setAskForName((String) fields.get("askForName"));
}


		return returnVal;
 } 
	public static TelecomNumber map(GenericValue val) {

TelecomNumber returnVal = new TelecomNumber();
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setCountryCode(val.getString("countryCode"));
		returnVal.setAreaCode(val.getString("areaCode"));
		returnVal.setContactNumber(val.getString("contactNumber"));
		returnVal.setAskForName(val.getString("askForName"));


return returnVal;

}

public static TelecomNumber map(HttpServletRequest request) throws Exception {

		TelecomNumber returnVal = new TelecomNumber();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechId")) {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}

		if(paramMap.containsKey("countryCode"))  {
returnVal.setCountryCode(request.getParameter("countryCode"));
}
		if(paramMap.containsKey("areaCode"))  {
returnVal.setAreaCode(request.getParameter("areaCode"));
}
		if(paramMap.containsKey("contactNumber"))  {
returnVal.setContactNumber(request.getParameter("contactNumber"));
}
		if(paramMap.containsKey("askForName"))  {
returnVal.setAskForName(request.getParameter("askForName"));
}
return returnVal;

}
}
