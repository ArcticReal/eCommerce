package com.skytala.eCommerce.domain.party.relations.partyAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;

public class PartyAttributeMapper  {


	public static Map<String, Object> map(PartyAttribute partyattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyattribute.getPartyId() != null ){
			returnVal.put("partyId",partyattribute.getPartyId());
}

		if(partyattribute.getAttrName() != null ){
			returnVal.put("attrName",partyattribute.getAttrName());
}

		if(partyattribute.getAttrValue() != null ){
			returnVal.put("attrValue",partyattribute.getAttrValue());
}

		if(partyattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",partyattribute.getAttrDescription());
}

		return returnVal;
}


	public static PartyAttribute map(Map<String, Object> fields) {

		PartyAttribute returnVal = new PartyAttribute();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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
	public static PartyAttribute mapstrstr(Map<String, String> fields) throws Exception {

		PartyAttribute returnVal = new PartyAttribute();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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
	public static PartyAttribute map(GenericValue val) {

PartyAttribute returnVal = new PartyAttribute();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static PartyAttribute map(HttpServletRequest request) throws Exception {

		PartyAttribute returnVal = new PartyAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
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
