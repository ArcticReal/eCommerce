package com.skytala.eCommerce.domain.order.relations.orderContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.OrderContent;

public class OrderContentMapper  {


	public static Map<String, Object> map(OrderContent ordercontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordercontent.getOrderId() != null ){
			returnVal.put("orderId",ordercontent.getOrderId());
}

		if(ordercontent.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",ordercontent.getOrderItemSeqId());
}

		if(ordercontent.getContentId() != null ){
			returnVal.put("contentId",ordercontent.getContentId());
}

		if(ordercontent.getOrderContentTypeId() != null ){
			returnVal.put("orderContentTypeId",ordercontent.getOrderContentTypeId());
}

		if(ordercontent.getFromDate() != null ){
			returnVal.put("fromDate",ordercontent.getFromDate());
}

		if(ordercontent.getThruDate() != null ){
			returnVal.put("thruDate",ordercontent.getThruDate());
}

		return returnVal;
}


	public static OrderContent map(Map<String, Object> fields) {

		OrderContent returnVal = new OrderContent();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("orderContentTypeId") != null) {
			returnVal.setOrderContentTypeId((String) fields.get("orderContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static OrderContent mapstrstr(Map<String, String> fields) throws Exception {

		OrderContent returnVal = new OrderContent();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("orderContentTypeId") != null) {
			returnVal.setOrderContentTypeId((String) fields.get("orderContentTypeId"));
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
	public static OrderContent map(GenericValue val) {

OrderContent returnVal = new OrderContent();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setOrderContentTypeId(val.getString("orderContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static OrderContent map(HttpServletRequest request) throws Exception {

		OrderContent returnVal = new OrderContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("orderContentTypeId"))  {
returnVal.setOrderContentTypeId(request.getParameter("orderContentTypeId"));
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
