package com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.typeAttr.OrderAdjustmentTypeAttr;

public class OrderAdjustmentTypeAttrMapper  {


	public static Map<String, Object> map(OrderAdjustmentTypeAttr orderadjustmenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderadjustmenttypeattr.getOrderAdjustmentTypeId() != null ){
			returnVal.put("orderAdjustmentTypeId",orderadjustmenttypeattr.getOrderAdjustmentTypeId());
}

		if(orderadjustmenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",orderadjustmenttypeattr.getAttrName());
}

		if(orderadjustmenttypeattr.getDescription() != null ){
			returnVal.put("description",orderadjustmenttypeattr.getDescription());
}

		return returnVal;
}


	public static OrderAdjustmentTypeAttr map(Map<String, Object> fields) {

		OrderAdjustmentTypeAttr returnVal = new OrderAdjustmentTypeAttr();

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderAdjustmentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		OrderAdjustmentTypeAttr returnVal = new OrderAdjustmentTypeAttr();

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderAdjustmentTypeAttr map(GenericValue val) {

OrderAdjustmentTypeAttr returnVal = new OrderAdjustmentTypeAttr();
		returnVal.setOrderAdjustmentTypeId(val.getString("orderAdjustmentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderAdjustmentTypeAttr map(HttpServletRequest request) throws Exception {

		OrderAdjustmentTypeAttr returnVal = new OrderAdjustmentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderAdjustmentTypeId")) {
returnVal.setOrderAdjustmentTypeId(request.getParameter("orderAdjustmentTypeId"));
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
