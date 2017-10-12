package com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.model.PaymentMethodTypeGlAccount;

public class PaymentMethodTypeGlAccountMapper  {


	public static Map<String, Object> map(PaymentMethodTypeGlAccount paymentmethodtypeglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentmethodtypeglaccount.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",paymentmethodtypeglaccount.getPaymentMethodTypeId());
}

		if(paymentmethodtypeglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",paymentmethodtypeglaccount.getOrganizationPartyId());
}

		if(paymentmethodtypeglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",paymentmethodtypeglaccount.getGlAccountId());
}

		return returnVal;
}


	public static PaymentMethodTypeGlAccount map(Map<String, Object> fields) {

		PaymentMethodTypeGlAccount returnVal = new PaymentMethodTypeGlAccount();

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static PaymentMethodTypeGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		PaymentMethodTypeGlAccount returnVal = new PaymentMethodTypeGlAccount();

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static PaymentMethodTypeGlAccount map(GenericValue val) {

PaymentMethodTypeGlAccount returnVal = new PaymentMethodTypeGlAccount();
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static PaymentMethodTypeGlAccount map(HttpServletRequest request) throws Exception {

		PaymentMethodTypeGlAccount returnVal = new PaymentMethodTypeGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodTypeId")) {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
