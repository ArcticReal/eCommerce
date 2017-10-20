package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayEway;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway.PaymentGatewayEway;

public class PaymentGatewayEwayMapper  {


	public static Map<String, Object> map(PaymentGatewayEway paymentgatewayeway) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayeway.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayeway.getPaymentGatewayConfigId());
}

		if(paymentgatewayeway.getCustomerId() != null ){
			returnVal.put("customerId",paymentgatewayeway.getCustomerId());
}

		if(paymentgatewayeway.getRefundPwd() != null ){
			returnVal.put("refundPwd",paymentgatewayeway.getRefundPwd());
}

		if(paymentgatewayeway.getTestMode() != null ){
			returnVal.put("testMode",paymentgatewayeway.getTestMode());
}

		if(paymentgatewayeway.getEnableCvn() != null ){
			returnVal.put("enableCvn",paymentgatewayeway.getEnableCvn());
}

		if(paymentgatewayeway.getEnableBeagle() != null ){
			returnVal.put("enableBeagle",paymentgatewayeway.getEnableBeagle());
}

		return returnVal;
}


	public static PaymentGatewayEway map(Map<String, Object> fields) {

		PaymentGatewayEway returnVal = new PaymentGatewayEway();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("customerId") != null) {
			returnVal.setCustomerId((long) fields.get("customerId"));
}

		if(fields.get("refundPwd") != null) {
			returnVal.setRefundPwd((long) fields.get("refundPwd"));
}

		if(fields.get("testMode") != null) {
			returnVal.setTestMode((String) fields.get("testMode"));
}

		if(fields.get("enableCvn") != null) {
			returnVal.setEnableCvn((String) fields.get("enableCvn"));
}

		if(fields.get("enableBeagle") != null) {
			returnVal.setEnableBeagle((String) fields.get("enableBeagle"));
}


		return returnVal;
 } 
	public static PaymentGatewayEway mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayEway returnVal = new PaymentGatewayEway();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("customerId") != null) {
String buf;
buf = fields.get("customerId");
long ibuf = Long.parseLong(buf);
			returnVal.setCustomerId(ibuf);
}

		if(fields.get("refundPwd") != null) {
String buf;
buf = fields.get("refundPwd");
long ibuf = Long.parseLong(buf);
			returnVal.setRefundPwd(ibuf);
}

		if(fields.get("testMode") != null) {
			returnVal.setTestMode((String) fields.get("testMode"));
}

		if(fields.get("enableCvn") != null) {
			returnVal.setEnableCvn((String) fields.get("enableCvn"));
}

		if(fields.get("enableBeagle") != null) {
			returnVal.setEnableBeagle((String) fields.get("enableBeagle"));
}


		return returnVal;
 } 
	public static PaymentGatewayEway map(GenericValue val) {

PaymentGatewayEway returnVal = new PaymentGatewayEway();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setCustomerId(val.getLong("customerId"));
		returnVal.setRefundPwd(val.getLong("refundPwd"));
		returnVal.setTestMode(val.getString("testMode"));
		returnVal.setEnableCvn(val.getString("enableCvn"));
		returnVal.setEnableBeagle(val.getString("enableBeagle"));


return returnVal;

}

public static PaymentGatewayEway map(HttpServletRequest request) throws Exception {

		PaymentGatewayEway returnVal = new PaymentGatewayEway();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("customerId"))  {
String buf = request.getParameter("customerId");
Long ibuf = Long.parseLong(buf);
returnVal.setCustomerId(ibuf);
}
		if(paramMap.containsKey("refundPwd"))  {
String buf = request.getParameter("refundPwd");
Long ibuf = Long.parseLong(buf);
returnVal.setRefundPwd(ibuf);
}
		if(paramMap.containsKey("testMode"))  {
returnVal.setTestMode(request.getParameter("testMode"));
}
		if(paramMap.containsKey("enableCvn"))  {
returnVal.setEnableCvn(request.getParameter("enableCvn"));
}
		if(paramMap.containsKey("enableBeagle"))  {
returnVal.setEnableBeagle(request.getParameter("enableBeagle"));
}
return returnVal;

}
}
