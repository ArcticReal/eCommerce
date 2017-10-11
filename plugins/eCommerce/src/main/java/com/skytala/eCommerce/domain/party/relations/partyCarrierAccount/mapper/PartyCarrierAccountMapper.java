package com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.model.PartyCarrierAccount;

public class PartyCarrierAccountMapper  {


	public static Map<String, Object> map(PartyCarrierAccount partycarrieraccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partycarrieraccount.getPartyId() != null ){
			returnVal.put("partyId",partycarrieraccount.getPartyId());
}

		if(partycarrieraccount.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",partycarrieraccount.getCarrierPartyId());
}

		if(partycarrieraccount.getFromDate() != null ){
			returnVal.put("fromDate",partycarrieraccount.getFromDate());
}

		if(partycarrieraccount.getThruDate() != null ){
			returnVal.put("thruDate",partycarrieraccount.getThruDate());
}

		if(partycarrieraccount.getAccountNumber() != null ){
			returnVal.put("accountNumber",partycarrieraccount.getAccountNumber());
}

		return returnVal;
}


	public static PartyCarrierAccount map(Map<String, Object> fields) {

		PartyCarrierAccount returnVal = new PartyCarrierAccount();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}


		return returnVal;
 } 
	public static PartyCarrierAccount mapstrstr(Map<String, String> fields) throws Exception {

		PartyCarrierAccount returnVal = new PartyCarrierAccount();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
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

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}


		return returnVal;
 } 
	public static PartyCarrierAccount map(GenericValue val) {

PartyCarrierAccount returnVal = new PartyCarrierAccount();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAccountNumber(val.getString("accountNumber"));


return returnVal;

}

public static PartyCarrierAccount map(HttpServletRequest request) throws Exception {

		PartyCarrierAccount returnVal = new PartyCarrierAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
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
		if(paramMap.containsKey("accountNumber"))  {
returnVal.setAccountNumber(request.getParameter("accountNumber"));
}
return returnVal;

}
}
