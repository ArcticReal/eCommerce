package com.skytala.eCommerce.domain.contactMechPurposeType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contactMechPurposeType.model.ContactMechPurposeType;

public class ContactMechPurposeTypeMapper  {


	public static Map<String, Object> map(ContactMechPurposeType contactmechpurposetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechpurposetype.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",contactmechpurposetype.getContactMechPurposeTypeId());
}

		if(contactmechpurposetype.getDescription() != null ){
			returnVal.put("description",contactmechpurposetype.getDescription());
}

		return returnVal;
}


	public static ContactMechPurposeType map(Map<String, Object> fields) {

		ContactMechPurposeType returnVal = new ContactMechPurposeType();

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechPurposeType mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechPurposeType returnVal = new ContactMechPurposeType();

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechPurposeType map(GenericValue val) {

ContactMechPurposeType returnVal = new ContactMechPurposeType();
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContactMechPurposeType map(HttpServletRequest request) throws Exception {

		ContactMechPurposeType returnVal = new ContactMechPurposeType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechPurposeTypeId")) {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
