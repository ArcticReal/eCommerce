package com.skytala.eCommerce.domain.partyType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.partyType.model.PartyType;

public class PartyTypeMapper  {


	public static Map<String, Object> map(PartyType partytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partytype.getPartyTypeId() != null ){
			returnVal.put("partyTypeId",partytype.getPartyTypeId());
}

		if(partytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partytype.getParentTypeId());
}

		if(partytype.getHasTable() != null ){
			returnVal.put("hasTable",partytype.getHasTable());
}

		if(partytype.getDescription() != null ){
			returnVal.put("description",partytype.getDescription());
}

		return returnVal;
}


	public static PartyType map(Map<String, Object> fields) {

		PartyType returnVal = new PartyType();

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
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
	public static PartyType mapstrstr(Map<String, String> fields) throws Exception {

		PartyType returnVal = new PartyType();

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
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
	public static PartyType map(GenericValue val) {

PartyType returnVal = new PartyType();
		returnVal.setPartyTypeId(val.getString("partyTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyType map(HttpServletRequest request) throws Exception {

		PartyType returnVal = new PartyType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyTypeId")) {
returnVal.setPartyTypeId(request.getParameter("partyTypeId"));
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
