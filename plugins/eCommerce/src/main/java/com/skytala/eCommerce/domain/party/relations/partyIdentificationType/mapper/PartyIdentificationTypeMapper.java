package com.skytala.eCommerce.domain.party.relations.partyIdentificationType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.model.PartyIdentificationType;

public class PartyIdentificationTypeMapper  {


	public static Map<String, Object> map(PartyIdentificationType partyidentificationtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyidentificationtype.getPartyIdentificationTypeId() != null ){
			returnVal.put("partyIdentificationTypeId",partyidentificationtype.getPartyIdentificationTypeId());
}

		if(partyidentificationtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partyidentificationtype.getParentTypeId());
}

		if(partyidentificationtype.getHasTable() != null ){
			returnVal.put("hasTable",partyidentificationtype.getHasTable());
}

		if(partyidentificationtype.getDescription() != null ){
			returnVal.put("description",partyidentificationtype.getDescription());
}

		return returnVal;
}


	public static PartyIdentificationType map(Map<String, Object> fields) {

		PartyIdentificationType returnVal = new PartyIdentificationType();

		if(fields.get("partyIdentificationTypeId") != null) {
			returnVal.setPartyIdentificationTypeId((String) fields.get("partyIdentificationTypeId"));
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
	public static PartyIdentificationType mapstrstr(Map<String, String> fields) throws Exception {

		PartyIdentificationType returnVal = new PartyIdentificationType();

		if(fields.get("partyIdentificationTypeId") != null) {
			returnVal.setPartyIdentificationTypeId((String) fields.get("partyIdentificationTypeId"));
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
	public static PartyIdentificationType map(GenericValue val) {

PartyIdentificationType returnVal = new PartyIdentificationType();
		returnVal.setPartyIdentificationTypeId(val.getString("partyIdentificationTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyIdentificationType map(HttpServletRequest request) throws Exception {

		PartyIdentificationType returnVal = new PartyIdentificationType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyIdentificationTypeId")) {
returnVal.setPartyIdentificationTypeId(request.getParameter("partyIdentificationTypeId"));
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
