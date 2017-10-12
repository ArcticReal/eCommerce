package com.skytala.eCommerce.domain.accounting.relations.eftAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;

public class EftAccountMapper  {


	public static Map<String, Object> map(EftAccount eftaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(eftaccount.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",eftaccount.getPaymentMethodId());
}

		if(eftaccount.getBankName() != null ){
			returnVal.put("bankName",eftaccount.getBankName());
}

		if(eftaccount.getRoutingNumber() != null ){
			returnVal.put("routingNumber",eftaccount.getRoutingNumber());
}

		if(eftaccount.getAccountType() != null ){
			returnVal.put("accountType",eftaccount.getAccountType());
}

		if(eftaccount.getAccountNumber() != null ){
			returnVal.put("accountNumber",eftaccount.getAccountNumber());
}

		if(eftaccount.getNameOnAccount() != null ){
			returnVal.put("nameOnAccount",eftaccount.getNameOnAccount());
}

		if(eftaccount.getCompanyNameOnAccount() != null ){
			returnVal.put("companyNameOnAccount",eftaccount.getCompanyNameOnAccount());
}

		if(eftaccount.getContactMechId() != null ){
			returnVal.put("contactMechId",eftaccount.getContactMechId());
}

		if(eftaccount.getYearsAtBank() != null ){
			returnVal.put("yearsAtBank",eftaccount.getYearsAtBank());
}

		return returnVal;
}


	public static EftAccount map(Map<String, Object> fields) {

		EftAccount returnVal = new EftAccount();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("bankName") != null) {
			returnVal.setBankName((String) fields.get("bankName"));
}

		if(fields.get("routingNumber") != null) {
			returnVal.setRoutingNumber((String) fields.get("routingNumber"));
}

		if(fields.get("accountType") != null) {
			returnVal.setAccountType((String) fields.get("accountType"));
}

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}

		if(fields.get("nameOnAccount") != null) {
			returnVal.setNameOnAccount((String) fields.get("nameOnAccount"));
}

		if(fields.get("companyNameOnAccount") != null) {
			returnVal.setCompanyNameOnAccount((String) fields.get("companyNameOnAccount"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("yearsAtBank") != null) {
			returnVal.setYearsAtBank((long) fields.get("yearsAtBank"));
}


		return returnVal;
 } 
	public static EftAccount mapstrstr(Map<String, String> fields) throws Exception {

		EftAccount returnVal = new EftAccount();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("bankName") != null) {
			returnVal.setBankName((String) fields.get("bankName"));
}

		if(fields.get("routingNumber") != null) {
			returnVal.setRoutingNumber((String) fields.get("routingNumber"));
}

		if(fields.get("accountType") != null) {
			returnVal.setAccountType((String) fields.get("accountType"));
}

		if(fields.get("accountNumber") != null) {
			returnVal.setAccountNumber((String) fields.get("accountNumber"));
}

		if(fields.get("nameOnAccount") != null) {
			returnVal.setNameOnAccount((String) fields.get("nameOnAccount"));
}

		if(fields.get("companyNameOnAccount") != null) {
			returnVal.setCompanyNameOnAccount((String) fields.get("companyNameOnAccount"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("yearsAtBank") != null) {
String buf;
buf = fields.get("yearsAtBank");
long ibuf = Long.parseLong(buf);
			returnVal.setYearsAtBank(ibuf);
}


		return returnVal;
 } 
	public static EftAccount map(GenericValue val) {

EftAccount returnVal = new EftAccount();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setBankName(val.getString("bankName"));
		returnVal.setRoutingNumber(val.getString("routingNumber"));
		returnVal.setAccountType(val.getString("accountType"));
		returnVal.setAccountNumber(val.getString("accountNumber"));
		returnVal.setNameOnAccount(val.getString("nameOnAccount"));
		returnVal.setCompanyNameOnAccount(val.getString("companyNameOnAccount"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setYearsAtBank(val.getLong("yearsAtBank"));


return returnVal;

}

public static EftAccount map(HttpServletRequest request) throws Exception {

		EftAccount returnVal = new EftAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodId")) {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}

		if(paramMap.containsKey("bankName"))  {
returnVal.setBankName(request.getParameter("bankName"));
}
		if(paramMap.containsKey("routingNumber"))  {
returnVal.setRoutingNumber(request.getParameter("routingNumber"));
}
		if(paramMap.containsKey("accountType"))  {
returnVal.setAccountType(request.getParameter("accountType"));
}
		if(paramMap.containsKey("accountNumber"))  {
returnVal.setAccountNumber(request.getParameter("accountNumber"));
}
		if(paramMap.containsKey("nameOnAccount"))  {
returnVal.setNameOnAccount(request.getParameter("nameOnAccount"));
}
		if(paramMap.containsKey("companyNameOnAccount"))  {
returnVal.setCompanyNameOnAccount(request.getParameter("companyNameOnAccount"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("yearsAtBank"))  {
String buf = request.getParameter("yearsAtBank");
Long ibuf = Long.parseLong(buf);
returnVal.setYearsAtBank(ibuf);
}
return returnVal;

}
}
