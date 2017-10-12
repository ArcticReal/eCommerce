package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;

public class PayPalPaymentMethodMapper  {


	public static Map<String, Object> map(PayPalPaymentMethod paypalpaymentmethod) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paypalpaymentmethod.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",paypalpaymentmethod.getPaymentMethodId());
}

		if(paypalpaymentmethod.getPayerId() != null ){
			returnVal.put("payerId",paypalpaymentmethod.getPayerId());
}

		if(paypalpaymentmethod.getExpressCheckoutToken() != null ){
			returnVal.put("expressCheckoutToken",paypalpaymentmethod.getExpressCheckoutToken());
}

		if(paypalpaymentmethod.getPayerStatus() != null ){
			returnVal.put("payerStatus",paypalpaymentmethod.getPayerStatus());
}

		if(paypalpaymentmethod.getAvsAddr() != null ){
			returnVal.put("avsAddr",paypalpaymentmethod.getAvsAddr());
}

		if(paypalpaymentmethod.getAvsZip() != null ){
			returnVal.put("avsZip",paypalpaymentmethod.getAvsZip());
}

		if(paypalpaymentmethod.getCorrelationId() != null ){
			returnVal.put("correlationId",paypalpaymentmethod.getCorrelationId());
}

		if(paypalpaymentmethod.getContactMechId() != null ){
			returnVal.put("contactMechId",paypalpaymentmethod.getContactMechId());
}

		if(paypalpaymentmethod.getTransactionId() != null ){
			returnVal.put("transactionId",paypalpaymentmethod.getTransactionId());
}

		return returnVal;
}


	public static PayPalPaymentMethod map(Map<String, Object> fields) {

		PayPalPaymentMethod returnVal = new PayPalPaymentMethod();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("payerId") != null) {
			returnVal.setPayerId((String) fields.get("payerId"));
}

		if(fields.get("expressCheckoutToken") != null) {
			returnVal.setExpressCheckoutToken((String) fields.get("expressCheckoutToken"));
}

		if(fields.get("payerStatus") != null) {
			returnVal.setPayerStatus((String) fields.get("payerStatus"));
}

		if(fields.get("avsAddr") != null) {
			returnVal.setAvsAddr((boolean) fields.get("avsAddr"));
}

		if(fields.get("avsZip") != null) {
			returnVal.setAvsZip((boolean) fields.get("avsZip"));
}

		if(fields.get("correlationId") != null) {
			returnVal.setCorrelationId((String) fields.get("correlationId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("transactionId") != null) {
			returnVal.setTransactionId((String) fields.get("transactionId"));
}


		return returnVal;
 } 
	public static PayPalPaymentMethod mapstrstr(Map<String, String> fields) throws Exception {

		PayPalPaymentMethod returnVal = new PayPalPaymentMethod();

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("payerId") != null) {
			returnVal.setPayerId((String) fields.get("payerId"));
}

		if(fields.get("expressCheckoutToken") != null) {
			returnVal.setExpressCheckoutToken((String) fields.get("expressCheckoutToken"));
}

		if(fields.get("payerStatus") != null) {
			returnVal.setPayerStatus((String) fields.get("payerStatus"));
}

		if(fields.get("avsAddr") != null) {
String buf;
buf = fields.get("avsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAvsAddr(ibuf);
}

		if(fields.get("avsZip") != null) {
String buf;
buf = fields.get("avsZip");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAvsZip(ibuf);
}

		if(fields.get("correlationId") != null) {
			returnVal.setCorrelationId((String) fields.get("correlationId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("transactionId") != null) {
			returnVal.setTransactionId((String) fields.get("transactionId"));
}


		return returnVal;
 } 
	public static PayPalPaymentMethod map(GenericValue val) {

PayPalPaymentMethod returnVal = new PayPalPaymentMethod();
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setPayerId(val.getString("payerId"));
		returnVal.setExpressCheckoutToken(val.getString("expressCheckoutToken"));
		returnVal.setPayerStatus(val.getString("payerStatus"));
		returnVal.setAvsAddr(val.getBoolean("avsAddr"));
		returnVal.setAvsZip(val.getBoolean("avsZip"));
		returnVal.setCorrelationId(val.getString("correlationId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setTransactionId(val.getString("transactionId"));


return returnVal;

}

public static PayPalPaymentMethod map(HttpServletRequest request) throws Exception {

		PayPalPaymentMethod returnVal = new PayPalPaymentMethod();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentMethodId")) {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}

		if(paramMap.containsKey("payerId"))  {
returnVal.setPayerId(request.getParameter("payerId"));
}
		if(paramMap.containsKey("expressCheckoutToken"))  {
returnVal.setExpressCheckoutToken(request.getParameter("expressCheckoutToken"));
}
		if(paramMap.containsKey("payerStatus"))  {
returnVal.setPayerStatus(request.getParameter("payerStatus"));
}
		if(paramMap.containsKey("avsAddr"))  {
String buf = request.getParameter("avsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAvsAddr(ibuf);
}
		if(paramMap.containsKey("avsZip"))  {
String buf = request.getParameter("avsZip");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAvsZip(ibuf);
}
		if(paramMap.containsKey("correlationId"))  {
returnVal.setCorrelationId(request.getParameter("correlationId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("transactionId"))  {
returnVal.setTransactionId(request.getParameter("transactionId"));
}
return returnVal;

}
}
