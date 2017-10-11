package com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.model.AgreementItemTypeAttr;

public class AgreementItemTypeAttrMapper  {


	public static Map<String, Object> map(AgreementItemTypeAttr agreementitemtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementitemtypeattr.getAgreementItemTypeId() != null ){
			returnVal.put("agreementItemTypeId",agreementitemtypeattr.getAgreementItemTypeId());
}

		if(agreementitemtypeattr.getAttrName() != null ){
			returnVal.put("attrName",agreementitemtypeattr.getAttrName());
}

		if(agreementitemtypeattr.getDescription() != null ){
			returnVal.put("description",agreementitemtypeattr.getDescription());
}

		return returnVal;
}


	public static AgreementItemTypeAttr map(Map<String, Object> fields) {

		AgreementItemTypeAttr returnVal = new AgreementItemTypeAttr();

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementItemTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		AgreementItemTypeAttr returnVal = new AgreementItemTypeAttr();

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static AgreementItemTypeAttr map(GenericValue val) {

AgreementItemTypeAttr returnVal = new AgreementItemTypeAttr();
		returnVal.setAgreementItemTypeId(val.getString("agreementItemTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static AgreementItemTypeAttr map(HttpServletRequest request) throws Exception {

		AgreementItemTypeAttr returnVal = new AgreementItemTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementItemTypeId")) {
returnVal.setAgreementItemTypeId(request.getParameter("agreementItemTypeId"));
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
