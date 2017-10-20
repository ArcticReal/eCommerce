package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.attribute.OrderItemAttribute;

public class OrderItemAttributeMapper  {


	public static Map<String, Object> map(OrderItemAttribute orderitemattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemattribute.getOrderId() != null ){
			returnVal.put("orderId",orderitemattribute.getOrderId());
}

		if(orderitemattribute.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemattribute.getOrderItemSeqId());
}

		if(orderitemattribute.getAttrName() != null ){
			returnVal.put("attrName",orderitemattribute.getAttrName());
}

		if(orderitemattribute.getAttrValue() != null ){
			returnVal.put("attrValue",orderitemattribute.getAttrValue());
}

		if(orderitemattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",orderitemattribute.getAttrDescription());
}

		return returnVal;
}


	public static OrderItemAttribute map(Map<String, Object> fields) {

		OrderItemAttribute returnVal = new OrderItemAttribute();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static OrderItemAttribute mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemAttribute returnVal = new OrderItemAttribute();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static OrderItemAttribute map(GenericValue val) {

OrderItemAttribute returnVal = new OrderItemAttribute();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static OrderItemAttribute map(HttpServletRequest request) throws Exception {

		OrderItemAttribute returnVal = new OrderItemAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
