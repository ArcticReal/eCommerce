package com.skytala.eCommerce.domain.party.relations.agreement.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;

public class AgreementTypeAttrMapper  {


	public static Map<String, Object> map(AgreementTypeAttr agreementtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementtypeattr.getAgreementTypeId() != null ){
			returnVal.put("agreementTypeId",agreementtypeattr.getAgreementTypeId());
}

		if(agreementtypeattr.getAttrName() != null ){
			returnVal.put("attrName",agreementtypeattr.getAttrName());
}

		if(agreementtypeattr.getDescription() != null ){
			returnVal.put("description",agreementtypeattr.getDescription());
}

		return returnVal;
}


	public static AgreementTypeAttr map(Map<String, Object> fields) {

		AgreementTypeAttr returnVal = new AgreementTypeAttr();

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		AgreementTypeAttr returnVal = new AgreementTypeAttr();

		if(fields.get("agreementTypeId") != null) {
			returnVal.setAgreementTypeId((String) fields.get("agreementTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementTypeAttr map(GenericValue val) {

AgreementTypeAttr returnVal = new AgreementTypeAttr();
		returnVal.setAgreementTypeId(val.getString("agreementTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementTypeAttr map(HttpServletRequest request) throws Exception {

		AgreementTypeAttr returnVal = new AgreementTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementTypeId")) {
returnVal.setAgreementTypeId(request.getParameter("agreementTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
