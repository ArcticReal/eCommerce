package com.skytala.eCommerce.domain.party.relations.agreementItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementItem.model.AgreementItem;

public class AgreementItemMapper  {


	public static Map<String, Object> map(AgreementItem agreementitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementitem.getAgreementId() != null ){
			returnVal.put("agreementId",agreementitem.getAgreementId());
}

		if(agreementitem.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementitem.getAgreementItemSeqId());
}

		if(agreementitem.getAgreementItemTypeId() != null ){
			returnVal.put("agreementItemTypeId",agreementitem.getAgreementItemTypeId());
}

		if(agreementitem.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",agreementitem.getCurrencyUomId());
}

		if(agreementitem.getAgreementText() != null ){
			returnVal.put("agreementText",agreementitem.getAgreementText());
}

		if(agreementitem.getAgreementImage() != null ){
			returnVal.put("agreementImage",agreementitem.getAgreementImage());
}

		return returnVal;
}


	public static AgreementItem map(Map<String, Object> fields) {

		AgreementItem returnVal = new AgreementItem();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("agreementText") != null) {
			returnVal.setAgreementText((String) fields.get("agreementText"));
}

		if(fields.get("agreementImage") != null) {
			returnVal.setAgreementImage((Object) fields.get("agreementImage"));
}


		return returnVal;
 } 
	public static AgreementItem mapstrstr(Map<String, String> fields) throws Exception {

		AgreementItem returnVal = new AgreementItem();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("agreementItemTypeId") != null) {
			returnVal.setAgreementItemTypeId((String) fields.get("agreementItemTypeId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("agreementText") != null) {
			returnVal.setAgreementText((String) fields.get("agreementText"));
}

		if(fields.get("agreementImage") != null) {
String buf = fields.get("agreementImage");
Object ibuf = buf;
returnVal.setAgreementImage(ibuf);
}


		return returnVal;
 } 
	public static AgreementItem map(GenericValue val) {

AgreementItem returnVal = new AgreementItem();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setAgreementItemTypeId(val.getString("agreementItemTypeId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setAgreementText(val.getString("agreementText"));
		returnVal.setAgreementImage(val.get("agreementImage"));


return returnVal;

}

public static AgreementItem map(HttpServletRequest request) throws Exception {

		AgreementItem returnVal = new AgreementItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("agreementItemTypeId"))  {
returnVal.setAgreementItemTypeId(request.getParameter("agreementItemTypeId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("agreementText"))  {
returnVal.setAgreementText(request.getParameter("agreementText"));
}
		if(paramMap.containsKey("agreementImage"))  {
String buf = request.getParameter("agreementImage");
Object ibuf = buf;
returnVal.setAgreementImage(ibuf);
}
return returnVal;

}
}
