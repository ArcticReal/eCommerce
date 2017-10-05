package com.skytala.eCommerce.domain.orderItemAssocType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderItemAssocType.model.OrderItemAssocType;

public class OrderItemAssocTypeMapper  {


	public static Map<String, Object> map(OrderItemAssocType orderitemassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemassoctype.getOrderItemAssocTypeId() != null ){
			returnVal.put("orderItemAssocTypeId",orderitemassoctype.getOrderItemAssocTypeId());
}

		if(orderitemassoctype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",orderitemassoctype.getParentTypeId());
}

		if(orderitemassoctype.getHasTable() != null ){
			returnVal.put("hasTable",orderitemassoctype.getHasTable());
}

		if(orderitemassoctype.getDescription() != null ){
			returnVal.put("description",orderitemassoctype.getDescription());
}

		return returnVal;
}


	public static OrderItemAssocType map(Map<String, Object> fields) {

		OrderItemAssocType returnVal = new OrderItemAssocType();

		if(fields.get("orderItemAssocTypeId") != null) {
			returnVal.setOrderItemAssocTypeId((String) fields.get("orderItemAssocTypeId"));
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
	public static OrderItemAssocType mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemAssocType returnVal = new OrderItemAssocType();

		if(fields.get("orderItemAssocTypeId") != null) {
			returnVal.setOrderItemAssocTypeId((String) fields.get("orderItemAssocTypeId"));
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
	public static OrderItemAssocType map(GenericValue val) {

OrderItemAssocType returnVal = new OrderItemAssocType();
		returnVal.setOrderItemAssocTypeId(val.getString("orderItemAssocTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderItemAssocType map(HttpServletRequest request) throws Exception {

		OrderItemAssocType returnVal = new OrderItemAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderItemAssocTypeId")) {
returnVal.setOrderItemAssocTypeId(request.getParameter("orderItemAssocTypeId"));
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
