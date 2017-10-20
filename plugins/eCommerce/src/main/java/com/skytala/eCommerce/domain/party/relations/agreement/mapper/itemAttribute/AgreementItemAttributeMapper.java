package com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemAttribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;

public class AgreementItemAttributeMapper  {


	public static Map<String, Object> map(AgreementItemAttribute agreementitemattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementitemattribute.getAgreementId() != null ){
			returnVal.put("agreementId",agreementitemattribute.getAgreementId());
}

		if(agreementitemattribute.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementitemattribute.getAgreementItemSeqId());
}

		if(agreementitemattribute.getAttrName() != null ){
			returnVal.put("attrName",agreementitemattribute.getAttrName());
}

		if(agreementitemattribute.getAttrValue() != null ){
			returnVal.put("attrValue",agreementitemattribute.getAttrValue());
}

		if(agreementitemattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",agreementitemattribute.getAttrDescription());
}

		return returnVal;
}


	public static AgreementItemAttribute map(Map<String, Object> fields) {

		AgreementItemAttribute returnVal = new AgreementItemAttribute();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
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
	public static AgreementItemAttribute mapstrstr(Map<String, String> fields) throws Exception {

		AgreementItemAttribute returnVal = new AgreementItemAttribute();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
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
	public static AgreementItemAttribute map(GenericValue val) {

AgreementItemAttribute returnVal = new AgreementItemAttribute();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static AgreementItemAttribute map(HttpServletRequest request) throws Exception {

		AgreementItemAttribute returnVal = new AgreementItemAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
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
