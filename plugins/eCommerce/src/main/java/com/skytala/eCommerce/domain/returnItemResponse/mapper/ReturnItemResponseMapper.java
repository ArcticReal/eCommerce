package com.skytala.eCommerce.domain.returnItemResponse.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;

public class ReturnItemResponseMapper  {


	public static Map<String, Object> map(ReturnItemResponse returnitemresponse) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitemresponse.getReturnItemResponseId() != null ){
			returnVal.put("returnItemResponseId",returnitemresponse.getReturnItemResponseId());
}

		if(returnitemresponse.getOrderPaymentPreferenceId() != null ){
			returnVal.put("orderPaymentPreferenceId",returnitemresponse.getOrderPaymentPreferenceId());
}

		if(returnitemresponse.getReplacementOrderId() != null ){
			returnVal.put("replacementOrderId",returnitemresponse.getReplacementOrderId());
}

		if(returnitemresponse.getPaymentId() != null ){
			returnVal.put("paymentId",returnitemresponse.getPaymentId());
}

		if(returnitemresponse.getBillingAccountId() != null ){
			returnVal.put("billingAccountId",returnitemresponse.getBillingAccountId());
}

		if(returnitemresponse.getFinAccountTransId() != null ){
			returnVal.put("finAccountTransId",returnitemresponse.getFinAccountTransId());
}

		if(returnitemresponse.getResponseAmount() != null ){
			returnVal.put("responseAmount",returnitemresponse.getResponseAmount());
}

		if(returnitemresponse.getResponseDate() != null ){
			returnVal.put("responseDate",returnitemresponse.getResponseDate());
}

		return returnVal;
}


	public static ReturnItemResponse map(Map<String, Object> fields) {

		ReturnItemResponse returnVal = new ReturnItemResponse();

		if(fields.get("returnItemResponseId") != null) {
			returnVal.setReturnItemResponseId((String) fields.get("returnItemResponseId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("replacementOrderId") != null) {
			returnVal.setReplacementOrderId((String) fields.get("replacementOrderId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("responseAmount") != null) {
			returnVal.setResponseAmount((BigDecimal) fields.get("responseAmount"));
}

		if(fields.get("responseDate") != null) {
			returnVal.setResponseDate((Timestamp) fields.get("responseDate"));
}


		return returnVal;
 } 
	public static ReturnItemResponse mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItemResponse returnVal = new ReturnItemResponse();

		if(fields.get("returnItemResponseId") != null) {
			returnVal.setReturnItemResponseId((String) fields.get("returnItemResponseId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("replacementOrderId") != null) {
			returnVal.setReplacementOrderId((String) fields.get("replacementOrderId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("billingAccountId") != null) {
			returnVal.setBillingAccountId((String) fields.get("billingAccountId"));
}

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
}

		if(fields.get("responseAmount") != null) {
String buf;
buf = fields.get("responseAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setResponseAmount(bd);
}

		if(fields.get("responseDate") != null) {
String buf = fields.get("responseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setResponseDate(ibuf);
}


		return returnVal;
 } 
	public static ReturnItemResponse map(GenericValue val) {

ReturnItemResponse returnVal = new ReturnItemResponse();
		returnVal.setReturnItemResponseId(val.getString("returnItemResponseId"));
		returnVal.setOrderPaymentPreferenceId(val.getString("orderPaymentPreferenceId"));
		returnVal.setReplacementOrderId(val.getString("replacementOrderId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setBillingAccountId(val.getString("billingAccountId"));
		returnVal.setFinAccountTransId(val.getString("finAccountTransId"));
		returnVal.setResponseAmount(val.getBigDecimal("responseAmount"));
		returnVal.setResponseDate(val.getTimestamp("responseDate"));


return returnVal;

}

public static ReturnItemResponse map(HttpServletRequest request) throws Exception {

		ReturnItemResponse returnVal = new ReturnItemResponse();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnItemResponseId")) {
returnVal.setReturnItemResponseId(request.getParameter("returnItemResponseId"));
}

		if(paramMap.containsKey("orderPaymentPreferenceId"))  {
returnVal.setOrderPaymentPreferenceId(request.getParameter("orderPaymentPreferenceId"));
}
		if(paramMap.containsKey("replacementOrderId"))  {
returnVal.setReplacementOrderId(request.getParameter("replacementOrderId"));
}
		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
}
		if(paramMap.containsKey("billingAccountId"))  {
returnVal.setBillingAccountId(request.getParameter("billingAccountId"));
}
		if(paramMap.containsKey("finAccountTransId"))  {
returnVal.setFinAccountTransId(request.getParameter("finAccountTransId"));
}
		if(paramMap.containsKey("responseAmount"))  {
String buf = request.getParameter("responseAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setResponseAmount(bd);
}
		if(paramMap.containsKey("responseDate"))  {
String buf = request.getParameter("responseDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setResponseDate(ibuf);
}
return returnVal;

}
}
