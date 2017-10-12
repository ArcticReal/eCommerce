package com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.giftCardFulfillment.model.GiftCardFulfillment;

public class GiftCardFulfillmentMapper  {


	public static Map<String, Object> map(GiftCardFulfillment giftcardfulfillment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(giftcardfulfillment.getFulfillmentId() != null ){
			returnVal.put("fulfillmentId",giftcardfulfillment.getFulfillmentId());
}

		if(giftcardfulfillment.getTypeEnumId() != null ){
			returnVal.put("typeEnumId",giftcardfulfillment.getTypeEnumId());
}

		if(giftcardfulfillment.getMerchantId() != null ){
			returnVal.put("merchantId",giftcardfulfillment.getMerchantId());
}

		if(giftcardfulfillment.getPartyId() != null ){
			returnVal.put("partyId",giftcardfulfillment.getPartyId());
}

		if(giftcardfulfillment.getOrderId() != null ){
			returnVal.put("orderId",giftcardfulfillment.getOrderId());
}

		if(giftcardfulfillment.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",giftcardfulfillment.getOrderItemSeqId());
}

		if(giftcardfulfillment.getSurveyResponseId() != null ){
			returnVal.put("surveyResponseId",giftcardfulfillment.getSurveyResponseId());
}

		if(giftcardfulfillment.getCardNumber() != null ){
			returnVal.put("cardNumber",giftcardfulfillment.getCardNumber());
}

		if(giftcardfulfillment.getPinNumber() != null ){
			returnVal.put("pinNumber",giftcardfulfillment.getPinNumber());
}

		if(giftcardfulfillment.getAmount() != null ){
			returnVal.put("amount",giftcardfulfillment.getAmount());
}

		if(giftcardfulfillment.getResponseCode() != null ){
			returnVal.put("responseCode",giftcardfulfillment.getResponseCode());
}

		if(giftcardfulfillment.getReferenceNum() != null ){
			returnVal.put("referenceNum",giftcardfulfillment.getReferenceNum());
}

		if(giftcardfulfillment.getAuthCode() != null ){
			returnVal.put("authCode",giftcardfulfillment.getAuthCode());
}

		if(giftcardfulfillment.getFulfillmentDate() != null ){
			returnVal.put("fulfillmentDate",giftcardfulfillment.getFulfillmentDate());
}

		return returnVal;
}


	public static GiftCardFulfillment map(Map<String, Object> fields) {

		GiftCardFulfillment returnVal = new GiftCardFulfillment();

		if(fields.get("fulfillmentId") != null) {
			returnVal.setFulfillmentId((String) fields.get("fulfillmentId"));
}

		if(fields.get("typeEnumId") != null) {
			returnVal.setTypeEnumId((String) fields.get("typeEnumId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("pinNumber") != null) {
			returnVal.setPinNumber((String) fields.get("pinNumber"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("responseCode") != null) {
			returnVal.setResponseCode((String) fields.get("responseCode"));
}

		if(fields.get("referenceNum") != null) {
			returnVal.setReferenceNum((String) fields.get("referenceNum"));
}

		if(fields.get("authCode") != null) {
			returnVal.setAuthCode((String) fields.get("authCode"));
}

		if(fields.get("fulfillmentDate") != null) {
			returnVal.setFulfillmentDate((Timestamp) fields.get("fulfillmentDate"));
}


		return returnVal;
 } 
	public static GiftCardFulfillment mapstrstr(Map<String, String> fields) throws Exception {

		GiftCardFulfillment returnVal = new GiftCardFulfillment();

		if(fields.get("fulfillmentId") != null) {
			returnVal.setFulfillmentId((String) fields.get("fulfillmentId"));
}

		if(fields.get("typeEnumId") != null) {
			returnVal.setTypeEnumId((String) fields.get("typeEnumId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}

		if(fields.get("cardNumber") != null) {
			returnVal.setCardNumber((String) fields.get("cardNumber"));
}

		if(fields.get("pinNumber") != null) {
			returnVal.setPinNumber((String) fields.get("pinNumber"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("responseCode") != null) {
			returnVal.setResponseCode((String) fields.get("responseCode"));
}

		if(fields.get("referenceNum") != null) {
			returnVal.setReferenceNum((String) fields.get("referenceNum"));
}

		if(fields.get("authCode") != null) {
			returnVal.setAuthCode((String) fields.get("authCode"));
}

		if(fields.get("fulfillmentDate") != null) {
String buf = fields.get("fulfillmentDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFulfillmentDate(ibuf);
}


		return returnVal;
 } 
	public static GiftCardFulfillment map(GenericValue val) {

GiftCardFulfillment returnVal = new GiftCardFulfillment();
		returnVal.setFulfillmentId(val.getString("fulfillmentId"));
		returnVal.setTypeEnumId(val.getString("typeEnumId"));
		returnVal.setMerchantId(val.getString("merchantId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setSurveyResponseId(val.getString("surveyResponseId"));
		returnVal.setCardNumber(val.getString("cardNumber"));
		returnVal.setPinNumber(val.getString("pinNumber"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setResponseCode(val.getString("responseCode"));
		returnVal.setReferenceNum(val.getString("referenceNum"));
		returnVal.setAuthCode(val.getString("authCode"));
		returnVal.setFulfillmentDate(val.getTimestamp("fulfillmentDate"));


return returnVal;

}

public static GiftCardFulfillment map(HttpServletRequest request) throws Exception {

		GiftCardFulfillment returnVal = new GiftCardFulfillment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fulfillmentId")) {
returnVal.setFulfillmentId(request.getParameter("fulfillmentId"));
}

		if(paramMap.containsKey("typeEnumId"))  {
returnVal.setTypeEnumId(request.getParameter("typeEnumId"));
}
		if(paramMap.containsKey("merchantId"))  {
returnVal.setMerchantId(request.getParameter("merchantId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("surveyResponseId"))  {
returnVal.setSurveyResponseId(request.getParameter("surveyResponseId"));
}
		if(paramMap.containsKey("cardNumber"))  {
returnVal.setCardNumber(request.getParameter("cardNumber"));
}
		if(paramMap.containsKey("pinNumber"))  {
returnVal.setPinNumber(request.getParameter("pinNumber"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("responseCode"))  {
returnVal.setResponseCode(request.getParameter("responseCode"));
}
		if(paramMap.containsKey("referenceNum"))  {
returnVal.setReferenceNum(request.getParameter("referenceNum"));
}
		if(paramMap.containsKey("authCode"))  {
returnVal.setAuthCode(request.getParameter("authCode"));
}
		if(paramMap.containsKey("fulfillmentDate"))  {
String buf = request.getParameter("fulfillmentDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFulfillmentDate(ibuf);
}
return returnVal;

}
}
