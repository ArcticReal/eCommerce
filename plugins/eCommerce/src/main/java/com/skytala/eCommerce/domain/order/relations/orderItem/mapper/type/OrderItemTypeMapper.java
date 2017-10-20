package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;

public class OrderItemTypeMapper  {


	public static Map<String, Object> map(OrderItemType orderitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemtype.getOrderItemTypeId() != null ){
			returnVal.put("orderItemTypeId",orderitemtype.getOrderItemTypeId());
}

		if(orderitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",orderitemtype.getParentTypeId());
}

		if(orderitemtype.getHasTable() != null ){
			returnVal.put("hasTable",orderitemtype.getHasTable());
}

		if(orderitemtype.getDescription() != null ){
			returnVal.put("description",orderitemtype.getDescription());
}

		return returnVal;
}


	public static OrderItemType map(Map<String, Object> fields) {

		OrderItemType returnVal = new OrderItemType();

		if(fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderItemType mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemType returnVal = new OrderItemType();

		if(fields.get("orderItemTypeId") != null) {
			returnVal.setOrderItemTypeId((String) fields.get("orderItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderItemType map(GenericValue val) {

OrderItemType returnVal = new OrderItemType();
		returnVal.setOrderItemTypeId(val.getString("orderItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderItemType map(HttpServletRequest request) throws Exception {

		OrderItemType returnVal = new OrderItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderItemTypeId")) {
returnVal.setOrderItemTypeId(request.getParameter("orderItemTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
