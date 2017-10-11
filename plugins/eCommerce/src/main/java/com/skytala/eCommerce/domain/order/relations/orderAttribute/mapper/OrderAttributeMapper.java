package com.skytala.eCommerce.domain.order.relations.orderAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;

public class OrderAttributeMapper  {


	public static Map<String, Object> map(OrderAttribute orderattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderattribute.getOrderId() != null ){
			returnVal.put("orderId",orderattribute.getOrderId());
}

		if(orderattribute.getAttrName() != null ){
			returnVal.put("attrName",orderattribute.getAttrName());
}

		if(orderattribute.getAttrValue() != null ){
			returnVal.put("attrValue",orderattribute.getAttrValue());
}

		if(orderattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",orderattribute.getAttrDescription());
}

		return returnVal;
}


	public static OrderAttribute map(Map<String, Object> fields) {

		OrderAttribute returnVal = new OrderAttribute();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
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
	public static OrderAttribute mapstrstr(Map<String, String> fields) throws Exception {

		OrderAttribute returnVal = new OrderAttribute();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
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
	public static OrderAttribute map(GenericValue val) {

OrderAttribute returnVal = new OrderAttribute();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static OrderAttribute map(HttpServletRequest request) throws Exception {

		OrderAttribute returnVal = new OrderAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
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
