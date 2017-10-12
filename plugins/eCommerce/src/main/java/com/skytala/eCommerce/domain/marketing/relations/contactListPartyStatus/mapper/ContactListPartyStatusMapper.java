package com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.model.ContactListPartyStatus;

public class ContactListPartyStatusMapper  {


	public static Map<String, Object> map(ContactListPartyStatus contactlistpartystatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactlistpartystatus.getContactListId() != null ){
			returnVal.put("contactListId",contactlistpartystatus.getContactListId());
}

		if(contactlistpartystatus.getPartyId() != null ){
			returnVal.put("partyId",contactlistpartystatus.getPartyId());
}

		if(contactlistpartystatus.getFromDate() != null ){
			returnVal.put("fromDate",contactlistpartystatus.getFromDate());
}

		if(contactlistpartystatus.getStatusDate() != null ){
			returnVal.put("statusDate",contactlistpartystatus.getStatusDate());
}

		if(contactlistpartystatus.getStatusId() != null ){
			returnVal.put("statusId",contactlistpartystatus.getStatusId());
}

		if(contactlistpartystatus.getSetByUserLoginId() != null ){
			returnVal.put("setByUserLoginId",contactlistpartystatus.getSetByUserLoginId());
}

		if(contactlistpartystatus.getOptInVerifyCode() != null ){
			returnVal.put("optInVerifyCode",contactlistpartystatus.getOptInVerifyCode());
}

		return returnVal;
}


	public static ContactListPartyStatus map(Map<String, Object> fields) {

		ContactListPartyStatus returnVal = new ContactListPartyStatus();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("setByUserLoginId") != null) {
			returnVal.setSetByUserLoginId((String) fields.get("setByUserLoginId"));
}

		if(fields.get("optInVerifyCode") != null) {
			returnVal.setOptInVerifyCode((String) fields.get("optInVerifyCode"));
}


		return returnVal;
 } 
	public static ContactListPartyStatus mapstrstr(Map<String, String> fields) throws Exception {

		ContactListPartyStatus returnVal = new ContactListPartyStatus();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("setByUserLoginId") != null) {
			returnVal.setSetByUserLoginId((String) fields.get("setByUserLoginId"));
}

		if(fields.get("optInVerifyCode") != null) {
			returnVal.setOptInVerifyCode((String) fields.get("optInVerifyCode"));
}


		return returnVal;
 } 
	public static ContactListPartyStatus map(GenericValue val) {

ContactListPartyStatus returnVal = new ContactListPartyStatus();
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setSetByUserLoginId(val.getString("setByUserLoginId"));
		returnVal.setOptInVerifyCode(val.getString("optInVerifyCode"));


return returnVal;

}

public static ContactListPartyStatus map(HttpServletRequest request) throws Exception {

		ContactListPartyStatus returnVal = new ContactListPartyStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactListId")) {
returnVal.setContactListId(request.getParameter("contactListId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("setByUserLoginId"))  {
returnVal.setSetByUserLoginId(request.getParameter("setByUserLoginId"));
}
		if(paramMap.containsKey("optInVerifyCode"))  {
returnVal.setOptInVerifyCode(request.getParameter("optInVerifyCode"));
}
return returnVal;

}
}
