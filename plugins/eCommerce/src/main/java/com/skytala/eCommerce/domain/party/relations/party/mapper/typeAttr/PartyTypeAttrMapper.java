package com.skytala.eCommerce.domain.party.relations.party.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;

public class PartyTypeAttrMapper  {


	public static Map<String, Object> map(PartyTypeAttr partytypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partytypeattr.getPartyTypeId() != null ){
			returnVal.put("partyTypeId",partytypeattr.getPartyTypeId());
}

		if(partytypeattr.getAttrName() != null ){
			returnVal.put("attrName",partytypeattr.getAttrName());
}

		if(partytypeattr.getDescription() != null ){
			returnVal.put("description",partytypeattr.getDescription());
}

		return returnVal;
}


	public static PartyTypeAttr map(Map<String, Object> fields) {

		PartyTypeAttr returnVal = new PartyTypeAttr();

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		PartyTypeAttr returnVal = new PartyTypeAttr();

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyTypeAttr map(GenericValue val) {

PartyTypeAttr returnVal = new PartyTypeAttr();
		returnVal.setPartyTypeId(val.getString("partyTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyTypeAttr map(HttpServletRequest request) throws Exception {

		PartyTypeAttr returnVal = new PartyTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyTypeId")) {
returnVal.setPartyTypeId(request.getParameter("partyTypeId"));
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
