package com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;

public class PartyQualMapper  {


	public static Map<String, Object> map(PartyQual partyqual) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyqual.getPartyId() != null ){
			returnVal.put("partyId",partyqual.getPartyId());
}

		if(partyqual.getPartyQualTypeId() != null ){
			returnVal.put("partyQualTypeId",partyqual.getPartyQualTypeId());
}

		if(partyqual.getQualificationDesc() != null ){
			returnVal.put("qualificationDesc",partyqual.getQualificationDesc());
}

		if(partyqual.getTitle() != null ){
			returnVal.put("title",partyqual.getTitle());
}

		if(partyqual.getStatusId() != null ){
			returnVal.put("statusId",partyqual.getStatusId());
}

		if(partyqual.getVerifStatusId() != null ){
			returnVal.put("verifStatusId",partyqual.getVerifStatusId());
}

		if(partyqual.getFromDate() != null ){
			returnVal.put("fromDate",partyqual.getFromDate());
}

		if(partyqual.getThruDate() != null ){
			returnVal.put("thruDate",partyqual.getThruDate());
}

		return returnVal;
}


	public static PartyQual map(Map<String, Object> fields) {

		PartyQual returnVal = new PartyQual();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyQualTypeId") != null) {
			returnVal.setPartyQualTypeId((String) fields.get("partyQualTypeId"));
}

		if(fields.get("qualificationDesc") != null) {
			returnVal.setQualificationDesc((String) fields.get("qualificationDesc"));
}

		if(fields.get("title") != null) {
			returnVal.setTitle((String) fields.get("title"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("verifStatusId") != null) {
			returnVal.setVerifStatusId((String) fields.get("verifStatusId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PartyQual mapstrstr(Map<String, String> fields) throws Exception {

		PartyQual returnVal = new PartyQual();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyQualTypeId") != null) {
			returnVal.setPartyQualTypeId((String) fields.get("partyQualTypeId"));
}

		if(fields.get("qualificationDesc") != null) {
			returnVal.setQualificationDesc((String) fields.get("qualificationDesc"));
}

		if(fields.get("title") != null) {
			returnVal.setTitle((String) fields.get("title"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("verifStatusId") != null) {
			returnVal.setVerifStatusId((String) fields.get("verifStatusId"));
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


		return returnVal;
 } 
	public static PartyQual map(GenericValue val) {

PartyQual returnVal = new PartyQual();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setPartyQualTypeId(val.getString("partyQualTypeId"));
		returnVal.setQualificationDesc(val.getString("qualificationDesc"));
		returnVal.setTitle(val.getString("title"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setVerifStatusId(val.getString("verifStatusId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyQual map(HttpServletRequest request) throws Exception {

		PartyQual returnVal = new PartyQual();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("partyQualTypeId"))  {
returnVal.setPartyQualTypeId(request.getParameter("partyQualTypeId"));
}
		if(paramMap.containsKey("qualificationDesc"))  {
returnVal.setQualificationDesc(request.getParameter("qualificationDesc"));
}
		if(paramMap.containsKey("title"))  {
returnVal.setTitle(request.getParameter("title"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("verifStatusId"))  {
returnVal.setVerifStatusId(request.getParameter("verifStatusId"));
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
return returnVal;

}
}
