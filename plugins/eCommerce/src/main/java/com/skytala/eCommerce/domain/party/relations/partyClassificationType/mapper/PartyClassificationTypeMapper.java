package com.skytala.eCommerce.domain.party.relations.partyClassificationType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyClassificationType.model.PartyClassificationType;

public class PartyClassificationTypeMapper  {


	public static Map<String, Object> map(PartyClassificationType partyclassificationtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyclassificationtype.getPartyClassificationTypeId() != null ){
			returnVal.put("partyClassificationTypeId",partyclassificationtype.getPartyClassificationTypeId());
}

		if(partyclassificationtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partyclassificationtype.getParentTypeId());
}

		if(partyclassificationtype.getHasTable() != null ){
			returnVal.put("hasTable",partyclassificationtype.getHasTable());
}

		if(partyclassificationtype.getDescription() != null ){
			returnVal.put("description",partyclassificationtype.getDescription());
}

		return returnVal;
}


	public static PartyClassificationType map(Map<String, Object> fields) {

		PartyClassificationType returnVal = new PartyClassificationType();

		if(fields.get("partyClassificationTypeId") != null) {
			returnVal.setPartyClassificationTypeId((String) fields.get("partyClassificationTypeId"));
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
	public static PartyClassificationType mapstrstr(Map<String, String> fields) throws Exception {

		PartyClassificationType returnVal = new PartyClassificationType();

		if(fields.get("partyClassificationTypeId") != null) {
			returnVal.setPartyClassificationTypeId((String) fields.get("partyClassificationTypeId"));
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
	public static PartyClassificationType map(GenericValue val) {

PartyClassificationType returnVal = new PartyClassificationType();
		returnVal.setPartyClassificationTypeId(val.getString("partyClassificationTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyClassificationType map(HttpServletRequest request) throws Exception {

		PartyClassificationType returnVal = new PartyClassificationType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyClassificationTypeId")) {
returnVal.setPartyClassificationTypeId(request.getParameter("partyClassificationTypeId"));
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
