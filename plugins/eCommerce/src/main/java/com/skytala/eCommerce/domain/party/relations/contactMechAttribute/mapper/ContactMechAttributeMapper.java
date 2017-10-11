package com.skytala.eCommerce.domain.party.relations.contactMechAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMechAttribute.model.ContactMechAttribute;

public class ContactMechAttributeMapper  {


	public static Map<String, Object> map(ContactMechAttribute contactmechattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechattribute.getContactMechId() != null ){
			returnVal.put("contactMechId",contactmechattribute.getContactMechId());
}

		if(contactmechattribute.getAttrName() != null ){
			returnVal.put("attrName",contactmechattribute.getAttrName());
}

		if(contactmechattribute.getAttrValue() != null ){
			returnVal.put("attrValue",contactmechattribute.getAttrValue());
}

		if(contactmechattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",contactmechattribute.getAttrDescription());
}

		return returnVal;
}


	public static ContactMechAttribute map(Map<String, Object> fields) {

		ContactMechAttribute returnVal = new ContactMechAttribute();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ContactMechAttribute mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechAttribute returnVal = new ContactMechAttribute();

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ContactMechAttribute map(GenericValue val) {

ContactMechAttribute returnVal = new ContactMechAttribute();
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static ContactMechAttribute map(HttpServletRequest request) throws Exception {

		ContactMechAttribute returnVal = new ContactMechAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechId")) {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
