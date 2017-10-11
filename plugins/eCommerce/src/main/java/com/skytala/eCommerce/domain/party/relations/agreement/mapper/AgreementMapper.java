package com.skytala.eCommerce.domain.party.relations.agreement.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.model.Agreement;

public class AgreementMapper  {


	public static Map<String, Object> map(Agreement agreement) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreement.getAgreementId() != null ){
			returnVal.put("agreementId",agreement.getAgreementId());
}

		if(agreement.getProductId() != null ){
			returnVal.put("productId",agreement.getProductId());
}

		if(agreement.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",agreement.getPartyIdFrom());
}

		if(agreement.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",agreement.getPartyIdTo());
}

		if(agreement.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",agreement.getRoleTypeIdFrom());
}

		if(agreement.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",agreement.getRoleTypeIdTo());
}

		if(agreement.getAgreementTypeId() != null ){
			returnVal.put("agreementTypeId",agreement.getAgreementTypeId());
}

		if(agreement.getAgreementDate() != null ){
			returnVal.put("agreementDate",agreement.getAgreementDate());
}

		if(agreement.getFromDate() != null ){
			returnVal.put("fromDate",agreement.getFromDate());
}

		if(agreement.getThruDate() != null ){
			returnVal.put("thruDate",agreement.getThruDate());
}

		if(agreement.getDescription() != null ){
			returnVal.put("description",agreement.getDescription());
}

		if(agreement.getTextData() != null ){
			returnVal.put("textData",agreement.getTextData());
}

		return returnVal;
}


	public static Agreement map(Map<String, Object> fields) {

		Agreement returnVal = new Agreement();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
}

		if(fields.get("agreementDate") != null) {
			returnVal.setAgreementDate((Timestamp) fields.get("agreementDate"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("textData") != null) {
			returnVal.setTextData((String) fields.get("textData"));
}


		return returnVal;
 } 
	public static Agreement mapstrstr(Map<String, String> fields) throws Exception {

		Agreement returnVal = new Agreement();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
}

		if(fields.get("agreementDate") != null) {
String buf = fields.get("agreementDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAgreementDate(ibuf);
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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("textData") != null) {
			returnVal.setTextData((String) fields.get("textData"));
}


		return returnVal;
 } 
	public static Agreement map(GenericValue val) {

Agreement returnVal = new Agreement();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setAgreementTypeId(val.getString("agreementTypeId"));
		returnVal.setAgreementDate(val.getTimestamp("agreementDate"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setTextData(val.getString("textData"));


return returnVal;

}

public static Agreement map(HttpServletRequest request) throws Exception {

		Agreement returnVal = new Agreement();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
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
		if(paramMap.containsKey("agreementTypeId"))  {
returnVal.setAgreementTypeId(request.getParameter("agreementTypeId"));
}
		if(paramMap.containsKey("agreementDate"))  {
String buf = request.getParameter("agreementDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAgreementDate(ibuf);
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
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("textData"))  {
returnVal.setTextData(request.getParameter("textData"));
}
return returnVal;

}
}
