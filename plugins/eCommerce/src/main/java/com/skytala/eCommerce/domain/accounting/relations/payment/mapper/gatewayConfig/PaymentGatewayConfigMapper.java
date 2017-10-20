package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayConfig;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfig.PaymentGatewayConfig;

public class PaymentGatewayConfigMapper  {


	public static Map<String, Object> map(PaymentGatewayConfig paymentgatewayconfig) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayconfig.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayconfig.getPaymentGatewayConfigId());
}

		if(paymentgatewayconfig.getPaymentGatewayConfigTypeId() != null ){
			returnVal.put("paymentGatewayConfigTypeId",paymentgatewayconfig.getPaymentGatewayConfigTypeId());
}

		if(paymentgatewayconfig.getDescription() != null ){
			returnVal.put("description",paymentgatewayconfig.getDescription());
}

		return returnVal;
}


	public static PaymentGatewayConfig map(Map<String, Object> fields) {

		PaymentGatewayConfig returnVal = new PaymentGatewayConfig();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("paymentGatewayConfigTypeId") != null) {
			returnVal.setPaymentGatewayConfigTypeId((String) fields.get("paymentGatewayConfigTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentGatewayConfig mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayConfig returnVal = new PaymentGatewayConfig();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("paymentGatewayConfigTypeId") != null) {
			returnVal.setPaymentGatewayConfigTypeId((String) fields.get("paymentGatewayConfigTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PaymentGatewayConfig map(GenericValue val) {

PaymentGatewayConfig returnVal = new PaymentGatewayConfig();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setPaymentGatewayConfigTypeId(val.getString("paymentGatewayConfigTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PaymentGatewayConfig map(HttpServletRequest request) throws Exception {

		PaymentGatewayConfig returnVal = new PaymentGatewayConfig();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("paymentGatewayConfigTypeId"))  {
returnVal.setPaymentGatewayConfigTypeId(request.getParameter("paymentGatewayConfigTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
