package com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.typeAttr.ContactMechTypeAttr;

public class ContactMechTypeAttrMapper  {


	public static Map<String, Object> map(ContactMechTypeAttr contactmechtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechtypeattr.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",contactmechtypeattr.getContactMechTypeId());
}

		if(contactmechtypeattr.getAttrName() != null ){
			returnVal.put("attrName",contactmechtypeattr.getAttrName());
}

		if(contactmechtypeattr.getDescription() != null ){
			returnVal.put("description",contactmechtypeattr.getDescription());
}

		return returnVal;
}


	public static ContactMechTypeAttr map(Map<String, Object> fields) {

		ContactMechTypeAttr returnVal = new ContactMechTypeAttr();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechTypeAttr returnVal = new ContactMechTypeAttr();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechTypeAttr map(GenericValue val) {

ContactMechTypeAttr returnVal = new ContactMechTypeAttr();
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContactMechTypeAttr map(HttpServletRequest request) throws Exception {

		ContactMechTypeAttr returnVal = new ContactMechTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechTypeId")) {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
