package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;

public class PaymentGatewaySagePayMapper  {


	public static Map<String, Object> map(PaymentGatewaySagePay paymentgatewaysagepay) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewaysagepay.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewaysagepay.getPaymentGatewayConfigId());
}

		if(paymentgatewaysagepay.getVendor() != null ){
			returnVal.put("vendor",paymentgatewaysagepay.getVendor());
}

		if(paymentgatewaysagepay.getProductionHost() != null ){
			returnVal.put("productionHost",paymentgatewaysagepay.getProductionHost());
}

		if(paymentgatewaysagepay.getTestingHost() != null ){
			returnVal.put("testingHost",paymentgatewaysagepay.getTestingHost());
}

		if(paymentgatewaysagepay.getSagePayMode() != null ){
			returnVal.put("sagePayMode",paymentgatewaysagepay.getSagePayMode());
}

		if(paymentgatewaysagepay.getProtocolVersion() != null ){
			returnVal.put("protocolVersion",paymentgatewaysagepay.getProtocolVersion());
}

		if(paymentgatewaysagepay.getAuthenticationTransType() != null ){
			returnVal.put("authenticationTransType",paymentgatewaysagepay.getAuthenticationTransType());
}

		if(paymentgatewaysagepay.getAuthenticationUrl() != null ){
			returnVal.put("authenticationUrl",paymentgatewaysagepay.getAuthenticationUrl());
}

		if(paymentgatewaysagepay.getAuthoriseTransType() != null ){
			returnVal.put("authoriseTransType",paymentgatewaysagepay.getAuthoriseTransType());
}

		if(paymentgatewaysagepay.getAuthoriseUrl() != null ){
			returnVal.put("authoriseUrl",paymentgatewaysagepay.getAuthoriseUrl());
}

		if(paymentgatewaysagepay.getReleaseTransType() != null ){
			returnVal.put("releaseTransType",paymentgatewaysagepay.getReleaseTransType());
}

		if(paymentgatewaysagepay.getReleaseUrl() != null ){
			returnVal.put("releaseUrl",paymentgatewaysagepay.getReleaseUrl());
}

		if(paymentgatewaysagepay.getVoidUrl() != null ){
			returnVal.put("voidUrl",paymentgatewaysagepay.getVoidUrl());
}

		if(paymentgatewaysagepay.getRefundUrl() != null ){
			returnVal.put("refundUrl",paymentgatewaysagepay.getRefundUrl());
}

		return returnVal;
}


	public static PaymentGatewaySagePay map(Map<String, Object> fields) {

		PaymentGatewaySagePay returnVal = new PaymentGatewaySagePay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("vendor") != null) {
			returnVal.setVendor((String) fields.get("vendor"));
}

		if(fields.get("productionHost") != null) {
			returnVal.setProductionHost((String) fields.get("productionHost"));
}

		if(fields.get("testingHost") != null) {
			returnVal.setTestingHost((String) fields.get("testingHost"));
}

		if(fields.get("sagePayMode") != null) {
			returnVal.setSagePayMode((String) fields.get("sagePayMode"));
}

		if(fields.get("protocolVersion") != null) {
			returnVal.setProtocolVersion((String) fields.get("protocolVersion"));
}

		if(fields.get("authenticationTransType") != null) {
			returnVal.setAuthenticationTransType((String) fields.get("authenticationTransType"));
}

		if(fields.get("authenticationUrl") != null) {
			returnVal.setAuthenticationUrl((String) fields.get("authenticationUrl"));
}

		if(fields.get("authoriseTransType") != null) {
			returnVal.setAuthoriseTransType((String) fields.get("authoriseTransType"));
}

		if(fields.get("authoriseUrl") != null) {
			returnVal.setAuthoriseUrl((String) fields.get("authoriseUrl"));
}

		if(fields.get("releaseTransType") != null) {
			returnVal.setReleaseTransType((String) fields.get("releaseTransType"));
}

		if(fields.get("releaseUrl") != null) {
			returnVal.setReleaseUrl((String) fields.get("releaseUrl"));
}

		if(fields.get("voidUrl") != null) {
			returnVal.setVoidUrl((String) fields.get("voidUrl"));
}

		if(fields.get("refundUrl") != null) {
			returnVal.setRefundUrl((String) fields.get("refundUrl"));
}


		return returnVal;
 } 
	public static PaymentGatewaySagePay mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewaySagePay returnVal = new PaymentGatewaySagePay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("vendor") != null) {
			returnVal.setVendor((String) fields.get("vendor"));
}

		if(fields.get("productionHost") != null) {
			returnVal.setProductionHost((String) fields.get("productionHost"));
}

		if(fields.get("testingHost") != null) {
			returnVal.setTestingHost((String) fields.get("testingHost"));
}

		if(fields.get("sagePayMode") != null) {
			returnVal.setSagePayMode((String) fields.get("sagePayMode"));
}

		if(fields.get("protocolVersion") != null) {
			returnVal.setProtocolVersion((String) fields.get("protocolVersion"));
}

		if(fields.get("authenticationTransType") != null) {
			returnVal.setAuthenticationTransType((String) fields.get("authenticationTransType"));
}

		if(fields.get("authenticationUrl") != null) {
			returnVal.setAuthenticationUrl((String) fields.get("authenticationUrl"));
}

		if(fields.get("authoriseTransType") != null) {
			returnVal.setAuthoriseTransType((String) fields.get("authoriseTransType"));
}

		if(fields.get("authoriseUrl") != null) {
			returnVal.setAuthoriseUrl((String) fields.get("authoriseUrl"));
}

		if(fields.get("releaseTransType") != null) {
			returnVal.setReleaseTransType((String) fields.get("releaseTransType"));
}

		if(fields.get("releaseUrl") != null) {
			returnVal.setReleaseUrl((String) fields.get("releaseUrl"));
}

		if(fields.get("voidUrl") != null) {
			returnVal.setVoidUrl((String) fields.get("voidUrl"));
}

		if(fields.get("refundUrl") != null) {
			returnVal.setRefundUrl((String) fields.get("refundUrl"));
}


		return returnVal;
 } 
	public static PaymentGatewaySagePay map(GenericValue val) {

PaymentGatewaySagePay returnVal = new PaymentGatewaySagePay();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setVendor(val.getString("vendor"));
		returnVal.setProductionHost(val.getString("productionHost"));
		returnVal.setTestingHost(val.getString("testingHost"));
		returnVal.setSagePayMode(val.getString("sagePayMode"));
		returnVal.setProtocolVersion(val.getString("protocolVersion"));
		returnVal.setAuthenticationTransType(val.getString("authenticationTransType"));
		returnVal.setAuthenticationUrl(val.getString("authenticationUrl"));
		returnVal.setAuthoriseTransType(val.getString("authoriseTransType"));
		returnVal.setAuthoriseUrl(val.getString("authoriseUrl"));
		returnVal.setReleaseTransType(val.getString("releaseTransType"));
		returnVal.setReleaseUrl(val.getString("releaseUrl"));
		returnVal.setVoidUrl(val.getString("voidUrl"));
		returnVal.setRefundUrl(val.getString("refundUrl"));


return returnVal;

}

public static PaymentGatewaySagePay map(HttpServletRequest request) throws Exception {

		PaymentGatewaySagePay returnVal = new PaymentGatewaySagePay();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("vendor"))  {
returnVal.setVendor(request.getParameter("vendor"));
}
		if(paramMap.containsKey("productionHost"))  {
returnVal.setProductionHost(request.getParameter("productionHost"));
}
		if(paramMap.containsKey("testingHost"))  {
returnVal.setTestingHost(request.getParameter("testingHost"));
}
		if(paramMap.containsKey("sagePayMode"))  {
returnVal.setSagePayMode(request.getParameter("sagePayMode"));
}
		if(paramMap.containsKey("protocolVersion"))  {
returnVal.setProtocolVersion(request.getParameter("protocolVersion"));
}
		if(paramMap.containsKey("authenticationTransType"))  {
returnVal.setAuthenticationTransType(request.getParameter("authenticationTransType"));
}
		if(paramMap.containsKey("authenticationUrl"))  {
returnVal.setAuthenticationUrl(request.getParameter("authenticationUrl"));
}
		if(paramMap.containsKey("authoriseTransType"))  {
returnVal.setAuthoriseTransType(request.getParameter("authoriseTransType"));
}
		if(paramMap.containsKey("authoriseUrl"))  {
returnVal.setAuthoriseUrl(request.getParameter("authoriseUrl"));
}
		if(paramMap.containsKey("releaseTransType"))  {
returnVal.setReleaseTransType(request.getParameter("releaseTransType"));
}
		if(paramMap.containsKey("releaseUrl"))  {
returnVal.setReleaseUrl(request.getParameter("releaseUrl"));
}
		if(paramMap.containsKey("voidUrl"))  {
returnVal.setVoidUrl(request.getParameter("voidUrl"));
}
		if(paramMap.containsKey("refundUrl"))  {
returnVal.setRefundUrl(request.getParameter("refundUrl"));
}
return returnVal;

}
}
