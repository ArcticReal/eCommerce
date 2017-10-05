package com.skytala.eCommerce.domain.orderItemChange.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderItemChange.model.OrderItemChange;

public class OrderItemChangeMapper  {


	public static Map<String, Object> map(OrderItemChange orderitemchange) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemchange.getOrderItemChangeId() != null ){
			returnVal.put("orderItemChangeId",orderitemchange.getOrderItemChangeId());
}

		if(orderitemchange.getOrderId() != null ){
			returnVal.put("orderId",orderitemchange.getOrderId());
}

		if(orderitemchange.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemchange.getOrderItemSeqId());
}

		if(orderitemchange.getChangeTypeEnumId() != null ){
			returnVal.put("changeTypeEnumId",orderitemchange.getChangeTypeEnumId());
}

		if(orderitemchange.getChangeDatetime() != null ){
			returnVal.put("changeDatetime",orderitemchange.getChangeDatetime());
}

		if(orderitemchange.getChangeUserLogin() != null ){
			returnVal.put("changeUserLogin",orderitemchange.getChangeUserLogin());
}

		if(orderitemchange.getQuantity() != null ){
			returnVal.put("quantity",orderitemchange.getQuantity());
}

		if(orderitemchange.getCancelQuantity() != null ){
			returnVal.put("cancelQuantity",orderitemchange.getCancelQuantity());
}

		if(orderitemchange.getUnitPrice() != null ){
			returnVal.put("unitPrice",orderitemchange.getUnitPrice());
}

		if(orderitemchange.getItemDescription() != null ){
			returnVal.put("itemDescription",orderitemchange.getItemDescription());
}

		if(orderitemchange.getReasonEnumId() != null ){
			returnVal.put("reasonEnumId",orderitemchange.getReasonEnumId());
}

		if(orderitemchange.getChangeComments() != null ){
			returnVal.put("changeComments",orderitemchange.getChangeComments());
}

		return returnVal;
}


	public static OrderItemChange map(Map<String, Object> fields) {

		OrderItemChange returnVal = new OrderItemChange();

		if(fields.get("orderItemChangeId") != null) {
			returnVal.setOrderItemChangeId((String) fields.get("orderItemChangeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("changeTypeEnumId") != null) {
			returnVal.setChangeTypeEnumId((String) fields.get("changeTypeEnumId"));
}

		if(fields.get("changeDatetime") != null) {
			returnVal.setChangeDatetime((Timestamp) fields.get("changeDatetime"));
}

		if(fields.get("changeUserLogin") != null) {
			returnVal.setChangeUserLogin((String) fields.get("changeUserLogin"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("cancelQuantity") != null) {
			returnVal.setCancelQuantity((BigDecimal) fields.get("cancelQuantity"));
}

		if(fields.get("unitPrice") != null) {
			returnVal.setUnitPrice((BigDecimal) fields.get("unitPrice"));
}

		if(fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("changeComments") != null) {
			returnVal.setChangeComments((String) fields.get("changeComments"));
}


		return returnVal;
 } 
	public static OrderItemChange mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemChange returnVal = new OrderItemChange();

		if(fields.get("orderItemChangeId") != null) {
			returnVal.setOrderItemChangeId((String) fields.get("orderItemChangeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("changeTypeEnumId") != null) {
			returnVal.setChangeTypeEnumId((String) fields.get("changeTypeEnumId"));
}

		if(fields.get("changeDatetime") != null) {
String buf = fields.get("changeDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setChangeDatetime(ibuf);
}

		if(fields.get("changeUserLogin") != null) {
			returnVal.setChangeUserLogin((String) fields.get("changeUserLogin"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("cancelQuantity") != null) {
String buf;
buf = fields.get("cancelQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
}

		if(fields.get("unitPrice") != null) {
String buf;
buf = fields.get("unitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
}

		if(fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("changeComments") != null) {
			returnVal.setChangeComments((String) fields.get("changeComments"));
}


		return returnVal;
 } 
	public static OrderItemChange map(GenericValue val) {

OrderItemChange returnVal = new OrderItemChange();
		returnVal.setOrderItemChangeId(val.getString("orderItemChangeId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setChangeTypeEnumId(val.getString("changeTypeEnumId"));
		returnVal.setChangeDatetime(val.getTimestamp("changeDatetime"));
		returnVal.setChangeUserLogin(val.getString("changeUserLogin"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setCancelQuantity(val.getBigDecimal("cancelQuantity"));
		returnVal.setUnitPrice(val.getBigDecimal("unitPrice"));
		returnVal.setItemDescription(val.getString("itemDescription"));
		returnVal.setReasonEnumId(val.getString("reasonEnumId"));
		returnVal.setChangeComments(val.getString("changeComments"));


return returnVal;

}

public static OrderItemChange map(HttpServletRequest request) throws Exception {

		OrderItemChange returnVal = new OrderItemChange();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderItemChangeId")) {
returnVal.setOrderItemChangeId(request.getParameter("orderItemChangeId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("changeTypeEnumId"))  {
returnVal.setChangeTypeEnumId(request.getParameter("changeTypeEnumId"));
}
		if(paramMap.containsKey("changeDatetime"))  {
String buf = request.getParameter("changeDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setChangeDatetime(ibuf);
}
		if(paramMap.containsKey("changeUserLogin"))  {
returnVal.setChangeUserLogin(request.getParameter("changeUserLogin"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("cancelQuantity"))  {
String buf = request.getParameter("cancelQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
}
		if(paramMap.containsKey("unitPrice"))  {
String buf = request.getParameter("unitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
}
		if(paramMap.containsKey("itemDescription"))  {
returnVal.setItemDescription(request.getParameter("itemDescription"));
}
		if(paramMap.containsKey("reasonEnumId"))  {
returnVal.setReasonEnumId(request.getParameter("reasonEnumId"));
}
		if(paramMap.containsKey("changeComments"))  {
returnVal.setChangeComments(request.getParameter("changeComments"));
}
return returnVal;

}
}
