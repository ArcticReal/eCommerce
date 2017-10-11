package com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;

public class OrderBlacklistMapper  {


	public static Map<String, Object> map(OrderBlacklist orderblacklist) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderblacklist.getBlacklistString() != null ){
			returnVal.put("blacklistString",orderblacklist.getBlacklistString());
}

		if(orderblacklist.getOrderBlacklistTypeId() != null ){
			returnVal.put("orderBlacklistTypeId",orderblacklist.getOrderBlacklistTypeId());
}

		return returnVal;
}


	public static OrderBlacklist map(Map<String, Object> fields) {

		OrderBlacklist returnVal = new OrderBlacklist();

		if(fields.get("blacklistString") != null) {
			returnVal.setBlacklistString((String) fields.get("blacklistString"));
}

		if(fields.get("orderBlacklistTypeId") != null) {
			returnVal.setOrderBlacklistTypeId((String) fields.get("orderBlacklistTypeId"));
}


		return returnVal;
 } 
	public static OrderBlacklist mapstrstr(Map<String, String> fields) throws Exception {

		OrderBlacklist returnVal = new OrderBlacklist();

		if(fields.get("blacklistString") != null) {
			returnVal.setBlacklistString((String) fields.get("blacklistString"));
}

		if(fields.get("orderBlacklistTypeId") != null) {
			returnVal.setOrderBlacklistTypeId((String) fields.get("orderBlacklistTypeId"));
}


		return returnVal;
 } 
	public static OrderBlacklist map(GenericValue val) {

OrderBlacklist returnVal = new OrderBlacklist();
		returnVal.setBlacklistString(val.getString("blacklistString"));
		returnVal.setOrderBlacklistTypeId(val.getString("orderBlacklistTypeId"));


return returnVal;

}

public static OrderBlacklist map(HttpServletRequest request) throws Exception {

		OrderBlacklist returnVal = new OrderBlacklist();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("blacklistString")) {
returnVal.setBlacklistString(request.getParameter("blacklistString"));
}

		if(paramMap.containsKey("orderBlacklistTypeId"))  {
returnVal.setOrderBlacklistTypeId(request.getParameter("orderBlacklistTypeId"));
}
return returnVal;

}
}
