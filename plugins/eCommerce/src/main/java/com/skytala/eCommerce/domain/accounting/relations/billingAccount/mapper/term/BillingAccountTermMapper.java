package com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.term;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.term.BillingAccountTerm;

public class BillingAccountTermMapper  {


	public static Map<String, Object> map(BillingAccountTerm billingaccountterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(billingaccountterm.getBillingAccountTermId() != null ){
			returnVal.put("billingAccountTermId",billingaccountterm.getBillingAccountTermId());
}

		if(billingaccountterm.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",billingaccountterm.getBillingAccountId());
}

		if(billingaccountterm.getTermTypeId() != null ){
			returnVal.put("termTypeId",billingaccountterm.getTermTypeId());
}

		if(billingaccountterm.getTermValue() != null ){
			returnVal.put("termValue",billingaccountterm.getTermValue());
}

		if(billingaccountterm.getTermDays() != null ){
			returnVal.put("termDays",billingaccountterm.getTermDays());
}

		if(billingaccountterm.getUomId() != null ){
			returnVal.put("uomId",billingaccountterm.getUomId());
}

		return returnVal;
}


	public static BillingAccountTerm map(Map<String, Object> fields) {

		BillingAccountTerm returnVal = new BillingAccountTerm();

		if(fields.get("billingAccountTermId") != null) {
			returnVal.setBillingAccountTermId((String) fields.get("billingAccountTermId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("termValue") != null) {
			returnVal.setTermValue((BigDecimal) fields.get("termValue"));
}

		if(fields.get("termDays") != null) {
			returnVal.setTermDays((long) fields.get("termDays"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static BillingAccountTerm mapstrstr(Map<String, String> fields) throws Exception {

		BillingAccountTerm returnVal = new BillingAccountTerm();

		if(fields.get("billingAccountTermId") != null) {
			returnVal.setBillingAccountTermId((String) fields.get("billingAccountTermId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("termValue") != null) {
String buf;
buf = fields.get("termValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}

		if(fields.get("termDays") != null) {
String buf;
buf = fields.get("termDays");
long ibuf = Long.parseLong(buf);
			returnVal.setTermDays(ibuf);
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static BillingAccountTerm map(GenericValue val) {

BillingAccountTerm returnVal = new BillingAccountTerm();
		returnVal.setBillingAccountTermId(val.getString("billingAccountTermId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setTermValue(val.getBigDecimal("termValue"));
		returnVal.setTermDays(val.getLong("termDays"));
		returnVal.setUomId(val.getString("uomId"));


return returnVal;

}

public static BillingAccountTerm map(HttpServletRequest request) throws Exception {

		BillingAccountTerm returnVal = new BillingAccountTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("billingAccountTermId")) {
returnVal.setBillingAccountTermId(request.getParameter("billingAccountTermId"));
}

		if(paramMap.containsKey("billingAccountId"))  {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}
		if(paramMap.containsKey("termTypeId"))  {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}
		if(paramMap.containsKey("termValue"))  {
String buf = request.getParameter("termValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}
		if(paramMap.containsKey("termDays"))  {
String buf = request.getParameter("termDays");
Long ibuf = Long.parseLong(buf);
returnVal.setTermDays(ibuf);
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
return returnVal;

}
}
