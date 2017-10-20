package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;

public class PartyFixedAssetAssignmentMapper  {


	public static Map<String, Object> map(PartyFixedAssetAssignment partyfixedassetassignment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyfixedassetassignment.getPartyId() != null ){
			returnVal.put("partyId",partyfixedassetassignment.getPartyId());
}

		if(partyfixedassetassignment.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partyfixedassetassignment.getRoleTypeId());
}

		if(partyfixedassetassignment.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",partyfixedassetassignment.getFixedAssetId());
}

		if(partyfixedassetassignment.getFromDate() != null ){
			returnVal.put("fromDate",partyfixedassetassignment.getFromDate());
}

		if(partyfixedassetassignment.getThruDate() != null ){
			returnVal.put("thruDate",partyfixedassetassignment.getThruDate());
}

		if(partyfixedassetassignment.getAllocatedDate() != null ){
			returnVal.put("allocatedDate",partyfixedassetassignment.getAllocatedDate());
}

		if(partyfixedassetassignment.getStatusId() != null ){
			returnVal.put("statusId",partyfixedassetassignment.getStatusId());
}

		if(partyfixedassetassignment.getComments() != null ){
			returnVal.put("comments",partyfixedassetassignment.getComments());
}

		return returnVal;
}


	public static PartyFixedAssetAssignment map(Map<String, Object> fields) {

		PartyFixedAssetAssignment returnVal = new PartyFixedAssetAssignment();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("allocatedDate") != null) {
			returnVal.setAllocatedDate((Timestamp) fields.get("allocatedDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PartyFixedAssetAssignment mapstrstr(Map<String, String> fields) throws Exception {

		PartyFixedAssetAssignment returnVal = new PartyFixedAssetAssignment();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
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

		if(fields.get("allocatedDate") != null) {
String buf = fields.get("allocatedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAllocatedDate(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PartyFixedAssetAssignment map(GenericValue val) {

PartyFixedAssetAssignment returnVal = new PartyFixedAssetAssignment();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAllocatedDate(val.getTimestamp("allocatedDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PartyFixedAssetAssignment map(HttpServletRequest request) throws Exception {

		PartyFixedAssetAssignment returnVal = new PartyFixedAssetAssignment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
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
		if(paramMap.containsKey("allocatedDate"))  {
String buf = request.getParameter("allocatedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAllocatedDate(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
