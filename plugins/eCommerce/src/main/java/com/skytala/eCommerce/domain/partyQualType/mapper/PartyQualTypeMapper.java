package com.skytala.eCommerce.domain.partyQualType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.partyQualType.model.PartyQualType;

public class PartyQualTypeMapper  {


	public static Map<String, Object> map(PartyQualType partyqualtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyqualtype.getPartyQualTypeId() != null ){
			returnVal.put("partyQualTypeId",partyqualtype.getPartyQualTypeId());
}

		if(partyqualtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partyqualtype.getParentTypeId());
}

		if(partyqualtype.getHasTable() != null ){
			returnVal.put("hasTable",partyqualtype.getHasTable());
}

		if(partyqualtype.getDescription() != null ){
			returnVal.put("description",partyqualtype.getDescription());
}

		return returnVal;
}


	public static PartyQualType map(Map<String, Object> fields) {

		PartyQualType returnVal = new PartyQualType();

		if(fields.get("partyQualTypeId") != null) {
			returnVal.setPartyQualTypeId((String) fields.get("partyQualTypeId"));
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
	public static PartyQualType mapstrstr(Map<String, String> fields) throws Exception {

		PartyQualType returnVal = new PartyQualType();

		if(fields.get("partyQualTypeId") != null) {
			returnVal.setPartyQualTypeId((String) fields.get("partyQualTypeId"));
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
	public static PartyQualType map(GenericValue val) {

PartyQualType returnVal = new PartyQualType();
		returnVal.setPartyQualTypeId(val.getString("partyQualTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyQualType map(HttpServletRequest request) throws Exception {

		PartyQualType returnVal = new PartyQualType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyQualTypeId")) {
returnVal.setPartyQualTypeId(request.getParameter("partyQualTypeId"));
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
