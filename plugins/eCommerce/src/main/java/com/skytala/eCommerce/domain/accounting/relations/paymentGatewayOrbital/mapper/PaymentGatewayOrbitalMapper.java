package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;

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
			returnVal.setConnectionPassword((long) fields.get("connectionPassword"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((long) fields.get("merchantId"));
}

		if(fields.get("engineClass") != null) {
			returnVal.setEngineClass((long) fields.get("engineClass"));
}

		if(fields.get("hostName") != null) {
			returnVal.setHostName((long) fields.get("hostName"));
}

		if(fields.get("port") != null) {
			returnVal.setPort((long) fields.get("port"));
}

		if(fields.get("hostNameFailover") != null) {
			returnVal.setHostNameFailover((long) fields.get("hostNameFailover"));
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
			returnVal.setAuthorizationURI((long) fields.get("authorizationURI"));
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
String buf;
buf = fields.get("connectionPassword");
long ibuf = Long.parseLong(buf);
			returnVal.setConnectionPassword(ibuf);
}

		if(fields.get("merchantId") != null) {
String buf;
buf = fields.get("merchantId");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantId(ibuf);
}

		if(fields.get("engineClass") != null) {
String buf;
buf = fields.get("engineClass");
long ibuf = Long.parseLong(buf);
			returnVal.setEngineClass(ibuf);
}

		if(fields.get("hostName") != null) {
String buf;
buf = fields.get("hostName");
long ibuf = Long.parseLong(buf);
			returnVal.setHostName(ibuf);
}

		if(fields.get("port") != null) {
String buf;
buf = fields.get("port");
long ibuf = Long.parseLong(buf);
			returnVal.setPort(ibuf);
}

		if(fields.get("hostNameFailover") != null) {
String buf;
buf = fields.get("hostNameFailover");
long ibuf = Long.parseLong(buf);
			returnVal.setHostNameFailover(ibuf);
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
String buf;
buf = fields.get("authorizationURI");
long ibuf = Long.parseLong(buf);
			returnVal.setAuthorizationURI(ibuf);
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
		returnVal.setConnectionPassword(val.getLong("connectionPassword"));
		returnVal.setMerchantId(val.getLong("merchantId"));
		returnVal.setEngineClass(val.getLong("engineClass"));
		returnVal.setHostName(val.getLong("hostName"));
		returnVal.setPort(val.getLong("port"));
		returnVal.setHostNameFailover(val.getLong("hostNameFailover"));
		returnVal.setPortFailover(val.getLong("portFailover"));
		returnVal.setConnectionTimeoutSeconds(val.getLong("connectionTimeoutSeconds"));
		returnVal.setReadTimeoutSeconds(val.getLong("readTimeoutSeconds"));
		returnVal.setAuthorizationURI(val.getLong("authorizationURI"));
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
String buf = request.getParameter("connectionPassword");
Long ibuf = Long.parseLong(buf);
returnVal.setConnectionPassword(ibuf);
}
		if(paramMap.containsKey("merchantId"))  {
String buf = request.getParameter("merchantId");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantId(ibuf);
}
		if(paramMap.containsKey("engineClass"))  {
String buf = request.getParameter("engineClass");
Long ibuf = Long.parseLong(buf);
returnVal.setEngineClass(ibuf);
}
		if(paramMap.containsKey("hostName"))  {
String buf = request.getParameter("hostName");
Long ibuf = Long.parseLong(buf);
returnVal.setHostName(ibuf);
}
		if(paramMap.containsKey("port"))  {
String buf = request.getParameter("port");
Long ibuf = Long.parseLong(buf);
returnVal.setPort(ibuf);
}
		if(paramMap.containsKey("hostNameFailover"))  {
String buf = request.getParameter("hostNameFailover");
Long ibuf = Long.parseLong(buf);
returnVal.setHostNameFailover(ibuf);
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
String buf = request.getParameter("authorizationURI");
Long ibuf = Long.parseLong(buf);
returnVal.setAuthorizationURI(ibuf);
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
