package com.skytala.eCommerce.domain.party.relations.agreement.mapper.employmentAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;

public class AgreementEmploymentApplMapper  {


	public static Map<String, Object> map(AgreementEmploymentAppl agreementemploymentappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementemploymentappl.getAgreementId() != null ){
			returnVal.put("agreementId",agreementemploymentappl.getAgreementId());
}

		if(agreementemploymentappl.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementemploymentappl.getAgreementItemSeqId());
}

		if(agreementemploymentappl.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",agreementemploymentappl.getPartyIdFrom());
}

		if(agreementemploymentappl.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",agreementemploymentappl.getPartyIdTo());
}

		if(agreementemploymentappl.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",agreementemploymentappl.getRoleTypeIdFrom());
}

		if(agreementemploymentappl.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",agreementemploymentappl.getRoleTypeIdTo());
}

		if(agreementemploymentappl.getFromDate() != null ){
			returnVal.put("fromDate",agreementemploymentappl.getFromDate());
}

		if(agreementemploymentappl.getAgreementDate() != null ){
			returnVal.put("agreementDate",agreementemploymentappl.getAgreementDate());
}

		if(agreementemploymentappl.getThruDate() != null ){
			returnVal.put("thruDate",agreementemploymentappl.getThruDate());
}

		return returnVal;
}


	public static AgreementEmploymentAppl map(Map<String, Object> fields) {

		AgreementEmploymentAppl returnVal = new AgreementEmploymentAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

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

		if(fields.get("agreementDate") != null) {
			returnVal.setAgreementDate((Timestamp) fields.get("agreementDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static AgreementEmploymentAppl mapstrstr(Map<String, String> fields) throws Exception {

		AgreementEmploymentAppl returnVal = new AgreementEmploymentAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

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

		if(fields.get("agreementDate") != null) {
String buf = fields.get("agreementDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAgreementDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static AgreementEmploymentAppl map(GenericValue val) {

AgreementEmploymentAppl returnVal = new AgreementEmploymentAppl();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setAgreementDate(val.getTimestamp("agreementDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static AgreementEmploymentAppl map(HttpServletRequest request) throws Exception {

		AgreementEmploymentAppl returnVal = new AgreementEmploymentAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
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
		if(paramMap.containsKey("agreementDate"))  {
String buf = request.getParameter("agreementDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAgreementDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
