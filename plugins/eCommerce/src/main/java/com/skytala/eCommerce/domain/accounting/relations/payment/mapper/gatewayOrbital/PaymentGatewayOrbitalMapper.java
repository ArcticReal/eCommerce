package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayOrbital;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital.PaymentGatewayOrbital;

public class PaymentGatewayOrbitalMapper  {


	public static Map<String, Object> map(PaymentGatewayOrbital paymentgatewayorbital) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayorbital.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayorbital.getPaymentGatewayConfigId());
}

		if(paymentgatewayorbital.getUsername() != null ){
			returnVal.put("username",paymentgatewayorbital.getUsername());
}

		if(paymentgatewayorbital.getConnectionPassword() != null ){
			returnVal.put("connectionPassword",paymentgatewayorbital.getConnectionPassword());
}

		if(paymentgatewayorbital.getMerchantId() != null ){
			returnVal.put("merchantId",paymentgatewayorbital.getMerchantId());
}

		if(paymentgatewayorbital.getEngineClass() != null ){
			returnVal.put("engineClass",paymentgatewayorbital.getEngineClass());
}

		if(paymentgatewayorbital.getHostName() != null ){
			returnVal.put("hostName",paymentgatewayorbital.getHostName());
}

		if(paymentgatewayorbital.getPort() != null ){
			returnVal.put("port",paymentgatewayorbital.getPort());
}

		if(paymentgatewayorbital.getHostNameFailover() != null ){
			returnVal.put("hostNameFailover",paymentgatewayorbital.getHostNameFailover());
}

		if(paymentgatewayorbital.getPortFailover() != null ){
			returnVal.put("portFailover",paymentgatewayorbital.getPortFailover());
}

		if(paymentgatewayorbital.getConnectionTimeoutSeconds() != null ){
			returnVal.put("connectionTimeoutSeconds",paymentgatewayorbital.getConnectionTimeoutSeconds());
}

		if(paymentgatewayorbital.getReadTimeoutSeconds() != null ){
			returnVal.put("readTimeoutSeconds",paymentgatewayorbital.getReadTimeoutSeconds());
}

		if(paymentgatewayorbital.getAuthorizationURI() != null ){
			returnVal.put("authorizationURI",paymentgatewayorbital.getAuthorizationURI());
}

		if(paymentgatewayorbital.getSdkVersion() != null ){
			returnVal.put("sdkVersion",paymentgatewayorbital.getSdkVersion());
}

		if(paymentgatewayorbital.getSslSocketFactory() != null ){
			returnVal.put("sslSocketFactory",paymentgatewayorbital.getSslSocketFactory());
}

		if(paymentgatewayorbital.getResponseType() != null ){
			returnVal.put("responseType",paymentgatewayorbital.getResponseType());
}

		return returnVal;
}


	public static PaymentGatewayOrbital map(Map<String, Object> fields) {

		PaymentGatewayOrbital returnVal = new PaymentGatewayOrbital();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("username") != null) {
			returnVal.setUsername((String) fields.get("username"));
}

		if(fields.get("connectionPassword") != null) {
			returnVal.setConnectionPassword((String) fields.get("connectionPassword"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("engineClass") != null) {
			returnVal.setEngineClass((String) fields.get("engineClass"));
}

		if(fields.get("hostName") != null) {
			returnVal.setHostName((String) fields.get("hostName"));
}

		if(fields.get("port") != null) {
			returnVal.setPort((long) fields.get("port"));
}

		if(fields.get("hostNameFailover") != null) {
			returnVal.setHostNameFailover((String) fields.get("hostNameFailover"));
}

		if(fields.get("portFailover") != null) {
			returnVal.setPortFailover((long) fields.get("portFailover"));
}

		if(fields.get("connectionTimeoutSeconds") != null) {
			returnVal.setConnectionTimeoutSeconds((long) fields.get("connectionTimeoutSeconds"));
}

		if(fields.get("readTimeoutSeconds") != null) {
			returnVal.setReadTimeoutSeconds((long) fields.get("readTimeoutSeconds"));
}

		if(fields.get("authorizationURI") != null) {
			returnVal.setAuthorizationURI((String) fields.get("authorizationURI"));
}

		if(fields.get("sdkVersion") != null) {
			returnVal.setSdkVersion((String) fields.get("sdkVersion"));
}

		if(fields.get("sslSocketFactory") != null) {
			returnVal.setSslSocketFactory((String) fields.get("sslSocketFactory"));
}

		if(fields.get("responseType") != null) {
			returnVal.setResponseType((String) fields.get("responseType"));
}


		return returnVal;
 } 
	public static PaymentGatewayOrbital mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayOrbital returnVal = new PaymentGatewayOrbital();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("username") != null) {
			returnVal.setUsername((String) fields.get("username"));
}

		if(fields.get("connectionPassword") != null) {
			returnVal.setConnectionPassword((String) fields.get("connectionPassword"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("engineClass") != null) {
			returnVal.setEngineClass((String) fields.get("engineClass"));
}

		if(fields.get("hostName") != null) {
			returnVal.setHostName((String) fields.get("hostName"));
}

		if(fields.get("port") != null) {
String buf;
buf = fields.get("port");
long ibuf = Long.parseLong(buf);
			returnVal.setPort(ibuf);
}

		if(fields.get("hostNameFailover") != null) {
			returnVal.setHostNameFailover((String) fields.get("hostNameFailover"));
}

		if(fields.get("portFailover") != null) {
String buf;
buf = fields.get("portFailover");
long ibuf = Long.parseLong(buf);
			returnVal.setPortFailover(ibuf);
}

		if(fields.get("connectionTimeoutSeconds") != null) {
String buf;
buf = fields.get("connectionTimeoutSeconds");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectionTimeoutSeconds(ibuf);
}

		if(fields.get("readTimeoutSeconds") != null) {
String buf;
buf = fields.get("readTimeoutSeconds");
long ibuf = Long.parseLong(buf);
			returnVal.setReadTimeoutSeconds(ibuf);
}

		if(fields.get("authorizationURI") != null) {
			returnVal.setAuthorizationURI((String) fields.get("authorizationURI"));
}

		if(fields.get("sdkVersion") != null) {
			returnVal.setSdkVersion((String) fields.get("sdkVersion"));
}

		if(fields.get("sslSocketFactory") != null) {
			returnVal.setSslSocketFactory((String) fields.get("sslSocketFactory"));
}

		if(fields.get("responseType") != null) {
			returnVal.setResponseType((String) fields.get("responseType"));
}


		return returnVal;
 } 
	public static PaymentGatewayOrbital map(GenericValue val) {

PaymentGatewayOrbital returnVal = new PaymentGatewayOrbital();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setUsername(val.getString("username"));
		returnVal.setConnectionPassword(val.getString("connectionPassword"));
		returnVal.setMerchantId(val.getString("merchantId"));
		returnVal.setEngineClass(val.getString("engineClass"));
		returnVal.setHostName(val.getString("hostName"));
		returnVal.setPort(val.getLong("port"));
		returnVal.setHostNameFailover(val.getString("hostNameFailover"));
		returnVal.setPortFailover(val.getLong("portFailover"));
		returnVal.setConnectionTimeoutSeconds(val.getLong("connectionTimeoutSeconds"));
		returnVal.setReadTimeoutSeconds(val.getLong("readTimeoutSeconds"));
		returnVal.setAuthorizationURI(val.getString("authorizationURI"));
		returnVal.setSdkVersion(val.getString("sdkVersion"));
		returnVal.setSslSocketFactory(val.getString("sslSocketFactory"));
		returnVal.setResponseType(val.getString("responseType"));


return returnVal;

}

public static PaymentGatewayOrbital map(HttpServletRequest request) throws Exception {

		PaymentGatewayOrbital returnVal = new PaymentGatewayOrbital();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("username"))  {
returnVal.setUsername(request.getParameter("username"));
}
		if(paramMap.containsKey("connectionPassword"))  {
returnVal.setConnectionPassword(request.getParameter("connectionPassword"));
}
		if(paramMap.containsKey("merchantId"))  {
returnVal.setMerchantId(request.getParameter("merchantId"));
}
		if(paramMap.containsKey("engineClass"))  {
returnVal.setEngineClass(request.getParameter("engineClass"));
}
		if(paramMap.containsKey("hostName"))  {
returnVal.setHostName(request.getParameter("hostName"));
}
		if(paramMap.containsKey("port"))  {
String buf = request.getParameter("port");
Long ibuf = Long.parseLong(buf);
returnVal.setPort(ibuf);
}
		if(paramMap.containsKey("hostNameFailover"))  {
returnVal.setHostNameFailover(request.getParameter("hostNameFailover"));
}
		if(paramMap.containsKey("portFailover"))  {
String buf = request.getParameter("portFailover");
Long ibuf = Long.parseLong(buf);
returnVal.setPortFailover(ibuf);
}
		if(paramMap.containsKey("connectionTimeoutSeconds"))  {
String buf = request.getParameter("connectionTimeoutSeconds");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectionTimeoutSeconds(ibuf);
}
		if(paramMap.containsKey("readTimeoutSeconds"))  {
String buf = request.getParameter("readTimeoutSeconds");
Long ibuf = Long.parseLong(buf);
returnVal.setReadTimeoutSeconds(ibuf);
}
		if(paramMap.containsKey("authorizationURI"))  {
returnVal.setAuthorizationURI(request.getParameter("authorizationURI"));
}
		if(paramMap.containsKey("sdkVersion"))  {
returnVal.setSdkVersion(request.getParameter("sdkVersion"));
}
		if(paramMap.containsKey("sslSocketFactory"))  {
returnVal.setSslSocketFactory(request.getParameter("sslSocketFactory"));
}
		if(paramMap.containsKey("responseType"))  {
returnVal.setResponseType(request.getParameter("responseType"));
}
return returnVal;

}
}
