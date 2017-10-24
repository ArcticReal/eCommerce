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
			returnVal.setBusinessEmail((String) fields.get("businessEmail"));
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
			returnVal.setNotifyUrl((String) fields.get("notifyUrl"));
}

		if(fields.get("returnUrl") != null) {
			returnVal.setReturnUrl((String) fields.get("returnUrl"));
}

		if(fields.get("cancelReturnUrl") != null) {
			returnVal.setCancelReturnUrl((String) fields.get("cancelReturnUrl"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}

		if(fields.get("confirmTemplate") != null) {
			returnVal.setConfirmTemplate((String) fields.get("confirmTemplate"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("confirmUrl") != null) {
			returnVal.setConfirmUrl((String) fields.get("confirmUrl"));
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
			returnVal.setBusinessEmail((String) fields.get("businessEmail"));
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
			returnVal.setNotifyUrl((String) fields.get("notifyUrl"));
}

		if(fields.get("returnUrl") != null) {
			returnVal.setReturnUrl((String) fields.get("returnUrl"));
}

		if(fields.get("cancelReturnUrl") != null) {
			returnVal.setCancelReturnUrl((String) fields.get("cancelReturnUrl"));
}

		if(fields.get("imageUrl") != null) {
			returnVal.setImageUrl((String) fields.get("imageUrl"));
}

		if(fields.get("confirmTemplate") != null) {
			returnVal.setConfirmTemplate((String) fields.get("confirmTemplate"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("confirmUrl") != null) {
			returnVal.setConfirmUrl((String) fields.get("confirmUrl"));
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
		returnVal.setBusinessEmail(val.getString("businessEmail"));
		returnVal.setApiUserName(val.getString("apiUserName"));
		returnVal.setApiPassword(val.getString("apiPassword"));
		returnVal.setApiSignature(val.getString("apiSignature"));
		returnVal.setApiEnvironment(val.getString("apiEnvironment"));
		returnVal.setNotifyUrl(val.getString("notifyUrl"));
		returnVal.setReturnUrl(val.getString("returnUrl"));
		returnVal.setCancelReturnUrl(val.getString("cancelReturnUrl"));
		returnVal.setImageUrl(val.getString("imageUrl"));
		returnVal.setConfirmTemplate(val.getString("confirmTemplate"));
		returnVal.setRedirectUrl(val.getString("redirectUrl"));
		returnVal.setConfirmUrl(val.getString("confirmUrl"));
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
returnVal.setBusinessEmail(request.getParameter("businessEmail"));
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
returnVal.setNotifyUrl(request.getParameter("notifyUrl"));
}
		if(paramMap.containsKey("returnUrl"))  {
returnVal.setReturnUrl(request.getParameter("returnUrl"));
}
		if(paramMap.containsKey("cancelReturnUrl"))  {
returnVal.setCancelReturnUrl(request.getParameter("cancelReturnUrl"));
}
		if(paramMap.containsKey("imageUrl"))  {
returnVal.setImageUrl(request.getParameter("imageUrl"));
}
		if(paramMap.containsKey("confirmTemplate"))  {
returnVal.setConfirmTemplate(request.getParameter("confirmTemplate"));
}
		if(paramMap.containsKey("redirectUrl"))  {
returnVal.setRedirectUrl(request.getParameter("redirectUrl"));
}
		if(paramMap.containsKey("confirmUrl"))  {
returnVal.setConfirmUrl(request.getParameter("confirmUrl"));
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
