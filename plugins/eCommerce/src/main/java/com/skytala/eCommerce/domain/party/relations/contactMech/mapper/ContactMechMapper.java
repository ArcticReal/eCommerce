package com.skytala.eCommerce.domain.party.relations.contactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;

public class ContactMechMapper  {


	public static Map<String, Object> map(ContactMech contactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",contactmech.getContactMechId());
}

		if(contactmech.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",contactmech.getContactMechTypeId());
}

		if(contactmech.getInfoString() != null ){
			returnVal.put("infoString",contactmech.getInfoString());
}

		return returnVal;
}


	public static ContactMech map(Map<String, Object> fields) {

		ContactMech returnVal = new ContactMech();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("infoString") != null) {
			returnVal.setInfoString((String) fields.get("infoString"));
}


		return returnVal;
 } 
	public static ContactMech mapstrstr(Map<String, String> fields) throws Exception {

		ContactMech returnVal = new ContactMech();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("infoString") != null) {
			returnVal.setInfoString((String) fields.get("infoString"));
}


		return returnVal;
 } 
	public static ContactMech map(GenericValue val) {

ContactMech returnVal = new ContactMech();
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setInfoString(val.getString("infoString"));


return returnVal;

}

public static ContactMech map(HttpServletRequest request) throws Exception {

		ContactMech returnVal = new ContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechId")) {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}

		if(paramMap.containsKey("contactMechTypeId"))  {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}
		if(paramMap.containsKey("infoString"))  {
returnVal.setInfoString(request.getParameter("infoString"));
}
return returnVal;

}
}
