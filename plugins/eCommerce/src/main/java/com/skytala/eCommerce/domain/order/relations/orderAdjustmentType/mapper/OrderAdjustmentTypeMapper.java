package com.skytala.eCommerce.domain.order.relations.orderAdjustmentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentType.model.OrderAdjustmentType;

public class OrderAdjustmentTypeMapper  {


	public static Map<String, Object> map(OrderAdjustmentType orderadjustmenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderadjustmenttype.getOrderAdjustmentTypeId() != null ){
			returnVal.put("orderAdjustmentTypeId",orderadjustmenttype.getOrderAdjustmentTypeId());
}

		if(orderadjustmenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",orderadjustmenttype.getParentTypeId());
}

		if(orderadjustmenttype.getHasTable() != null ){
			returnVal.put("hasTable",orderadjustmenttype.getHasTable());
}

		if(orderadjustmenttype.getDescription() != null ){
			returnVal.put("description",orderadjustmenttype.getDescription());
}

		return returnVal;
}


	public static OrderAdjustmentType map(Map<String, Object> fields) {

		OrderAdjustmentType returnVal = new OrderAdjustmentType();

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
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
	public static OrderAdjustmentType mapstrstr(Map<String, String> fields) throws Exception {

		OrderAdjustmentType returnVal = new OrderAdjustmentType();

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
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
	public static OrderAdjustmentType map(GenericValue val) {

OrderAdjustmentType returnVal = new OrderAdjustmentType();
		returnVal.setOrderAdjustmentTypeId(val.getString("orderAdjustmentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderAdjustmentType map(HttpServletRequest request) throws Exception {

		OrderAdjustmentType returnVal = new OrderAdjustmentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderAdjustmentTypeId")) {
returnVal.setOrderAdjustmentTypeId(request.getParameter("orderAdjustmentTypeId"));
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
