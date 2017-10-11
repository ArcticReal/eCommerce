package com.skytala.eCommerce.domain.order.relations.respondingParty.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;

public class RespondingPartyMapper  {


	public static Map<String, Object> map(RespondingParty respondingparty) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(respondingparty.getRespondingPartySeqId() != null ){
			returnVal.put("respondingPartySeqId",respondingparty.getRespondingPartySeqId());
}

		if(respondingparty.getCustRequestId() != null ){
			returnVal.put("custRequestId",respondingparty.getCustRequestId());
}

		if(respondingparty.getPartyId() != null ){
			returnVal.put("partyId",respondingparty.getPartyId());
}

		if(respondingparty.getContactMechId() != null ){
			returnVal.put("contactMechId",respondingparty.getContactMechId());
}

		if(respondingparty.getDateSent() != null ){
			returnVal.put("dateSent",respondingparty.getDateSent());
}

		return returnVal;
}


	public static RespondingParty map(Map<String, Object> fields) {

		RespondingParty returnVal = new RespondingParty();

		if(fields.get("respondingPartySeqId") != null) {
			returnVal.setRespondingPartySeqId((String) fields.get("respondingPartySeqId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("dateSent") != null) {
			returnVal.setDateSent((Timestamp) fields.get("dateSent"));
}


		return returnVal;
 } 
	public static RespondingParty mapstrstr(Map<String, String> fields) throws Exception {

		RespondingParty returnVal = new RespondingParty();

		if(fields.get("respondingPartySeqId") != null) {
			returnVal.setRespondingPartySeqId((String) fields.get("respondingPartySeqId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("dateSent") != null) {
String buf = fields.get("dateSent");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateSent(ibuf);
}


		return returnVal;
 } 
	public static RespondingParty map(GenericValue val) {

RespondingParty returnVal = new RespondingParty();
		returnVal.setRespondingPartySeqId(val.getString("respondingPartySeqId"));
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setDateSent(val.getTimestamp("dateSent"));


return returnVal;

}

public static RespondingParty map(HttpServletRequest request) throws Exception {

		RespondingParty returnVal = new RespondingParty();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("respondingPartySeqId")) {
returnVal.setRespondingPartySeqId(request.getParameter("respondingPartySeqId"));
}

		if(paramMap.containsKey("custRequestId"))  {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("dateSent"))  {
String buf = request.getParameter("dateSent");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateSent(ibuf);
}
return returnVal;

}
}
