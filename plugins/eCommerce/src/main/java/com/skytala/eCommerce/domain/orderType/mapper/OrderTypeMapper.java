package com.skytala.eCommerce.domain.orderType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderType.model.OrderType;

public class OrderTypeMapper  {


	public static Map<String, Object> map(OrderType ordertype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordertype.getOrderTypeId() != null ){
			returnVal.put("orderTypeId",ordertype.getOrderTypeId());
}

		if(ordertype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",ordertype.getParentTypeId());
}

		if(ordertype.getHasTable() != null ){
			returnVal.put("hasTable",ordertype.getHasTable());
}

		if(ordertype.getDescription() != null ){
			returnVal.put("description",ordertype.getDescription());
}

		return returnVal;
}


	public static OrderType map(Map<String, Object> fields) {

		OrderType returnVal = new OrderType();

		if(fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
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
	public static OrderType mapstrstr(Map<String, String> fields) throws Exception {

		OrderType returnVal = new OrderType();

		if(fields.get("orderTypeId") != null) {
			returnVal.setOrderTypeId((String) fields.get("orderTypeId"));
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
	public static OrderType map(GenericValue val) {

OrderType returnVal = new OrderType();
		returnVal.setOrderTypeId(val.getString("orderTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderType map(HttpServletRequest request) throws Exception {

		OrderType returnVal = new OrderType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderTypeId")) {
returnVal.setOrderTypeId(request.getParameter("orderTypeId"));
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
