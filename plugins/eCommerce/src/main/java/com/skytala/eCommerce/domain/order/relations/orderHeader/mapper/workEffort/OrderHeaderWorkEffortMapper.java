package com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.workEffort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;

public class OrderHeaderWorkEffortMapper  {


	public static Map<String, Object> map(OrderHeaderWorkEffort orderheaderworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderheaderworkeffort.getOrderId() != null ){
			returnVal.put("orderId",orderheaderworkeffort.getOrderId());
}

		if(orderheaderworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",orderheaderworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static OrderHeaderWorkEffort map(Map<String, Object> fields) {

		OrderHeaderWorkEffort returnVal = new OrderHeaderWorkEffort();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static OrderHeaderWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		OrderHeaderWorkEffort returnVal = new OrderHeaderWorkEffort();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static OrderHeaderWorkEffort map(GenericValue val) {

OrderHeaderWorkEffort returnVal = new OrderHeaderWorkEffort();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static OrderHeaderWorkEffort map(HttpServletRequest request) throws Exception {

		OrderHeaderWorkEffort returnVal = new OrderHeaderWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
