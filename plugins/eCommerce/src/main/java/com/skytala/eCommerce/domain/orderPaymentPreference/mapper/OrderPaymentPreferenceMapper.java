package com.skytala.eCommerce.domain.orderPaymentPreference.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderPaymentPreference.model.OrderPaymentPreference;

public class OrderPaymentPreferenceMapper  {


	public static Map<String, Object> map(OrderPaymentPreference orderpaymentpreference) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderpaymentpreference.getOrderPaymentPreferenceId() != null ){
			returnVal.put("orderPaymentPreferenceId",orderpaymentpreference.getOrderPaymentPreferenceId());
}

		if(orderpaymentpreference.getOrderId() != null ){
			returnVal.put("orderId",orderpaymentpreference.getOrderId());
}

		if(orderpaymentpreference.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderpaymentpreference.getOrderItemSeqId());
}

		if(orderpaymentpreference.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderpaymentpreference.getShipGroupSeqId());
}

		if(orderpaymentpreference.getProductPricePurposeId() != null ){
			returnVal.put("productPricePurposeId",orderpaymentpreference.getProductPricePurposeId());
}

		if(orderpaymentpreference.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",orderpaymentpreference.getPaymentMethodTypeId());
}

		if(orderpaymentpreference.getPaymentMethodId() != null ){
			returnVal.put("paymentMethodId",orderpaymentpreference.getPaymentMethodId());
}

		if(orderpaymentpreference.getFinAccountId() != null ){
			returnVal.put("finAccountId",orderpaymentpreference.getFinAccountId());
}

		if(orderpaymentpreference.getSecurityCode() != null ){
			returnVal.put("securityCode",orderpaymentpreference.getSecurityCode());
}

		if(orderpaymentpreference.getTrack2() != null ){
			returnVal.put("track2",orderpaymentpreference.getTrack2());
}

		if(orderpaymentpreference.getPresentFlag() != null ){
			returnVal.put("presentFlag",orderpaymentpreference.getPresentFlag());
}

		if(orderpaymentpreference.getSwipedFlag() != null ){
			returnVal.put("swipedFlag",orderpaymentpreference.getSwipedFlag());
}

		if(orderpaymentpreference.getOverflowFlag() != null ){
			returnVal.put("overflowFlag",orderpaymentpreference.getOverflowFlag());
}

		if(orderpaymentpreference.getMaxAmount() != null ){
			returnVal.put("maxAmount",orderpaymentpreference.getMaxAmount());
}

		if(orderpaymentpreference.getProcessAttempt() != null ){
			returnVal.put("processAttempt",orderpaymentpreference.getProcessAttempt());
}

		if(orderpaymentpreference.getBillingPostalCode() != null ){
			returnVal.put("billingPostalCode",orderpaymentpreference.getBillingPostalCode());
}

		if(orderpaymentpreference.getManualAuthCode() != null ){
			returnVal.put("manualAuthCode",orderpaymentpreference.getManualAuthCode());
}

		if(orderpaymentpreference.getManualRefNum() != null ){
			returnVal.put("manualRefNum",orderpaymentpreference.getManualRefNum());
}

		if(orderpaymentpreference.getStatusId() != null ){
			returnVal.put("statusId",orderpaymentpreference.getStatusId());
}

		if(orderpaymentpreference.getNeedsNsfRetry() != null ){
			returnVal.put("needsNsfRetry",orderpaymentpreference.getNeedsNsfRetry());
}

		if(orderpaymentpreference.getCreatedDate() != null ){
			returnVal.put("createdDate",orderpaymentpreference.getCreatedDate());
}

		if(orderpaymentpreference.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",orderpaymentpreference.getCreatedByUserLogin());
}

		if(orderpaymentpreference.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",orderpaymentpreference.getLastModifiedDate());
}

		if(orderpaymentpreference.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",orderpaymentpreference.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static OrderPaymentPreference map(Map<String, Object> fields) {

		OrderPaymentPreference returnVal = new OrderPaymentPreference();

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("securityCode") != null) {
			returnVal.setSecurityCode((String) fields.get("securityCode"));
}

		if(fields.get("track2") != null) {
			returnVal.setTrack2((String) fields.get("track2"));
}

		if(fields.get("presentFlag") != null) {
			returnVal.setPresentFlag((boolean) fields.get("presentFlag"));
}

		if(fields.get("swipedFlag") != null) {
			returnVal.setSwipedFlag((boolean) fields.get("swipedFlag"));
}

		if(fields.get("overflowFlag") != null) {
			returnVal.setOverflowFlag((boolean) fields.get("overflowFlag"));
}

		if(fields.get("maxAmount") != null) {
			returnVal.setMaxAmount((BigDecimal) fields.get("maxAmount"));
}

		if(fields.get("processAttempt") != null) {
			returnVal.setProcessAttempt((long) fields.get("processAttempt"));
}

		if(fields.get("billingPostalCode") != null) {
			returnVal.setBillingPostalCode((String) fields.get("billingPostalCode"));
}

		if(fields.get("manualAuthCode") != null) {
			returnVal.setManualAuthCode((String) fields.get("manualAuthCode"));
}

		if(fields.get("manualRefNum") != null) {
			returnVal.setManualRefNum((String) fields.get("manualRefNum"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("needsNsfRetry") != null) {
			returnVal.setNeedsNsfRetry((boolean) fields.get("needsNsfRetry"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static OrderPaymentPreference mapstrstr(Map<String, String> fields) throws Exception {

		OrderPaymentPreference returnVal = new OrderPaymentPreference();

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("productPricePurposeId") != null) {
			returnVal.setProductPricePurposeId((String) fields.get("productPricePurposeId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentMethodId") != null) {
			returnVal.setPaymentMethodId((String) fields.get("paymentMethodId"));
}

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
}

		if(fields.get("securityCode") != null) {
			returnVal.setSecurityCode((String) fields.get("securityCode"));
}

		if(fields.get("track2") != null) {
			returnVal.setTrack2((String) fields.get("track2"));
}

		if(fields.get("presentFlag") != null) {
String buf;
buf = fields.get("presentFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPresentFlag(ibuf);
}

		if(fields.get("swipedFlag") != null) {
String buf;
buf = fields.get("swipedFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSwipedFlag(ibuf);
}

		if(fields.get("overflowFlag") != null) {
String buf;
buf = fields.get("overflowFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setOverflowFlag(ibuf);
}

		if(fields.get("maxAmount") != null) {
String buf;
buf = fields.get("maxAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxAmount(bd);
}

		if(fields.get("processAttempt") != null) {
String buf;
buf = fields.get("processAttempt");
long ibuf = Long.parseLong(buf);
			returnVal.setProcessAttempt(ibuf);
}

		if(fields.get("billingPostalCode") != null) {
			returnVal.setBillingPostalCode((String) fields.get("billingPostalCode"));
}

		if(fields.get("manualAuthCode") != null) {
			returnVal.setManualAuthCode((String) fields.get("manualAuthCode"));
}

		if(fields.get("manualRefNum") != null) {
			returnVal.setManualRefNum((String) fields.get("manualRefNum"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("needsNsfRetry") != null) {
String buf;
buf = fields.get("needsNsfRetry");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setNeedsNsfRetry(ibuf);
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static OrderPaymentPreference map(GenericValue val) {

OrderPaymentPreference returnVal = new OrderPaymentPreference();
		returnVal.setOrderPaymentPreferenceId(val.getString("orderPaymentPreferenceId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setProductPricePurposeId(val.getString("productPricePurposeId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPaymentMethodId(val.getString("paymentMethodId"));
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setSecurityCode(val.getString("securityCode"));
		returnVal.setTrack2(val.getString("track2"));
		returnVal.setPresentFlag(val.getBoolean("presentFlag"));
		returnVal.setSwipedFlag(val.getBoolean("swipedFlag"));
		returnVal.setOverflowFlag(val.getBoolean("overflowFlag"));
		returnVal.setMaxAmount(val.getBigDecimal("maxAmount"));
		returnVal.setProcessAttempt(val.getLong("processAttempt"));
		returnVal.setBillingPostalCode(val.getString("billingPostalCode"));
		returnVal.setManualAuthCode(val.getString("manualAuthCode"));
		returnVal.setManualRefNum(val.getString("manualRefNum"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setNeedsNsfRetry(val.getBoolean("needsNsfRetry"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static OrderPaymentPreference map(HttpServletRequest request) throws Exception {

		OrderPaymentPreference returnVal = new OrderPaymentPreference();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderPaymentPreferenceId")) {
returnVal.setOrderPaymentPreferenceId(request.getParameter("orderPaymentPreferenceId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("productPricePurposeId"))  {
returnVal.setProductPricePurposeId(request.getParameter("productPricePurposeId"));
}
		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("paymentMethodId"))  {
returnVal.setPaymentMethodId(request.getParameter("paymentMethodId"));
}
		if(paramMap.containsKey("finAccountId"))  {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
}
		if(paramMap.containsKey("securityCode"))  {
returnVal.setSecurityCode(request.getParameter("securityCode"));
}
		if(paramMap.containsKey("track2"))  {
returnVal.setTrack2(request.getParameter("track2"));
}
		if(paramMap.containsKey("presentFlag"))  {
String buf = request.getParameter("presentFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPresentFlag(ibuf);
}
		if(paramMap.containsKey("swipedFlag"))  {
String buf = request.getParameter("swipedFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSwipedFlag(ibuf);
}
		if(paramMap.containsKey("overflowFlag"))  {
String buf = request.getParameter("overflowFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setOverflowFlag(ibuf);
}
		if(paramMap.containsKey("maxAmount"))  {
String buf = request.getParameter("maxAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxAmount(bd);
}
		if(paramMap.containsKey("processAttempt"))  {
String buf = request.getParameter("processAttempt");
Long ibuf = Long.parseLong(buf);
returnVal.setProcessAttempt(ibuf);
}
		if(paramMap.containsKey("billingPostalCode"))  {
returnVal.setBillingPostalCode(request.getParameter("billingPostalCode"));
}
		if(paramMap.containsKey("manualAuthCode"))  {
returnVal.setManualAuthCode(request.getParameter("manualAuthCode"));
}
		if(paramMap.containsKey("manualRefNum"))  {
returnVal.setManualRefNum(request.getParameter("manualRefNum"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("needsNsfRetry"))  {
String buf = request.getParameter("needsNsfRetry");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setNeedsNsfRetry(ibuf);
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
