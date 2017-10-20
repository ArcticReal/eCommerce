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
			returnVal.setMerchantId((long) fields.get("merchantId"));
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("production") != null) {
			returnVal.setProduction((String) fields.get("production"));
}

		if(fields.get("keysDir") != null) {
			returnVal.setKeysDir((long) fields.get("keysDir"));
}

		if(fields.get("keysFile") != null) {
			returnVal.setKeysFile((long) fields.get("keysFile"));
}

		if(fields.get("logEnabled") != null) {
			returnVal.setLogEnabled((String) fields.get("logEnabled"));
}

		if(fields.get("logDir") != null) {
			returnVal.setLogDir((long) fields.get("logDir"));
}

		if(fields.get("logFile") != null) {
			returnVal.setLogFile((long) fields.get("logFile"));
}

		if(fields.get("logSize") != null) {
			returnVal.setLogSize((long) fields.get("logSize"));
}

		if(fields.get("merchantDescr") != null) {
			returnVal.setMerchantDescr((long) fields.get("merchantDescr"));
}

		if(fields.get("merchantContact") != null) {
			returnVal.setMerchantContact((long) fields.get("merchantContact"));
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
			returnVal.setAvsDeclineCodes((long) fields.get("avsDeclineCodes"));
}


		return returnVal;
 } 
	public static PaymentGatewayCyberSource mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
String buf;
buf = fields.get("merchantId");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantId(ibuf);
}

		if(fields.get("apiVersion") != null) {
			returnVal.setApiVersion((String) fields.get("apiVersion"));
}

		if(fields.get("production") != null) {
			returnVal.setProduction((String) fields.get("production"));
}

		if(fields.get("keysDir") != null) {
String buf;
buf = fields.get("keysDir");
long ibuf = Long.parseLong(buf);
			returnVal.setKeysDir(ibuf);
}

		if(fields.get("keysFile") != null) {
String buf;
buf = fields.get("keysFile");
long ibuf = Long.parseLong(buf);
			returnVal.setKeysFile(ibuf);
}

		if(fields.get("logEnabled") != null) {
			returnVal.setLogEnabled((String) fields.get("logEnabled"));
}

		if(fields.get("logDir") != null) {
String buf;
buf = fields.get("logDir");
long ibuf = Long.parseLong(buf);
			returnVal.setLogDir(ibuf);
}

		if(fields.get("logFile") != null) {
String buf;
buf = fields.get("logFile");
long ibuf = Long.parseLong(buf);
			returnVal.setLogFile(ibuf);
}

		if(fields.get("logSize") != null) {
String buf;
buf = fields.get("logSize");
long ibuf = Long.parseLong(buf);
			returnVal.setLogSize(ibuf);
}

		if(fields.get("merchantDescr") != null) {
String buf;
buf = fields.get("merchantDescr");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantDescr(ibuf);
}

		if(fields.get("merchantContact") != null) {
String buf;
buf = fields.get("merchantContact");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantContact(ibuf);
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
String buf;
buf = fields.get("avsDeclineCodes");
long ibuf = Long.parseLong(buf);
			returnVal.setAvsDeclineCodes(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayCyberSource map(GenericValue val) {

PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setMerchantId(val.getLong("merchantId"));
		returnVal.setApiVersion(val.getString("apiVersion"));
		returnVal.setProduction(val.getString("production"));
		returnVal.setKeysDir(val.getLong("keysDir"));
		returnVal.setKeysFile(val.getLong("keysFile"));
		returnVal.setLogEnabled(val.getString("logEnabled"));
		returnVal.setLogDir(val.getLong("logDir"));
		returnVal.setLogFile(val.getLong("logFile"));
		returnVal.setLogSize(val.getLong("logSize"));
		returnVal.setMerchantDescr(val.getLong("merchantDescr"));
		returnVal.setMerchantContact(val.getLong("merchantContact"));
		returnVal.setAutoBill(val.getString("autoBill"));
		returnVal.setEnableDav(val.getBoolean("enableDav"));
		returnVal.setFraudScore(val.getBoolean("fraudScore"));
		returnVal.setIgnoreAvs(val.getString("ignoreAvs"));
		returnVal.setDisableBillAvs(val.getBoolean("disableBillAvs"));
		returnVal.setAvsDeclineCodes(val.getLong("avsDeclineCodes"));


return returnVal;

}

public static PaymentGatewayCyberSource map(HttpServletRequest request) throws Exception {

		PaymentGatewayCyberSource returnVal = new PaymentGatewayCyberSource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("merchantId"))  {
String buf = request.getParameter("merchantId");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantId(ibuf);
}
		if(paramMap.containsKey("apiVersion"))  {
returnVal.setApiVersion(request.getParameter("apiVersion"));
}
		if(paramMap.containsKey("production"))  {
returnVal.setProduction(request.getParameter("production"));
}
		if(paramMap.containsKey("keysDir"))  {
String buf = request.getParameter("keysDir");
Long ibuf = Long.parseLong(buf);
returnVal.setKeysDir(ibuf);
}
		if(paramMap.containsKey("keysFile"))  {
String buf = request.getParameter("keysFile");
Long ibuf = Long.parseLong(buf);
returnVal.setKeysFile(ibuf);
}
		if(paramMap.containsKey("logEnabled"))  {
returnVal.setLogEnabled(request.getParameter("logEnabled"));
}
		if(paramMap.containsKey("logDir"))  {
String buf = request.getParameter("logDir");
Long ibuf = Long.parseLong(buf);
returnVal.setLogDir(ibuf);
}
		if(paramMap.containsKey("logFile"))  {
String buf = request.getParameter("logFile");
Long ibuf = Long.parseLong(buf);
returnVal.setLogFile(ibuf);
}
		if(paramMap.containsKey("logSize"))  {
String buf = request.getParameter("logSize");
Long ibuf = Long.parseLong(buf);
returnVal.setLogSize(ibuf);
}
		if(paramMap.containsKey("merchantDescr"))  {
String buf = request.getParameter("merchantDescr");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantDescr(ibuf);
}
		if(paramMap.containsKey("merchantContact"))  {
String buf = request.getParameter("merchantContact");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantContact(ibuf);
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
String buf = request.getParameter("avsDeclineCodes");
Long ibuf = Long.parseLong(buf);
returnVal.setAvsDeclineCodes(ibuf);
}
return returnVal;

}
}
