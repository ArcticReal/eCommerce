package com.skytala.eCommerce.domain.payment.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.payment.model.Payment;

public class PaymentMapper  {


	public static Map<String, Object> map(Payment payment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(payment.getPaymentId() != null ){
			returnVal.put("paymentId",payment.getPaymentId());
}

		if(payment.getPaymentTypeId() != null ){
			returnVal.put("paymentTypeId",payment.getPaymentTypeId());
}

		if(payment.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",payment.getPaymentMethodTypeId());
}

		if(payment.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",payment.getPaymentMethodId());
}

		if(payment.getPaymentGatewayResponseId() != null ){
			returnVal.put("paymentGatewayResponseId",payment.getPaymentGatewayResponseId());
}

		if(payment.getPaymentPreferenceId() != null ){
			returnVal.put("paymentPreferenceId",payment.getPaymentPreferenceId());
}

		if(payment.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",payment.getPartyIdFrom());
}

		if(payment.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",payment.getPartyIdTo());
}

		if(payment.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",payment.getRoleTypeIdTo());
}

		if(payment.getStatusId() != null ){
			returnVal.put("statusId",payment.getStatusId());
}

		if(payment.getEffectiveDate() != null ){
			returnVal.put("effectiveDate",payment.getEffectiveDate());
}

		if(payment.getPaymentRefNum() != null ){
			returnVal.put("paymentRefNum",payment.getPaymentRefNum());
}

		if(payment.getAmount() != null ){
			returnVal.put("amount",payment.getAmount());
}

		if(payment.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",payment.getCurrencyUomId());
}

		if(payment.getComments() != null ){
			returnVal.put("comments",payment.getComments());
}

		if(payment.getFinAccountTransId() != null ){
			returnVal.put("finAccountTransId",payment.getFinAccountTransId());
}

		if(payment.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",payment.getOverrideGlAccountId());
}

		if(payment.getActualCurrencyAmount() != null ){
			returnVal.put("actualCurrencyAmount",payment.getActualCurrencyAmount());
}

		if(payment.getActualCurrencyUomId() != null ){
			returnVal.put("actualCurrencyUomId",payment.getActualCurrencyUomId());
}

		return returnVal;
}


	public static Payment map(Map<String, Object> fields) {

		Payment returnVal = new Payment();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("paymentPreferenceId") != null) {
			returnVal.setPaymentPreferenceId((String) fields.get("paymentPreferenceId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("effectiveDate") != null) {
			returnVal.setEffectiveDate((Timestamp) fields.get("effectiveDate"));
}

		if(fields.get("paymentRefNum") != null) {
			returnVal.setPaymentRefNum((String) fields.get("paymentRefNum"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("actualCurrencyAmount") != null) {
			returnVal.setActualCurrencyAmount((BigDecimal) fields.get("actualCurrencyAmount"));
}

		if(fields.get("actualCurrencyUomId") != null) {
			returnVal.setActualCurrencyUomId((String) fields.get("actualCurrencyUomId"));
}


		return returnVal;
 } 
	public static Payment mapstrstr(Map<String, String> fields) throws Exception {

		Payment returnVal = new Payment();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("paymentTypeId") != null) {
			returnVal.setPaymentTypeId((String) fields.get("paymentTypeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("paymentGatewayResponseId") != null) {
			returnVal.setPaymentGatewayResponseId((String) fields.get("paymentGatewayResponseId"));
}

		if(fields.get("paymentPreferenceId") != null) {
			returnVal.setPaymentPreferenceId((String) fields.get("paymentPreferenceId"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("effectiveDate") != null) {
String buf = fields.get("effectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEffectiveDate(ibuf);
}

		if(fields.get("paymentRefNum") != null) {
			returnVal.setPaymentRefNum((String) fields.get("paymentRefNum"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("actualCurrencyAmount") != null) {
String buf;
buf = fields.get("actualCurrencyAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCurrencyAmount(bd);
}

		if(fields.get("actualCurrencyUomId") != null) {
			returnVal.setActualCurrencyUomId((String) fields.get("actualCurrencyUomId"));
}


		return returnVal;
 } 
	public static Payment map(GenericValue val) {

Payment returnVal = new Payment();
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setPaymentTypeId(val.getString("paymentTypeId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setPaymentGatewayResponseId(val.getString("paymentGatewayResponseId"));
		returnVal.setPaymentPreferenceId(val.getString("paymentPreferenceId"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setEffectiveDate(val.getTimestamp("effectiveDate"));
		returnVal.setPaymentRefNum(val.getString("paymentRefNum"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setFinAccountTransId(val.getString("finAccountTransId"));
		returnVal.setOverrideGlAccountId(val.getString("overrideGlAccountId"));
		returnVal.setActualCurrencyAmount(val.getBigDecimal("actualCurrencyAmount"));
		returnVal.setActualCurrencyUomId(val.getString("actualCurrencyUomId"));


return returnVal;

}

public static Payment map(HttpServletRequest request) throws Exception {

		Payment returnVal = new Payment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentId")) {
returnVal.setPaymentId(request.getParameter("paymentId"));
}

		if(paramMap.containsKey("paymentTypeId"))  {
returnVal.setPaymentTypeId(request.getParameter("paymentTypeId"));
}
		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("paymentMethodId"))  {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}
		if(paramMap.containsKey("paymentGatewayResponseId"))  {
returnVal.setPaymentGatewayResponseId(request.getParameter("paymentGatewayResponseId"));
}
		if(paramMap.containsKey("paymentPreferenceId"))  {
returnVal.setPaymentPreferenceId(request.getParameter("paymentPreferenceId"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("effectiveDate"))  {
String buf = request.getParameter("effectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEffectiveDate(ibuf);
}
		if(paramMap.containsKey("paymentRefNum"))  {
returnVal.setPaymentRefNum(request.getParameter("paymentRefNum"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("finAccountTransId"))  {
returnVal.setFinAccountTransId(request.getParameter("finAccountTransId"));
}
		if(paramMap.containsKey("overrideGlAccountId"))  {
returnVal.setOverrideGlAccountId(request.getParameter("overrideGlAccountId"));
}
		if(paramMap.containsKey("actualCurrencyAmount"))  {
String buf = request.getParameter("actualCurrencyAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualCurrencyAmount(bd);
}
		if(paramMap.containsKey("actualCurrencyUomId"))  {
returnVal.setActualCurrencyUomId(request.getParameter("actualCurrencyUomId"));
}
return returnVal;

}
}
