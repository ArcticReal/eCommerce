package com.skytala.eCommerce.domain.party.relations.contactMechType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMechType.model.ContactMechType;

public class ContactMechTypeMapper  {


	public static Map<String, Object> map(ContactMechType contactmechtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactmechtype.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",contactmechtype.getContactMechTypeId());
}

		if(contactmechtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",contactmechtype.getParentTypeId());
}

		if(contactmechtype.getHasTable() != null ){
			returnVal.put("hasTable",contactmechtype.getHasTable());
}

		if(contactmechtype.getDescription() != null ){
			returnVal.put("description",contactmechtype.getDescription());
}

		return returnVal;
}


	public static ContactMechType map(Map<String, Object> fields) {

		ContactMechType returnVal = new ContactMechType();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechType mapstrstr(Map<String, String> fields) throws Exception {

		ContactMechType returnVal = new ContactMechType();

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContactMechType map(GenericValue val) {

ContactMechType returnVal = new ContactMechType();
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContactMechType map(HttpServletRequest request) throws Exception {

		ContactMechType returnVal = new ContactMechType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactMechTypeId")) {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
