package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayAuthorizeNet;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;

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
			returnVal.setTransactionUrl((String) fields.get("transactionUrl"));
}

		if(fields.get("certificateAlias") != null) {
			returnVal.setCertificateAlias((String) fields.get("certificateAlias"));
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
			returnVal.setTranKey((String) fields.get("tranKey"));
}

		if(fields.get("userId") != null) {
			returnVal.setUserId((String) fields.get("userId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((String) fields.get("pwd"));
}

		if(fields.get("transDescription") != null) {
			returnVal.setTransDescription((String) fields.get("transDescription"));
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
			returnVal.setTransactionUrl((String) fields.get("transactionUrl"));
}

		if(fields.get("certificateAlias") != null) {
			returnVal.setCertificateAlias((String) fields.get("certificateAlias"));
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
			returnVal.setTranKey((String) fields.get("tranKey"));
}

		if(fields.get("userId") != null) {
			returnVal.setUserId((String) fields.get("userId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((String) fields.get("pwd"));
}

		if(fields.get("transDescription") != null) {
			returnVal.setTransDescription((String) fields.get("transDescription"));
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
		returnVal.setTransactionUrl(val.getString("transactionUrl"));
		returnVal.setCertificateAlias(val.getString("certificateAlias"));
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
		returnVal.setTranKey(val.getString("tranKey"));
		returnVal.setUserId(val.getString("userId"));
		returnVal.setPwd(val.getString("pwd"));
		returnVal.setTransDescription(val.getString("transDescription"));
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
returnVal.setTransactionUrl(request.getParameter("transactionUrl"));
}
		if(paramMap.containsKey("certificateAlias"))  {
returnVal.setCertificateAlias(request.getParameter("certificateAlias"));
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
returnVal.setTranKey(request.getParameter("tranKey"));
}
		if(paramMap.containsKey("userId"))  {
returnVal.setUserId(request.getParameter("userId"));
}
		if(paramMap.containsKey("pwd"))  {
returnVal.setPwd(request.getParameter("pwd"));
}
		if(paramMap.containsKey("transDescription"))  {
returnVal.setTransDescription(request.getParameter("transDescription"));
}
		if(paramMap.containsKey("duplicateWindow"))  {
String buf = request.getParameter("duplicateWindow");
Long ibuf = Long.parseLong(buf);
returnVal.setDuplicateWindow(ibuf);
}
return returnVal;

}
}
