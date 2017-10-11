package com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.model.AgreementWorkEffortApplic;

public class AgreementWorkEffortApplicMapper  {


	public static Map<String, Object> map(AgreementWorkEffortApplic agreementworkeffortapplic) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementworkeffortapplic.getAgreementId() != null ){
			returnVal.put("agreementId",agreementworkeffortapplic.getAgreementId());
}

		if(agreementworkeffortapplic.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementworkeffortapplic.getAgreementItemSeqId());
}

		if(agreementworkeffortapplic.getWorkEffortId() != null ){
			returnVal.put("workEffortId",agreementworkeffortapplic.getWorkEffortId());
}

		return returnVal;
}


	public static AgreementWorkEffortApplic map(Map<String, Object> fields) {

		AgreementWorkEffortApplic returnVal = new AgreementWorkEffortApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static AgreementWorkEffortApplic mapstrstr(Map<String, String> fields) throws Exception {

		AgreementWorkEffortApplic returnVal = new AgreementWorkEffortApplic();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static AgreementWorkEffortApplic map(GenericValue val) {

AgreementWorkEffortApplic returnVal = new AgreementWorkEffortApplic();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static AgreementWorkEffortApplic map(HttpServletRequest request) throws Exception {

		AgreementWorkEffortApplic returnVal = new AgreementWorkEffortApplic();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
