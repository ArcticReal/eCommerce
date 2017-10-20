package com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typePurpose;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;

public class ContactMechTypePurposeMapper  {


	public static Map<String, Object> map(ContactMechTypePurpose contactmechtypepurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechtypepurpose.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",contactmechtypepurpose.getContactMechTypeId());
}

		if(contactmechtypepurpose.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",contactmechtypepurpose.getContactMechPurposeTypeId());
}

		return returnVal;
}


	public static ContactMechTypePurpose map(Map<String, Object> fields) {

		ContactMechTypePurpose returnVal = new ContactMechTypePurpose();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}


		return returnVal;
 } 
	public static ContactMechTypePurpose mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechTypePurpose returnVal = new ContactMechTypePurpose();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}


		return returnVal;
 } 
	public static ContactMechTypePurpose map(GenericValue val) {

ContactMechTypePurpose returnVal = new ContactMechTypePurpose();
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));


return returnVal;

}

public static ContactMechTypePurpose map(HttpServletRequest request) throws Exception {

		ContactMechTypePurpose returnVal = new ContactMechTypePurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechTypeId")) {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}

		if(paramMap.containsKey("contactMechPurposeTypeId"))  {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
}
return returnVal;

}
}
