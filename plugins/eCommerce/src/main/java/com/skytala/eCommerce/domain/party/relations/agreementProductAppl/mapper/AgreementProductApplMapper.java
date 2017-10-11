package com.skytala.eCommerce.domain.party.relations.agreementProductAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;

public class AgreementProductApplMapper  {


	public static Map<String, Object> map(AgreementProductAppl agreementproductappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(agreementproductappl.getAgreementId() != null ){
			returnVal.put("agreementId",agreementproductappl.getAgreementId());
}

		if(agreementproductappl.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",agreementproductappl.getAgreementItemSeqId());
}

		if(agreementproductappl.getProductId() != null ){
			returnVal.put("productId",agreementproductappl.getProductId());
}

		if(agreementproductappl.getPrice() != null ){
			returnVal.put("price",agreementproductappl.getPrice());
}

		return returnVal;
}


	public static AgreementProductAppl map(Map<String, Object> fields) {

		AgreementProductAppl returnVal = new AgreementProductAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("price") != null) {
			returnVal.setPrice((BigDecimal) fields.get("price"));
}


		return returnVal;
 } 
	public static AgreementProductAppl mapstrstr(Map<String, String> fields) throws Exception {

		AgreementProductAppl returnVal = new AgreementProductAppl();

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("price") != null) {
String buf;
buf = fields.get("price");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPrice(bd);
}


		return returnVal;
 } 
	public static AgreementProductAppl map(GenericValue val) {

AgreementProductAppl returnVal = new AgreementProductAppl();
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPrice(val.getBigDecimal("price"));


return returnVal;

}

public static AgreementProductAppl map(HttpServletRequest request) throws Exception {

		AgreementProductAppl returnVal = new AgreementProductAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("agreementId")) {
returnVal.setAgreementId(request.getParameter("agreementId"));
}

		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("price"))  {
String buf = request.getParameter("price");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPrice(bd);
}
return returnVal;

}
}
