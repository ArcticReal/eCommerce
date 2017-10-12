package com.skytala.eCommerce.domain.accounting.relations.checkAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;

public class CheckAccountMapper  {


	public static Map<String, Object> map(CheckAccount checkaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(checkaccount.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",checkaccount.getPaymentMethodId());
}

		if(checkaccount.getBankName() != null ){
			returnVal.put("bankName",checkaccount.getBankName());
}

		if(checkaccount.getRoutingNumber() != null ){
			returnVal.put("routingNumber",checkaccount.getRoutingNumber());
}

		if(checkaccount.getAccountType() != null ){
			returnVal.put("accountType",checkaccount.getAccountType());
}

		if(checkaccount.getAccountNumber() != null ){
			returnVal.put("accountNumber",checkaccount.getAccountNumber());
}

		if(checkaccount.getNameOnAccount() != null ){
			returnVal.put("nameOnAccount",checkaccount.getNameOnAccount());
}

		if(checkaccount.getCompanyNameOnAccount() != null ){
			returnVal.put("companyNameOnAccount",checkaccount.getCompanyNameOnAccount());
}

		if(checkaccount.getContactMechId() != null ){
			returnVal.put("contactMechId",checkaccount.getContactMechId());
}

		if(checkaccount.getBranchCode() != null ){
			returnVal.put("branchCode",checkaccount.getBranchCode());
}

		return returnVal;
}


	public static CheckAccount map(Map<String, Object> fields) {

		CheckAccount returnVal = new CheckAccount();

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

		if(fields.get("branchCode") != null) {
			returnVal.setBranchCode((String) fields.get("branchCode"));
}


		return returnVal;
 } 
	public static CheckAccount mapstrstr(Map<String, String> fields) throws Exception {

		CheckAccount returnVal = new CheckAccount();

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

		if(fields.get("branchCode") != null) {
			returnVal.setBranchCode((String) fields.get("branchCode"));
}


		return returnVal;
 } 
	public static CheckAccount map(GenericValue val) {

CheckAccount returnVal = new CheckAccount();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setBankName(val.getString("bankName"));
		returnVal.setRoutingNumber(val.getString("routingNumber"));
		returnVal.setAccountType(val.getString("accountType"));
		returnVal.setAccountNumber(val.getString("accountNumber"));
		returnVal.setNameOnAccount(val.getString("nameOnAccount"));
		returnVal.setCompanyNameOnAccount(val.getString("companyNameOnAccount"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setBranchCode(val.getString("branchCode"));


return returnVal;

}

public static CheckAccount map(HttpServletRequest request) throws Exception {

		CheckAccount returnVal = new CheckAccount();

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
		if(paramMap.containsKey("branchCode"))  {
returnVal.setBranchCode(request.getParameter("branchCode"));
}
return returnVal;

}
}
