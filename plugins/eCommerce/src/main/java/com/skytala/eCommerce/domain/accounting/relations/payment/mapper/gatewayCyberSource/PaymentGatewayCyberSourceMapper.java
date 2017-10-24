package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayCyberSource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;

public class PaymentGatewayCyberSourceMapper  {


	public static Map<String, Object> map(PaymentGatewayCyberSource paymentgatewaycybersource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewaycybersource.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewaycybersource.getPaymentGatewayConfigId());
}

		if(paymentgatewaycybersource.getMerchantId() != null ){
			returnVal.put("merchantId",paymentgatewaycybersource.getMerchantId());
}

		if(paymentgatewaycybersource.getApiVersion() != null ){
			returnVal.put("apiVersion",paymentgatewaycybersource.getApiVersion());
}

		if(paymentgatewaycybersource.getProduction() != null ){
			returnVal.put("production",paymentgatewaycybersource.getProduction());
}

		if(paymentgatewaycybersource.getKeysDir() != null ){
			returnVal.put("keysDir",paymentgatewaycybersource.getKeysDir());
}

		if(paymentgatewaycybersource.getKeysFile() != null ){
			returnVal.put("keysFile",paymentgatewaycybersource.getKeysFile());
}

		if(paymentgatewaycybersource.getLogEnabled() != null ){
			returnVal.put("logEnabled",paymentgatewaycybersource.getLogEnabled());
}

		if(paymentgatewaycybersource.getLogDir() != null ){
			returnVal.put("logDir",paymentgatewaycybersource.getLogDir());
}

		if(paymentgatewaycybersource.getLogFile() != null ){
			returnVal.put("logFile",paymentgatewaycybersource.getLogFile());
}

		if(paymentgatewaycybersource.getLogSize() != null ){
			returnVal.put("logSize",paymentgatewaycybersource.getLogSize());
}

		if(paymentgatewaycybersource.getMerchantDescr() != null ){
			returnVal.put("merchantDescr",paymentgatewaycybersource.getMerchantDescr());
}

		if(paymentgatewaycybersource.getMerchantContact() != null ){
			returnVal.put("merchantContact",paymentgatewaycybersource.getMerchantContact());
}

		if(paymentgatewaycybersource.getAutoBill() != null ){
			returnVal.put("autoBill",paymentgatewaycybersource.getAutoBill());
}

		if(paymentgatewaycybersource.getEnableDav() != null ){
			returnVal.put("enableDav",paymentgatewaycybersource.getEnableDav());
}

		if(paymentgatewaycybersource.getFraudScore() != null ){
			returnVal.put("fraudScore",paymentgatewaycybersource.getFraudScore());
}

		if(paymentgatewaycybersource.getIgnoreAvs() != null ){
			returnVal.put("ignoreAvs",paymentgatewaycybersource.getIgnoreAvs());
}

		if(paymentgatewaycybersource.getDisableBillAvs() != null ){
			returnVal.put("disableBillAvs",paymentgatewaycybersource.getDisableBillAvs());
}

		if(paymentgatewaycybersource.getAvsDeclineCodes() != null ){
			returnVal.put("avsDeclineCodes",paymentgatewaycybersource.getAvsDeclineCodes());
}

		return returnVal;
}


	public static PaymentGatewayCyberSource map(Map<String, Object> fields) {

		PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("production") != null) {
			returnVal.setProduction((String) fields.get("production"));
}

		if(fields.get("keysDir") != null) {
			returnVal.setKeysDir((String) fields.get("keysDir"));
}

		if(fields.get("keysFile") != null) {
			returnVal.setKeysFile((String) fields.get("keysFile"));
}

		if(fields.get("logEnabled") != null) {
			returnVal.setLogEnabled((String) fields.get("logEnabled"));
}

		if(fields.get("logDir") != null) {
			returnVal.setLogDir((String) fields.get("logDir"));
}

		if(fields.get("logFile") != null) {
			returnVal.setLogFile((String) fields.get("logFile"));
}

		if(fields.get("logSize") != null) {
			returnVal.setLogSize((long) fields.get("logSize"));
}

		if(fields.get("merchantDescr") != null) {
			returnVal.setMerchantDescr((String) fields.get("merchantDescr"));
}

		if(fields.get("merchantContact") != null) {
			returnVal.setMerchantContact((String) fields.get("merchantContact"));
}

		if(fields.get("autoBill") != null) {
			returnVal.setAutoBill((String) fields.get("autoBill"));
}

		if(fields.get("enableDav") != null) {
			returnVal.setEnableDav((boolean) fields.get("enableDav"));
}

		if(fields.get("fraudScore") != null) {
			returnVal.setFraudScore((boolean) fields.get("fraudScore"));
}

		if(fields.get("ignoreAvs") != null) {
			returnVal.setIgnoreAvs((String) fields.get("ignoreAvs"));
}

		if(fields.get("disableBillAvs") != null) {
			returnVal.setDisableBillAvs((boolean) fields.get("disableBillAvs"));
}

		if(fields.get("avsDeclineCodes") != null) {
			returnVal.setAvsDeclineCodes((String) fields.get("avsDeclineCodes"));
}


		return returnVal;
 } 
	public static PaymentGatewayCyberSource mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("production") != null) {
			returnVal.setProduction((String) fields.get("production"));
}

		if(fields.get("keysDir") != null) {
			returnVal.setKeysDir((String) fields.get("keysDir"));
}

		if(fields.get("keysFile") != null) {
			returnVal.setKeysFile((String) fields.get("keysFile"));
}

		if(fields.get("logEnabled") != null) {
			returnVal.setLogEnabled((String) fields.get("logEnabled"));
}

		if(fields.get("logDir") != null) {
			returnVal.setLogDir((String) fields.get("logDir"));
}

		if(fields.get("logFile") != null) {
			returnVal.setLogFile((String) fields.get("logFile"));
}

		if(fields.get("logSize") != null) {
String buf;
buf = fields.get("logSize");
long ibuf = Long.parseLong(buf);
			returnVal.setLogSize(ibuf);
}

		if(fields.get("merchantDescr") != null) {
			returnVal.setMerchantDescr((String) fields.get("merchantDescr"));
}

		if(fields.get("merchantContact") != null) {
			returnVal.setMerchantContact((String) fields.get("merchantContact"));
}

		if(fields.get("autoBill") != null) {
			returnVal.setAutoBill((String) fields.get("autoBill"));
}

		if(fields.get("enableDav") != null) {
String buf;
buf = fields.get("enableDav");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnableDav(ibuf);
}

		if(fields.get("fraudScore") != null) {
String buf;
buf = fields.get("fraudScore");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setFraudScore(ibuf);
}

		if(fields.get("ignoreAvs") != null) {
			returnVal.setIgnoreAvs((String) fields.get("ignoreAvs"));
}

		if(fields.get("disableBillAvs") != null) {
String buf;
buf = fields.get("disableBillAvs");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setDisableBillAvs(ibuf);
}

		if(fields.get("avsDeclineCodes") != null) {
			returnVal.setAvsDeclineCodes((String) fields.get("avsDeclineCodes"));
}


		return returnVal;
 } 
	public static PaymentGatewayCyberSource map(GenericValue val) {

PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setMerchantId(val.getString("merchantId"));
		returnVal.setApiVersion(val.getString("apiVersion"));
		returnVal.setProduction(val.getString("production"));
		returnVal.setKeysDir(val.getString("keysDir"));
		returnVal.setKeysFile(val.getString("keysFile"));
		returnVal.setLogEnabled(val.getString("logEnabled"));
		returnVal.setLogDir(val.getString("logDir"));
		returnVal.setLogFile(val.getString("logFile"));
		returnVal.setLogSize(val.getLong("logSize"));
		returnVal.setMerchantDescr(val.getString("merchantDescr"));
		returnVal.setMerchantContact(val.getString("merchantContact"));
		returnVal.setAutoBill(val.getString("autoBill"));
		returnVal.setEnableDav(val.getBoolean("enableDav"));
		returnVal.setFraudScore(val.getBoolean("fraudScore"));
		returnVal.setIgnoreAvs(val.getString("ignoreAvs"));
		returnVal.setDisableBillAvs(val.getBoolean("disableBillAvs"));
		returnVal.setAvsDeclineCodes(val.getString("avsDeclineCodes"));


return returnVal;

}

public static PaymentGatewayCyberSource map(HttpServletRequest request) throws Exception {

		PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("merchantId"))  {
returnVal.setMerchantId(request.getParameter("merchantId"));
}
		if(paramMap.containsKey("apiVersion"))  {
returnVal.setApiVersion(request.getParameter("apiVersion"));
}
		if(paramMap.containsKey("production"))  {
returnVal.setProduction(request.getParameter("production"));
}
		if(paramMap.containsKey("keysDir"))  {
returnVal.setKeysDir(request.getParameter("keysDir"));
}
		if(paramMap.containsKey("keysFile"))  {
returnVal.setKeysFile(request.getParameter("keysFile"));
}
		if(paramMap.containsKey("logEnabled"))  {
returnVal.setLogEnabled(request.getParameter("logEnabled"));
}
		if(paramMap.containsKey("logDir"))  {
returnVal.setLogDir(request.getParameter("logDir"));
}
		if(paramMap.containsKey("logFile"))  {
returnVal.setLogFile(request.getParameter("logFile"));
}
		if(paramMap.containsKey("logSize"))  {
String buf = request.getParameter("logSize");
Long ibuf = Long.parseLong(buf);
returnVal.setLogSize(ibuf);
}
		if(paramMap.containsKey("merchantDescr"))  {
returnVal.setMerchantDescr(request.getParameter("merchantDescr"));
}
		if(paramMap.containsKey("merchantContact"))  {
returnVal.setMerchantContact(request.getParameter("merchantContact"));
}
		if(paramMap.containsKey("autoBill"))  {
returnVal.setAutoBill(request.getParameter("autoBill"));
}
		if(paramMap.containsKey("enableDav"))  {
String buf = request.getParameter("enableDav");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setEnableDav(ibuf);
}
		if(paramMap.containsKey("fraudScore"))  {
String buf = request.getParameter("fraudScore");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setFraudScore(ibuf);
}
		if(paramMap.containsKey("ignoreAvs"))  {
returnVal.setIgnoreAvs(request.getParameter("ignoreAvs"));
}
		if(paramMap.containsKey("disableBillAvs"))  {
String buf = request.getParameter("disableBillAvs");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setDisableBillAvs(ibuf);
}
		if(paramMap.containsKey("avsDeclineCodes"))  {
returnVal.setAvsDeclineCodes(request.getParameter("avsDeclineCodes"));
}
return returnVal;

}
}
