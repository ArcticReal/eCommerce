package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayflowPro;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;

public class PaymentGatewayPayflowProMapper  {


	public static Map<String, Object> map(PaymentGatewayPayflowPro paymentgatewaypayflowpro) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewaypayflowpro.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewaypayflowpro.getPaymentGatewayConfigId());
}

		if(paymentgatewaypayflowpro.getCertsPath() != null ){
			returnVal.put("certsPath",paymentgatewaypayflowpro.getCertsPath());
}

		if(paymentgatewaypayflowpro.getHostAddress() != null ){
			returnVal.put("hostAddress",paymentgatewaypayflowpro.getHostAddress());
}

		if(paymentgatewaypayflowpro.getHostPort() != null ){
			returnVal.put("hostPort",paymentgatewaypayflowpro.getHostPort());
}

		if(paymentgatewaypayflowpro.getTimeout() != null ){
			returnVal.put("timeout",paymentgatewaypayflowpro.getTimeout());
}

		if(paymentgatewaypayflowpro.getProxyAddress() != null ){
			returnVal.put("proxyAddress",paymentgatewaypayflowpro.getProxyAddress());
}

		if(paymentgatewaypayflowpro.getProxyPort() != null ){
			returnVal.put("proxyPort",paymentgatewaypayflowpro.getProxyPort());
}

		if(paymentgatewaypayflowpro.getProxyLogon() != null ){
			returnVal.put("proxyLogon",paymentgatewaypayflowpro.getProxyLogon());
}

		if(paymentgatewaypayflowpro.getProxyPassword() != null ){
			returnVal.put("proxyPassword",paymentgatewaypayflowpro.getProxyPassword());
}

		if(paymentgatewaypayflowpro.getVendor() != null ){
			returnVal.put("vendor",paymentgatewaypayflowpro.getVendor());
}

		if(paymentgatewaypayflowpro.getUserId() != null ){
			returnVal.put("userId",paymentgatewaypayflowpro.getUserId());
}

		if(paymentgatewaypayflowpro.getPwd() != null ){
			returnVal.put("pwd",paymentgatewaypayflowpro.getPwd());
}

		if(paymentgatewaypayflowpro.getPartner() != null ){
			returnVal.put("partner",paymentgatewaypayflowpro.getPartner());
}

		if(paymentgatewaypayflowpro.getCheckAvs() != null ){
			returnVal.put("checkAvs",paymentgatewaypayflowpro.getCheckAvs());
}

		if(paymentgatewaypayflowpro.getCheckCvv2() != null ){
			returnVal.put("checkCvv2",paymentgatewaypayflowpro.getCheckCvv2());
}

		if(paymentgatewaypayflowpro.getPreAuth() != null ){
			returnVal.put("preAuth",paymentgatewaypayflowpro.getPreAuth());
}

		if(paymentgatewaypayflowpro.getEnableTransmit() != null ){
			returnVal.put("enableTransmit",paymentgatewaypayflowpro.getEnableTransmit());
}

		if(paymentgatewaypayflowpro.getLogFileName() != null ){
			returnVal.put("logFileName",paymentgatewaypayflowpro.getLogFileName());
}

		if(paymentgatewaypayflowpro.getLoggingLevel() != null ){
			returnVal.put("loggingLevel",paymentgatewaypayflowpro.getLoggingLevel());
}

		if(paymentgatewaypayflowpro.getMaxLogFileSize() != null ){
			returnVal.put("maxLogFileSize",paymentgatewaypayflowpro.getMaxLogFileSize());
}

		if(paymentgatewaypayflowpro.getStackTraceOn() != null ){
			returnVal.put("stackTraceOn",paymentgatewaypayflowpro.getStackTraceOn());
}

		if(paymentgatewaypayflowpro.getRedirectUrl() != null ){
			returnVal.put("redirectUrl",paymentgatewaypayflowpro.getRedirectUrl());
}

		if(paymentgatewaypayflowpro.getReturnUrl() != null ){
			returnVal.put("returnUrl",paymentgatewaypayflowpro.getReturnUrl());
}

		if(paymentgatewaypayflowpro.getCancelReturnUrl() != null ){
			returnVal.put("cancelReturnUrl",paymentgatewaypayflowpro.getCancelReturnUrl());
}

		return returnVal;
}


	public static PaymentGatewayPayflowPro map(Map<String, Object> fields) {

		PaymentGatewayPayflowPro returnVal = new PaymentGatewayPayflowPro();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("certsPath") != null) {
			returnVal.setCertsPath((String) fields.get("certsPath"));
}

		if(fields.get("hostAddress") != null) {
			returnVal.setHostAddress((String) fields.get("hostAddress"));
}

		if(fields.get("hostPort") != null) {
			returnVal.setHostPort((long) fields.get("hostPort"));
}

		if(fields.get("timeout") != null) {
			returnVal.setTimeout((long) fields.get("timeout"));
}

		if(fields.get("proxyAddress") != null) {
			returnVal.setProxyAddress((String) fields.get("proxyAddress"));
}

		if(fields.get("proxyPort") != null) {
			returnVal.setProxyPort((long) fields.get("proxyPort"));
}

		if(fields.get("proxyLogon") != null) {
			returnVal.setProxyLogon((String) fields.get("proxyLogon"));
}

		if(fields.get("proxyPassword") != null) {
			returnVal.setProxyPassword((String) fields.get("proxyPassword"));
}

		if(fields.get("vendor") != null) {
			returnVal.setVendor((String) fields.get("vendor"));
}

		if(fields.get("userId") != null) {
			returnVal.setUserId((String) fields.get("userId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((String) fields.get("pwd"));
}

		if(fields.get("partner") != null) {
			returnVal.setPartner((String) fields.get("partner"));
}

		if(fields.get("checkAvs") != null) {
			returnVal.setCheckAvs((boolean) fields.get("checkAvs"));
}

		if(fields.get("checkCvv2") != null) {
			returnVal.setCheckCvv2((boolean) fields.get("checkCvv2"));
}

		if(fields.get("preAuth") != null) {
			returnVal.setPreAuth((boolean) fields.get("preAuth"));
}

		if(fields.get("enableTransmit") != null) {
			returnVal.setEnableTransmit((String) fields.get("enableTransmit"));
}

		if(fields.get("logFileName") != null) {
			returnVal.setLogFileName((String) fields.get("logFileName"));
}

		if(fields.get("loggingLevel") != null) {
			returnVal.setLoggingLevel((long) fields.get("loggingLevel"));
}

		if(fields.get("maxLogFileSize") != null) {
			returnVal.setMaxLogFileSize((long) fields.get("maxLogFileSize"));
}

		if(fields.get("stackTraceOn") != null) {
			returnVal.setStackTraceOn((boolean) fields.get("stackTraceOn"));
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("returnUrl") != null) {
			returnVal.setReturnUrl((String) fields.get("returnUrl"));
}

		if(fields.get("cancelReturnUrl") != null) {
			returnVal.setCancelReturnUrl((String) fields.get("cancelReturnUrl"));
}


		return returnVal;
 } 
	public static PaymentGatewayPayflowPro mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayPayflowPro returnVal = new PaymentGatewayPayflowPro();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("certsPath") != null) {
			returnVal.setCertsPath((String) fields.get("certsPath"));
}

		if(fields.get("hostAddress") != null) {
			returnVal.setHostAddress((String) fields.get("hostAddress"));
}

		if(fields.get("hostPort") != null) {
String buf;
buf = fields.get("hostPort");
long ibuf = Long.parseLong(buf);
			returnVal.setHostPort(ibuf);
}

		if(fields.get("timeout") != null) {
String buf;
buf = fields.get("timeout");
long ibuf = Long.parseLong(buf);
			returnVal.setTimeout(ibuf);
}

		if(fields.get("proxyAddress") != null) {
			returnVal.setProxyAddress((String) fields.get("proxyAddress"));
}

		if(fields.get("proxyPort") != null) {
String buf;
buf = fields.get("proxyPort");
long ibuf = Long.parseLong(buf);
			returnVal.setProxyPort(ibuf);
}

		if(fields.get("proxyLogon") != null) {
			returnVal.setProxyLogon((String) fields.get("proxyLogon"));
}

		if(fields.get("proxyPassword") != null) {
			returnVal.setProxyPassword((String) fields.get("proxyPassword"));
}

		if(fields.get("vendor") != null) {
			returnVal.setVendor((String) fields.get("vendor"));
}

		if(fields.get("userId") != null) {
			returnVal.setUserId((String) fields.get("userId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((String) fields.get("pwd"));
}

		if(fields.get("partner") != null) {
			returnVal.setPartner((String) fields.get("partner"));
}

		if(fields.get("checkAvs") != null) {
String buf;
buf = fields.get("checkAvs");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCheckAvs(ibuf);
}

		if(fields.get("checkCvv2") != null) {
String buf;
buf = fields.get("checkCvv2");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCheckCvv2(ibuf);
}

		if(fields.get("preAuth") != null) {
String buf;
buf = fields.get("preAuth");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPreAuth(ibuf);
}

		if(fields.get("enableTransmit") != null) {
			returnVal.setEnableTransmit((String) fields.get("enableTransmit"));
}

		if(fields.get("logFileName") != null) {
			returnVal.setLogFileName((String) fields.get("logFileName"));
}

		if(fields.get("loggingLevel") != null) {
String buf;
buf = fields.get("loggingLevel");
long ibuf = Long.parseLong(buf);
			returnVal.setLoggingLevel(ibuf);
}

		if(fields.get("maxLogFileSize") != null) {
String buf;
buf = fields.get("maxLogFileSize");
long ibuf = Long.parseLong(buf);
			returnVal.setMaxLogFileSize(ibuf);
}

		if(fields.get("stackTraceOn") != null) {
String buf;
buf = fields.get("stackTraceOn");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setStackTraceOn(ibuf);
}

		if(fields.get("redirectUrl") != null) {
			returnVal.setRedirectUrl((String) fields.get("redirectUrl"));
}

		if(fields.get("returnUrl") != null) {
			returnVal.setReturnUrl((String) fields.get("returnUrl"));
}

		if(fields.get("cancelReturnUrl") != null) {
			returnVal.setCancelReturnUrl((String) fields.get("cancelReturnUrl"));
}


		return returnVal;
 } 
	public static PaymentGatewayPayflowPro map(GenericValue val) {

PaymentGatewayPayflowPro returnVal = new PaymentGatewayPayflowPro();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setCertsPath(val.getString("certsPath"));
		returnVal.setHostAddress(val.getString("hostAddress"));
		returnVal.setHostPort(val.getLong("hostPort"));
		returnVal.setTimeout(val.getLong("timeout"));
		returnVal.setProxyAddress(val.getString("proxyAddress"));
		returnVal.setProxyPort(val.getLong("proxyPort"));
		returnVal.setProxyLogon(val.getString("proxyLogon"));
		returnVal.setProxyPassword(val.getString("proxyPassword"));
		returnVal.setVendor(val.getString("vendor"));
		returnVal.setUserId(val.getString("userId"));
		returnVal.setPwd(val.getString("pwd"));
		returnVal.setPartner(val.getString("partner"));
		returnVal.setCheckAvs(val.getBoolean("checkAvs"));
		returnVal.setCheckCvv2(val.getBoolean("checkCvv2"));
		returnVal.setPreAuth(val.getBoolean("preAuth"));
		returnVal.setEnableTransmit(val.getString("enableTransmit"));
		returnVal.setLogFileName(val.getString("logFileName"));
		returnVal.setLoggingLevel(val.getLong("loggingLevel"));
		returnVal.setMaxLogFileSize(val.getLong("maxLogFileSize"));
		returnVal.setStackTraceOn(val.getBoolean("stackTraceOn"));
		returnVal.setRedirectUrl(val.getString("redirectUrl"));
		returnVal.setReturnUrl(val.getString("returnUrl"));
		returnVal.setCancelReturnUrl(val.getString("cancelReturnUrl"));


return returnVal;

}

public static PaymentGatewayPayflowPro map(HttpServletRequest request) throws Exception {

		PaymentGatewayPayflowPro returnVal = new PaymentGatewayPayflowPro();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("certsPath"))  {
returnVal.setCertsPath(request.getParameter("certsPath"));
}
		if(paramMap.containsKey("hostAddress"))  {
returnVal.setHostAddress(request.getParameter("hostAddress"));
}
		if(paramMap.containsKey("hostPort"))  {
String buf = request.getParameter("hostPort");
Long ibuf = Long.parseLong(buf);
returnVal.setHostPort(ibuf);
}
		if(paramMap.containsKey("timeout"))  {
String buf = request.getParameter("timeout");
Long ibuf = Long.parseLong(buf);
returnVal.setTimeout(ibuf);
}
		if(paramMap.containsKey("proxyAddress"))  {
returnVal.setProxyAddress(request.getParameter("proxyAddress"));
}
		if(paramMap.containsKey("proxyPort"))  {
String buf = request.getParameter("proxyPort");
Long ibuf = Long.parseLong(buf);
returnVal.setProxyPort(ibuf);
}
		if(paramMap.containsKey("proxyLogon"))  {
returnVal.setProxyLogon(request.getParameter("proxyLogon"));
}
		if(paramMap.containsKey("proxyPassword"))  {
returnVal.setProxyPassword(request.getParameter("proxyPassword"));
}
		if(paramMap.containsKey("vendor"))  {
returnVal.setVendor(request.getParameter("vendor"));
}
		if(paramMap.containsKey("userId"))  {
returnVal.setUserId(request.getParameter("userId"));
}
		if(paramMap.containsKey("pwd"))  {
returnVal.setPwd(request.getParameter("pwd"));
}
		if(paramMap.containsKey("partner"))  {
returnVal.setPartner(request.getParameter("partner"));
}
		if(paramMap.containsKey("checkAvs"))  {
String buf = request.getParameter("checkAvs");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCheckAvs(ibuf);
}
		if(paramMap.containsKey("checkCvv2"))  {
String buf = request.getParameter("checkCvv2");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCheckCvv2(ibuf);
}
		if(paramMap.containsKey("preAuth"))  {
String buf = request.getParameter("preAuth");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPreAuth(ibuf);
}
		if(paramMap.containsKey("enableTransmit"))  {
returnVal.setEnableTransmit(request.getParameter("enableTransmit"));
}
		if(paramMap.containsKey("logFileName"))  {
returnVal.setLogFileName(request.getParameter("logFileName"));
}
		if(paramMap.containsKey("loggingLevel"))  {
String buf = request.getParameter("loggingLevel");
Long ibuf = Long.parseLong(buf);
returnVal.setLoggingLevel(ibuf);
}
		if(paramMap.containsKey("maxLogFileSize"))  {
String buf = request.getParameter("maxLogFileSize");
Long ibuf = Long.parseLong(buf);
returnVal.setMaxLogFileSize(ibuf);
}
		if(paramMap.containsKey("stackTraceOn"))  {
String buf = request.getParameter("stackTraceOn");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setStackTraceOn(ibuf);
}
		if(paramMap.containsKey("redirectUrl"))  {
returnVal.setRedirectUrl(request.getParameter("redirectUrl"));
}
		if(paramMap.containsKey("returnUrl"))  {
returnVal.setReturnUrl(request.getParameter("returnUrl"));
}
		if(paramMap.containsKey("cancelReturnUrl"))  {
returnVal.setCancelReturnUrl(request.getParameter("cancelReturnUrl"));
}
return returnVal;

}
}
