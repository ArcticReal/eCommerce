package com.skytala.eCommerce.domain.orderStatus.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderStatus.model.OrderStatus;

public class OrderStatusMapper  {


	public static Map<String, Object> map(OrderStatus orderstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderstatus.getOrderStatusId() != null ){
			returnVal.put("orderStatusId",orderstatus.getOrderStatusId());
}

		if(orderstatus.getStatusId() != null ){
			returnVal.put("statusId",orderstatus.getStatusId());
}

		if(orderstatus.getOrderId() != null ){
			returnVal.put("orderId",orderstatus.getOrderId());
}

		if(orderstatus.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderstatus.getOrderItemSeqId());
}

		if(orderstatus.getOrderPaymentPreferenceId() != null ){
			returnVal.put("orderPaymentPreferenceId",orderstatus.getOrderPaymentPreferenceId());
}

		if(orderstatus.getStatusDatetime() != null ){
			returnVal.put("statusDatetime",orderstatus.getStatusDatetime());
}

		if(orderstatus.getStatusUserLogin() != null ){
			returnVal.put("statusUserLogin",orderstatus.getStatusUserLogin());
}

		if(orderstatus.getChangeReason() != null ){
			returnVal.put("changeReason",orderstatus.getChangeReason());
}

		return returnVal;
}


	public static OrderStatus map(Map<String, Object> fields) {

		OrderStatus returnVal = new OrderStatus();

		if(fields.get("orderStatusId") != null) {
			returnVal.setOrderStatusId((String) fields.get("orderStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("statusDatetime") != null) {
			returnVal.setStatusDatetime((Timestamp) fields.get("statusDatetime"));
}

		if(fields.get("statusUserLogin") != null) {
			returnVal.setStatusUserLogin((String) fields.get("statusUserLogin"));
}

		if(fields.get("changeReason") != null) {
			returnVal.setChangeReason((String) fields.get("changeReason"));
}


		return returnVal;
 } 
	public static OrderStatus mapstrstr(Map<String, String> fields) throws Exception {

		OrderStatus returnVal = new OrderStatus();

		if(fields.get("orderStatusId") != null) {
			returnVal.setOrderStatusId((String) fields.get("orderStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("orderPaymentPreferenceId") != null) {
			returnVal.setOrderPaymentPreferenceId((String) fields.get("orderPaymentPreferenceId"));
}

		if(fields.get("statusDatetime") != null) {
String buf = fields.get("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDatetime(ibuf);
}

		if(fields.get("statusUserLogin") != null) {
			returnVal.setStatusUserLogin((String) fields.get("statusUserLogin"));
}

		if(fields.get("changeReason") != null) {
			returnVal.setChangeReason((String) fields.get("changeReason"));
}


		return returnVal;
 } 
	public static OrderStatus map(GenericValue val) {

OrderStatus returnVal = new OrderStatus();
		returnVal.setOrderStatusId(val.getString("orderStatusId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setOrderPaymentPreferenceId(val.getString("orderPaymentPreferenceId"));
		returnVal.setStatusDatetime(val.getTimestamp("statusDatetime"));
		returnVal.setStatusUserLogin(val.getString("statusUserLogin"));
		returnVal.setChangeReason(val.getString("changeReason"));


return returnVal;

}

public static OrderStatus map(HttpServletRequest request) throws Exception {

		OrderStatus returnVal = new OrderStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderStatusId")) {
returnVal.setOrderStatusId(request.getParameter("orderStatusId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("orderPaymentPreferenceId"))  {
returnVal.setOrderPaymentPreferenceId(request.getParameter("orderPaymentPreferenceId"));
}
		if(paramMap.containsKey("statusDatetime"))  {
String buf = request.getParameter("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDatetime(ibuf);
}
		if(paramMap.containsKey("statusUserLogin"))  {
returnVal.setStatusUserLogin(request.getParameter("statusUserLogin"));
}
		if(paramMap.containsKey("changeReason"))  {
returnVal.setChangeReason(request.getParameter("changeReason"));
}
return returnVal;

}
}
