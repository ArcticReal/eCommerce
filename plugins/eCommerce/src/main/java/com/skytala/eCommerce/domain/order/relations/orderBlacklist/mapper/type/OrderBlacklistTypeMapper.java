package com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;

public class OrderBlacklistTypeMapper  {


	public static Map<String, Object> map(OrderBlacklistType orderblacklisttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderblacklisttype.getOrderBlacklistTypeId() != null ){
			returnVal.put("orderBlacklistTypeId",orderblacklisttype.getOrderBlacklistTypeId());
}

		if(orderblacklisttype.getDescription() != null ){
			returnVal.put("description",orderblacklisttype.getDescription());
}

		return returnVal;
}


	public static OrderBlacklistType map(Map<String, Object> fields) {

		OrderBlacklistType returnVal = new OrderBlacklistType();

		if(fields.get("orderBlacklistTypeId") != null) {
			returnVal.setOrderBlacklistTypeId((String) fields.get("orderBlacklistTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderBlacklistType mapstrstr(Map<String, String> fields) throws Exception {

		OrderBlacklistType returnVal = new OrderBlacklistType();

		if(fields.get("orderBlacklistTypeId") != null) {
			returnVal.setOrderBlacklistTypeId((String) fields.get("orderBlacklistTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderBlacklistType map(GenericValue val) {

OrderBlacklistType returnVal = new OrderBlacklistType();
		returnVal.setOrderBlacklistTypeId(val.getString("orderBlacklistTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderBlacklistType map(HttpServletRequest request) throws Exception {

		OrderBlacklistType returnVal = new OrderBlacklistType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderBlacklistTypeId")) {
returnVal.setOrderBlacklistTypeId(request.getParameter("orderBlacklistTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
