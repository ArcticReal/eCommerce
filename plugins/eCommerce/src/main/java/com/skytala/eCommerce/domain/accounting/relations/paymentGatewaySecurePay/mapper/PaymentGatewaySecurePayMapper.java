package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model.PaymentGatewaySecurePay;

public class PaymentGatewaySecurePayMapper  {


	public static Map<String, Object> map(PaymentGatewaySecurePay paymentgatewaysecurepay) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewaysecurepay.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewaysecurepay.getPaymentGatewayConfigId());
}

		if(paymentgatewaysecurepay.getMerchantId() != null ){
			returnVal.put("merchantId",paymentgatewaysecurepay.getMerchantId());
}

		if(paymentgatewaysecurepay.getPwd() != null ){
			returnVal.put("pwd",paymentgatewaysecurepay.getPwd());
}

		if(paymentgatewaysecurepay.getServerURL() != null ){
			returnVal.put("serverURL",paymentgatewaysecurepay.getServerURL());
}

		if(paymentgatewaysecurepay.getProcessTimeout() != null ){
			returnVal.put("processTimeout",paymentgatewaysecurepay.getProcessTimeout());
}

		if(paymentgatewaysecurepay.getEnableAmountRound() != null ){
			returnVal.put("enableAmountRound",paymentgatewaysecurepay.getEnableAmountRound());
}

		return returnVal;
}


	public static PaymentGatewaySecurePay map(Map<String, Object> fields) {

		PaymentGatewaySecurePay returnVal = new PaymentGatewaySecurePay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((long) fields.get("merchantId"));
}

		if(fields.get("pwd") != null) {
			returnVal.setPwd((long) fields.get("pwd"));
}

		if(fields.get("serverURL") != null) {
			returnVal.setServerURL((long) fields.get("serverURL"));
}

		if(fields.get("processTimeout") != null) {
			returnVal.setProcessTimeout((long) fields.get("processTimeout"));
}

		if(fields.get("enableAmountRound") != null) {
			returnVal.setEnableAmountRound((boolean) fields.get("enableAmountRound"));
}


		return returnVal;
 } 
	public static PaymentGatewaySecurePay mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewaySecurePay returnVal = new PaymentGatewaySecurePay();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
String buf;
buf = fields.get("merchantId");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantId(ibuf);
}

		if(fields.get("pwd") != null) {
String buf;
buf = fields.get("pwd");
long ibuf = Long.parseLong(buf);
			returnVal.setPwd(ibuf);
}

		if(fields.get("serverURL") != null) {
String buf;
buf = fields.get("serverURL");
long ibuf = Long.parseLong(buf);
			returnVal.setServerURL(ibuf);
}

		if(fields.get("processTimeout") != null) {
String buf;
buf = fields.get("processTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setProcessTimeout(ibuf);
}

		if(fields.get("enableAmountRound") != null) {
String buf;
buf = fields.get("enableAmountRound");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnableAmountRound(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewaySecurePay map(GenericValue val) {

PaymentGatewaySecurePay returnVal = new PaymentGatewaySecurePay();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setMerchantId(val.getLong("merchantId"));
		returnVal.setPwd(val.getLong("pwd"));
		returnVal.setServerURL(val.getLong("serverURL"));
		returnVal.setProcessTimeout(val.getLong("processTimeout"));
		returnVal.setEnableAmountRound(val.getBoolean("enableAmountRound"));


return returnVal;

}

public static PaymentGatewaySecurePay map(HttpServletRequest request) throws Exception {

		PaymentGatewaySecurePay returnVal = new PaymentGatewaySecurePay();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("merchantId"))  {
String buf = request.getParameter("merchantId");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantId(ibuf);
}
		if(paramMap.containsKey("pwd"))  {
String buf = request.getParameter("pwd");
Long ibuf = Long.parseLong(buf);
returnVal.setPwd(ibuf);
}
		if(paramMap.containsKey("serverURL"))  {
String buf = request.getParameter("serverURL");
Long ibuf = Long.parseLong(buf);
returnVal.setServerURL(ibuf);
}
		if(paramMap.containsKey("processTimeout"))  {
String buf = request.getParameter("processTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setProcessTimeout(ibuf);
}
		if(paramMap.containsKey("enableAmountRound"))  {
String buf = request.getParameter("enableAmountRound");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setEnableAmountRound(ibuf);
}
return returnVal;

}
}
