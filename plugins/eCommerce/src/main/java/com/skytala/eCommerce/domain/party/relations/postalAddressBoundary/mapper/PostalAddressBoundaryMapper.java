package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model.PostalAddressBoundary;

public class PostalAddressBoundaryMapper  {


	public static Map<String, Object> map(PostalAddressBoundary postaladdressboundary) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(postaladdressboundary.getContactMechId() != null ){
			returnVal.put("contactMechId",postaladdressboundary.getContactMechId());
}

		if(postaladdressboundary.getGeoId() != null ){
			returnVal.put("geoId",postaladdressboundary.getGeoId());
}

		return returnVal;
}


	public static PostalAddressBoundary map(Map<String, Object> fields) {

		PostalAddressBoundary returnVal = new PostalAddressBoundary();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static PostalAddressBoundary mapstrstr(Map<String, String> fields) throws Exception {

		PostalAddressBoundary returnVal = new PostalAddressBoundary();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}


		return returnVal;
 } 
	public static PostalAddressBoundary map(GenericValue val) {

PostalAddressBoundary returnVal = new PostalAddressBoundary();
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setGeoId(val.getString("geoId"));


return returnVal;

}

public static PostalAddressBoundary map(HttpServletRequest request) throws Exception {

		PostalAddressBoundary returnVal = new PostalAddressBoundary();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechId")) {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}

		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
}
return returnVal;

}
}
