package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayPal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayPal.PaymentGatewayPayPal;

public class PaymentGatewayPayPalMapper  {


	public static Map<String, Object> map(PaymentGatewayPayPal paymentgatewaypaypal) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewaypaypal.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewaypaypal.getPaymentGatewayConfigId());
}

		if(paymentgatewaypaypal.getBusinessEmail() != null ){
			returnVal.put("businessEmail",paymentgatewaypaypal.getBusinessEmail());
}

		if(paymentgatewaypaypal.getApiUserName() != null ){
			returnVal.put("apiUserName",paymentgatewaypaypal.getApiUserName());
}

		if(paymentgatewaypaypal.getApiPassword() != null ){
			returnVal.put("apiPassword",paymentgatewaypaypal.getApiPassword());
}

		if(paymentgatewaypaypal.getApiSignature() != null ){
			returnVal.put("apiSignature",paymentgatewaypaypal.getApiSignature());
}

		if(paymentgatewaypaypal.getApiEnvironment() != null ){
			returnVal.put("apiEnvironment",paymentgatewaypaypal.getApiEnvironment());
}

		if(paymentgatewaypaypal.getNotifyUrl() != null ){
			returnVal.put("notifyUrl",paymentgatewaypaypal.getNotifyUrl());
}

		if(paymentgatewaypaypal.getReturnUrl() != null ){
			returnVal.put("returnUrl",paymentgatewaypaypal.getReturnUrl());
}

		if(paymentgatewaypaypal.getCancelReturnUrl() != null ){
			returnVal.put("cancelReturnUrl",paymentgatewaypaypal.getCancelReturnUrl());
}

		if(paymentgatewaypaypal.getImageUrl() != null ){
			returnVal.put("imageUrl",paymentgatewaypaypal.getImageUrl());
}

		if(paymentgatewaypaypal.getConfirmTemplate() != null ){
			returnVal.put("confirmTemplate",paymentgatewaypaypal.getConfirmTemplate());
}

		if(paymentgatewaypaypal.getRedirectUrl() != null ){
			returnVal.put("redirectUrl",paymentgatewaypaypal.getRedirectUrl());
}

		if(paymentgatewaypaypal.getConfirmUrl() != null ){
			returnVal.put("confirmUrl",paymentgatewaypaypal.getConfirmUrl());
}

		if(paymentgatewaypaypal.getShippingCallbackUrl() != null ){
			returnVal.put("shippingCallbackUrl",paymentgatewaypaypal.getShippingCallbackUrl());
}

		if(paymentgatewaypaypal.getRequireConfirmedShipping() != null ){
			returnVal.put("requireConfirmedShipping",paymentgatewaypaypal.getRequireConfirmedShipping());
}

		return returnVal;
}


	public static PaymentGatewayPayPal map(Map<String, Object> fields) {

		PaymentGatewayPayPal returnVal = new PaymentGatewayPayPal();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("businessEmail") != null) {
			returnVal.setBusinessEmail((long) fields.get("businessEmail"));
}

		if(fields.get("apiUserName") != null) {
			returnVal.setApiUserName((String) fields.get("apiUserName"));
}

		if(fields.get("apiPassword") != null) {
			returnVal.setApiPassword((String) fields.get("apiPassword"));
}

		if(fields.get("apiSignature") != null) {
			returnVal.setApiSignature((String) fields.get("apiSignature"));
}

		if(fields.get("apiEnvironment") != null) {
			returnVal.setApiEnvironment((String) fields.get("apiEnvironment"));
}

		if(fields.get("notifyUrl") != null) {
			returnVal.setNotifyUrl((long) fields.get("notifyUrl"));
}

		if(fields.get("returnUrl") != null) {
			returnVal.setReturnUrl((long) fields.get("returnUrl"));
}

		if(fields.get("cancelReturnUrl") != null) {
			returnVal.setCancelReturnUrl((long) fields.get("cancelReturnUrl"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((long) fields.get("imageUrl"));
}

		if(fields.get("confirmTemplate") != null) {
			returnVal.setConfirmTemplate((long) fields.get("confirmTemplate"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((long) fields.get("redirectUrl"));
}

		if(fields.get("confirmUrl") != null) {
			returnVal.setConfirmUrl((long) fields.get("confirmUrl"));
}

		if(fields.get("shippingCallbackUrl") != null) {
			returnVal.setShippingCallbackUrl((String) fields.get("shippingCallbackUrl"));
}

		if(fields.get("requireConfirmedShipping") != null) {
			returnVal.setRequireConfirmedShipping((boolean) fields.get("requireConfirmedShipping"));
}


		return returnVal;
 } 
	public static PaymentGatewayPayPal mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayPayPal returnVal = new PaymentGatewayPayPal();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("businessEmail") != null) {
String buf;
buf = fields.get("businessEmail");
long ibuf = Long.parseLong(buf);
			returnVal.setBusinessEmail(ibuf);
}

		if(fields.get("apiUserName") != null) {
			returnVal.setApiUserName((String) fields.get("apiUserName"));
}

		if(fields.get("apiPassword") != null) {
			returnVal.setApiPassword((String) fields.get("apiPassword"));
}

		if(fields.get("apiSignature") != null) {
			returnVal.setApiSignature((String) fields.get("apiSignature"));
}

		if(fields.get("apiEnvironment") != null) {
			returnVal.setApiEnvironment((String) fields.get("apiEnvironment"));
}

		if(fields.get("notifyUrl") != null) {
String buf;
buf = fields.get("notifyUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setNotifyUrl(ibuf);
}

		if(fields.get("returnUrl") != null) {
String buf;
buf = fields.get("returnUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setReturnUrl(ibuf);
}

		if(fields.get("cancelReturnUrl") != null) {
String buf;
buf = fields.get("cancelReturnUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setCancelReturnUrl(ibuf);
}

		if(fields.get("imageUrl") != null) {
String buf;
buf = fields.get("imageUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setImageUrl(ibuf);
}

		if(fields.get("confirmTemplate") != null) {
String buf;
buf = fields.get("confirmTemplate");
long ibuf = Long.parseLong(buf);
			returnVal.setConfirmTemplate(ibuf);
}

		if(fields.get("redirectUrl") != null) {
String buf;
buf = fields.get("redirectUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setRedirectUrl(ibuf);
}

		if(fields.get("confirmUrl") != null) {
String buf;
buf = fields.get("confirmUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setConfirmUrl(ibuf);
}

		if(fields.get("shippingCallbackUrl") != null) {
			returnVal.setShippingCallbackUrl((String) fields.get("shippingCallbackUrl"));
}

		if(fields.get("requireConfirmedShipping") != null) {
String buf;
buf = fields.get("requireConfirmedShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireConfirmedShipping(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayPayPal map(GenericValue val) {

PaymentGatewayPayPal returnVal = new PaymentGatewayPayPal();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setBusinessEmail(val.getLong("businessEmail"));
		returnVal.setApiUserName(val.getString("apiUserName"));
		returnVal.setApiPassword(val.getString("apiPassword"));
		returnVal.setApiSignature(val.getString("apiSignature"));
		returnVal.setApiEnvironment(val.getString("apiEnvironment"));
		returnVal.setNotifyUrl(val.getLong("notifyUrl"));
		returnVal.setReturnUrl(val.getLong("returnUrl"));
		returnVal.setCancelReturnUrl(val.getLong("cancelReturnUrl"));
		returnVal.setImageUrl(val.getLong("imageUrl"));
		returnVal.setConfirmTemplate(val.getLong("confirmTemplate"));
		returnVal.setRedirectUrl(val.getLong("redirectUrl"));
		returnVal.setConfirmUrl(val.getLong("confirmUrl"));
		returnVal.setShippingCallbackUrl(val.getString("shippingCallbackUrl"));
		returnVal.setRequireConfirmedShipping(val.getBoolean("requireConfirmedShipping"));


return returnVal;

}

public static PaymentGatewayPayPal map(HttpServletRequest request) throws Exception {

		PaymentGatewayPayPal returnVal = new PaymentGatewayPayPal();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("businessEmail"))  {
String buf = request.getParameter("businessEmail");
Long ibuf = Long.parseLong(buf);
returnVal.setBusinessEmail(ibuf);
}
		if(paramMap.containsKey("apiUserName"))  {
returnVal.setApiUserName(request.getParameter("apiUserName"));
}
		if(paramMap.containsKey("apiPassword"))  {
returnVal.setApiPassword(request.getParameter("apiPassword"));
}
		if(paramMap.containsKey("apiSignature"))  {
returnVal.setApiSignature(request.getParameter("apiSignature"));
}
		if(paramMap.containsKey("apiEnvironment"))  {
returnVal.setApiEnvironment(request.getParameter("apiEnvironment"));
}
		if(paramMap.containsKey("notifyUrl"))  {
String buf = request.getParameter("notifyUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setNotifyUrl(ibuf);
}
		if(paramMap.containsKey("returnUrl"))  {
String buf = request.getParameter("returnUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setReturnUrl(ibuf);
}
		if(paramMap.containsKey("cancelReturnUrl"))  {
String buf = request.getParameter("cancelReturnUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setCancelReturnUrl(ibuf);
}
		if(paramMap.containsKey("imageUrl"))  {
String buf = request.getParameter("imageUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setImageUrl(ibuf);
}
		if(paramMap.containsKey("confirmTemplate"))  {
String buf = request.getParameter("confirmTemplate");
Long ibuf = Long.parseLong(buf);
returnVal.setConfirmTemplate(ibuf);
}
		if(paramMap.containsKey("redirectUrl"))  {
String buf = request.getParameter("redirectUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setRedirectUrl(ibuf);
}
		if(paramMap.containsKey("confirmUrl"))  {
String buf = request.getParameter("confirmUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setConfirmUrl(ibuf);
}
		if(paramMap.containsKey("shippingCallbackUrl"))  {
returnVal.setShippingCallbackUrl(request.getParameter("shippingCallbackUrl"));
}
		if(paramMap.containsKey("requireConfirmedShipping"))  {
String buf = request.getParameter("requireConfirmedShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireConfirmedShipping(ibuf);
}
return returnVal;

}
}
