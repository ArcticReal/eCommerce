package com.skytala.eCommerce.domain.party.relations.agreementRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementRole.model.AgreementRole;

public class AgreementRoleMapper  {


	public static Map<String, Object> map(AgreementRole agreementrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementrole.getAgreementId() != null ){
			returnVal.put("agreementId",agreementrole.getAgreementId());
}

		if(agreementrole.getPartyId() != null ){
			returnVal.put("partyId",agreementrole.getPartyId());
}

		if(agreementrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",agreementrole.getRoleTypeId());
}

		return returnVal;
}


	public static AgreementRole map(Map<String, Object> fields) {

		AgreementRole returnVal = new AgreementRole();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static AgreementRole mapstrstr(Map<String, String> fields) throws Exception {

		AgreementRole returnVal = new AgreementRole();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static AgreementRole map(GenericValue val) {

AgreementRole returnVal = new AgreementRole();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static AgreementRole map(HttpServletRequest request) throws Exception {

		AgreementRole returnVal = new AgreementRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
