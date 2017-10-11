package com.skytala.eCommerce.domain.party.relations.agreementContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementContent.model.AgreementContent;

public class AgreementContentMapper  {


	public static Map<String, Object> map(AgreementContent agreementcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementcontent.getAgreementId() != null ){
			returnVal.put("agreementId",agreementcontent.getAgreementId());
}

		if(agreementcontent.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementcontent.getAgreementItemSeqId());
}

		if(agreementcontent.getAgreementContentTypeId() != null ){
			returnVal.put("agreementContentTypeId",agreementcontent.getAgreementContentTypeId());
}

		if(agreementcontent.getContentId() != null ){
			returnVal.put("contentId",agreementcontent.getContentId());
}

		if(agreementcontent.getFromDate() != null ){
			returnVal.put("fromDate",agreementcontent.getFromDate());
}

		if(agreementcontent.getThruDate() != null ){
			returnVal.put("thruDate",agreementcontent.getThruDate());
}

		return returnVal;
}


	public static AgreementContent map(Map<String, Object> fields) {

		AgreementContent returnVal = new AgreementContent();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("agreementContentTypeId") != null) {
			returnVal.setAgreementContentTypeId((String) fields.get("agreementContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static AgreementContent mapstrstr(Map<String, String> fields) throws Exception {

		AgreementContent returnVal = new AgreementContent();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("agreementContentTypeId") != null) {
			returnVal.setAgreementContentTypeId((String) fields.get("agreementContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
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
	public static AgreementContent map(GenericValue val) {

AgreementContent returnVal = new AgreementContent();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setAgreementContentTypeId(val.getString("agreementContentTypeId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static AgreementContent map(HttpServletRequest request) throws Exception {

		AgreementContent returnVal = new AgreementContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("agreementContentTypeId"))  {
returnVal.setAgreementContentTypeId(request.getParameter("agreementContentTypeId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
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
