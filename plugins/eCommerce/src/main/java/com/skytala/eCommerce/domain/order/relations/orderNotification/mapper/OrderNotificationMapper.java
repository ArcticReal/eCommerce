package com.skytala.eCommerce.domain.order.relations.orderNotification.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderNotification.model.OrderNotification;

public class OrderNotificationMapper  {


	public static Map<String, Object> map(OrderNotification ordernotification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordernotification.getOrderNotificationId() != null ){
			returnVal.put("orderNotificationId",ordernotification.getOrderNotificationId());
}

		if(ordernotification.getOrderId() != null ){
			returnVal.put("orderId",ordernotification.getOrderId());
}

		if(ordernotification.getEmailType() != null ){
			returnVal.put("emailType",ordernotification.getEmailType());
}

		if(ordernotification.getComments() != null ){
			returnVal.put("comments",ordernotification.getComments());
}

		if(ordernotification.getNotificationDate() != null ){
			returnVal.put("notificationDate",ordernotification.getNotificationDate());
}

		return returnVal;
}


	public static OrderNotification map(Map<String, Object> fields) {

		OrderNotification returnVal = new OrderNotification();

		if(fields.get("orderNotificationId") != null) {
			returnVal.setOrderNotificationId((String) fields.get("orderNotificationId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("emailType") != null) {
			returnVal.setEmailType((String) fields.get("emailType"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("notificationDate") != null) {
			returnVal.setNotificationDate((Timestamp) fields.get("notificationDate"));
}


		return returnVal;
 } 
	public static OrderNotification mapstrstr(Map<String, String> fields) throws Exception {

		OrderNotification returnVal = new OrderNotification();

		if(fields.get("orderNotificationId") != null) {
			returnVal.setOrderNotificationId((String) fields.get("orderNotificationId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("emailType") != null) {
			returnVal.setEmailType((String) fields.get("emailType"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("notificationDate") != null) {
String buf = fields.get("notificationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setNotificationDate(ibuf);
}


		return returnVal;
 } 
	public static OrderNotification map(GenericValue val) {

OrderNotification returnVal = new OrderNotification();
		returnVal.setOrderNotificationId(val.getString("orderNotificationId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setEmailType(val.getString("emailType"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setNotificationDate(val.getTimestamp("notificationDate"));


return returnVal;

}

public static OrderNotification map(HttpServletRequest request) throws Exception {

		OrderNotification returnVal = new OrderNotification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderNotificationId")) {
returnVal.setOrderNotificationId(request.getParameter("orderNotificationId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("emailType"))  {
returnVal.setEmailType(request.getParameter("emailType"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("notificationDate"))  {
String buf = request.getParameter("notificationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setNotificationDate(ibuf);
}
return returnVal;

}
}
