package com.skytala.eCommerce.domain.party.relations.agreement.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;

public class AgreementAttributeMapper  {


	public static Map<String, Object> map(AgreementAttribute agreementattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementattribute.getAgreementId() != null ){
			returnVal.put("agreementId",agreementattribute.getAgreementId());
}

		if(agreementattribute.getAttrName() != null ){
			returnVal.put("attrName",agreementattribute.getAttrName());
}

		if(agreementattribute.getAttrValue() != null ){
			returnVal.put("attrValue",agreementattribute.getAttrValue());
}

		if(agreementattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",agreementattribute.getAttrDescription());
}

		return returnVal;
}


	public static AgreementAttribute map(Map<String, Object> fields) {

		AgreementAttribute returnVal = new AgreementAttribute();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static AgreementAttribute mapstrstr(Map<String, String> fields) throws Exception {

		AgreementAttribute returnVal = new AgreementAttribute();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static AgreementAttribute map(GenericValue val) {

AgreementAttribute returnVal = new AgreementAttribute();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static AgreementAttribute map(HttpServletRequest request) throws Exception {

		AgreementAttribute returnVal = new AgreementAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
