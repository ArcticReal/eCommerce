package com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementPartyApplic.model.AgreementPartyApplic;

public class AgreementPartyApplicMapper  {


	public static Map<String, Object> map(AgreementPartyApplic agreementpartyapplic) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementpartyapplic.getAgreementId() != null ){
			returnVal.put("agreementId",agreementpartyapplic.getAgreementId());
}

		if(agreementpartyapplic.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementpartyapplic.getAgreementItemSeqId());
}

		if(agreementpartyapplic.getPartyId() != null ){
			returnVal.put("partyId",agreementpartyapplic.getPartyId());
}

		return returnVal;
}


	public static AgreementPartyApplic map(Map<String, Object> fields) {

		AgreementPartyApplic returnVal = new AgreementPartyApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static AgreementPartyApplic mapstrstr(Map<String, String> fields) throws Exception {

		AgreementPartyApplic returnVal = new AgreementPartyApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}


		return returnVal;
 } 
	public static AgreementPartyApplic map(GenericValue val) {

AgreementPartyApplic returnVal = new AgreementPartyApplic();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setPartyId(val.getString("partyId"));


return returnVal;

}

public static AgreementPartyApplic map(HttpServletRequest request) throws Exception {

		AgreementPartyApplic returnVal = new AgreementPartyApplic();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
return returnVal;

}
}
