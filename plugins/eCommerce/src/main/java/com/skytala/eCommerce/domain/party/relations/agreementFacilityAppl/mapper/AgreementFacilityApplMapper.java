package com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.model.AgreementFacilityAppl;

public class AgreementFacilityApplMapper  {


	public static Map<String, Object> map(AgreementFacilityAppl agreementfacilityappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementfacilityappl.getAgreementId() != null ){
			returnVal.put("agreementId",agreementfacilityappl.getAgreementId());
}

		if(agreementfacilityappl.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementfacilityappl.getAgreementItemSeqId());
}

		if(agreementfacilityappl.getFacilityId() != null ){
			returnVal.put("facilityId",agreementfacilityappl.getFacilityId());
}

		return returnVal;
}


	public static AgreementFacilityAppl map(Map<String, Object> fields) {

		AgreementFacilityAppl returnVal = new AgreementFacilityAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}


		return returnVal;
 } 
	public static AgreementFacilityAppl mapstrstr(Map<String, String> fields) throws Exception {

		AgreementFacilityAppl returnVal = new AgreementFacilityAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}


		return returnVal;
 } 
	public static AgreementFacilityAppl map(GenericValue val) {

AgreementFacilityAppl returnVal = new AgreementFacilityAppl();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setFacilityId(val.getString("facilityId"));


return returnVal;

}

public static AgreementFacilityAppl map(HttpServletRequest request) throws Exception {

		AgreementFacilityAppl returnVal = new AgreementFacilityAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
return returnVal;

}
}
