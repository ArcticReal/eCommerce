package com.skytala.eCommerce.domain.party.relations.contactMech.mapper.link;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;

public class ContactMechLinkMapper  {


	public static Map<String, Object> map(ContactMechLink contactmechlink) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechlink.getContactMechIdFrom() != null ){
			returnVal.put("contactMechIdFrom",contactmechlink.getContactMechIdFrom());
}

		if(contactmechlink.getContactMechIdTo() != null ){
			returnVal.put("contactMechIdTo",contactmechlink.getContactMechIdTo());
}

		return returnVal;
}


	public static ContactMechLink map(Map<String, Object> fields) {

		ContactMechLink returnVal = new ContactMechLink();

		if(fields.get("contactMechIdFrom") != null) {
			returnVal.setContactMechIdFrom((String) fields.get("contactMechIdFrom"));
}

		if(fields.get("contactMechIdTo") != null) {
			returnVal.setContactMechIdTo((String) fields.get("contactMechIdTo"));
}


		return returnVal;
 } 
	public static ContactMechLink mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechLink returnVal = new ContactMechLink();

		if(fields.get("contactMechIdFrom") != null) {
			returnVal.setContactMechIdFrom((String) fields.get("contactMechIdFrom"));
}

		if(fields.get("contactMechIdTo") != null) {
			returnVal.setContactMechIdTo((String) fields.get("contactMechIdTo"));
}


		return returnVal;
 } 
	public static ContactMechLink map(GenericValue val) {

ContactMechLink returnVal = new ContactMechLink();
		returnVal.setContactMechIdFrom(val.getString("contactMechIdFrom"));
		returnVal.setContactMechIdTo(val.getString("contactMechIdTo"));


return returnVal;

}

public static ContactMechLink map(HttpServletRequest request) throws Exception {

		ContactMechLink returnVal = new ContactMechLink();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechIdFrom")) {
returnVal.setContactMechIdFrom(request.getParameter("contactMechIdFrom"));
}

		if(paramMap.containsKey("contactMechIdTo"))  {
returnVal.setContactMechIdTo(request.getParameter("contactMechIdTo"));
}
return returnVal;

}
}
