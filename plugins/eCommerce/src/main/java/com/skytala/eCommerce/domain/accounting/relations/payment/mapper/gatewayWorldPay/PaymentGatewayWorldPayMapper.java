package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayWorldPay;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;

public class PaymentGatewayWorldPayMapper  {


	public static Map<String, Object> map(PaymentGatewayWorldPay paymentgatewayworldpay) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayworldpay.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayworldpay.getPaymentGatewayConfigId());
}

		if(paymentgatewayworldpay.getRedirectUrl() != null ){
			returnVal.put("redirectUrl",paymentgatewayworldpay.getRedirectUrl());
}

		if(paymentgatewayworldpay.getInstId() != null ){
			returnVal.put("instId",paymentgatewayworldpay.getInstId());
}

		if(paymentgatewayworldpay.getAuthMode() != null ){
			returnVal.put("authMode",paymentgatewayworldpay.getAuthMode());
}

		if(paymentgatewayworldpay.getFixContact() != null ){
			returnVal.put("fixContact",paymentgatewayworldpay.getFixContact());
}

		if(paymentgatewayworldpay.getHideContact() != null ){
			returnVal.put("hideContact",paymentgatewayworldpay.getHideContact());
}

		if(paymentgatewayworldpay.getHideCurrency() != null ){
			returnVal.put("hideCurrency",paymentgatewayworldpay.getHideCurrency());
}

		if(paymentgatewayworldpay.getLangId() != null ){
			returnVal.put("langId",paymentgatewayworldpay.getLangId());
}

		if(paymentgatewayworldpay.getNoLanguageMenu() != null ){
			returnVal.put("noLanguageMenu",paymentgatewayworldpay.getNoLanguageMenu());
}

		if(paymentgatewayworldpay.getWithDelivery() != null ){
			returnVal.put("withDelivery",paymentgatewayworldpay.getWithDelivery());
}

		if(paymentgatewayworldpay.getTestMode() != null ){
			returnVal.put("testMode",paymentgatewayworldpay.getTestMode());
}

		return returnVal;
}


	public static PaymentGatewayWorldPay map(Map<String, Object> fields) {

		PaymentGatewayWorldPay returnVal = new PaymentGatewayWorldPay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("instId") != null) {
			returnVal.setInstId((String) fields.get("instId"));
}

		if(fields.get("authMode") != null) {
			returnVal.setAuthMode((boolean) fields.get("authMode"));
}

		if(fields.get("fixContact") != null) {
			returnVal.setFixContact((boolean) fields.get("fixContact"));
}

		if(fields.get("hideContact") != null) {
			returnVal.setHideContact((boolean) fields.get("hideContact"));
}

		if(fields.get("hideCurrency") != null) {
			returnVal.setHideCurrency((boolean) fields.get("hideCurrency"));
}

		if(fields.get("langId") != null) {
			returnVal.setLangId((String) fields.get("langId"));
}

		if(fields.get("noLanguageMenu") != null) {
			returnVal.setNoLanguageMenu((boolean) fields.get("noLanguageMenu"));
}

		if(fields.get("withDelivery") != null) {
			returnVal.setWithDelivery((boolean) fields.get("withDelivery"));
}

		if(fields.get("testMode") != null) {
			returnVal.setTestMode((long) fields.get("testMode"));
}


		return returnVal;
 } 
	public static PaymentGatewayWorldPay mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayWorldPay returnVal = new PaymentGatewayWorldPay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("instId") != null) {
			returnVal.setInstId((String) fields.get("instId"));
}

		if(fields.get("authMode") != null) {
String buf;
buf = fields.get("authMode");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAuthMode(ibuf);
}

		if(fields.get("fixContact") != null) {
String buf;
buf = fields.get("fixContact");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setFixContact(ibuf);
}

		if(fields.get("hideContact") != null) {
String buf;
buf = fields.get("hideContact");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHideContact(ibuf);
}

		if(fields.get("hideCurrency") != null) {
String buf;
buf = fields.get("hideCurrency");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHideCurrency(ibuf);
}

		if(fields.get("langId") != null) {
			returnVal.setLangId((String) fields.get("langId"));
}

		if(fields.get("noLanguageMenu") != null) {
String buf;
buf = fields.get("noLanguageMenu");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setNoLanguageMenu(ibuf);
}

		if(fields.get("withDelivery") != null) {
String buf;
buf = fields.get("withDelivery");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setWithDelivery(ibuf);
}

		if(fields.get("testMode") != null) {
String buf;
buf = fields.get("testMode");
long ibuf = Long.parseLong(buf);
			returnVal.setTestMode(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayWorldPay map(GenericValue val) {

PaymentGatewayWorldPay returnVal = new PaymentGatewayWorldPay();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setRedirectUrl(val.getString("redirectUrl"));
		returnVal.setInstId(val.getString("instId"));
		returnVal.setAuthMode(val.getBoolean("authMode"));
		returnVal.setFixContact(val.getBoolean("fixContact"));
		returnVal.setHideContact(val.getBoolean("hideContact"));
		returnVal.setHideCurrency(val.getBoolean("hideCurrency"));
		returnVal.setLangId(val.getString("langId"));
		returnVal.setNoLanguageMenu(val.getBoolean("noLanguageMenu"));
		returnVal.setWithDelivery(val.getBoolean("withDelivery"));
		returnVal.setTestMode(val.getLong("testMode"));


return returnVal;

}

public static PaymentGatewayWorldPay map(HttpServletRequest request) throws Exception {

		PaymentGatewayWorldPay returnVal = new PaymentGatewayWorldPay();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("redirectUrl"))  {
returnVal.setRedirectUrl(request.getParameter("redirectUrl"));
}
		if(paramMap.containsKey("instId"))  {
returnVal.setInstId(request.getParameter("instId"));
}
		if(paramMap.containsKey("authMode"))  {
String buf = request.getParameter("authMode");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAuthMode(ibuf);
}
		if(paramMap.containsKey("fixContact"))  {
String buf = request.getParameter("fixContact");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setFixContact(ibuf);
}
		if(paramMap.containsKey("hideContact"))  {
String buf = request.getParameter("hideContact");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHideContact(ibuf);
}
		if(paramMap.containsKey("hideCurrency"))  {
String buf = request.getParameter("hideCurrency");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHideCurrency(ibuf);
}
		if(paramMap.containsKey("langId"))  {
returnVal.setLangId(request.getParameter("langId"));
}
		if(paramMap.containsKey("noLanguageMenu"))  {
String buf = request.getParameter("noLanguageMenu");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setNoLanguageMenu(ibuf);
}
		if(paramMap.containsKey("withDelivery"))  {
String buf = request.getParameter("withDelivery");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setWithDelivery(ibuf);
}
		if(paramMap.containsKey("testMode"))  {
String buf = request.getParameter("testMode");
Long ibuf = Long.parseLong(buf);
returnVal.setTestMode(ibuf);
}
return returnVal;

}
}
