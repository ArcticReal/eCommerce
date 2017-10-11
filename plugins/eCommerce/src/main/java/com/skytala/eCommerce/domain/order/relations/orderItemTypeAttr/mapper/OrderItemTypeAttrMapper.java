package com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.model.OrderItemTypeAttr;

public class OrderItemTypeAttrMapper  {


	public static Map<String, Object> map(OrderItemTypeAttr orderitemtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemtypeattr.getOrderItemTypeId() != null ){
			returnVal.put("orderItemTypeId",orderitemtypeattr.getOrderItemTypeId());
}

		if(orderitemtypeattr.getAttrName() != null ){
			returnVal.put("attrName",orderitemtypeattr.getAttrName());
}

		if(orderitemtypeattr.getDescription() != null ){
			returnVal.put("description",orderitemtypeattr.getDescription());
}

		return returnVal;
}


	public static OrderItemTypeAttr map(Map<String, Object> fields) {

		OrderItemTypeAttr returnVal = new OrderItemTypeAttr();

		if(fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderItemTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemTypeAttr returnVal = new OrderItemTypeAttr();

		if(fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderItemTypeAttr map(GenericValue val) {

OrderItemTypeAttr returnVal = new OrderItemTypeAttr();
		returnVal.setOrderItemTypeId(val.getString("orderItemTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderItemTypeAttr map(HttpServletRequest request) throws Exception {

		OrderItemTypeAttr returnVal = new OrderItemTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderItemTypeId")) {
returnVal.setOrderItemTypeId(request.getParameter("orderItemTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
