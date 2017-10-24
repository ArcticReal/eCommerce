package com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;

public class OrderAdjustmentAttributeMapper  {


	public static Map<String, Object> map(OrderAdjustmentAttribute orderadjustmentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderadjustmentattribute.getOrderAdjustmentId() != null ){
			returnVal.put("orderAdjustmentId",orderadjustmentattribute.getOrderAdjustmentId());
}

		if(orderadjustmentattribute.getAttrName() != null ){
			returnVal.put("attrName",orderadjustmentattribute.getAttrName());
}

		if(orderadjustmentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",orderadjustmentattribute.getAttrValue());
}

		if(orderadjustmentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",orderadjustmentattribute.getAttrDescription());
}

		return returnVal;
}


	public static OrderAdjustmentAttribute map(Map<String, Object> fields) {

		OrderAdjustmentAttribute returnVal = new OrderAdjustmentAttribute();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static OrderAdjustmentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		OrderAdjustmentAttribute returnVal = new OrderAdjustmentAttribute();

		if(fields.get("orderAdjustmentId") != null) {
			returnVal.setOrderAdjustmentId((String) fields.get("orderAdjustmentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static OrderAdjustmentAttribute map(GenericValue val) {

OrderAdjustmentAttribute returnVal = new OrderAdjustmentAttribute();
		returnVal.setOrderAdjustmentId(val.getString("orderAdjustmentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static OrderAdjustmentAttribute map(HttpServletRequest request) throws Exception {

		OrderAdjustmentAttribute returnVal = new OrderAdjustmentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderAdjustmentId")) {
returnVal.setOrderAdjustmentId(request.getParameter("orderAdjustmentId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
returnVal.setAttrValue(request.getParameter("attrValue"));
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
