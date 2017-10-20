package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupMember;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;

public class PaymentGroupMemberMapper  {


	public static Map<String, Object> map(PaymentGroupMember paymentgroupmember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgroupmember.getPaymentGroupId() != null ){
			returnVal.put("paymentGroupId",paymentgroupmember.getPaymentGroupId());
}

		if(paymentgroupmember.getPaymentId() != null ){
			returnVal.put("paymentId",paymentgroupmember.getPaymentId());
}

		if(paymentgroupmember.getFromDate() != null ){
			returnVal.put("fromDate",paymentgroupmember.getFromDate());
}

		if(paymentgroupmember.getThruDate() != null ){
			returnVal.put("thruDate",paymentgroupmember.getThruDate());
}

		if(paymentgroupmember.getSequenceNum() != null ){
			returnVal.put("sequenceNum",paymentgroupmember.getSequenceNum());
}

		return returnVal;
}


	public static PaymentGroupMember map(Map<String, Object> fields) {

		PaymentGroupMember returnVal = new PaymentGroupMember();

		if(fields.get("paymentGroupId") != null) {
			returnVal.setPaymentGroupId((String) fields.get("paymentGroupId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static PaymentGroupMember mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGroupMember returnVal = new PaymentGroupMember();

		if(fields.get("paymentGroupId") != null) {
			returnVal.setPaymentGroupId((String) fields.get("paymentGroupId"));
}

		if(fields.get("paymentId") != null) {
			returnVal.setPaymentId((String) fields.get("paymentId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static PaymentGroupMember map(GenericValue val) {

PaymentGroupMember returnVal = new PaymentGroupMember();
		returnVal.setPaymentGroupId(val.getString("paymentGroupId"));
		returnVal.setPaymentId(val.getString("paymentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static PaymentGroupMember map(HttpServletRequest request) throws Exception {

		PaymentGroupMember returnVal = new PaymentGroupMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGroupId")) {
returnVal.setPaymentGroupId(request.getParameter("paymentGroupId"));
}

		if(paramMap.containsKey("paymentId"))  {
returnVal.setPaymentId(request.getParameter("paymentId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
