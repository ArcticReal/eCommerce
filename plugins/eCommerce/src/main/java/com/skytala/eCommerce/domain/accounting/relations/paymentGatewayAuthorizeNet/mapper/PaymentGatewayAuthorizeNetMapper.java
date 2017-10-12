package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayAuthorizeNet.model.PaymentGatewayAuthorizeNet;

public class PaymentGatewayAuthorizeNetMapper  {


	public static Map<String, Object> map(PaymentGatewayAuthorizeNet paymentgatewayauthorizenet) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayauthorizenet.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayauthorizenet.getPaymentGatewayConfigId());
}

		if(paymentgatewayauthorizenet.getTransactionUrl() != null ){
			returnVal.put("transactionUrl",paymentgatewayauthorizenet.getTransactionUrl());
}

		if(paymentgatewayauthorizenet.getCertificateAlias() != null ){
			returnVal.put("certificateAlias",paymentgatewayauthorizenet.getCertificateAlias());
}

		if(paymentgatewayauthorizenet.getApiVersion() != null ){
			returnVal.put("apiVersion",paymentgatewayauthorizenet.getApiVersion());
}

		if(paymentgatewayauthorizenet.getDelimitedData() != null ){
			returnVal.put("delimitedData",paymentgatewayauthorizenet.getDelimitedData());
}

		if(paymentgatewayauthorizenet.getDelimiterChar() != null ){
			returnVal.put("delimiterChar",paymentgatewayauthorizenet.getDelimiterChar());
}

		if(paymentgatewayauthorizenet.getCpVersion() != null ){
			returnVal.put("cpVersion",paymentgatewayauthorizenet.getCpVersion());
}

		if(paymentgatewayauthorizenet.getCpMarketType() != null ){
			returnVal.put("cpMarketType",paymentgatewayauthorizenet.getCpMarketType());
}

		if(paymentgatewayauthorizenet.getCpDeviceType() != null ){
			returnVal.put("cpDeviceType",paymentgatewayauthorizenet.getCpDeviceType());
}

		if(paymentgatewayauthorizenet.getMethod() != null ){
			returnVal.put("method",paymentgatewayauthorizenet.getMethod());
}

		if(paymentgatewayauthorizenet.getEmailCustomer() != null ){
			returnVal.put("emailCustomer",paymentgatewayauthorizenet.getEmailCustomer());
}

		if(paymentgatewayauthorizenet.getEmailMerchant() != null ){
			returnVal.put("emailMerchant",paymentgatewayauthorizenet.getEmailMerchant());
}

		if(paymentgatewayauthorizenet.getTestMode() != null ){
			returnVal.put("testMode",paymentgatewayauthorizenet.getTestMode());
}

		if(paymentgatewayauthorizenet.getRelayResponse() != null ){
			returnVal.put("relayResponse",paymentgatewayauthorizenet.getRelayResponse());
}

		if(paymentgatewayauthorizenet.getTranKey() != null ){
			returnVal.put("tranKey",paymentgatewayauthorizenet.getTranKey());
}

		if(paymentgatewayauthorizenet.getUserId() != null ){
			returnVal.put("userId",paymentgatewayauthorizenet.getUserId());
}

		if(paymentgatewayauthorizenet.getPwd() != null ){
			returnVal.put("pwd",paymentgatewayauthorizenet.getPwd());
}

		if(paymentgatewayauthorizenet.getTransDescription() != null ){
			returnVal.put("transDescription",paymentgatewayauthorizenet.getTransDescription());
}

		if(paymentgatewayauthorizenet.getDuplicateWindow() != null ){
			returnVal.put("duplicateWindow",paymentgatewayauthorizenet.getDuplicateWindow());
}

		return returnVal;
}


	public static PaymentGatewayAuthorizeNet map(Map<String, Object> fields) {

		PaymentGatewayAuthorizeNet returnVal = new PaymentGatewayAuthorizeNet();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("transactionUrl") != null) {
			returnVal.setTransactionUrl((long) fields.get("transactionUrl"));
}

		if(fields.get("certificateAlias") != null) {
			returnVal.setCertificateAlias((long) fields.get("certificateAlias"));
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("delimitedData") != null) {
			returnVal.setDelimitedData((String) fields.get("delimitedData"));
}

		if(fields.get("delimiterChar") != null) {
			returnVal.setDelimiterChar((String) fields.get("delimiterChar"));
}

		if(fields.get("cpVersion") != null) {
			returnVal.setCpVersion((String) fields.get("cpVersion"));
}

		if(fields.get("cpMarketType") != null) {
			returnVal.setCpMarketType((String) fields.get("cpMarketType"));
}

		if(fields.get("cpDeviceType") != null) {
			returnVal.setCpDeviceType((String) fields.get("cpDeviceType"));
}

		if(fields.get("method") != null) {
			returnVal.setMethod((String) fields.get("method"));
}

		if(fields.get("emailCustomer") != null) {
			returnVal.setEmailCustomer((String) fields.get("emailCustomer"));
}

		if(fields.get("emailMerchant") != null) {
			returnVal.setEmailMerchant((String) fields.get("emailMerchant"));
}

		if(fields.get("testMode") != null) {
			returnVal.setTestMode((String) fields.get("testMode"));
}

		if(fields.get("relayResponse") != null) {
			returnVal.setRelayResponse((String) fields.get("relayResponse"));
}

		if(fields.get("tranKey") != null) {
			returnVal.setTranKey((long) fields.get("tranKey"));
}

		if(fields.get("userId") != null) {
			returnVal.setUserId((long) fields.get("userId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((long) fields.get("pwd"));
}

		if(fields.get("transDescription") != null) {
			returnVal.setTransDescription((long) fields.get("transDescription"));
}

		if(fields.get("duplicateWindow") != null) {
			returnVal.setDuplicateWindow((long) fields.get("duplicateWindow"));
}


		return returnVal;
 } 
	public static PaymentGatewayAuthorizeNet mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayAuthorizeNet returnVal = new PaymentGatewayAuthorizeNet();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("transactionUrl") != null) {
String buf;
buf = fields.get("transactionUrl");
long ibuf = Long.parseLong(buf);
			returnVal.setTransactionUrl(ibuf);
}

		if(fields.get("certificateAlias") != null) {
String buf;
buf = fields.get("certificateAlias");
long ibuf = Long.parseLong(buf);
			returnVal.setCertificateAlias(ibuf);
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("delimitedData") != null) {
			returnVal.setDelimitedData((String) fields.get("delimitedData"));
}

		if(fields.get("delimiterChar") != null) {
			returnVal.setDelimiterChar((String) fields.get("delimiterChar"));
}

		if(fields.get("cpVersion") != null) {
			returnVal.setCpVersion((String) fields.get("cpVersion"));
}

		if(fields.get("cpMarketType") != null) {
			returnVal.setCpMarketType((String) fields.get("cpMarketType"));
}

		if(fields.get("cpDeviceType") != null) {
			returnVal.setCpDeviceType((String) fields.get("cpDeviceType"));
}

		if(fields.get("method") != null) {
			returnVal.setMethod((String) fields.get("method"));
}

		if(fields.get("emailCustomer") != null) {
			returnVal.setEmailCustomer((String) fields.get("emailCustomer"));
}

		if(fields.get("emailMerchant") != null) {
			returnVal.setEmailMerchant((String) fields.get("emailMerchant"));
}

		if(fields.get("testMode") != null) {
			returnVal.setTestMode((String) fields.get("testMode"));
}

		if(fields.get("relayResponse") != null) {
			returnVal.setRelayResponse((String) fields.get("relayResponse"));
}

		if(fields.get("tranKey") != null) {
String buf;
buf = fields.get("tranKey");
long ibuf = Long.parseLong(buf);
			returnVal.setTranKey(ibuf);
}

		if(fields.get("userId") != null) {
String buf;
buf = fields.get("userId");
long ibuf = Long.parseLong(buf);
			returnVal.setUserId(ibuf);
}

		if(fields.get("pwd") != null) {
String buf;
buf = fields.get("pwd");
long ibuf = Long.parseLong(buf);
			returnVal.setPwd(ibuf);
}

		if(fields.get("transDescription") != null) {
String buf;
buf = fields.get("transDescription");
long ibuf = Long.parseLong(buf);
			returnVal.setTransDescription(ibuf);
}

		if(fields.get("duplicateWindow") != null) {
String buf;
buf = fields.get("duplicateWindow");
long ibuf = Long.parseLong(buf);
			returnVal.setDuplicateWindow(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayAuthorizeNet map(GenericValue val) {

PaymentGatewayAuthorizeNet returnVal = new PaymentGatewayAuthorizeNet();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setTransactionUrl(val.getLong("transactionUrl"));
		returnVal.setCertificateAlias(val.getLong("certificateAlias"));
		returnVal.setApiVersion(val.getString("apiVersion"));
		returnVal.setDelimitedData(val.getString("delimitedData"));
		returnVal.setDelimiterChar(val.getString("delimiterChar"));
		returnVal.setCpVersion(val.getString("cpVersion"));
		returnVal.setCpMarketType(val.getString("cpMarketType"));
		returnVal.setCpDeviceType(val.getString("cpDeviceType"));
		returnVal.setMethod(val.getString("method"));
		returnVal.setEmailCustomer(val.getString("emailCustomer"));
		returnVal.setEmailMerchant(val.getString("emailMerchant"));
		returnVal.setTestMode(val.getString("testMode"));
		returnVal.setRelayResponse(val.getString("relayResponse"));
		returnVal.setTranKey(val.getLong("tranKey"));
		returnVal.setUserId(val.getLong("userId"));
		returnVal.setPwd(val.getLong("pwd"));
		returnVal.setTransDescription(val.getLong("transDescription"));
		returnVal.setDuplicateWindow(val.getLong("duplicateWindow"));


return returnVal;

}

public static PaymentGatewayAuthorizeNet map(HttpServletRequest request) throws Exception {

		PaymentGatewayAuthorizeNet returnVal = new PaymentGatewayAuthorizeNet();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("transactionUrl"))  {
String buf = request.getParameter("transactionUrl");
Long ibuf = Long.parseLong(buf);
returnVal.setTransactionUrl(ibuf);
}
		if(paramMap.containsKey("certificateAlias"))  {
String buf = request.getParameter("certificateAlias");
Long ibuf = Long.parseLong(buf);
returnVal.setCertificateAlias(ibuf);
}
		if(paramMap.containsKey("apiVersion"))  {
returnVal.setApiVersion(request.getParameter("apiVersion"));
}
		if(paramMap.containsKey("delimitedData"))  {
returnVal.setDelimitedData(request.getParameter("delimitedData"));
}
		if(paramMap.containsKey("delimiterChar"))  {
returnVal.setDelimiterChar(request.getParameter("delimiterChar"));
}
		if(paramMap.containsKey("cpVersion"))  {
returnVal.setCpVersion(request.getParameter("cpVersion"));
}
		if(paramMap.containsKey("cpMarketType"))  {
returnVal.setCpMarketType(request.getParameter("cpMarketType"));
}
		if(paramMap.containsKey("cpDeviceType"))  {
returnVal.setCpDeviceType(request.getParameter("cpDeviceType"));
}
		if(paramMap.containsKey("method"))  {
returnVal.setMethod(request.getParameter("method"));
}
		if(paramMap.containsKey("emailCustomer"))  {
returnVal.setEmailCustomer(request.getParameter("emailCustomer"));
}
		if(paramMap.containsKey("emailMerchant"))  {
returnVal.setEmailMerchant(request.getParameter("emailMerchant"));
}
		if(paramMap.containsKey("testMode"))  {
returnVal.setTestMode(request.getParameter("testMode"));
}
		if(paramMap.containsKey("relayResponse"))  {
returnVal.setRelayResponse(request.getParameter("relayResponse"));
}
		if(paramMap.containsKey("tranKey"))  {
String buf = request.getParameter("tranKey");
Long ibuf = Long.parseLong(buf);
returnVal.setTranKey(ibuf);
}
		if(paramMap.containsKey("userId"))  {
String buf = request.getParameter("userId");
Long ibuf = Long.parseLong(buf);
returnVal.setUserId(ibuf);
}
		if(paramMap.containsKey("pwd"))  {
String buf = request.getParameter("pwd");
Long ibuf = Long.parseLong(buf);
returnVal.setPwd(ibuf);
}
		if(paramMap.containsKey("transDescription"))  {
String buf = request.getParameter("transDescription");
Long ibuf = Long.parseLong(buf);
returnVal.setTransDescription(ibuf);
}
		if(paramMap.containsKey("duplicateWindow"))  {
String buf = request.getParameter("duplicateWindow");
Long ibuf = Long.parseLong(buf);
returnVal.setDuplicateWindow(ibuf);
}
return returnVal;

}
}
