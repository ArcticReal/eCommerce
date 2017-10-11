package com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.model.PartyClassificationGroup;

public class PartyClassificationGroupMapper  {


	public static Map<String, Object> map(PartyClassificationGroup partyclassificationgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyclassificationgroup.getPartyClassificationGroupId() != null ){
			returnVal.put("partyClassificationGroupId",partyclassificationgroup.getPartyClassificationGroupId());
}

		if(partyclassificationgroup.getPartyClassificationTypeId() != null ){
			returnVal.put("partyClassificationTypeId",partyclassificationgroup.getPartyClassificationTypeId());
}

		if(partyclassificationgroup.getParentGroupId() != null ){
			returnVal.put("parentGroupId",partyclassificationgroup.getParentGroupId());
}

		if(partyclassificationgroup.getDescription() != null ){
			returnVal.put("description",partyclassificationgroup.getDescription());
}

		return returnVal;
}


	public static PartyClassificationGroup map(Map<String, Object> fields) {

		PartyClassificationGroup returnVal = new PartyClassificationGroup();

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}

		if(fields.get("partyClassificationTypeId") != null) {
			returnVal.setPartyClassificationTypeId((String) fields.get("partyClassificationTypeId"));
}

		if(fields.get("parentGroupId") != null) {
			returnVal.setParentGroupId((String) fields.get("parentGroupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyClassificationGroup mapstrstr(Map<String, String> fields) throws Exception {

		PartyClassificationGroup returnVal = new PartyClassificationGroup();

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}

		if(fields.get("partyClassificationTypeId") != null) {
			returnVal.setPartyClassificationTypeId((String) fields.get("partyClassificationTypeId"));
}

		if(fields.get("parentGroupId") != null) {
			returnVal.setParentGroupId((String) fields.get("parentGroupId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyClassificationGroup map(GenericValue val) {

PartyClassificationGroup returnVal = new PartyClassificationGroup();
		returnVal.setPartyClassificationGroupId(val.getString("partyClassificationGroupId"));
		returnVal.setPartyClassificationTypeId(val.getString("partyClassificationTypeId"));
		returnVal.setParentGroupId(val.getString("parentGroupId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyClassificationGroup map(HttpServletRequest request) throws Exception {

		PartyClassificationGroup returnVal = new PartyClassificationGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyClassificationGroupId")) {
returnVal.setPartyClassificationGroupId(request.getParameter("partyClassificationGroupId"));
}

		if(paramMap.containsKey("partyClassificationTypeId"))  {
returnVal.setPartyClassificationTypeId(request.getParameter("partyClassificationTypeId"));
}
		if(paramMap.containsKey("parentGroupId"))  {
returnVal.setParentGroupId(request.getParameter("parentGroupId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
