package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.content.PaymentContent;

public class PaymentContentMapper  {


	public static Map<String, Object> map(PaymentContent paymentcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentcontent.getPaymentId() != null ){
			returnVal.put("paymentId",paymentcontent.getPaymentId());
}

		if(paymentcontent.getPaymentContentTypeId() != null ){
			returnVal.put("paymentContentTypeId",paymentcontent.getPaymentContentTypeId());
}

		if(paymentcontent.getContentId() != null ){
			returnVal.put("contentId",paymentcontent.getContentId());
}

		if(paymentcontent.getFromDate() != null ){
			returnVal.put("fromDate",paymentcontent.getFromDate());
}

		if(paymentcontent.getThruDate() != null ){
			returnVal.put("thruDate",paymentcontent.getThruDate());
}

		return returnVal;
}


	public static PaymentContent map(Map<String, Object> fields) {

		PaymentContent returnVal = new PaymentContent();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("paymentContentTypeId") != null) {
			returnVal.setPaymentContentTypeId((String) fields.get("paymentContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PaymentContent mapstrstr(Map<String, String> fields) throws Exception {

		PaymentContent returnVal = new PaymentContent();

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("paymentContentTypeId") != null) {
			returnVal.setPaymentContentTypeId((String) fields.get("paymentContentTypeId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static PaymentContent map(GenericValue val) {

PaymentContent returnVal = new PaymentContent();
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setPaymentContentTypeId(val.getString("paymentContentTypeId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PaymentContent map(HttpServletRequest request) throws Exception {

		PaymentContent returnVal = new PaymentContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentId")) {
returnVal.setPaymentId(request.getParameter("paymentId"));
}

		if(paramMap.containsKey("paymentContentTypeId"))  {
returnVal.setPaymentContentTypeId(request.getParameter("paymentContentTypeId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
