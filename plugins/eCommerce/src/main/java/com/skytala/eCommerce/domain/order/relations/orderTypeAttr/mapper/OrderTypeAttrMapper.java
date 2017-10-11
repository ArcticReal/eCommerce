package com.skytala.eCommerce.domain.order.relations.orderTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.model.OrderTypeAttr;

public class OrderTypeAttrMapper  {


	public static Map<String, Object> map(OrderTypeAttr ordertypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordertypeattr.getOrderTypeId() != null ){
			returnVal.put("orderTypeId",ordertypeattr.getOrderTypeId());
}

		if(ordertypeattr.getAttrName() != null ){
			returnVal.put("attrName",ordertypeattr.getAttrName());
}

		if(ordertypeattr.getDescription() != null ){
			returnVal.put("description",ordertypeattr.getDescription());
}

		return returnVal;
}


	public static OrderTypeAttr map(Map<String, Object> fields) {

		OrderTypeAttr returnVal = new OrderTypeAttr();

		if(fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		OrderTypeAttr returnVal = new OrderTypeAttr();

		if(fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderTypeAttr map(GenericValue val) {

OrderTypeAttr returnVal = new OrderTypeAttr();
		returnVal.setOrderTypeId(val.getString("orderTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderTypeAttr map(HttpServletRequest request) throws Exception {

		OrderTypeAttr returnVal = new OrderTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderTypeId")) {
returnVal.setOrderTypeId(request.getParameter("orderTypeId"));
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
