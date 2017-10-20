package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentTypeMap;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;

public class PaymentGlAccountTypeMapMapper  {


	public static Map<String, Object> map(PaymentGlAccountTypeMap paymentglaccounttypemap) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentglaccounttypemap.getPaymentTypeId() != null ){
			returnVal.put("paymentTypeId",paymentglaccounttypemap.getPaymentTypeId());
}

		if(paymentglaccounttypemap.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",paymentglaccounttypemap.getOrganizationPartyId());
}

		if(paymentglaccounttypemap.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",paymentglaccounttypemap.getGlAccountTypeId());
}

		return returnVal;
}


	public static PaymentGlAccountTypeMap map(Map<String, Object> fields) {

		PaymentGlAccountTypeMap returnVal = new PaymentGlAccountTypeMap();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}


		return returnVal;
 } 
	public static PaymentGlAccountTypeMap mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGlAccountTypeMap returnVal = new PaymentGlAccountTypeMap();

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}


		return returnVal;
 } 
	public static PaymentGlAccountTypeMap map(GenericValue val) {

PaymentGlAccountTypeMap returnVal = new PaymentGlAccountTypeMap();
		returnVal.setPaymentTypeId(val.getString("paymentTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));


return returnVal;

}

public static PaymentGlAccountTypeMap map(HttpServletRequest request) throws Exception {

		PaymentGlAccountTypeMap returnVal = new PaymentGlAccountTypeMap();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentTypeId")) {
returnVal.setPaymentTypeId(request.getParameter("paymentTypeId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountTypeId"))  {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}
return returnVal;

}
}
