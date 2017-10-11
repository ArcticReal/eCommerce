package com.skytala.eCommerce.domain.party.relations.partyContentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyContentType.model.PartyContentType;

public class PartyContentTypeMapper  {


	public static Map<String, Object> map(PartyContentType partycontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partycontenttype.getPartyContentTypeId() != null ){
			returnVal.put("partyContentTypeId",partycontenttype.getPartyContentTypeId());
}

		if(partycontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partycontenttype.getParentTypeId());
}

		if(partycontenttype.getDescription() != null ){
			returnVal.put("description",partycontenttype.getDescription());
}

		return returnVal;
}


	public static PartyContentType map(Map<String, Object> fields) {

		PartyContentType returnVal = new PartyContentType();

		if(fields.get("partyContentTypeId") != null) {
			returnVal.setPartyContentTypeId((String) fields.get("partyContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyContentType mapstrstr(Map<String, String> fields) throws Exception {

		PartyContentType returnVal = new PartyContentType();

		if(fields.get("partyContentTypeId") != null) {
			returnVal.setPartyContentTypeId((String) fields.get("partyContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyContentType map(GenericValue val) {

PartyContentType returnVal = new PartyContentType();
		returnVal.setPartyContentTypeId(val.getString("partyContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyContentType map(HttpServletRequest request) throws Exception {

		PartyContentType returnVal = new PartyContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyContentTypeId")) {
returnVal.setPartyContentTypeId(request.getParameter("partyContentTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
