package com.skytala.eCommerce.domain.party.relations.party.mapper.relationship;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.relationship.PartyRelationship;

public class PartyRelationshipMapper  {


	public static Map<String, Object> map(PartyRelationship partyrelationship) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyrelationship.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",partyrelationship.getPartyIdFrom());
}

		if(partyrelationship.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",partyrelationship.getPartyIdTo());
}

		if(partyrelationship.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",partyrelationship.getRoleTypeIdFrom());
}

		if(partyrelationship.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",partyrelationship.getRoleTypeIdTo());
}

		if(partyrelationship.getFromDate() != null ){
			returnVal.put("fromDate",partyrelationship.getFromDate());
}

		if(partyrelationship.getThruDate() != null ){
			returnVal.put("thruDate",partyrelationship.getThruDate());
}

		if(partyrelationship.getStatusId() != null ){
			returnVal.put("statusId",partyrelationship.getStatusId());
}

		if(partyrelationship.getRelationshipName() != null ){
			returnVal.put("relationshipName",partyrelationship.getRelationshipName());
}

		if(partyrelationship.getSecurityGroupId() != null ){
			returnVal.put("securityGroupId",partyrelationship.getSecurityGroupId());
}

		if(partyrelationship.getPriorityTypeId() != null ){
			returnVal.put("priorityTypeId",partyrelationship.getPriorityTypeId());
}

		if(partyrelationship.getPartyRelationshipTypeId() != null ){
			returnVal.put("partyRelationshipTypeId",partyrelationship.getPartyRelationshipTypeId());
}

		if(partyrelationship.getPermissionsEnumId() != null ){
			returnVal.put("permissionsEnumId",partyrelationship.getPermissionsEnumId());
}

		if(partyrelationship.getPositionTitle() != null ){
			returnVal.put("positionTitle",partyrelationship.getPositionTitle());
}

		if(partyrelationship.getComments() != null ){
			returnVal.put("comments",partyrelationship.getComments());
}

		return returnVal;
}


	public static PartyRelationship map(Map<String, Object> fields) {

		PartyRelationship returnVal = new PartyRelationship();

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("relationshipName") != null) {
			returnVal.setRelationshipName((String) fields.get("relationshipName"));
}

		if(fields.get("securityGroupId") != null) {
			returnVal.setSecurityGroupId((String) fields.get("securityGroupId"));
}

		if(fields.get("priorityTypeId") != null) {
			returnVal.setPriorityTypeId((String) fields.get("priorityTypeId"));
}

		if(fields.get("partyRelationshipTypeId") != null) {
			returnVal.setPartyRelationshipTypeId((String) fields.get("partyRelationshipTypeId"));
}

		if(fields.get("permissionsEnumId") != null) {
			returnVal.setPermissionsEnumId((String) fields.get("permissionsEnumId"));
}

		if(fields.get("positionTitle") != null) {
			returnVal.setPositionTitle((String) fields.get("positionTitle"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PartyRelationship mapstrstr(Map<String, String> fields) throws Exception {

		PartyRelationship returnVal = new PartyRelationship();

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("relationshipName") != null) {
			returnVal.setRelationshipName((String) fields.get("relationshipName"));
}

		if(fields.get("securityGroupId") != null) {
			returnVal.setSecurityGroupId((String) fields.get("securityGroupId"));
}

		if(fields.get("priorityTypeId") != null) {
			returnVal.setPriorityTypeId((String) fields.get("priorityTypeId"));
}

		if(fields.get("partyRelationshipTypeId") != null) {
			returnVal.setPartyRelationshipTypeId((String) fields.get("partyRelationshipTypeId"));
}

		if(fields.get("permissionsEnumId") != null) {
			returnVal.setPermissionsEnumId((String) fields.get("permissionsEnumId"));
}

		if(fields.get("positionTitle") != null) {
			returnVal.setPositionTitle((String) fields.get("positionTitle"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PartyRelationship map(GenericValue val) {

PartyRelationship returnVal = new PartyRelationship();
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setRelationshipName(val.getString("relationshipName"));
		returnVal.setSecurityGroupId(val.getString("securityGroupId"));
		returnVal.setPriorityTypeId(val.getString("priorityTypeId"));
		returnVal.setPartyRelationshipTypeId(val.getString("partyRelationshipTypeId"));
		returnVal.setPermissionsEnumId(val.getString("permissionsEnumId"));
		returnVal.setPositionTitle(val.getString("positionTitle"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PartyRelationship map(HttpServletRequest request) throws Exception {

		PartyRelationship returnVal = new PartyRelationship();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyIdFrom")) {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}

		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("roleTypeIdFrom"))  {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}
		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("relationshipName"))  {
returnVal.setRelationshipName(request.getParameter("relationshipName"));
}
		if(paramMap.containsKey("securityGroupId"))  {
returnVal.setSecurityGroupId(request.getParameter("securityGroupId"));
}
		if(paramMap.containsKey("priorityTypeId"))  {
returnVal.setPriorityTypeId(request.getParameter("priorityTypeId"));
}
		if(paramMap.containsKey("partyRelationshipTypeId"))  {
returnVal.setPartyRelationshipTypeId(request.getParameter("partyRelationshipTypeId"));
}
		if(paramMap.containsKey("permissionsEnumId"))  {
returnVal.setPermissionsEnumId(request.getParameter("permissionsEnumId"));
}
		if(paramMap.containsKey("positionTitle"))  {
returnVal.setPositionTitle(request.getParameter("positionTitle"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
