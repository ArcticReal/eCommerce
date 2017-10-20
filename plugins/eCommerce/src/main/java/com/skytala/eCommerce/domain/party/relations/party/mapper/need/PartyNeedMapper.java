package com.skytala.eCommerce.domain.party.relations.party.mapper.need;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.need.PartyNeed;

public class PartyNeedMapper  {


	public static Map<String, Object> map(PartyNeed partyneed) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyneed.getPartyNeedId() != null ){
			returnVal.put("partyNeedId",partyneed.getPartyNeedId());
}

		if(partyneed.getPartyId() != null ){
			returnVal.put("partyId",partyneed.getPartyId());
}

		if(partyneed.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partyneed.getRoleTypeId());
}

		if(partyneed.getPartyTypeId() != null ){
			returnVal.put("partyTypeId",partyneed.getPartyTypeId());
}

		if(partyneed.getNeedTypeId() != null ){
			returnVal.put("needTypeId",partyneed.getNeedTypeId());
}

		if(partyneed.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",partyneed.getCommunicationEventId());
}

		if(partyneed.getProductId() != null ){
			returnVal.put("productId",partyneed.getProductId());
}

		if(partyneed.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",partyneed.getProductCategoryId());
}

		if(partyneed.getVisitId() != null ){
			returnVal.put("visitId",partyneed.getVisitId());
}

		if(partyneed.getDatetimeRecorded() != null ){
			returnVal.put("datetimeRecorded",partyneed.getDatetimeRecorded());
}

		if(partyneed.getDescription() != null ){
			returnVal.put("description",partyneed.getDescription());
}

		return returnVal;
}


	public static PartyNeed map(Map<String, Object> fields) {

		PartyNeed returnVal = new PartyNeed();

		if(fields.get("partyNeedId") != null) {
			returnVal.setPartyNeedId((String) fields.get("partyNeedId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
}

		if(fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("datetimeRecorded") != null) {
			returnVal.setDatetimeRecorded((Timestamp) fields.get("datetimeRecorded"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyNeed mapstrstr(Map<String, String> fields) throws Exception {

		PartyNeed returnVal = new PartyNeed();

		if(fields.get("partyNeedId") != null) {
			returnVal.setPartyNeedId((String) fields.get("partyNeedId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("partyTypeId") != null) {
			returnVal.setPartyTypeId((String) fields.get("partyTypeId"));
}

		if(fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("datetimeRecorded") != null) {
String buf = fields.get("datetimeRecorded");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeRecorded(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PartyNeed map(GenericValue val) {

PartyNeed returnVal = new PartyNeed();
		returnVal.setPartyNeedId(val.getString("partyNeedId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setPartyTypeId(val.getString("partyTypeId"));
		returnVal.setNeedTypeId(val.getString("needTypeId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setDatetimeRecorded(val.getTimestamp("datetimeRecorded"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PartyNeed map(HttpServletRequest request) throws Exception {

		PartyNeed returnVal = new PartyNeed();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyNeedId")) {
returnVal.setPartyNeedId(request.getParameter("partyNeedId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("partyTypeId"))  {
returnVal.setPartyTypeId(request.getParameter("partyTypeId"));
}
		if(paramMap.containsKey("needTypeId"))  {
returnVal.setNeedTypeId(request.getParameter("needTypeId"));
}
		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
		if(paramMap.containsKey("visitId"))  {
returnVal.setVisitId(request.getParameter("visitId"));
}
		if(paramMap.containsKey("datetimeRecorded"))  {
String buf = request.getParameter("datetimeRecorded");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeRecorded(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
