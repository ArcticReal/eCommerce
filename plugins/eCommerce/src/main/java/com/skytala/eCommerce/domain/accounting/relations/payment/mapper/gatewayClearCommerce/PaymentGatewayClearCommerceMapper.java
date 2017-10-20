package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayClearCommerce;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce.PaymentGatewayClearCommerce;

public class PaymentGatewayClearCommerceMapper  {


	public static Map<String, Object> map(PaymentGatewayClearCommerce paymentgatewayclearcommerce) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayclearcommerce.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayclearcommerce.getPaymentGatewayConfigId());
}

		if(paymentgatewayclearcommerce.getSourceId() != null ){
			returnVal.put("sourceId",paymentgatewayclearcommerce.getSourceId());
}

		if(paymentgatewayclearcommerce.getGroupId() != null ){
			returnVal.put("groupId",paymentgatewayclearcommerce.getGroupId());
}

		if(paymentgatewayclearcommerce.getClientId() != null ){
			returnVal.put("clientId",paymentgatewayclearcommerce.getClientId());
}

		if(paymentgatewayclearcommerce.getUsername() != null ){
			returnVal.put("username",paymentgatewayclearcommerce.getUsername());
}

		if(paymentgatewayclearcommerce.getPwd() != null ){
			returnVal.put("pwd",paymentgatewayclearcommerce.getPwd());
}

		if(paymentgatewayclearcommerce.getUserAlias() != null ){
			returnVal.put("userAlias",paymentgatewayclearcommerce.getUserAlias());
}

		if(paymentgatewayclearcommerce.getEffectiveAlias() != null ){
			returnVal.put("effectiveAlias",paymentgatewayclearcommerce.getEffectiveAlias());
}

		if(paymentgatewayclearcommerce.getProcessMode() != null ){
			returnVal.put("processMode",paymentgatewayclearcommerce.getProcessMode());
}

		if(paymentgatewayclearcommerce.getServerURL() != null ){
			returnVal.put("serverURL",paymentgatewayclearcommerce.getServerURL());
}

		if(paymentgatewayclearcommerce.getEnableCVM() != null ){
			returnVal.put("enableCVM",paymentgatewayclearcommerce.getEnableCVM());
}

		return returnVal;
}


	public static PaymentGatewayClearCommerce map(Map<String, Object> fields) {

		PaymentGatewayClearCommerce returnVal = new PaymentGatewayClearCommerce();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("sourceId") != null) {
			returnVal.setSourceId((String) fields.get("sourceId"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("clientId") != null) {
			returnVal.setClientId((String) fields.get("clientId"));
}

		if(fields.get("username") != null) {
			returnVal.setUsername((String) fields.get("username"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((long) fields.get("pwd"));
}

		if(fields.get("userAlias") != null) {
			returnVal.setUserAlias((String) fields.get("userAlias"));
}

		if(fields.get("effectiveAlias") != null) {
			returnVal.setEffectiveAlias((String) fields.get("effectiveAlias"));
}

		if(fields.get("processMode") != null) {
			returnVal.setProcessMode((boolean) fields.get("processMode"));
}

		if(fields.get("serverURL") != null) {
			returnVal.setServerURL((long) fields.get("serverURL"));
}

		if(fields.get("enableCVM") != null) {
			returnVal.setEnableCVM((boolean) fields.get("enableCVM"));
}


		return returnVal;
 } 
	public static PaymentGatewayClearCommerce mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayClearCommerce returnVal = new PaymentGatewayClearCommerce();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("sourceId") != null) {
			returnVal.setSourceId((String) fields.get("sourceId"));
}

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("clientId") != null) {
			returnVal.setClientId((String) fields.get("clientId"));
}

		if(fields.get("username") != null) {
			returnVal.setUsername((String) fields.get("username"));
}

		if(fields.get("pwd") != null) {
String buf;
buf = fields.get("pwd");
long ibuf = Long.parseLong(buf);
			returnVal.setPwd(ibuf);
}

		if(fields.get("userAlias") != null) {
			returnVal.setUserAlias((String) fields.get("userAlias"));
}

		if(fields.get("effectiveAlias") != null) {
			returnVal.setEffectiveAlias((String) fields.get("effectiveAlias"));
}

		if(fields.get("processMode") != null) {
String buf;
buf = fields.get("processMode");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setProcessMode(ibuf);
}

		if(fields.get("serverURL") != null) {
String buf;
buf = fields.get("serverURL");
long ibuf = Long.parseLong(buf);
			returnVal.setServerURL(ibuf);
}

		if(fields.get("enableCVM") != null) {
String buf;
buf = fields.get("enableCVM");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnableCVM(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayClearCommerce map(GenericValue val) {

PaymentGatewayClearCommerce returnVal = new PaymentGatewayClearCommerce();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setSourceId(val.getString("sourceId"));
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setClientId(val.getString("clientId"));
		returnVal.setUsername(val.getString("username"));
		returnVal.setPwd(val.getLong("pwd"));
		returnVal.setUserAlias(val.getString("userAlias"));
		returnVal.setEffectiveAlias(val.getString("effectiveAlias"));
		returnVal.setProcessMode(val.getBoolean("processMode"));
		returnVal.setServerURL(val.getLong("serverURL"));
		returnVal.setEnableCVM(val.getBoolean("enableCVM"));


return returnVal;

}

public static PaymentGatewayClearCommerce map(HttpServletRequest request) throws Exception {

		PaymentGatewayClearCommerce returnVal = new PaymentGatewayClearCommerce();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("sourceId"))  {
returnVal.setSourceId(request.getParameter("sourceId"));
}
		if(paramMap.containsKey("groupId"))  {
returnVal.setGroupId(request.getParameter("groupId"));
}
		if(paramMap.containsKey("clientId"))  {
returnVal.setClientId(request.getParameter("clientId"));
}
		if(paramMap.containsKey("username"))  {
returnVal.setUsername(request.getParameter("username"));
}
		if(paramMap.containsKey("pwd"))  {
String buf = request.getParameter("pwd");
Long ibuf = Long.parseLong(buf);
returnVal.setPwd(ibuf);
}
		if(paramMap.containsKey("userAlias"))  {
returnVal.setUserAlias(request.getParameter("userAlias"));
}
		if(paramMap.containsKey("effectiveAlias"))  {
returnVal.setEffectiveAlias(request.getParameter("effectiveAlias"));
}
		if(paramMap.containsKey("processMode"))  {
String buf = request.getParameter("processMode");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setProcessMode(ibuf);
}
		if(paramMap.containsKey("serverURL"))  {
String buf = request.getParameter("serverURL");
Long ibuf = Long.parseLong(buf);
returnVal.setServerURL(ibuf);
}
		if(paramMap.containsKey("enableCVM"))  {
String buf = request.getParameter("enableCVM");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setEnableCVM(ibuf);
}
return returnVal;

}
}
