package com.skytala.eCommerce.domain.partyInvitation.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.partyInvitation.model.PartyInvitation;

public class PartyInvitationMapper  {


	public static Map<String, Object> map(PartyInvitation partyinvitation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyinvitation.getPartyInvitationId() != null ){
			returnVal.put("partyInvitationId",partyinvitation.getPartyInvitationId());
}

		if(partyinvitation.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",partyinvitation.getPartyIdFrom());
}

		if(partyinvitation.getPartyId() != null ){
			returnVal.put("partyId",partyinvitation.getPartyId());
}

		if(partyinvitation.getToName() != null ){
			returnVal.put("toName",partyinvitation.getToName());
}

		if(partyinvitation.getEmailAddress() != null ){
			returnVal.put("emailAddress",partyinvitation.getEmailAddress());
}

		if(partyinvitation.getStatusId() != null ){
			returnVal.put("statusId",partyinvitation.getStatusId());
}

		if(partyinvitation.getLastInviteDate() != null ){
			returnVal.put("lastInviteDate",partyinvitation.getLastInviteDate());
}

		return returnVal;
}


	public static PartyInvitation map(Map<String, Object> fields) {

		PartyInvitation returnVal = new PartyInvitation();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("toName") != null) {
			returnVal.setToName((String) fields.get("toName"));
}

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("lastInviteDate") != null) {
			returnVal.setLastInviteDate((Timestamp) fields.get("lastInviteDate"));
}


		return returnVal;
 } 
	public static PartyInvitation mapstrstr(Map<String, String> fields) throws Exception {

		PartyInvitation returnVal = new PartyInvitation();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("toName") != null) {
			returnVal.setToName((String) fields.get("toName"));
}

		if(fields.get("emailAddress") != null) {
			returnVal.setEmailAddress((String) fields.get("emailAddress"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("lastInviteDate") != null) {
String buf = fields.get("lastInviteDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastInviteDate(ibuf);
}


		return returnVal;
 } 
	public static PartyInvitation map(GenericValue val) {

PartyInvitation returnVal = new PartyInvitation();
		returnVal.setPartyInvitationId(val.getString("partyInvitationId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setToName(val.getString("toName"));
		returnVal.setEmailAddress(val.getString("emailAddress"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setLastInviteDate(val.getTimestamp("lastInviteDate"));


return returnVal;

}

public static PartyInvitation map(HttpServletRequest request) throws Exception {

		PartyInvitation returnVal = new PartyInvitation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyInvitationId")) {
returnVal.setPartyInvitationId(request.getParameter("partyInvitationId"));
}

		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("toName"))  {
returnVal.setToName(request.getParameter("toName"));
}
		if(paramMap.containsKey("emailAddress"))  {
returnVal.setEmailAddress(request.getParameter("emailAddress"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("lastInviteDate"))  {
String buf = request.getParameter("lastInviteDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastInviteDate(ibuf);
}
return returnVal;

}
}
