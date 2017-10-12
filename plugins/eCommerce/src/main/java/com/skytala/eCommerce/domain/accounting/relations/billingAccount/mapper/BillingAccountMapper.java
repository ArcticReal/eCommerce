package com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;

public class BillingAccountMapper  {


	public static Map<String, Object> map(BillingAccount billingaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(billingaccount.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",billingaccount.getBillingAccountId());
}

		if(billingaccount.getAccountLimit() != null ){
			returnVal.put("accountLimit",billingaccount.getAccountLimit());
}

		if(billingaccount.getAccountCurrencyUomId() != null ){
			returnVal.put("accountCurrencyUomId",billingaccount.getAccountCurrencyUomId());
}

		if(billingaccount.getContactMechId() != null ){
			returnVal.put("contactMechId",billingaccount.getContactMechId());
}

		if(billingaccount.getFromDate() != null ){
			returnVal.put("fromDate",billingaccount.getFromDate());
}

		if(billingaccount.getThruDate() != null ){
			returnVal.put("thruDate",billingaccount.getThruDate());
}

		if(billingaccount.getDescription() != null ){
			returnVal.put("description",billingaccount.getDescription());
}

		if(billingaccount.getExternalAccountId() != null ){
			returnVal.put("externalAccountId",billingaccount.getExternalAccountId());
}

		return returnVal;
}


	public static BillingAccount map(Map<String, Object> fields) {

		BillingAccount returnVal = new BillingAccount();

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("accountLimit") != null) {
			returnVal.setAccountLimit((BigDecimal) fields.get("accountLimit"));
}

		if(fields.get("accountCurrencyUomId") != null) {
			returnVal.setAccountCurrencyUomId((String) fields.get("accountCurrencyUomId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("externalAccountId") != null) {
			returnVal.setExternalAccountId((String) fields.get("externalAccountId"));
}


		return returnVal;
 } 
	public static BillingAccount mapstrstr(Map<String, String> fields) throws Exception {

		BillingAccount returnVal = new BillingAccount();

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("accountLimit") != null) {
String buf;
buf = fields.get("accountLimit");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountLimit(bd);
}

		if(fields.get("accountCurrencyUomId") != null) {
			returnVal.setAccountCurrencyUomId((String) fields.get("accountCurrencyUomId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("externalAccountId") != null) {
			returnVal.setExternalAccountId((String) fields.get("externalAccountId"));
}


		return returnVal;
 } 
	public static BillingAccount map(GenericValue val) {

BillingAccount returnVal = new BillingAccount();
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setAccountLimit(val.getBigDecimal("accountLimit"));
		returnVal.setAccountCurrencyUomId(val.getString("accountCurrencyUomId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setExternalAccountId(val.getString("externalAccountId"));


return returnVal;

}

public static BillingAccount map(HttpServletRequest request) throws Exception {

		BillingAccount returnVal = new BillingAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("billingAccountId")) {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}

		if(paramMap.containsKey("accountLimit"))  {
String buf = request.getParameter("accountLimit");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountLimit(bd);
}
		if(paramMap.containsKey("accountCurrencyUomId"))  {
returnVal.setAccountCurrencyUomId(request.getParameter("accountCurrencyUomId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("externalAccountId"))  {
returnVal.setExternalAccountId(request.getParameter("externalAccountId"));
}
return returnVal;

}
}
