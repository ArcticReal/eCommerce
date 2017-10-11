package com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementTermAttribute.model.AgreementTermAttribute;

public class AgreementTermAttributeMapper  {


	public static Map<String, Object> map(AgreementTermAttribute agreementtermattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementtermattribute.getAgreementTermId() != null ){
			returnVal.put("agreementTermId",agreementtermattribute.getAgreementTermId());
}

		if(agreementtermattribute.getAttrName() != null ){
			returnVal.put("attrName",agreementtermattribute.getAttrName());
}

		if(agreementtermattribute.getAttrValue() != null ){
			returnVal.put("attrValue",agreementtermattribute.getAttrValue());
}

		if(agreementtermattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",agreementtermattribute.getAttrDescription());
}

		return returnVal;
}


	public static AgreementTermAttribute map(Map<String, Object> fields) {

		AgreementTermAttribute returnVal = new AgreementTermAttribute();

		if(fields.get("agreementTermId") != null) {
			returnVal.setAgreementTermId((String) fields.get("agreementTermId"));
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
	public static AgreementTermAttribute mapstrstr(Map<String, String> fields) throws Exception {

		AgreementTermAttribute returnVal = new AgreementTermAttribute();

		if(fields.get("agreementTermId") != null) {
			returnVal.setAgreementTermId((String) fields.get("agreementTermId"));
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
	public static AgreementTermAttribute map(GenericValue val) {

AgreementTermAttribute returnVal = new AgreementTermAttribute();
		returnVal.setAgreementTermId(val.getString("agreementTermId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static AgreementTermAttribute map(HttpServletRequest request) throws Exception {

		AgreementTermAttribute returnVal = new AgreementTermAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementTermId")) {
returnVal.setAgreementTermId(request.getParameter("agreementTermId"));
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
