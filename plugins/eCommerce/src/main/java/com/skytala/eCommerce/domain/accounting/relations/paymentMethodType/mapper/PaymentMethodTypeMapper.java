package com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.model.PaymentMethodType;

public class PaymentMethodTypeMapper  {


	public static Map<String, Object> map(PaymentMethodType paymentmethodtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentmethodtype.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",paymentmethodtype.getPaymentMethodTypeId());
}

		if(paymentmethodtype.getDescription() != null ){
			returnVal.put("description",paymentmethodtype.getDescription());
}

		if(paymentmethodtype.getDefaultGlAccountId() != null ){
			returnVal.put("defaultGlAccountId",paymentmethodtype.getDefaultGlAccountId());
}

		return returnVal;
}


	public static PaymentMethodType map(Map<String, Object> fields) {

		PaymentMethodType returnVal = new PaymentMethodType();

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultGlAccountId") != null) {
			returnVal.setDefaultGlAccountId((String) fields.get("defaultGlAccountId"));
}


		return returnVal;
 } 
	public static PaymentMethodType mapstrstr(Map<String, String> fields) throws Exception {

		PaymentMethodType returnVal = new PaymentMethodType();

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultGlAccountId") != null) {
			returnVal.setDefaultGlAccountId((String) fields.get("defaultGlAccountId"));
}


		return returnVal;
 } 
	public static PaymentMethodType map(GenericValue val) {

PaymentMethodType returnVal = new PaymentMethodType();
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDefaultGlAccountId(val.getString("defaultGlAccountId"));


return returnVal;

}

public static PaymentMethodType map(HttpServletRequest request) throws Exception {

		PaymentMethodType returnVal = new PaymentMethodType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodTypeId")) {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("defaultGlAccountId"))  {
returnVal.setDefaultGlAccountId(request.getParameter("defaultGlAccountId"));
}
return returnVal;

}
}
