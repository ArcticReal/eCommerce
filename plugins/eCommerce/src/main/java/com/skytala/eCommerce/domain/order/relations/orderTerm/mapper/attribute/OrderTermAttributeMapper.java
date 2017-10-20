package com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;

public class OrderTermAttributeMapper  {


	public static Map<String, Object> map(OrderTermAttribute ordertermattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordertermattribute.getTermTypeId() != null ){
			returnVal.put("termTypeId",ordertermattribute.getTermTypeId());
}

		if(ordertermattribute.getOrderId() != null ){
			returnVal.put("orderId",ordertermattribute.getOrderId());
}

		if(ordertermattribute.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",ordertermattribute.getOrderItemSeqId());
}

		if(ordertermattribute.getAttrName() != null ){
			returnVal.put("attrName",ordertermattribute.getAttrName());
}

		if(ordertermattribute.getAttrValue() != null ){
			returnVal.put("attrValue",ordertermattribute.getAttrValue());
}

		if(ordertermattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",ordertermattribute.getAttrDescription());
}

		return returnVal;
}


	public static OrderTermAttribute map(Map<String, Object> fields) {

		OrderTermAttribute returnVal = new OrderTermAttribute();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

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
	public static OrderTermAttribute mapstrstr(Map<String, String> fields) throws Exception {

		OrderTermAttribute returnVal = new OrderTermAttribute();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

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
	public static OrderTermAttribute map(GenericValue val) {

OrderTermAttribute returnVal = new OrderTermAttribute();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static OrderTermAttribute map(HttpServletRequest request) throws Exception {

		OrderTermAttribute returnVal = new OrderTermAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("termTypeId")) {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}

		if(paramMap.containsKey("orderId"))  {
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
