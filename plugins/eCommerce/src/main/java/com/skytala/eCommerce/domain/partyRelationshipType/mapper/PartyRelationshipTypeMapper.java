package com.skytala.eCommerce.domain.partyRelationshipType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.partyRelationshipType.model.PartyRelationshipType;

public class PartyRelationshipTypeMapper  {


	public static Map<String, Object> map(PartyRelationshipType partyrelationshiptype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyrelationshiptype.getPartyRelationshipTypeId() != null ){
			returnVal.put("partyRelationshipTypeId",partyrelationshiptype.getPartyRelationshipTypeId());
}

		if(partyrelationshiptype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",partyrelationshiptype.getParentTypeId());
}

		if(partyrelationshiptype.getHasTable() != null ){
			returnVal.put("hasTable",partyrelationshiptype.getHasTable());
}

		if(partyrelationshiptype.getPartyRelationshipName() != null ){
			returnVal.put("partyRelationshipName",partyrelationshiptype.getPartyRelationshipName());
}

		if(partyrelationshiptype.getDescription() != null ){
			returnVal.put("description",partyrelationshiptype.getDescription());
}

		if(partyrelationshiptype.getRoleTypeIdValidFrom() != null ){
			returnVal.put("roleTypeIdValidFrom",partyrelationshiptype.getRoleTypeIdValidFrom());
}

		if(partyrelationshiptype.getRoleTypeIdValidTo() != null ){
			returnVal.put("roleTypeIdValidTo",partyrelationshiptype.getRoleTypeIdValidTo());
}

		return returnVal;
}


	public static PartyRelationshipType map(Map<String, Object> fields) {

		PartyRelationshipType returnVal = new PartyRelationshipType();

		if(fields.get("partyRelationshipTypeId") != null) {
			returnVal.setPartyRelationshipTypeId((String) fields.get("partyRelationshipTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("partyRelationshipName") != null) {
			returnVal.setPartyRelationshipName((String) fields.get("partyRelationshipName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("roleTypeIdValidFrom") != null) {
			returnVal.setRoleTypeIdValidFrom((String) fields.get("roleTypeIdValidFrom"));
}

		if(fields.get("roleTypeIdValidTo") != null) {
			returnVal.setRoleTypeIdValidTo((String) fields.get("roleTypeIdValidTo"));
}


		return returnVal;
 } 
	public static PartyRelationshipType mapstrstr(Map<String, String> fields) throws Exception {

		PartyRelationshipType returnVal = new PartyRelationshipType();

		if(fields.get("partyRelationshipTypeId") != null) {
			returnVal.setPartyRelationshipTypeId((String) fields.get("partyRelationshipTypeId"));
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

		if(fields.get("partyRelationshipName") != null) {
			returnVal.setPartyRelationshipName((String) fields.get("partyRelationshipName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("roleTypeIdValidFrom") != null) {
			returnVal.setRoleTypeIdValidFrom((String) fields.get("roleTypeIdValidFrom"));
}

		if(fields.get("roleTypeIdValidTo") != null) {
			returnVal.setRoleTypeIdValidTo((String) fields.get("roleTypeIdValidTo"));
}


		return returnVal;
 } 
	public static PartyRelationshipType map(GenericValue val) {

PartyRelationshipType returnVal = new PartyRelationshipType();
		returnVal.setPartyRelationshipTypeId(val.getString("partyRelationshipTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setPartyRelationshipName(val.getString("partyRelationshipName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setRoleTypeIdValidFrom(val.getString("roleTypeIdValidFrom"));
		returnVal.setRoleTypeIdValidTo(val.getString("roleTypeIdValidTo"));


return returnVal;

}

public static PartyRelationshipType map(HttpServletRequest request) throws Exception {

		PartyRelationshipType returnVal = new PartyRelationshipType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyRelationshipTypeId")) {
returnVal.setPartyRelationshipTypeId(request.getParameter("partyRelationshipTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("partyRelationshipName"))  {
returnVal.setPartyRelationshipName(request.getParameter("partyRelationshipName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("roleTypeIdValidFrom"))  {
returnVal.setRoleTypeIdValidFrom(request.getParameter("roleTypeIdValidFrom"));
}
		if(paramMap.containsKey("roleTypeIdValidTo"))  {
returnVal.setRoleTypeIdValidTo(request.getParameter("roleTypeIdValidTo"));
}
return returnVal;

}
}
