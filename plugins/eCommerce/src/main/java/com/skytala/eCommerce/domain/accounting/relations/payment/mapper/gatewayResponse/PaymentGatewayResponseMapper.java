package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayResponse.PaymentGatewayResponse;

public class PaymentGatewayResponseMapper  {


	public static Map<String, Object> map(PaymentGatewayResponse paymentgatewayresponse) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayresponse.getPaymentGatewayResponseId() != null ){
			returnVal.put("paymentGatewayResponseId",paymentgatewayresponse.getPaymentGatewayResponseId());
}

		if(paymentgatewayresponse.getPaymentServiceTypeEnumId() != null ){
			returnVal.put("paymentServiceTypeEnumId",paymentgatewayresponse.getPaymentServiceTypeEnumId());
}

		if(paymentgatewayresponse.getOrderPaymentPreferenceId() != null ){
			returnVal.put("orderPaymentPreferenceId",paymentgatewayresponse.getOrderPaymentPreferenceId());
}

		if(paymentgatewayresponse.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",paymentgatewayresponse.getPaymentMethodTypeId());
}

		if(paymentgatewayresponse.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",paymentgatewayresponse.getPaymentMethodId());
}

		if(paymentgatewayresponse.getTransCodeEnumId() != null ){
			returnVal.put("transCodeEnumId",paymentgatewayresponse.getTransCodeEnumId());
}

		if(paymentgatewayresponse.getAmount() != null ){
			returnVal.put("amount",paymentgatewayresponse.getAmount());
}

		if(paymentgatewayresponse.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",paymentgatewayresponse.getCurrencyUomId());
}

		if(paymentgatewayresponse.getReferenceNum() != null ){
			returnVal.put("referenceNum",paymentgatewayresponse.getReferenceNum());
}

		if(paymentgatewayresponse.getAltReference() != null ){
			returnVal.put("altReference",paymentgatewayresponse.getAltReference());
}

		if(paymentgatewayresponse.getSubReference() != null ){
			returnVal.put("subReference",paymentgatewayresponse.getSubReference());
}

		if(paymentgatewayresponse.getGatewayCode() != null ){
			returnVal.put("gatewayCode",paymentgatewayresponse.getGatewayCode());
}

		if(paymentgatewayresponse.getGatewayFlag() != null ){
			returnVal.put("gatewayFlag",paymentgatewayresponse.getGatewayFlag());
}

		if(paymentgatewayresponse.getGatewayAvsResult() != null ){
			returnVal.put("gatewayAvsResult",paymentgatewayresponse.getGatewayAvsResult());
}

		if(paymentgatewayresponse.getGatewayCvResult() != null ){
			returnVal.put("gatewayCvResult",paymentgatewayresponse.getGatewayCvResult());
}

		if(paymentgatewayresponse.getGatewayScoreResult() != null ){
			returnVal.put("gatewayScoreResult",paymentgatewayresponse.getGatewayScoreResult());
}

		if(paymentgatewayresponse.getGatewayMessage() != null ){
			returnVal.put("gatewayMessage",paymentgatewayresponse.getGatewayMessage());
}

		if(paymentgatewayresponse.getTransactionDate() != null ){
			returnVal.put("transactionDate",paymentgatewayresponse.getTransactionDate());
}

		if(paymentgatewayresponse.getResultDeclined() != null ){
			returnVal.put("resultDeclined",paymentgatewayresponse.getResultDeclined());
}

		if(paymentgatewayresponse.getResultNsf() != null ){
			returnVal.put("resultNsf",paymentgatewayresponse.getResultNsf());
}

		if(paymentgatewayresponse.getResultBadExpire() != null ){
			returnVal.put("resultBadExpire",paymentgatewayresponse.getResultBadExpire());
}

		if(paymentgatewayresponse.getResultBadCardNumber() != null ){
			returnVal.put("resultBadCardNumber",paymentgatewayresponse.getResultBadCardNumber());
}

		return returnVal;
}


	public static PaymentGatewayResponse map(Map<String, Object> fields) {

		PaymentGatewayResponse returnVal = new PaymentGatewayResponse();

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("paymentServiceTypeEnumId") != null) {
			returnVal.setPaymentServiceTypeEnumId((String) fields.get("paymentServiceTypeEnumId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("transCodeEnumId") != null) {
			returnVal.setTransCodeEnumId((String) fields.get("transCodeEnumId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("referenceNum") != null) {
			returnVal.setReferenceNum((String) fields.get("referenceNum"));
}

		if(fields.get("altReference") != null) {
			returnVal.setAltReference((String) fields.get("altReference"));
}

		if(fields.get("subReference") != null) {
			returnVal.setSubReference((String) fields.get("subReference"));
}

		if(fields.get("gatewayCode") != null) {
			returnVal.setGatewayCode((String) fields.get("gatewayCode"));
}

		if(fields.get("gatewayFlag") != null) {
			returnVal.setGatewayFlag((String) fields.get("gatewayFlag"));
}

		if(fields.get("gatewayAvsResult") != null) {
			returnVal.setGatewayAvsResult((String) fields.get("gatewayAvsResult"));
}

		if(fields.get("gatewayCvResult") != null) {
			returnVal.setGatewayCvResult((String) fields.get("gatewayCvResult"));
}

		if(fields.get("gatewayScoreResult") != null) {
			returnVal.setGatewayScoreResult((String) fields.get("gatewayScoreResult"));
}

		if(fields.get("gatewayMessage") != null) {
			returnVal.setGatewayMessage((String) fields.get("gatewayMessage"));
}

		if(fields.get("transactionDate") != null) {
			returnVal.setTransactionDate((Timestamp) fields.get("transactionDate"));
}

		if(fields.get("resultDeclined") != null) {
			returnVal.setResultDeclined((boolean) fields.get("resultDeclined"));
}

		if(fields.get("resultNsf") != null) {
			returnVal.setResultNsf((boolean) fields.get("resultNsf"));
}

		if(fields.get("resultBadExpire") != null) {
			returnVal.setResultBadExpire((boolean) fields.get("resultBadExpire"));
}

		if(fields.get("resultBadCardNumber") != null) {
			returnVal.setResultBadCardNumber((boolean) fields.get("resultBadCardNumber"));
}


		return returnVal;
 } 
	public static PaymentGatewayResponse mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayResponse returnVal = new PaymentGatewayResponse();

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("paymentServiceTypeEnumId") != null) {
			returnVal.setPaymentServiceTypeEnumId((String) fields.get("paymentServiceTypeEnumId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("transCodeEnumId") != null) {
			returnVal.setTransCodeEnumId((String) fields.get("transCodeEnumId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("referenceNum") != null) {
			returnVal.setReferenceNum((String) fields.get("referenceNum"));
}

		if(fields.get("altReference") != null) {
			returnVal.setAltReference((String) fields.get("altReference"));
}

		if(fields.get("subReference") != null) {
			returnVal.setSubReference((String) fields.get("subReference"));
}

		if(fields.get("gatewayCode") != null) {
			returnVal.setGatewayCode((String) fields.get("gatewayCode"));
}

		if(fields.get("gatewayFlag") != null) {
			returnVal.setGatewayFlag((String) fields.get("gatewayFlag"));
}

		if(fields.get("gatewayAvsResult") != null) {
			returnVal.setGatewayAvsResult((String) fields.get("gatewayAvsResult"));
}

		if(fields.get("gatewayCvResult") != null) {
			returnVal.setGatewayCvResult((String) fields.get("gatewayCvResult"));
}

		if(fields.get("gatewayScoreResult") != null) {
			returnVal.setGatewayScoreResult((String) fields.get("gatewayScoreResult"));
}

		if(fields.get("gatewayMessage") != null) {
			returnVal.setGatewayMessage((String) fields.get("gatewayMessage"));
}

		if(fields.get("transactionDate") != null) {
String buf = fields.get("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setTransactionDate(ibuf);
}

		if(fields.get("resultDeclined") != null) {
String buf;
buf = fields.get("resultDeclined");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setResultDeclined(ibuf);
}

		if(fields.get("resultNsf") != null) {
String buf;
buf = fields.get("resultNsf");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setResultNsf(ibuf);
}

		if(fields.get("resultBadExpire") != null) {
String buf;
buf = fields.get("resultBadExpire");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setResultBadExpire(ibuf);
}

		if(fields.get("resultBadCardNumber") != null) {
String buf;
buf = fields.get("resultBadCardNumber");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setResultBadCardNumber(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayResponse map(GenericValue val) {

PaymentGatewayResponse returnVal = new PaymentGatewayResponse();
		returnVal.setPaymentGatewayResponseId(val.getString("paymentGatewayResponseId"));
		returnVal.setPaymentServiceTypeEnumId(val.getString("paymentServiceTypeEnumId"));
		returnVal.setOrderPaymentPreferenceId(val.getString("orderPaymentPreferenceId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setTransCodeEnumId(val.getString("transCodeEnumId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setReferenceNum(val.getString("referenceNum"));
		returnVal.setAltReference(val.getString("altReference"));
		returnVal.setSubReference(val.getString("subReference"));
		returnVal.setGatewayCode(val.getString("gatewayCode"));
		returnVal.setGatewayFlag(val.getString("gatewayFlag"));
		returnVal.setGatewayAvsResult(val.getString("gatewayAvsResult"));
		returnVal.setGatewayCvResult(val.getString("gatewayCvResult"));
		returnVal.setGatewayScoreResult(val.getString("gatewayScoreResult"));
		returnVal.setGatewayMessage(val.getString("gatewayMessage"));
		returnVal.setTransactionDate(val.getTimestamp("transactionDate"));
		returnVal.setResultDeclined(val.getBoolean("resultDeclined"));
		returnVal.setResultNsf(val.getBoolean("resultNsf"));
		returnVal.setResultBadExpire(val.getBoolean("resultBadExpire"));
		returnVal.setResultBadCardNumber(val.getBoolean("resultBadCardNumber"));


return returnVal;

}

public static PaymentGatewayResponse map(HttpServletRequest request) throws Exception {

		PaymentGatewayResponse returnVal = new PaymentGatewayResponse();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayResponseId")) {
returnVal.setPaymentGatewayResponseId(request.getParameter("paymentGatewayResponseId"));
}

		if(paramMap.containsKey("paymentServiceTypeEnumId"))  {
returnVal.setPaymentServiceTypeEnumId(request.getParameter("paymentServiceTypeEnumId"));
}
		if(paramMap.containsKey("orderPaymentPreferenceId"))  {
returnVal.setOrderPaymentPreferenceId(request.getParameter("orderPaymentPreferenceId"));
}
		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("paymentMethodId"))  {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}
		if(paramMap.containsKey("transCodeEnumId"))  {
returnVal.setTransCodeEnumId(request.getParameter("transCodeEnumId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("referenceNum"))  {
returnVal.setReferenceNum(request.getParameter("referenceNum"));
}
		if(paramMap.containsKey("altReference"))  {
returnVal.setAltReference(request.getParameter("altReference"));
}
		if(paramMap.containsKey("subReference"))  {
returnVal.setSubReference(request.getParameter("subReference"));
}
		if(paramMap.containsKey("gatewayCode"))  {
returnVal.setGatewayCode(request.getParameter("gatewayCode"));
}
		if(paramMap.containsKey("gatewayFlag"))  {
returnVal.setGatewayFlag(request.getParameter("gatewayFlag"));
}
		if(paramMap.containsKey("gatewayAvsResult"))  {
returnVal.setGatewayAvsResult(request.getParameter("gatewayAvsResult"));
}
		if(paramMap.containsKey("gatewayCvResult"))  {
returnVal.setGatewayCvResult(request.getParameter("gatewayCvResult"));
}
		if(paramMap.containsKey("gatewayScoreResult"))  {
returnVal.setGatewayScoreResult(request.getParameter("gatewayScoreResult"));
}
		if(paramMap.containsKey("gatewayMessage"))  {
returnVal.setGatewayMessage(request.getParameter("gatewayMessage"));
}
		if(paramMap.containsKey("transactionDate"))  {
String buf = request.getParameter("transactionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setTransactionDate(ibuf);
}
		if(paramMap.containsKey("resultDeclined"))  {
String buf = request.getParameter("resultDeclined");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setResultDeclined(ibuf);
}
		if(paramMap.containsKey("resultNsf"))  {
String buf = request.getParameter("resultNsf");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setResultNsf(ibuf);
}
		if(paramMap.containsKey("resultBadExpire"))  {
String buf = request.getParameter("resultBadExpire");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setResultBadExpire(ibuf);
}
		if(paramMap.containsKey("resultBadCardNumber"))  {
String buf = request.getParameter("resultBadCardNumber");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setResultBadCardNumber(ibuf);
}
return returnVal;

}
}
